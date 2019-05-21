package service;
import  dao.*;
import bean.*;
public class UserinfoServiceImpl implements UserinfoService {
    private UserinfoDao userinfoDao = new UserinfoDaoImpl();
    public UserinfoBean login(String username, String password) {
        UserinfoBean result = null;
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
