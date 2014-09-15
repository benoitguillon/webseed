package org.bgi.webseed.model;

public abstract class BaseDto<O extends BaseEntity> {
	
	public BaseDto(){
		
	}
	
	public BaseDto(O modelObject){
		
	}
	
	public abstract O toModel();
	
	public abstract void fromModel(O model);
	
}
