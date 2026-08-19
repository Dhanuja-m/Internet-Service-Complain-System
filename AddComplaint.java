import javax.swing.*;
import java.awt.*;

public class AddComplaint extends JFrame {

    JTextField userIdField, issueField;
    JTextArea descriptionArea;
    JComboBox<String> priorityBox;

    public AddComplaint() {

        setTitle("Add Complaint");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("ADD COMPLAINT");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(150, 30, 250, 40);
        panel.add(title);

        // User ID
        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(50, 100, 100, 30);
        panel.add(userIdLabel);

        userIdField = new JTextField();
        userIdField.setBounds(170, 100, 250, 30);
        panel.add(userIdField);

        // Issue Type
        JLabel issueLabel = new JLabel("Issue Type:");
        issueLabel.setBounds(50, 150, 100, 30);
        panel.add(issueLabel);

        issueField = new JTextField();
        issueField.setBounds(170, 150, 250, 30);
        panel.add(issueField);

        // Description
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(50, 200, 100, 30);
        panel.add(descriptionLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setBounds(170, 200, 250, 100);
        panel.add(scrollPane);

        // Priority
        JLabel priorityLabel = new JLabel("Priority:");
        priorityLabel.setBounds(50, 330, 100, 30);
        panel.add(priorityLabel);

        String[] priorities = {"Low", "Medium", "High"};

        priorityBox = new JComboBox<>(priorities);
        priorityBox.setBounds(170, 330, 250, 30);
        panel.add(priorityBox);

        // Submit
        JButton submitButton = new JButton("Submit Complaint");
        submitButton.setBounds(150, 390, 180, 40);
        panel.add(submitButton);

        // Back
        JButton backButton = new JButton("Back to Dashboard");
        backButton.setBounds(150, 450, 180, 35);
        panel.add(backButton);

        // Submit Action
        submitButton.addActionListener(e -> submitComplaint());

        // Back Action
        backButton.addActionListener(e -> {
            dispose();
            new Dashboard();
        });

        add(panel);
        setVisible(true);
    }

    private void submitComplaint() {

        String userIdText = userIdField.getText();
        String issue = issueField.getText();
        String description = descriptionArea.getText();
        String priority = priorityBox.getSelectedItem().toString();

        if (userIdText.isEmpty() ||
            issue.isEmpty() ||
            description.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please fill all fields!");

            return;
        }

        try {

            int userId = Integer.parseInt(userIdText);

            Complaint complaint = new Complaint();

            complaint.setUserId(userId);
            complaint.setIssueType(issue);
            complaint.setDescription(description);
            complaint.setPriority(priority);
            complaint.setStatus("Pending");

            ComplaintDAO dao = new ComplaintDAO();

            if (dao.addComplaint(complaint)) {

                JOptionPane.showMessageDialog(this,
                        "Complaint Added Successfully!");

                dispose();
                new Dashboard();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Failed to Add Complaint!");
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "User ID must be a number!");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new AddComplaint();
    }
}
