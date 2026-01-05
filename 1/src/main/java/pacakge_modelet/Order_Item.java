package pacakge_modelet;

public class Order_Item {
	
	private long order_item_id;
	private long item_id;
	private long order_id;
	private int quantity;
	private long unit_price;
	
	//Konstruktori me parametra i modelit Order_Item
	public Order_Item (long order_item_id, long item_id, long order_id, int quantity, long unit_price) {
		this.order_item_id=order_item_id;
		this.item_id=item_id;
		this.order_id=order_id;
		this.quantity=quantity;
		this.unit_price=unit_price;
	}
	
	//Metodat get dhe set per atributet e modelit Order_Item
	public long getOrder_item_id() {
		return order_item_id;
	}
	public void setOrder_item_id(Long order_item_id) {
		this.order_item_id=order_item_id;
	}
	
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id=order_id;
	}
	
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id=item_id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	
	public long getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(long unit_price) {
		this.unit_price=unit_price;
	}


}
