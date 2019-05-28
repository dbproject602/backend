package service;
import bean.*;
public interface UserService {
    UserBean login(String username, String password) throws Exception;
    int updateUser(UserBean userBean) throws Exception;
    int registerUser(UserBean userBean) throws Exception;
}
