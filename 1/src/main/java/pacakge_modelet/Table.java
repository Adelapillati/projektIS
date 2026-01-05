package pacakge_modelet;

public class Table {
	private long table_id;
	private long zone_id;
	private boolean status;
	private int seats;
	

	//Konstruktori me parametra i modelit Table
	public Table (long table_id, long zone_id, boolean status, int seats) {
		this.table_id=table_id;
		this.zone_id=zone_id;
		this.status=status;
		this.seats=seats;
	}
	
	//Metodat get dhe set te atributeve te modelit table
	public long getTable_id() {
		return table_id;
	}
	public void setTable_id(long table_id) {
		this.table_id = table_id;
	}
	public long getZone_id() {
		return zone_id;
	}
	public void setZone_id(long zone_id) {
		this.zone_id = zone_id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}

}
