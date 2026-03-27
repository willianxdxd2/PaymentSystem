package paymentType;

public interface PaymentService {

	public default void tax(Double value) {
		Double newValue = (value * 0.30) + value;
	}

	public boolean invalidBalance();

}
