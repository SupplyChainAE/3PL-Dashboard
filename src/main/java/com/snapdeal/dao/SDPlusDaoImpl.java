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
import com.snapdeal.util.DateConvertor;

@Transactional
@Named("sdplusDao")
public class SDPlusDaoImpl implements SDPlusDao {

	@Inject
	@Named("entityDao")
	EntityDao entityDao;

	@Override
	@SuppressWarnings("unchecked")
	public List<SdPlus> getAllData(List<String> shipperNames, String startDate,
			String endDate) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select sdp from SdPlus sdp where sdp.created between :start and :end AND sdp.shipper IN (:shipperList)");
		query.setParameter("start", DateConvertor.convertToDate(startDate));
		query.setParameter("end", DateConvertor.convertToDate(endDate));
		query.setParameter("shipperList", shipperNames);
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

	@Override
	public List<SdPlusFilter> genericGroup(String q, List<String> shipperNames,
			String shipper, String startDate, String endDate, String mode) {
		EntityManager entityManager = entityDao.getEntityManager();
		Query query = entityManager
				.createQuery("Select sdp.shipperGroup,sdp.shipper,sdp.mode,"
						+ "SUM(sdp.shippedToday),SUM(sdp.notshippedOneDay),SUM(sdp.notshippedTwoDays),SUM(sdp.notshippedThreeDays),"
						+ "SUM(sdp.notshippedFourDays),SUM(sdp.notshippedMoreFourDays) from SdPlus sdp where sdp.created between :start and :end "
						+ q);
		if (!startDate.equals(""))
			query.setParameter("start", DateConvertor.convertToDate(startDate));
		if (!endDate.equals(""))
			query.setParameter("end", DateConvertor.convertToDate(endDate));
		if (shipperNames.size() > 0)
			query.setParameter("shipperList", shipperNames);
		if (!mode.equals(""))
			query.setParameter("mode", mode);
		if (!shipper.equals(""))
			query.setParameter("shipper", shipper);
		List<Object[]> objectList = query.getResultList();
		List<SdPlusFilter> resultList = convertObjectToSdPlus(objectList);
		return resultList;
	}

}
