package elisha.fyp.project.entity;

public enum Status {
	
	Pending("Pending"), Declined("Declined"), Accepted("Accepted");
	
	String statusType;
	
	Status(String statusType)
	{
		this.statusType = statusType;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	
	public String getValue()
	{
		return name();
	}
	
	public void setValue(String value)
	{
		
	}
	
	
	

}
