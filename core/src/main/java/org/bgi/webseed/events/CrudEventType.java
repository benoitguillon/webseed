package org.bgi.webseed.events;

public enum CrudEventType {
	
	CREATE, UPDATE, SOFT_DELETE, HARD_DELETE;
	
	public boolean isCreate(){
		return CREATE.equals(this);
	}
	
	public boolean isUpdate(){
		return UPDATE.equals(this);
	}
	
	public boolean isSoftDelete(){
		return SOFT_DELETE.equals(this);
	}
	
	public boolean isHardDelete(){
		return HARD_DELETE.equals(this);
	}
	
	public boolean isDelete(){
		return isSoftDelete() || isHardDelete();
	}

}
