import java.sql.*;
import java.util.ArrayList;

public class ComplaintDAO {

    Connection con;

    public ComplaintDAO() {
        con = DBConnection.getConnection();
    }

    // Add Complaint
    public boolean addComplaint(Complaint complaint) {

        String sql = "INSERT INTO complaints(user_id, issue_type, description, priority, status) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, complaint.getUserId());
            ps.setString(2, complaint.getIssueType());
            ps.setString(3, complaint.getDescription());
            ps.setString(4, complaint.getPriority());
            ps.setString(5, complaint.getStatus());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // View All Complaints
    public ArrayList<Complaint> getAllComplaints() {

        ArrayList<Complaint> list = new ArrayList<>();

        String sql = "SELECT * FROM complaints";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Complaint c = new Complaint();

                c.setComplaintId(rs.getInt("complaint_id"));
                c.setUserId(rs.getInt("user_id"));
                c.setIssueType(rs.getString("issue_type"));
                c.setDescription(rs.getString("description"));
                c.setPriority(rs.getString("priority"));
                c.setStatus(rs.getString("status"));

                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Search Complaint
    public Complaint searchComplaint(int id) {

        String sql = "SELECT * FROM complaints WHERE complaint_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Complaint c = new Complaint();

                c.setComplaintId(rs.getInt("complaint_id"));
                c.setUserId(rs.getInt("user_id"));
                c.setIssueType(rs.getString("issue_type"));
                c.setDescription(rs.getString("description"));
                c.setPriority(rs.getString("priority"));
                c.setStatus(rs.getString("status"));

                return c;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update Complaint Status
    public boolean updateStatus(int complaintId, String status) {

        String sql = "UPDATE complaints SET status=? WHERE complaint_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, complaintId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete Complaint
    public boolean deleteComplaint(int complaintId) {

        String sql = "DELETE FROM complaints WHERE complaint_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, complaintId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}