package paymentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import entities.enums.Status;

public class Credit  extends Charge implements PaymentService{
	
	public Credit(String identifier, Double value, Status status, ArrayList<LocalDate> relevantDates) {
		super(identifier, value, status, relevantDates);
	}
	@Override
	public void pay() {

		if(this.getIdentifier() != null && this.getStatus() == Status.PEDING_PAYMENT && this.getRelevantDates() != null && invalidBalance()) {
			System.out.println("Default debt: " + this.getDebt());
			tax(this.getDebt());
			System.out.println("Debt: " + this.getDebt() + " plus 25% Tax on credit");
			Double newvalue = this.getValue() - this.getDebt();
			this.setValue(newvalue);
			System.out.println("Debt paid Successfully!");
			System.out.println("Your balance is: " + newvalue);
		}
		
	}
	
	@Override
	public void tax(Double value) {
		Double newValue = (this.getDebt()) * 0.25 + this.getDebt();//tax of 25% for credit
		this.setDebt(newValue);
	}
	
	public Credit() {
		super();
	}

	@Override
	public boolean invalidBalance() {
		return this.getValue() > this.getDebt();
	}
	
}
