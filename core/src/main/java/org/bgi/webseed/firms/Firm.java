package org.bgi.webseed.firms;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.bgi.webseed.model.BaseEntity;
import org.bgi.webseed.users.User;

@Entity
public class Firm extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(max=255)
	private String name;
	
	@NotNull
	@Size(max=20)
	@Pattern(regexp="[a-z]+")
	private String webContext;
	
	@OneToMany(mappedBy="firm", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<User> users = new HashSet<User>();
	
	public Firm(){
		
	}
	
	public Firm(Long id, String name, String webContext){
		this.id = id;
		this.name = name;
		this.webContext = webContext;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebContext() {
		return webContext;
	}

	public void setWebContext(String webContext) {
		this.webContext = webContext;
	}
	
}
