package com.snapdeal.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.entity.Cancellation;
import com.snapdeal.util.DateConvertor;

@Transactional
@Named("cancellationDao")
public class CancellationDaoImpl implements CancellationDao {

	@Inject
	@Named("entityDao")
	EntityDao entityDao;

	@Override
	@SuppressWarnings("unchecked")
	public List<Cancellation> getAllData(List<String> shipperList, String date) {
		String[] daterange = date.split(":");
		String startDate = daterange[0];
		String endDate = daterange[1];
		System.out.println(startDate);
		System.out.println(endDate);

		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select  canc from Cancellation canc where canc.shipper IN (:shipperList) and canc.created BETWEEN :start AND :end");
		query.setParameter("shipperList", shipperList);
		query.setParameter("start", DateConvertor.convertToDate(startDate));
		query.setParameter("end", DateConvertor.convertToDate(endDate));
		System.out.println(DateConvertor.convertToDate(endDate));
		System.out.println(DateConvertor.convertToDate(startDate));
		List<Cancellation> resultList = query.getResultList();
		System.out.println(resultList.size());
		return resultList;
	}

	@Override
	public List<String> getModes() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select  distinct canc.mode from Cancellation canc");
		@SuppressWarnings("unchecked")
		List<String> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<String> getShippers() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select distinct canc.shipper from Cancellation canc");
		@SuppressWarnings("unchecked")
		List<String> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<String> getShipperGroups() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select distinct canc.shipperGroup from Cancellation canc");
		@SuppressWarnings("unchecked")
		List<String> resultList = query.getResultList();
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Cancellation> getPincodeData(List<String> shipperList,
			List<String> pincodes, String date) {
		String[] daterange = date.split(":");
		String startDate = daterange[0];
		String endDate = daterange[1];

		EntityManager entityManager = entityDao.getEntityManager();
		if (pincodes.size() > 8000) {
			int num_batches = pincodes.size() / 8000 + 1;
			System.out.println(num_batches);
			ArrayList<ArrayList<String>> pincodeList = new ArrayList<ArrayList<String>>();
			ArrayList<String> arr = new ArrayList<String>();
			int j = 1;
			for (int i = 0; i < pincodes.size(); i++) {
				if (i > 8000 * j || (i == pincodes.size() - 1)) {
					pincodeList.add(arr);
					arr = new ArrayList<String>();
					j++;
				}
				arr.add(pincodes.get(i));

			}
			System.out.println("Pincode List Size:" + pincodeList.size());
			List<Cancellation> finalResultList = new ArrayList<Cancellation>();
			for (int i = 1; i <= pincodeList.size(); i++) {
				Query query = entityManager
						.createQuery("Select canc from Cancellation canc where canc.shipper IN (:shipperList) "
								+ "and canc.created BETWEEN :start AND :end and canc.sellerPinCode IN (:pincode)");

				query.setParameter("pincode", pincodeList.get(i));
				if (!startDate.equals(""))
					query.setParameter("start",
							DateConvertor.convertToDate(startDate));
				if (!endDate.equals(""))
					query.setParameter("end",
							DateConvertor.convertToDate(endDate));
				if (shipperList.size() > 0)
					query.setParameter("shipperList", shipperList);

				List<Cancellation> resultList = query.getResultList();
				finalResultList.addAll(resultList);
			}
			return finalResultList;
		}

		Query query = entityManager
				.createQuery("Select canc from Cancellation canc where canc.shipper IN (:shipperList) "
						+ "and canc.created BETWEEN :start AND :end and canc.sellerPinCode IN (:pincodes)");

		query.setParameter("shipperList", shipperList);
		query.setParameter("pincodes", pincodes);
		query.setParameter("start", DateConvertor.convertToDate(startDate));
		query.setParameter("end", DateConvertor.convertToDate(endDate));

		List<Cancellation> resultList = query.getResultList();
		return resultList;
	}

}
