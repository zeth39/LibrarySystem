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
        super(name,status,privilege);
    }
    
    public static Information studentDetails() {
        System.out.println("======Student Information======");
        System.out.println("Please enter your name: ");
        String studentName = Main.input.nextLine();
        System.out.println("Enter your Course");
        String studentCourse = Main.input.nextLine(); 

        return new Information(studentName,studentCourse, 2);
    }
    
    public static Information adminDetails() {
        System.out.println("======Admin Information======");
        System.out.println("Please enter your name:");
        String adminName = Main.input.nextLine();
        System.out.println("Enter your Department/Role: ");
        String adminRole = Main.input.nextLine();
        return new Information(adminName,adminRole, 3);
    }
}