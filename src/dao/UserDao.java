package dao;
import bean.*;
public interface UserDao {
    UserBean login(String account, String password) throws Exception;
    int registerUser(UserBean userinfoBean) throws Exception;
}
