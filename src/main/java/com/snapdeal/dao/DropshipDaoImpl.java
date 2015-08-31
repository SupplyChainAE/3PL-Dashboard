package com.snapdeal.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.dto.DropshipFilter;
import com.snapdeal.entity.Dropship;

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
		Query query = entityManager.createQuery("Select  drop from Dropship drop");
		List<Dropship> resultList = query.getResultList();
		return resultList;
	}
	
	@Override
	public List<String> getModes() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select  distinct drop.mode from Dropship drop");
		@SuppressWarnings("unchecked")
		List<String> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<String> getShippers() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select distinct drop.shipper from Dropship drop");
		@SuppressWarnings("unchecked")
		List<String> resultList = query.getResultList();
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getShipperGroups() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select distinct shipperGroup from Dropship drop");
		List<String> resultList = query.getResultList();
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DropshipFilter> groupByModeGroupShipper() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode," +
				"COUNT(drop.shippedToday),COUNT(drop.notshippedOneDay),COUNT(drop.notshippedTwoDays),COUNT(drop.notshippedThreeDays)," +
				"COUNT(drop.notshippedFourDays),COUNT(drop.notshippedMoreFourDays) from Dropship drop " +
				"GROUP BY drop.shipperGroup,drop.mode,drop.shipper ");
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList =  convertObjectToDropship(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DropshipFilter> groupByMode() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode," +
				"COUNT(drop.shippedToday),COUNT(drop.notshippedOneDay),COUNT(drop.notshippedTwoDays),COUNT(drop.notshippedThreeDays)," +
				"COUNT(drop.notshippedFourDays),COUNT(drop.notshippedMoreFourDays) from Dropship drop " +
				"GROUP BY drop.mode");
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList =  convertObjectToDropship(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DropshipFilter> groupByGroup() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode," +
				"COUNT(drop.shippedToday),COUNT(drop.notshippedOneDay),COUNT(drop.notshippedTwoDays),COUNT(drop.notshippedThreeDays)," +
				"COUNT(drop.notshippedFourDays),COUNT(drop.notshippedMoreFourDays) from Dropship drop " +
				"GROUP BY drop.shipperGroup");
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList =  convertObjectToDropship(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DropshipFilter> groupByShipper() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode," +
				"COUNT(drop.shippedToday),COUNT(drop.notshippedOneDay),COUNT(drop.notshippedTwoDays),COUNT(drop.notshippedThreeDays)," +
				"COUNT(drop.notshippedFourDays),COUNT(drop.notshippedMoreFourDays) from Dropship drop " +
				"GROUP BY drop.shipper");
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList =  convertObjectToDropship(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DropshipFilter> groupByGroupShipper() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode," +
				"COUNT(drop.shippedToday),COUNT(drop.notshippedOneDay),COUNT(drop.notshippedTwoDays),COUNT(drop.notshippedThreeDays)," +
				"COUNT(drop.notshippedFourDays),COUNT(drop.notshippedMoreFourDays) from Dropship drop " +
				"GROUP BY drop.shipperGroup,drop.shipper");
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList =  convertObjectToDropship(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DropshipFilter> groupByModeShipper() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode" +
				"COUNT(drop.shippedToday),COUNT(drop.notshippedOneDay),COUNT(drop.notshippedTwoDays),COUNT(drop.notshippedThreeDays)," +
				"COUNT(drop.notshippedFourDays),COUNT(drop.notshippedMoreFourDays) from Dropship drop " +
				"GROUP BY drop.mode,drop.shipper");
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList =  convertObjectToDropship(objectList);
		return resultList;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DropshipFilter> groupByModeGroup() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager.createQuery("Select drop.shipperGroup,drop.shipper,drop.mode" +
				"COUNT(drop.shippedToday),COUNT(drop.notshippedOneDay),COUNT(drop.notshippedTwoDays),COUNT(drop.notshippedThreeDays)," +
				"COUNT(drop.notshippedFourDays),COUNT(drop.notshippedMoreFourDays) from Dropship drop " +
				"GROUP BY drop.mode,drop.shipperGroup");
		List<Object[]> objectList = query.getResultList();
		List<DropshipFilter> resultList =  convertObjectToDropship(objectList);
		return resultList;
	}
	
	public List<DropshipFilter> convertObjectToDropship(List<Object[]> objectList)
	{
		List<DropshipFilter> resultList = new ArrayList<DropshipFilter>();
		for(Object[] objArray : objectList )
		{
			DropshipFilter dropshipFilter = new DropshipFilter();
			
			dropshipFilter.setShipperGroup((String)objArray[0]);
			dropshipFilter.setShipper((String)objArray[1]);
			dropshipFilter.setMode((String)objArray[2]);
			dropshipFilter.setShippedToday((Long)objArray[3]);
			dropshipFilter.setNotshippedOneDay((Long)objArray[4]);
			dropshipFilter.setNotshippedTwoDays((Long)objArray[5]);
			dropshipFilter.setNotshippedThreeDays((Long)objArray[6]);
			dropshipFilter.setNotshippedFourDays((Long)objArray[7]);
			dropshipFilter.setNotshippedMoreFourDays((Long)objArray[8]);
			
			resultList.add(dropshipFilter);
		}
		return resultList;
	}

	
}
