import java.util.ArrayList;
import java.util.List;

public class ManageUser {
    private final List<User> users;

    public ManageUser() {
        this.users = new ArrayList<>();
    }

    public void registerUser(String userId, String userName) {
        User newUser = new User(userId, userName);
        users.add(newUser);
    }

    public User getUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public void fineOverdueUsers() {
        for (User user : users) {
            if (user.getDaysOverdue() > 0) {
                System.out.println(user.getUserName() + " has " + user.getDaysOverdue() + " days overdue.");
            }
        }
    }

    public void banUser(String userId) {
        User user = getUserById(userId);
        if (user == null) {
            System.out.println("User with ID " + userId + " not found.");
        } else {
            user.setBanned(true);
            System.out.println(user.getUserName() + " has been banned from borrowing books.");
        }
    }

    public void banUser(User user) {
        banUser(user.getUserId());
    }
}
