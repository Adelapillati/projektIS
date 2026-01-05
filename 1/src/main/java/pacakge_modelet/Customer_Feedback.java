package pacakge_modelet;

public class Customer_Feedback {
	
	private long feedback_id;
	private long invoice_id;
	private int rate;
	private long tip_amount;
	
	//Konstruktori me parametra i modelit Customer_Feedback
	public Customer_Feedback(long feedback_id, long invoice_id, int rate, long tip_amount) {
		this.feedback_id=feedback_id;
		this.invoice_id=invoice_id;
		this.rate=rate;
		this.tip_amount=tip_amount;
	}
	
	//Metodat get dhe set te atributeve te modelit Customer_Feedback
	public long getFeedback_id() {
		return feedback_id;
	}
	public void setFeedback_id(long feedback_id) {
		this.feedback_id = feedback_id;
	}
	public long getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(long invoice_id) {
		this.invoice_id = invoice_id;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public long getTip_amount() {
		return tip_amount;
	}
	public void setTip_amount(long tip_amount) {
		this.tip_amount = tip_amount;
	}

}
