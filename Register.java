import javax.swing.*;
import java.awt.*;

public class Register extends JFrame {

    JTextField nameField, emailField, phoneField;
    JPasswordField passwordField;

    public Register() {

        setTitle("User Registration");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("CREATE ACCOUNT");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(120, 30, 250, 40);
        panel.add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 100, 100, 30);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 100, 230, 30);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 150, 100, 30);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 150, 230, 30);
        panel.add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 200, 100, 30);
        panel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(150, 200, 230, 30);
        panel.add(phoneField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 250, 100, 30);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 250, 230, 30);
        panel.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 310, 110, 35);
        panel.add(registerButton);

        JButton loginButton = new JButton("Already have an account? Login");
        loginButton.setBounds(100, 370, 250, 35);
        panel.add(loginButton);

        registerButton.addActionListener(e -> registerUser());

        loginButton.addActionListener(e -> {
            dispose();
            new Login();
        });

        add(panel);
        setVisible(true);
    }

    private void registerUser() {

        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || email.isEmpty()
                || phone.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please fill all fields!");

            return;
        }

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setRole("Customer");

        UserDAO dao = new UserDAO();

        if (dao.registerUser(user)) {

            JOptionPane.showMessageDialog(this,
                    "Registration Successful!");

            dispose();
            new Login();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Registration Failed!");
        }
    }

    public static void main(String[] args) {
        new Register();
    }
}