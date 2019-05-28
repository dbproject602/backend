package service;

import bean.SenderBean;
import dao.*;

public class SenderServiceImpl implements SenderService{

    private SenderDao senderDao = new SenderDaoImpl();

    public SenderBean login(String senderName, String password) throws Exception {
        SenderBean senderBean = null;
        senderBean = senderDao.fetchSender(senderName, password);
        return senderBean;
    }

    public int registerUser(SenderBean senderBean) throws Exception {
        int result = 0;
        result = senderDao.addSender(senderBean);
        return result;
    }
}
