package service;
import  dao.*;
import bean.*;
public class UserinfoServiceImpl implements UserinfoService {
    private UserinfoDao userinfoDao = new UserinfoDaoImpl();
    public int login(String username, String password) {
        int result = 0;
        try{
            result=userinfoDao.login(username,password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public int registerUserinfo(UserinfoBean userinfoBean) {
        int result=0;
        try{
            result=userinfoDao.registerUserinfo(userinfoBean);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
