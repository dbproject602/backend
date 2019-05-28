package dao;


import bean.SenderBean;

public interface SenderDao {
    SenderBean fetchSender(String senderName, String password) throws Exception;
    int deleteSenderById(int senderId) throws Exception;
    int updateSender(SenderBean senderBean) throws Exception;
    int addSender(SenderBean senderBean) throws  Exception;
    int fetchAvailSenderId() throws Exception;
    void recoverSenderById(int senderid)throws Exception;
}
