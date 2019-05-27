package service;
import  dao.*;
import bean.*;
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    public UserBean login(String username, String password) throws Exception
    {
        UserBean result = null;
        try{
            System.out.println("Start to get user in service!");
            result=userDao.fetchUser(username,password);
            System.out.println("Finish getting user in service!");
        }catch(Exception e){
            System.out.println("Fail to get user in user service!");
            e.printStackTrace();
        }
        return result;
    }

    public int registerUser(UserBean userBean) throws Exception {
        int result=0;
        System.out.println("Start to add user in service!");
        result = userDao.addUser(userBean);
        System.out.println("Finish adding user in service!");
        return result;
    }
}
