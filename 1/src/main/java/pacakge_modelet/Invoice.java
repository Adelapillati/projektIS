package pacakge_modelet;

public class Invoice {

	private long invoice_id;
	private long order_id;
	private String fiscal_code;
	private String date;


	//Konstruktori me parametra i modelit Invoice
	public Invoice(long invoice_id, long order_id, String fiscal_code, String date) {
		this.invoice_id=invoice_id;
		this.order_id=order_id;
		this.fiscal_code=fiscal_code;
		this.date=date;
	}
	
	//Metodat get dhe set te atributeve te modelit Invoice
	public long getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(long invoice_id) {
		this.invoice_id = invoice_id;
	}
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public String getFiscal_code() {
		return fiscal_code;
	}
	public void setFiscal_code(String fiscal_code) {
		this.fiscal_code = fiscal_code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
