package id_206863185;

public class OnlineStoreGeneralException extends Exception {

	public OnlineStoreGeneralException(String errorMessage) {
		super("Error: " + errorMessage);
	}
}
