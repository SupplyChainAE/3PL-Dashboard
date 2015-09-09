package com.snapdeal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snapdeal.entity.Cancellation;

@Service
public interface CancellationService {
	
	public String generateCancellationData(Cancellation[] data);

}
