package src.Classes;

public class Customer {
    private String first_name;
    private String last_name;
    private String id;
    private String birthDate;
    private String address;
    private String phone;
    private String email;

    public Customer() {

    }

    public Customer(String first_name, String last_name, String id, String birthDate, String address, String phone, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.id = id;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getId() {
        return id;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
