package org.bgi.webseed.firms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public class FirmsServiceImpl implements FirmsService {

	@Autowired
	private FirmsRepository repository;
	
	
}
