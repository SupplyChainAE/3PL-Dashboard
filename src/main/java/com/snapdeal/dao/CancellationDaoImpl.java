package com.snapdeal.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.entity.Cancellation;
import com.snapdeal.entity.Shipper;
import com.snapdeal.util.DateConvertor;

@Transactional
@Named("cancellationDao")
public class CancellationDaoImpl implements CancellationDao{

	@Inject
	@Named("entityDao")
	EntityDao entityDao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Cancellation> getAllData(List<Shipper> shipperList,String date) {
		String[] daterange  = date.split(":");
		String startDate = daterange[0];
		String endDate = daterange[1];
		System.out.println(startDate);
		System.out.println(endDate);
		List<String> shipperNames = new ArrayList<String>();
		for(Shipper shipper : shipperList)
		{
			shipperNames.add(shipper.getCourier());
		}
		
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select  canc from Cancellation canc where canc.shipper IN (:shipperList) and canc.created BETWEEN :start AND :end");
		query.setParameter("shipperList",shipperNames);
		query.setParameter("start",DateConvertor.convertToDate(startDate));
		query.setParameter("end",DateConvertor.convertToDate(endDate));
		System.out.println(DateConvertor.convertToDate(endDate));
		System.out.println(DateConvertor.convertToDate(startDate));
		List<Cancellation> resultList = query.getResultList();
		System.out.println(resultList.size());
		return resultList;
	}

	@Override
	public List<String> getModes() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select  distinct canc.mode from Cancellation canc");
		@SuppressWarnings("unchecked")
		List<String> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<String> getShippers() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select distinct canc.shipper from Cancellation canc");
		@SuppressWarnings("unchecked")
		List<String> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<String> getShipperGroups() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select distinct canc.shipperGroup from Cancellation canc");
		@SuppressWarnings("unchecked")
		List<String> resultList = query.getResultList();
		return resultList;
	}

}
