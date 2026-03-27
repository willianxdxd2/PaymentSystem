package paymentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import entities.enums.Status;

public class Pix extends Charge implements PaymentService{

	
	private Integer Token;


	public void generateToken() {
		Random random = new Random();
		Integer number = 100000 + random.nextInt(900000);
		this.setToken(number);
		System.out.println("Token generated: " + number);
	}

	
	public Integer getToken() {
		return Token;
	}


	public void setToken(Integer token) {
		Token = token;
	}

	@Override
	public void pay(Integer token) {
		if(Objects.equals(this.Token, token) && this.getIdentifier() != null && this.getStatus() == Status.PEDING_PAYMENT && this.getRelevantDates() != null && !this.getRelevantDates().isEmpty()&&invalidBalance()) {
			System.out.println("Default Debt: " + this.getDebt());
			Double newvalue = this.getValue() - this.getDebt();
			System.out.println("Debt paid Successfully!");
			System.out.println("Your balance is: " + newvalue);
		}else {
			System.out.println("Error on payng debt");
		}
		//immediate payment, no tax
	}

	public Pix(String identifier, Double value, Status status, ArrayList<LocalDate> relevantDates) {
		super(identifier, value, status, relevantDates);
	}

	public Pix() {
	}


	@Override
	public boolean invalidBalance() {
	
		return this.getValue() > this.getDebt();
	}
}
