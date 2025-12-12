package models;


public class Location {

	private long location_id;
	private String location_name;
	private String location_address;
	
	//Konstruktori pa parametra i modelit Location
	public Location() {}
	//Konstruktori me parametra i modelit Location
	public Location(long location_id, String location_name, String location_address) {
		this.location_id=location_id;
		this.location_name=location_name;
		this.location_address=location_address;
	}
	
	// Metodat get dhe set te atributeve te modelit Location
	
	public long getLocation_id() {
		return this.location_id;
	}
	public void setLocation_id( long location_id) {
		this.location_id=location_id;
	}
	
	public String getLocation_name() {
		return this.location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name=location_name;
	}
	
	public String getLocation_address() {
		return this.location_address;
	}
	public void setLocation_adress(String location_address) {
		this.location_address=location_address;
	}
	
	@Override
	public String toString() {
		return "Lokacioni ka kete id: " +location_id+ "ka emer: "+location_name+"dhe ndodhet ne: "+location_address+".";
	}
	
}