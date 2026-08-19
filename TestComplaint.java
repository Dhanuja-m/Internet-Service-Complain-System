public class TestComplaint {
    public static void main(String[] args) {

        Complaint complaint = new Complaint();

        complaint.setComplaintId(101);
        complaint.setUserId(1);
        complaint.setIssueType("No Internet");
        complaint.setDescription("Internet connection is not working.");
        complaint.setPriority("High");
        complaint.setStatus("Pending");

        complaint.displayComplaint();
    }
}