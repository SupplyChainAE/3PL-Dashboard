package com.snapdeal.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.entity.Cancellation;
import com.snapdeal.entity.Shipper;

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
		String startDate = daterange[0]+" 00:00:00";
		String endDate = daterange[1]+" 23:59:59";
		
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select  canc from Cancellation canc where canc.shipper IN :shipperList and canc.created IN(:start , :end)");
		query.setParameter("shipperList",shipperList);
		query.setParameter("start",startDate);
		query.setParameter("end",endDate);
		List<Cancellation> resultList = query.getResultList();
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
