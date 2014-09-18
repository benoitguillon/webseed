package org.bgi.webseed.firms;

import org.springframework.data.repository.CrudRepository;

public interface FirmsRepository extends CrudRepository<Firm, Long>{
	
	public Firm findByWebContext(String webContext);

}
