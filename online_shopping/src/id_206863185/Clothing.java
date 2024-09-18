package id_206863185;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Clothing extends Product implements Serializable {
    private static final long serialVersionUID = -4570215886637363909L; 

    private String color;
    private String size;
    private Gender gender;

    public Clothing(int id, String productName, int quantity, String color, String size, Gender gender, String category,
            LocalDateTime timeStamp) {
        super(id, productName, quantity, category, timeStamp);
        this.color = color;
        this.size = size;
        this.gender = gender;
    }

    public enum Gender {
        M, F
    }

    @Override
    public String toString() {
        return super.toString() + ", color: " + color + ", size: " + size + ", gender: " + gender;
    }
}