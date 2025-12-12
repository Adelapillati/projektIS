package models;


public class Employee {
	
	private long employee_id;
	private long role_id;
	private long zone_id;
	private long table_id;
	private long unique_code;
	private String full_name;
	
	//Konstruktori pa parametra i modelit Employee
	public Employee() {}
	//Konstruktori me parametri i modelit Employee
	public Employee(long employee_id, long role_id, long table_id, long unique_code, String full_name) {
		this.employee_id=employee_id;
		this.role_id=role_id;
		this.table_id=table_id;
		this.unique_code=unique_code;
		this.full_name=full_name;
	}
	
	//Metodat get dhe set te atributeve te modelit Employee
	public long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(long employee_id) {
		this.employee_id = employee_id;
	}
	public long getRole_id() {
		return role_id;
	}
	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}
	public long getZone_id() {
		return zone_id;
	}
	public void setZone_id(long zone_id) {
		this.zone_id = zone_id;
	}
	public long getTable_id() {
		return table_id;
	}
	public void setTable_id(long table_id) {
		this.table_id = table_id;
	}
	public long getUnique_code() {
		return unique_code;
	}
	public void setUnique_code(long unique_code) {
		this.unique_code = unique_code;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	
	@Override
	public String toString() {
		return "Punonjesi ka id: "+employee_id+" dhe ka role id: "+role_id+"punon ne zonen me id: "+zone_id+"dhe ne tavolinat me id"+table_id+"me kod unik: "+ unique_code+"dhe ka emer"+full_name+".";
	}
	

}