package librarysystem;

class TypeMenu {

    static Information call() {
        int choice = 0;
        Information currentUser = null;

        do {
            System.out.println("======Selection Menu======");
            System.out.println("\tPlease select your Status:");

            System.out.println("1. Guest");
            System.out.println("2. Student");
            System.out.println("3. Admin");
                
            int selectChoice = Main.input.nextInt();
            choice = selectChoice;

            Main.input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Guest chosen");
                    //Sets information automatically due to being a guest
                    currentUser = new Information("Guest User", "Guest", 1);
                    break;

                    case 2:
                        System.out.println("Student Chosen");
                        //Call studentDetails()
                        currentUser = PersonalDetails.studentDetails();
                        break;

                    case 3:
                        System.out.println("Admin Chosen");
                        currentUser = PersonalDetails.adminDetails();
                        break;

                    default:
                        System.out.println("Invalid choice!");
            }
        } while (choice != 1&& choice != 2&& choice != 3);
        if (currentUser != null) {
            System.out.println("Logged in as: "+ currentUser.name);
        }
        return currentUser;
    }
}