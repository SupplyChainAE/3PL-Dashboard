package com.snapdeal.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.dto.DropshipFilter;
import com.snapdeal.dto.SdPlusFilter;
import com.snapdeal.entity.Dropship;
import com.snapdeal.util.DateConvertor;

@Transactional
@Named("dropshipDao")
public class DropshipDaoImpl implements DropshipDao {

	@Inject
	@Named("entityDao")
	EntityDao entityDao;

	@Override
	@SuppressWarnings("unchecked")
	public List<Dropship> getAllData() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select  drop from Dropship drop");
		List<Dropship> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<String> getModes() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select  distinct drop.mode from Dropship drop");
		@SuppressWarnings("unchecked")
		List<String> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<String> getShippers() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select distinct drop.shipper from Dropship drop");
		@SuppressWarnings("unchecked")
		List<String> resultList = query.getResultList();
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getShipperGroups() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select distinct shipperGroup from Dropship drop");
		List<String> resultList = query.getResultList();
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DropshipFilter> groupByModeGroupShipper() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode,"
						+ "SUM(drop.shippedToday),SUM(drop.notshippedOneDay),SUM(drop.notshippedTwoDays),SUM(drop.notshippedThreeDays),"
						+ "SUM(drop.notshippedFourDays),SUM(drop.notshippedMoreFourDays) from Dropship drop "
						+ "GROUP BY drop.shipperGroup,drop.mode,drop.shipper ");
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList = convertObjectToDropship(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DropshipFilter> groupByMode() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode,"
						+ "SUM(drop.shippedToday),SUM(drop.notshippedOneDay),SUM(drop.notshippedTwoDays),SUM(drop.notshippedThreeDays),"
						+ "SUM(drop.notshippedFourDays),SUM(drop.notshippedMoreFourDays) from Dropship drop "
						+ "GROUP BY drop.mode");
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList = convertObjectToDropship(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DropshipFilter> groupByGroup() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode,"
						+ "SUM(drop.shippedToday),SUM(drop.notshippedOneDay),SUM(drop.notshippedTwoDays),SUM(drop.notshippedThreeDays),"
						+ "SUM(drop.notshippedFourDays),SUM(drop.notshippedMoreFourDays) from Dropship drop "
						+ "GROUP BY drop.shipperGroup");
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList = convertObjectToDropship(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DropshipFilter> groupByShipper() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode,"
						+ "SUM(drop.shippedToday),SUM(drop.notshippedOneDay),SUM(drop.notshippedTwoDays),SUM(drop.notshippedThreeDays),"
						+ "SUM(drop.notshippedFourDays),SUM(drop.notshippedMoreFourDays) from Dropship drop "
						+ "GROUP BY drop.shipper");
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList = convertObjectToDropship(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DropshipFilter> groupByGroupShipper() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode,"
						+ "SUM(drop.shippedToday),SUM(drop.notshippedOneDay),SUM(drop.notshippedTwoDays),SUM(drop.notshippedThreeDays),"
						+ "SUM(drop.notshippedFourDays),SUM(drop.notshippedMoreFourDays) from Dropship drop "
						+ "GROUP BY drop.shipperGroup,drop.shipper");
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList = convertObjectToDropship(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DropshipFilter> groupByModeShipper() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode"
						+ "SUM(drop.shippedToday),SUM(drop.notshippedOneDay),SUM(drop.notshippedTwoDays),SUM(drop.notshippedThreeDays),"
						+ "SUM(drop.notshippedFourDays),SUM(drop.notshippedMoreFourDays) from Dropship drop "
						+ "GROUP BY drop.mode,drop.shipper");
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList = convertObjectToDropship(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DropshipFilter> groupByModeGroup() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode,"
						+ "SUM(drop.shippedToday),SUM(drop.notshippedOneDay),SUM(drop.notshippedTwoDays),SUM(drop.notshippedThreeDays),"
						+ "SUM(drop.notshippedFourDays),SUM(drop.notshippedMoreFourDays) from Dropship drop "
						+ "GROUP BY drop.mode,drop.shipperGroup");
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList = convertObjectToDropship(objectList);
		return resultList;
	}

	public List<DropshipFilter> convertObjectToDropship(
			List<Object[]> objectList) {
		List<DropshipFilter> resultList = new ArrayList<DropshipFilter>();
		for (Object[] objArray : objectList) {
			DropshipFilter dropshipFilter = new DropshipFilter();

			dropshipFilter.setShipperGroup((String) objArray[0]);
			dropshipFilter.setShipper((String) objArray[1]);
			dropshipFilter.setMode((String) objArray[2]);
			dropshipFilter.setShippedToday((Long) objArray[3]);
			dropshipFilter.setNotshippedOneDay((Long) objArray[4]);
			dropshipFilter.setNotshippedTwoDays((Long) objArray[5]);
			dropshipFilter.setNotshippedThreeDays((Long) objArray[6]);
			dropshipFilter.setNotshippedFourDays((Long) objArray[7]);
			dropshipFilter.setNotshippedMoreFourDays((Long) objArray[8]);

			resultList.add(dropshipFilter);
		}
		return resultList;
	}

	@Override
	public List<DropshipFilter> genericGroup(String q,
			List<String> shipperNames, String shipper, String startDate,
			String endDate, List<String> pincode) {
		EntityManager entityManager = entityDao.getEntityManager();
		if (pincode.size() > 8000) {
			int num_batches = pincode.size() / 8000 + 1;
			System.out.println(num_batches);
			ArrayList<ArrayList<String>> pincodeList = new ArrayList<ArrayList<String>>();
			ArrayList<String> arr = new ArrayList<String>();
			int j = 1;
			for (int i = 0; i < pincode.size(); i++) {
				if (i > 8000 * j || (i == pincode.size() - 1)) {
					pincodeList.add(arr);
					arr = new ArrayList<String>();
					j++;
				}
				arr.add(pincode.get(i));

			}
			System.out.println("Pincode List Size:" + pincodeList.size());
			List<DropshipFilter> finalResultList = new ArrayList<DropshipFilter>();
			System.out.println("q:\n" + q + "\n");
			System.out.println("Enter Loop");
			for (int i = 1; i <= pincodeList.size(); i++) {
				Query query = entityManager
						.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode,"
								+ "SUM(drop.shippedToday),SUM(drop.notshippedOneDay),SUM(drop.notshippedTwoDays),SUM(drop.notshippedThreeDays),"
								+ "SUM(drop.notshippedFourDays),SUM(drop.notshippedMoreFourDays) from Dropship drop where drop.created between :start and :end "
								+ q);
				query.setParameter("pincode", pincodeList.get(i));
				if (!startDate.equals(""))
					query.setParameter("start",
							DateConvertor.convertToDate(startDate));
				if (!endDate.equals(""))
					query.setParameter("end",
							DateConvertor.convertToDate(endDate));
				if (shipperNames.size() > 0)
					query.setParameter("shipperList", shipperNames);

				if (!shipper.equals(""))
					query.setParameter("shipper", shipper);
				List<Object[]> objectList = query.getResultList();
				List<DropshipFilter> resultList = convertObjectToDropship(objectList);
				finalResultList.addAll(resultList);
			}
			return finalResultList;
		}
		Query query = entityManager
				.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode,"
						+ "SUM(drop.shippedToday),SUM(drop.notshippedOneDay),SUM(drop.notshippedTwoDays),SUM(drop.notshippedThreeDays),"
						+ "SUM(drop.notshippedFourDays),SUM(drop.notshippedMoreFourDays) from Dropship drop where drop.created between :start and :end "
						+ q);
		if (!startDate.equals(""))
			query.setParameter("start", DateConvertor.convertToDate(startDate));
		if (!endDate.equals(""))
			query.setParameter("end", DateConvertor.convertToDate(endDate));
		if (shipperNames.size() > 0)
			query.setParameter("shipperList", shipperNames);
		if (pincode.size() > 0)
			query.setParameter("pincode", pincode);
		if (!shipper.equals(""))
			query.setParameter("shipper", shipper);
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList = convertObjectToDropship(objectList);
		return resultList;
	}
}
