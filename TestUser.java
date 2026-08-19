public class TestUser {
    public static void main(String[] args) {

        User user = new User();

        user.setUserId(1);
        user.setName("Dhanuja");
        user.setEmail("dhanuja@gmail.com");
        user.setPhone("6238738638");
        user.setPassword("12345");
        user.setRole("Customer");

        System.out.println("User ID: " + user.getUserId());
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone: " + user.getPhone());
        System.out.println("Role: " + user.getRole());
    }
}