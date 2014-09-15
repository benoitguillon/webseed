package org.bgi.webseed.firms;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FirmsRepository extends CrudRepository<Firm, Long>{
	
	public List<Firm> findByWebContext(String webContext);

}
