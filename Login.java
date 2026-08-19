import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    JTextField emailField;
    JPasswordField passwordField;

    public Login() {

        setTitle("Login");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("LOGIN");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(180, 40, 150, 40);
        panel.add(title);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(60, 120, 100, 30);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 120, 230, 30);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 180, 100, 30);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 180, 230, 30);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 240, 100, 35);
        panel.add(loginButton);

        JButton registerButton = new JButton("Create Account");
        registerButton.setBounds(135, 300, 150, 35);
        panel.add(registerButton);

        loginButton.addActionListener(e -> loginUser());

        registerButton.addActionListener(e -> {
            dispose();
            new Register();
        });

        add(panel);
        setVisible(true);
    }

    private void loginUser() {

        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please enter email and password!");

            return;
        }

        UserDAO dao = new UserDAO();

        User user = dao.login(email, password);

        if (user != null) {

            JOptionPane.showMessageDialog(this,
                    "Login Successful!");

            dispose();

            new Dashboard();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Invalid Email or Password!");
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}