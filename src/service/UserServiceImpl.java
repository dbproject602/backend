package service;
import  dao.*;
import bean.*;
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    public UserBean login(String username, String password) {
        UserBean result = null;
        try{
            result=userDao.fetchUser(username,password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public int registerUser(UserBean userBean) {
        int result=0;

        return result;
    }
}
