package pacakge_modelet;

public class Menu_Item {
	
	private long item_id;
	private long menu_id;
	private String item_name;
	private long item_price;
	
    //Konstruktori me parametra i modelit Menu_Item
    public Menu_Item(long item_id, long menu_id, String item_name, long item_price) {
    	this.item_id=item_id;
    	this.menu_id=menu_id;
    	this.item_name=item_name;
    	this.item_price=item_price;
    }
    
    // Metodat get dhe set i atributeve te modelit Menu_Item 
    public long getItem_id() {
    	return item_id;
    }
    public void setItem_id(long item_id) {
    	this.item_id=item_id;
    }
    
    public long getMenu_id() {
    	return menu_id;
    }
    public void setMenu_id(long menu_id) {
    	this.menu_id=menu_id;
    }
    
    public String getItem_name() {
    	return item_name;
    }
    public void setItem_name(String item_name) {
    	this.item_name=item_name;
    }
    
    public long getItem_price() {
    	return item_price;
    }
    public void setItem_price(long item_price) {
    	this.item_price=item_price;
    }

}
