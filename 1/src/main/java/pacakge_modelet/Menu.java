package pacakge_modelet;

public class Menu {

    private long menu_id;
    private String menu_type;
    
    //Konstruktori me parametra i modelit menu
    public Menu (long menu_id, String menu_type) {
    	this.menu_id=menu_id;
    	this.menu_type=menu_type;
    }
   
    //Metodat get dhe set per atributet e modelit
    public long getMenu_id(){
    	return menu_id;
    }
    
    public void setMenu_id(long menu_id) {
    	this.menu_id=menu_id;
    }
    
    public String getMenu_type() {
    	return menu_type;
    }
	
    public void setMenu_type(String menu_type) {
    	this.menu_type=menu_type;
    }
}

