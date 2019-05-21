package dao;
import bean.*;
public interface UserinfoDao {
    UserinfoBean login(String account, String password) throws Exception;
    int registerUserinfo(UserinfoBean userinfoBean) throws Exception;
}
