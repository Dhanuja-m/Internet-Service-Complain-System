public class Complaint {

    private int complaintId;
    private int userId;
    private String issueType;
    private String description;
    private String priority;
    private String status;

    // Default Constructor
    public Complaint() {
    }

    // Parameterized Constructor
    public Complaint(int complaintId, int userId, String issueType,
                     String description, String priority, String status) {
        this.complaintId = complaintId;
        this.userId = userId;
        this.issueType = issueType;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    // Getters
    public int getComplaintId() {
        return complaintId;
    }

    public int getUserId() {
        return userId;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Display Complaint Details
    public void displayComplaint() {
        System.out.println("Complaint ID : " + complaintId);
        System.out.println("User ID      : " + userId);
        System.out.println("Issue Type   : " + issueType);
        System.out.println("Description  : " + description);
        System.out.println("Priority     : " + priority);
        System.out.println("Status       : " + status);
        System.out.println("-------------------------------");
    }
}