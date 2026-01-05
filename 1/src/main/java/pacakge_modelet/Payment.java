package pacakge_modelet;

public class Payment {
	
	private long payment_id;
	private long item_id;
	private long order_id;
	private int quantity;
	private long unit_price;
	

	//Konstruktori me parametra i modelit Payment
	public Payment(long payment_id, long item_id, long order_id, int quantity, long unit_price) {
		this.payment_id=payment_id;
		this.item_id=item_id;
		this.order_id=order_id;
		this.quantity=quantity;
		this.unit_price=unit_price;
	}
	
	//Metodat get dhe set te atributeve te modelit Payment
	public long getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(long payment_id) {
		this.payment_id = payment_id;
	}
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(long unit_price) {
		this.unit_price = unit_price;
	}
	
}
