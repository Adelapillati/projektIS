package models;

public class Order {
	
	private long order_id;
	private long table_id;
	private long waiter_id;
	private boolean status;
	private float discount_percentage;
	
	//Konstruktori pa parametra i modelit Order
	public Order() {}
	//Konstruktori me parametra i modelit Order
	public Order(long order_id,long table_id, long waiter_id, boolean status, float discount_percentage) {
		this.order_id=order_id;
		this.table_id=table_id;
		this.waiter_id=waiter_id;
		this.status=status;
		this.discount_percentage=discount_percentage;
	}
	
	// Metodat get dhe set te atributeve te modelit Order

	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public long getTable_id() {
		return table_id;
	}
	public void setTable_id(long table_id) {
		this.table_id = table_id;
	}
	public long getWaiter_id() {
		return waiter_id;
	}
	public void setWaiter_id(long waiter_id) {
		this.waiter_id = waiter_id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public float getDiscount_percentage() {
		return discount_percentage;
	}
	public void setDiscount_percentage(float discount_percentage) {
		this.discount_percentage = discount_percentage;
	}
	
	@Override
	public String toString() {
		return "Porosia ka id: "+order_id+" dhe i perket tavolines: "+table_id+"qe po e punon kamarieri me id: "+waiter_id+"dhe porosia eshte paguar: "+status+"dhe perqindja e aplikuar eshte: "+discount_percentage+".";
	}


}