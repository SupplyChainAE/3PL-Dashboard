package com.snapdeal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snapdeal.dto.DropshipFilter;
import com.snapdeal.entity.Dropship;


@Service
public interface DropshipService {

	public String generateDropshipData(List<Dropship> data);
	
	public String generateDropshipFilterData(List<DropshipFilter> data);

}
