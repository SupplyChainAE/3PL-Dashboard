package com.snapdeal.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.snapdeal.dto.SdPlusFilter;
import com.snapdeal.entity.SdPlus;

@Transactional
@Named("sdplusDao")
public class SDPlusDaoImpl implements SDPlusDao {

	@Inject
	@Named("entityDao")
	EntityDao entityDao;

	@Override
	@SuppressWarnings("unchecked")
	public List<SdPlus> getAllData(String startDate, String endDate) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select sdp from SdPlus sdp where sdp.created between :start and :end");
		query.setParameter("start", startDate);
		query.setParameter("end", endDate);
		List<SdPlus> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<String> getModes() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select  distinct sdp.mode from SdPlus sdp");
		@SuppressWarnings("unchecked")
		List<String> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<String> getShippers() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select distinct sdp.shipper from SdPlus sdp");
		@SuppressWarnings("unchecked")
		List<String> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<String> getShipperGroups() {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select distinct sdp.shipperGroup from SdPlus sdp");
		@SuppressWarnings("unchecked")
		List<String> resultList = query.getResultList();
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SdPlusFilter> groupByModeShipper(String startDate,
			String endDate, String mode, String shipper) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select sdp.shipperGroup,sdp.shipper,sdp.mode"
						+ "COUNT(sdp.shippedToday),COUNT(sdp.notshippedOneDay),COUNT(sdp.notshippedTwoDays),COUNT(sdp.notshippedThreeDays),"
						+ "COUNT(sdp.notshippedFourDays),COUNT(sdp.notshippedMoreFourDays) from SdPlus sdp where sdp.created between :start and :end "
						+ "GROUP BY sdp.mode,sdp.shipper HAVING sdp.mode= :mode AND sdp.shipper= :shipper");
		query.setParameter("start", startDate);
		query.setParameter("end", endDate);
		query.setParameter("mode", mode);
		query.setParameter("shipper", shipper);
		List<Object[]> objectList = query.getResultList();
		List<SdPlusFilter> resultList = convertObjectToSdPlus(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SdPlusFilter> groupByModeGroupShipper(String startDate,
			String endDate) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select sdp.shipperGroup,sdp.shipper,sdp.mode,"
						+ "COUNT(sdp.shippedToday),COUNT(sdp.notshippedOneDay),COUNT(sdp.notshippedTwoDays),COUNT(sdp.notshippedThreeDays),"
						+ "COUNT(sdp.notshippedFourDays),COUNT(sdp.notshippedMoreFourDays) from SdPlus sdp where sdp.created between :start and :end "
						+ "GROUP BY sdp.shipperGroup,sdp.mode,sdp.shipper ");
		query.setParameter("start", startDate);
		query.setParameter("end", endDate);
		List<Object[]> objectList = query.getResultList();
		List<SdPlusFilter> resultList = convertObjectToSdPlus(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SdPlusFilter> groupByGroup(String startDate, String endDate) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select sdp.shipperGroup,sdp.shipper,sdp.mode,"
						+ "COUNT(sdp.shippedToday),COUNT(sdp.notshippedOneDay),COUNT(sdp.notshippedTwoDays),COUNT(sdp.notshippedThreeDays),"
						+ "COUNT(sdp.notshippedFourDays),COUNT(sdp.notshippedMoreFourDays) from SdPlus sdp where sdp.created between :start and :end "
						+ "GROUP BY sdp.shipperGroup");
		query.setParameter("start", startDate);
		query.setParameter("end", endDate);
		List<Object[]> objectList = query.getResultList();
		List<SdPlusFilter> resultList = convertObjectToSdPlus(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SdPlusFilter> groupByMode(String startDate, String endDate ,String mode) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select sdp.shipperGroup,sdp.shipper,sdp.mode,"
						+ "COUNT(sdp.shippedToday),COUNT(sdp.notshippedOneDay),COUNT(sdp.notshippedTwoDays),COUNT(sdp.notshippedThreeDays),"
						+ "COUNT(sdp.notshippedFourDays),COUNT(sdp.notshippedMoreFourDays) from SdPlus sdp where sdp.created between :start and :end "
						+ "GROUP BY sdp.mode HAVING sdp.mode= :mode");
		query.setParameter("start", startDate);
		query.setParameter("end", endDate);
		query.setParameter("mode",mode);
		List<Object[]> objectList = query.getResultList();
		List<SdPlusFilter> resultList = convertObjectToSdPlus(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SdPlusFilter> groupByShipper(String startDate, String endDate,
			String shipper) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select sdp.shipperGroup,sdp.shipper,sdp.mode,"
						+ "COUNT(sdp.shippedToday),COUNT(sdp.notshippedOneDay),COUNT(sdp.notshippedTwoDays),COUNT(sdp.notshippedThreeDays),"
						+ "COUNT(sdp.notshippedFourDays),COUNT(sdp.notshippedMoreFourDays) from SdPlus sdp where sdp.created between :start and :end "
						+ "GROUP BY sdp.shipper HAVING sdp.shipper= :shipper");
		query.setParameter("start", startDate);
		query.setParameter("end", endDate);
		query.setParameter("shipper", shipper);
		List<Object[]> objectList = query.getResultList();
		List<SdPlusFilter> resultList = convertObjectToSdPlus(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SdPlusFilter> groupByGroupShipper(String startDate,
			String endDate) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select sdp.shipperGroup,sdp.shipper,sdp.mode"
						+ "COUNT(sdp.shippedToday),COUNT(sdp.notshippedOneDay),COUNT(sdp.notshippedTwoDays),COUNT(sdp.notshippedThreeDays),"
						+ "COUNT(sdp.notshippedFourDays),COUNT(sdp.notshippedMoreFourDays) from SdPlus sdp where sdp.created between :start and :end "
						+ "GROUP BY sdp.shipper,sdp.shipperGroup");
		query.setParameter("start", startDate);
		query.setParameter("end", endDate);
		List<Object[]> objectList = query.getResultList();
		List<SdPlusFilter> resultList = convertObjectToSdPlus(objectList);
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SdPlusFilter> groupByModeGroup(String startDate,
			String endDate, String mode) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select sdp.shipperGroup,sdp.shipper,sdp.mode"
						+ "COUNT(sdp.shippedToday),COUNT(sdp.notshippedOneDay),COUNT(sdp.notshippedTwoDays),COUNT(sdp.notshippedThreeDays),"
						+ "COUNT(sdp.notshippedFourDays),COUNT(sdp.notshippedMoreFourDays) from SdPlus sdp where sdp.created between :start and :end "
						+ "GROUP BY sdp.mode,sdp.shipperGroup HAVING sdp.mode= :mode");
		query.setParameter("start", startDate);
		query.setParameter("end", endDate);
		query.setParameter("mode", mode);
		List<Object[]> objectList = query.getResultList();
		List<SdPlusFilter> resultList = convertObjectToSdPlus(objectList);
		return resultList;
	}

	public List<SdPlusFilter> convertObjectToSdPlus(List<Object[]> objectList) {
		List<SdPlusFilter> resultList = new ArrayList<SdPlusFilter>();

		for (Object[] objArray : objectList) {
			SdPlusFilter sdplusFilter = new SdPlusFilter();

			sdplusFilter.setShipperGroup((String) objArray[0]);
			sdplusFilter.setShipper((String) objArray[1]);
			sdplusFilter.setMode((String) objArray[2]);
			sdplusFilter.setShippedToday((Long) objArray[3]);
			sdplusFilter.setNotshippedOneDay((Long) objArray[4]);
			sdplusFilter.setNotshippedTwoDays((Long) objArray[5]);
			sdplusFilter.setNotshippedThreeDays((Long) objArray[6]);
			sdplusFilter.setNotshippedFourDays((Long) objArray[7]);
			sdplusFilter.setNotshippedMoreFourDays((Long) objArray[8]);

			resultList.add(sdplusFilter);
		}
		return resultList;
	}

}
