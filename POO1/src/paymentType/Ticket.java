package paymentType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import entities.enums.Status;

public class Ticket extends Charge implements PaymentService{
	
		private LocalDate UserDate;
		

		
		public Ticket(String identifier, Double value, Status status, ArrayList<LocalDate> relevantDates,
				LocalDate userDate) {
			super(identifier, value, status, relevantDates);
			UserDate = userDate;
		}
		

		public Ticket() {
			super();
		}
		
	
	public Ticket(String identifier, Double value, Status status, ArrayList<LocalDate> relevantDates) {
		super(identifier, value, status, relevantDates);
	}
	
	

	@Override
	public void pay(LocalDate userdate) {
		LocalDate initial = LocalDate.of(2026,1, 1);
		LocalDate end = LocalDate.of(2026, 12, 31);
		
		long randomDay = ThreadLocalRandom.current().nextLong(initial.toEpochDay(),end.toEpochDay() + 1);
		
		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		boolean okDate = randomDate.isBefore(userdate) || randomDate.isEqual(userdate);
		
		if(this.getIdentifier() != null && this.getStatus() == Status.PEDING_PAYMENT && this.getRelevantDates() != null && !this.getRelevantDates().isEmpty() && invalidBalance() && okDate) {
			
			
			System.out.println("Default debt: " + this.getDebt());
			tax(this.getDebt());//Default Tax
			System.out.println("Debt: " + this.getDebt() + "plus 30% Default Tax on Ticket");
		
			Double newvalue = this.getValue() - this.getDebt();
			this.setValue(newvalue);
			
			System.out.println("Debt paid Successfully!");
			System.out.println("Your balance is: " + newvalue);
		}else {
			System.out.println("Error on payment: ");
		}
		
	
	}
	public LocalDate getUserDate() {
		return UserDate;
	}


	public void setUserDate(LocalDate userDate) {
		UserDate = userDate;
	}

	
	

	@Override
	public boolean invalidBalance() {
		return this.getValue() > this.getDebt();
	}
	@Override
	public void tax(Double value) {
		Double newValue = (value * 0.30) + value;
		this.setDebt(newValue);
	}
	

	
}
