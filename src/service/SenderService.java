package service;

import bean.SenderBean;

public interface SenderService {
    SenderBean login(String senderName, String password) throws Exception;
    int registerUser(SenderBean senderBean) throws Exception;
}
