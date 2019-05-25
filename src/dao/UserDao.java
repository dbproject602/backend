package dao;
import bean.*;
public interface UserDao {
    UserBean fetchUser(String userName, String password) throws Exception;
    int addUser(UserBean userBean) throws Exception; //
    int updateUser(UserBean userBean) throws Exception;
    int deleteUserbyId(int userId) throws Exception;

}
