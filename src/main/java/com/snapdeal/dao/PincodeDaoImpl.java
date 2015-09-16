package com.snapdeal.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@Named("pincodeDao")
public class PincodeDaoImpl implements PincodeDao{

	@Inject
	@Named("entityDao")
	EntityDao entityDao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<String> getPincodeForZone(String zone) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select pin.pincode from Pincode pin where pin.zone = :zone");
		query.setParameter("zone", zone);
		List<String> resultList = query.getResultList();
		
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getZones() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select distinct pin.zone from Pincode pin");
		List<String> resultList = query.getResultList();
		
		return resultList;
	}

}
