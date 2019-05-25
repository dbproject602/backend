package service;
import bean.*;
public interface UserService {
    UserBean login(String username, String password);
    int registerUser(UserBean userBean);
}
