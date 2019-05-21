package service;
import bean.*;
public interface UserinfoService {
    UserinfoBean login(String username, String password);
    int registerUserinfo(UserinfoBean userinfoBean);
}
