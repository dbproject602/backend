package service;
import  dao.*;
import bean.*;
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    public UserBean login(String username, String password) throws Exception
    {
        UserBean result = null;
        try{
            result=userDao.fetchUser(username,password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public int registerUser(UserBean userBean) throws Exception {
        int result=0;
        result = userDao.addUser(userBean);
        return result;
    }
}
