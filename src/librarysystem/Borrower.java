
package librarysystem;

public class Borrower {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String birthday;
    private String contactNumber;
    private String email;
    private String address;
    private int violations;

    public Borrower(String id, String firstName,
            String middleName, String lastName,
            String gender, String birthday,
            String contactNumber, String email,
            String address, int violations) {

        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.violations = violations;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return firstName + " " + middleName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getViolations() {
        return violations;
    }

    public void setFirstName(String value) {
        firstName = value;
    }

    public void setMiddleName(String value) {
        middleName = value;
    }

    public void setLastName(String value) {
        lastName = value;
    }

    public void setGender(String value) {
        gender = value;
    }

    public void setBirthday(String value) {
        birthday = value;
    }

    public void setContactNumber(String value) {
        contactNumber = value;
    }

    public void setEmail(String value) {
        email = value;
    }

    public void setAddress(String value) {
        address = value;
    }

    public void setViolations(int value) {
        violations = value;
    }

    public void displayDetails() {
        System.out.println("\n=== BORROWER DETAILS ===");
        System.out.println("Borrower ID: " + id);
        System.out.println("First Name: " + firstName);
        System.out.println("Middle Name: " + middleName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Gender: " + gender);
        System.out.println("Birthday: " + birthday);
        System.out.println("Contact: " + contactNumber);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
        System.out.println("Violations: " + violations);
    }
}
