package com.example.service;

import java.util.List;

import com.example.vo.ServiceHistory;


public interface IECManager {
	
	void addServiceToHistory(ServiceHistory history);
	void clearServiceHistory();
	List<ServiceHistory> getAllServiceHistory();
	
}
