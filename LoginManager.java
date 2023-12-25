
import java.util.ArrayList;
import java.util.List;

public class LoginManager {

	private List<User> userList;

	public LoginManager() {
		userList = new ArrayList<>();
		userList.add(new User("shoib", "omaan")); // Sample user, replace with your own
	}

	public boolean authenticateUser(String username, String password) {
		for (User user : userList) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
}
