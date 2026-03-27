package paymentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import entities.enums.Status;

public class Debit extends Charge implements PaymentService{

	public Debit(String identifier, Double value, Status status, ArrayList<LocalDate> relevantDates) {
		super(identifier, value, status, relevantDates);
	}

	@Override
	public void tax(Double value) {
		Double newValue = (this.getDebt()* 0.10) + this.getDebt();//Tax of 10% for debit
		this.setDebt(newValue);
	}
	public Debit() {
		super();
	}

	@Override
	public boolean invalidBalance() {
		return this.getValue() > this.getDebt();
	}
	@Override
	public void pay() {

		if(this.getIdentifier() != null && this.getStatus() == Status.PEDING_PAYMENT && this.getRelevantDates() != null && invalidBalance()) {
		
			System.out.println("Default debt: " + this.getDebt());
			tax(this.getDebt());
			System.out.println("The Debt: " + this.getDebt() + " plus 10% Tax on debit");
			Double newvalue = this.getValue() - this.getDebt();
			System.out.println("Debt paid Successfully!");
			System.out.println("Your balance is: " + newvalue);
		}
		
	}
}
