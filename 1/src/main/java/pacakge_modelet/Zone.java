package pacakge_modelet;

public class Zone {

	private long zone_id;
	private long location_id;
	private String zone_name;
	
	//Konstruktori me parametri i modelit Zone
	public Zone (long zone_id, long location_id, String zone_name) {
		this.zone_id=zone_id;
		this.location_id=location_id;
		this.zone_name=zone_name;
	}
	
	//Metodat get dhe set te atributeve te modelit Zone 
	public long getZone_id() {
		return zone_id;
	}
	public void setZone_id(long zone_id) {
		this.zone_id = zone_id;
	}
	public long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(long location_id) {
		this.location_id = location_id;
	}
	public String getZone_name() {
		return zone_name;
	}
	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}
		
}
