package dao;
import bean.*;
public interface UserinfoDao {
    int login(String username, String password) throws Exception;
    int registerUserinfo(UserinfoBean userinfoBean) throws Exception;
}
