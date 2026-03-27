package poo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.enums.Status;
import paymentType.Charge;
import paymentType.Credit;
import paymentType.Debit;
import paymentType.Pix;
import paymentType.Ticket;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		System.out.println("Welcome to bank, how many debts do you have?");
		int n = sc.nextInt();
		
	
		
	
		sc.nextLine();
		List<Charge> chList = new ArrayList<>();
		for(int i = 1;i < n+1 ; i++) {
			
			System.out.println("Enter indentifier of debt: ");
			String identifier = sc.nextLine();
			System.out.println("Enter the value of debt payment: ");
			Double value = sc.nextDouble();
			System.out.println("Enter status of debt(Pending_Payment,Processing,Paid,Cancelled) \n");
			sc.nextLine();
			String chars = sc.nextLine();
			Status status;
			if(chars.equals("Pending_Payment")) {
				status = Status.PEDING_PAYMENT;
			}else if(chars.equals("Processing")) {
				status = Status.PROCESSING;
				
			}else if(chars.equals("Paid")) {
				status = Status.PAID;
			}else if(chars.equals("Cancelled")) {
				status = Status.CANCELED;
			}else {
				throw new IllegalArgumentException("Status invalid");
			}
					
			ArrayList<LocalDate> dateList = new ArrayList<>(); 
				System.out.println("How many relevant dates do you have? ");
				int number = sc.nextInt();
				sc.nextLine();
				for(int l = 0 ; l < number ; l++ ) {
					System.out.println("Enter relevant dates on format dd/MM/yyyy");
					String dates = sc.nextLine();
					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate data = LocalDate.parse(dates,fmt);
					dateList.add(data);
				}
				
			System.out.println("PAYMENT SERIVCE");
			System.out.println("Do you what type of payment do you wanna use? Credit,Debit,Pix,Ticket");
			String payment_type = sc.nextLine();
			
			
			
			if(payment_type.equals("Credit")) {
				
				Charge credit = new Credit(identifier,value,status,dateList);
			
				credit.pay();
				
				chList.add(credit);
			}else if(payment_type.equals("Debit")) {
				
				Charge debit = new Debit(identifier,value,status,dateList);
			
				debit.pay();
				
				chList.add(debit);
				
			}else if(payment_type.equals("Pix")) {
				Pix pix = new Pix(identifier,value,status,dateList);
				System.out.println("Enter the Token of payment ");
			
				pix.generateToken();
				pix.getToken();
				Integer token = sc.nextInt();
				pix.pay(token);
				
				Charge chPix = pix;
				chList.add(chPix);
			}else if(payment_type.equals("Ticket")) {
				Ticket ticket = new Ticket(identifier,value,status,dateList);
				System.out.println("Enter date payment on format (dd/MM/yyyy)");
				String date = sc.nextLine();
				
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate userdate = LocalDate.parse(date,fmt);
				
				ticket.setUserDate(userdate);
				
				ticket.pay(userdate);
				
				Charge chTicket = ticket;
				
				chList.add(chTicket);
			}else {
				throw new IllegalArgumentException("Payment type invalid");
			}
		}
		
		for(Charge ch : chList) {
			System.out.println(ch.toString());
		}
		
		
		
		
		sc.close();
		
	}

}
