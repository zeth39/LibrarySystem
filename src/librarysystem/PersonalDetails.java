package librarysystem;

class Information {
    String name;
    String status;
    int privilege;

    public Information(String name, String status, int privilege) {
        this.name = name;
        this.status = status;
        this.privilege = privilege;
    }
}


class PersonalDetails extends Information {

    public PersonalDetails(String name, String status, int privilege) {
        super(name, status, privilege);
    }
    
    public static String readValidName(String prompt) {
        while (true) {
            System.out.println(prompt);
            String name = Main.input.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("This field cannot be empty.");
            } else if (name.matches("^[a-zA-Z\\s\\'-]+$")) {
                return name;
            } else {
                System.out.println("Invalid name! Only letters, spaces, hyphens, and apostrophes are allowed.");
            }
        }
    }
    
    public static Information studentDetails() {
        System.out.println("======Student Information======");
        String studentName = readValidName("Please enter your name: ");
        System.out.println("Enter your Course");
        String studentCourse = Main.input.nextLine(); 

        return new Information(studentName, studentCourse, 2);
    }
    
    public static Information adminDetails() {
        System.out.println("======Admin Information======");
        String adminName = readValidName("Please enter your name:");
        System.out.println("Enter your Department/Role: ");
        String adminRole = Main.input.nextLine();
        return new Information(adminName, adminRole, 3);
    }
}