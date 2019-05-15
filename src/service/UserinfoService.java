package service;
import bean.*;
public interface UserinfoService {
    int login(String username, String password);
    int registerUserinfo(UserinfoBean userinfoBean);
}
