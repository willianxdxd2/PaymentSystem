package paymentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import entities.enums.Status;
public abstract class Charge{
	
	private String identifier;
	private Double value;
	private Status status;
	private ArrayList<LocalDate> RelevantDates = new ArrayList<>();
	
	public Double debt = (double) ThreadLocalRandom.current().nextInt(100, 1000);
	public Double getDebt() {
		return debt;
	}
	public void setDebt(Double newValue) {
		this.debt = newValue;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public ArrayList<LocalDate> getRelevantDates() {
		return RelevantDates;
	}
	public void setRelevantDates(ArrayList<LocalDate> relevantDates) {
		RelevantDates = relevantDates;
	}
	public Charge(String identifier, Double value, Status status, ArrayList<LocalDate> relevantDates) {
		super();
		this.identifier = identifier;
		this.value = value;
		this.status = status;
		RelevantDates = relevantDates;
	}
	public Charge() {
	}
	public void pay() {

		if(this.identifier != null && this.status == Status.PEDING_PAYMENT && this.RelevantDates != null && this.value > this.debt) {
			Double newvalue = value - this.getDebt();
			System.out.println("Debt paid Successfully!");
			System.out.println("Your balance is: " + newvalue);
		}
		
	}
	public String toString() {
		
		return "Identifier: " +this.getIdentifier() + " Value Payment: " + this.getValue() + " Status Type: " + this.getStatus() + " Relevant Dates: " + this.getRelevantDates(); 
	}
	public void pay(Integer token) {
		// TODO Auto-generated method stub
		
	}
	public void pay(LocalDate userdate) {
		// TODO Auto-generated method stub
		
	}
}