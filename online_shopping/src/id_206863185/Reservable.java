package id_206863185;

public interface Reservable {
	void reserve(int quantity, int id, String action) throws ReachedMaxAmountException;

}
