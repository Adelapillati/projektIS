package pacakge_modelet;

public class Role {

	private long role_id;
	private String name;
	
	//Konstruktori me parametra per modelin role 
	public Role(long role_id, String name) {
		this.role_id=role_id;
		this.name=name;
	}
	
	//Metodat get dhe set per atributet e modelit Role
	public long getRole_id() {
		return role_id;
	}
	public void setRole_id(long role_id) {
		this.role_id=role_id;
	}
	
	public String getName() {
	    return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
}
