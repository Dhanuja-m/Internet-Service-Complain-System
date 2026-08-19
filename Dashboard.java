import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Dashboard extends JFrame {

    JLabel totalLabel;
    JLabel pendingLabel;
    JLabel progressLabel;
    JLabel resolvedLabel;

    JTable table;
    DefaultTableModel model;

    ComplaintDAO complaintDAO;

    public Dashboard() {

        complaintDAO = new ComplaintDAO();

        setTitle("Internet Service Complaint System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        // ================= HEADER =================

        JPanel header = new JPanel();
        header.setBackground(new Color(40, 70, 120));
        header.setPreferredSize(new Dimension(1000, 80));

        JLabel title = new JLabel("INTERNET SERVICE COMPLAINT SYSTEM");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        header.add(title);

        // ================= STATISTICS =================

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(1, 4, 15, 15));
        statsPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        statsPanel.setBackground(new Color(245, 247, 250));

        totalLabel = new JLabel("0", SwingConstants.CENTER);
        pendingLabel = new JLabel("0", SwingConstants.CENTER);
        progressLabel = new JLabel("0", SwingConstants.CENTER);
        resolvedLabel = new JLabel("0", SwingConstants.CENTER);

        statsPanel.add(
                createCard("TOTAL COMPLAINTS", totalLabel));

        statsPanel.add(
                createCard("PENDING", pendingLabel));

        statsPanel.add(
                createCard("IN PROGRESS", progressLabel));

        statsPanel.add(
                createCard("RESOLVED", resolvedLabel));

        // ================= BUTTONS =================

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(
                new GridLayout(1, 6, 10, 10));

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20));

        buttonPanel.setBackground(
                new Color(245, 247, 250));

        JButton addButton =
                new JButton("Add Complaint");

        JButton viewButton =
                new JButton("View Complaints");

        JButton searchButton =
                new JButton("Search");

        JButton updateButton =
                new JButton("Update Status");

        JButton deleteButton =
                new JButton("Delete");

        JButton logoutButton =
                new JButton("Logout");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(logoutButton);

        // ================= TABLE =================

        model = new DefaultTableModel();

        model.addColumn("Complaint ID");
        model.addColumn("User ID");
        model.addColumn("Issue");
        model.addColumn("Description");
        model.addColumn("Priority");
        model.addColumn("Status");

        table = new JTable(model);

        JScrollPane scrollPane =
                new JScrollPane(table);

        // ================= BUTTON ACTIONS =================

        // ADD
        addButton.addActionListener(e -> {

            dispose();

            new AddComplaint();
        });

        // VIEW
        viewButton.addActionListener(e -> {

            loadComplaints();
        });

        // SEARCH
        searchButton.addActionListener(e -> {

            searchComplaint();
        });

        // UPDATE
        updateButton.addActionListener(e -> {

            updateStatus();
        });

        // DELETE
        deleteButton.addActionListener(e -> {

            deleteComplaint();
        });

        // LOGOUT
        logoutButton.addActionListener(e -> {

            dispose();

            new Login();
        });

        // ================= ADD COMPONENTS =================

        mainPanel.add(header, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());

        centerPanel.setBackground(
                new Color(245, 247, 250));

        centerPanel.add(statsPanel,
                BorderLayout.NORTH);

        centerPanel.add(scrollPane,
                BorderLayout.CENTER);

        centerPanel.add(buttonPanel,
                BorderLayout.SOUTH);

        mainPanel.add(centerPanel,
                BorderLayout.CENTER);

        add(mainPanel);

        // Load initial data
        loadComplaints();

        setVisible(true);
    }

    // ================= CARD =================

    private JPanel createCard(
            String title,
            JLabel valueLabel) {

        JPanel card = new JPanel(
                new BorderLayout());

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createLineBorder(
                        Color.LIGHT_GRAY));

        JLabel titleLabel =
                new JLabel(title,
                        SwingConstants.CENTER);

        titleLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        13));

        valueLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        28));

        card.add(titleLabel,
                BorderLayout.NORTH);

        card.add(valueLabel,
                BorderLayout.CENTER);

        return card;
    }

    // ================= VIEW =================

    private void loadComplaints() {

        model.setRowCount(0);

        ArrayList<Complaint> list =
                complaintDAO.getAllComplaints();

        for (Complaint c : list) {

            model.addRow(new Object[]{
                    c.getComplaintId(),
                    c.getUserId(),
                    c.getIssueType(),
                    c.getDescription(),
                    c.getPriority(),
                    c.getStatus()
            });
        }

        updateStatistics();
    }

    // ================= SEARCH =================

    private void searchComplaint() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Complaint ID:");

        if (input == null)
            return;

        try {

            int id = Integer.parseInt(input);

            Complaint c =
                    complaintDAO.searchComplaint(id);

            if (c != null) {

                model.setRowCount(0);

                model.addRow(new Object[]{
                        c.getComplaintId(),
                        c.getUserId(),
                        c.getIssueType(),
                        c.getDescription(),
                        c.getPriority(),
                        c.getStatus()
                });

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Complaint Not Found!");
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Complaint ID!");
        }
    }

    // ================= UPDATE =================

    private void updateStatus() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Complaint ID:");

        if (input == null)
            return;

        try {

            int id =
                    Integer.parseInt(input);

            String[] statuses = {
                    "Pending",
                    "In Progress",
                    "Resolved"
            };

            String status =
                    (String) JOptionPane.showInputDialog(
                            this,
                            "Select New Status:",
                            "Update Status",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            statuses,
                            statuses[0]);

            if (status == null)
                return;

            if (complaintDAO.updateStatus(
                    id, status)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Status Updated Successfully!");

                loadComplaints();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Complaint Not Found!");
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Complaint ID!");
        }
    }

    // ================= DELETE =================

    private void deleteComplaint() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Complaint ID:");

        if (input == null)
            return;

        try {

            int id =
                    Integer.parseInt(input);

            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {

                if (complaintDAO.deleteComplaint(id)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Complaint Deleted Successfully!");

                    loadComplaints();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Complaint Not Found!");
                }
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Complaint ID!");
        }
    }

    // ================= STATISTICS =================

    private void updateStatistics() {

        int total = 0;
        int pending = 0;
        int progress = 0;
        int resolved = 0;

        ArrayList<Complaint> list =
                complaintDAO.getAllComplaints();

        for (Complaint c : list) {

            total++;

            if (c.getStatus().equalsIgnoreCase("Pending"))
                pending++;

            else if (c.getStatus()
                    .equalsIgnoreCase("In Progress"))
                progress++;

            else if (c.getStatus()
                    .equalsIgnoreCase("Resolved"))
                resolved++;
        }

        totalLabel.setText(
                String.valueOf(total));

        pendingLabel.setText(
                String.valueOf(pending));

        progressLabel.setText(
                String.valueOf(progress));

        resolvedLabel.setText(
                String.valueOf(resolved));
    }

    public static void main(String[] args) {

        new Dashboard();
    }
}