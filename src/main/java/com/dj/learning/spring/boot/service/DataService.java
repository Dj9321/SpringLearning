package com.dj.learning.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.learning.spring.boot.entity.CheckTable;
import com.dj.learning.spring.boot.repository.CreatedByDBRepo;

import jakarta.transaction.Transactional;

@Service
public class DataService {

	@Autowired
	CreatedByDBRepo db;

	@Transactional
	public CheckTable saveDataInCreatedByDB() {
		CheckTable entity = new CheckTable();
		// If you’re using auto-increment IDs, let the database generate them to avoid
		// ID and version conflicts.
//		entity.setPrimary_key1(3);
		entity.setColumn1('H');
		entity.setColumn2('D');
		CheckTable savedEntity = db.save(entity);
		return savedEntity;
	}

}
