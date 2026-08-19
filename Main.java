import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static UserDAO userDAO = new UserDAO();
    static ComplaintDAO complaintDAO = new ComplaintDAO();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n==================================");
            System.out.println(" Internet Service Complaint System");
            System.out.println("==================================");
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("3. Add Complaint");
            System.out.println("4. View Complaints");
            System.out.println("5. Search Complaint");
            System.out.println("6. Update Complaint Status");
            System.out.println("7. Delete Complaint");
            System.out.println("8. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    registerUser();
                    break;

                case 2:
                    loginUser();
                    break;

                case 3:
                    addComplaint();
                    break;

                case 4:
                    viewComplaints();
                    break;

                case 5:
                    searchComplaint();
                    break;

                case 6:
                    updateStatus();
                    break;

                case 7:
                    deleteComplaint();
                    break;

                case 8:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    // Register
    static void registerUser() {

        User user = new User();

        System.out.print("Name: ");
        user.setName(sc.nextLine());

        System.out.print("Email: ");
        user.setEmail(sc.nextLine());

        System.out.print("Phone: ");
        user.setPhone(sc.nextLine());

        System.out.print("Password: ");
        user.setPassword(sc.nextLine());

        user.setRole("Customer");

        if (userDAO.registerUser(user))
            System.out.println("Registration Successful!");
        else
            System.out.println("Registration Failed!");
    }

    // Login
    static void loginUser() {

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = userDAO.login(email, password);

        if (user != null) {
            System.out.println("Welcome " + user.getName());
            System.out.println("Role : " + user.getRole());
        } else {
            System.out.println("Invalid Login!");
        }
    }

    // Add Complaint
    static void addComplaint() {

        Complaint c = new Complaint();

        System.out.print("User ID: ");
        c.setUserId(sc.nextInt());
        sc.nextLine();

        System.out.print("Issue Type: ");
        c.setIssueType(sc.nextLine());

        System.out.print("Description: ");
        c.setDescription(sc.nextLine());

        System.out.print("Priority (Low/Medium/High): ");
        c.setPriority(sc.nextLine());

        c.setStatus("Pending");

        if (complaintDAO.addComplaint(c))
            System.out.println("Complaint Added Successfully!");
        else
            System.out.println("Failed to Add Complaint!");
    }

    // View Complaints
    static void viewComplaints() {

        ArrayList<Complaint> list = complaintDAO.getAllComplaints();

        if (list.isEmpty()) {
            System.out.println("No Complaints Found!");
        } else {
            for (Complaint c : list) {
                c.displayComplaint();
            }
        }
    }

    // Search Complaint
    static void searchComplaint() {

        System.out.print("Enter Complaint ID: ");
        int id = sc.nextInt();

        Complaint c = complaintDAO.searchComplaint(id);

        if (c != null)
            c.displayComplaint();
        else
            System.out.println("Complaint Not Found!");
    }

    // Update Status
    static void updateStatus() {

        System.out.print("Complaint ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("New Status: ");
        String status = sc.nextLine();

        if (complaintDAO.updateStatus(id, status))
            System.out.println("Status Updated!");
        else
            System.out.println("Update Failed!");
    }

    // Delete Complaint
    static void deleteComplaint() {

        System.out.print("Complaint ID: ");
        int id = sc.nextInt();

        if (complaintDAO.deleteComplaint(id))
            System.out.println("Complaint Deleted!");
        else
            System.out.println("Delete Failed!");
    }
}
