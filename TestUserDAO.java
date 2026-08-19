public class TestUserDAO {

    public static void main(String[] args) {

        UserDAO dao = new UserDAO();

        User user = new User();

        user.setName("Dhanuja");
        user.setEmail("dhanuja@gmail.com");
        user.setPhone("6238738638");
        user.setPassword("12345");
        user.setRole("Customer");

        boolean result = dao.registerUser(user);

        if (result) {
            System.out.println("User Registered Successfully!");
        } else {
            System.out.println("Registration Failed!");
        }
    }
}