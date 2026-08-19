public class TestComplaintDAO {

    public static void main(String[] args) {

        ComplaintDAO dao = new ComplaintDAO();

        Complaint c = new Complaint();

        c.setUserId(1);
        c.setIssueType("No Internet");
        c.setDescription("Internet is not working");
        c.setPriority("High");
        c.setStatus("Pending");

        if (dao.addComplaint(c)) {
            System.out.println("Complaint Added Successfully!");
        } else {
            System.out.println("Failed to Add Complaint!");
        }
    }
}
