package src.Classes;

public class Customer {
    private String first_name;
    private String last_name;
    private long id;
    private int birthDate;
    private String address;
    private String phone;

    public Customer(String first_name, String last_name, long id, int birthDate, String address, String phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.id = id;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
    }
}
