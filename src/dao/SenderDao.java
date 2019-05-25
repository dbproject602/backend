package dao;


import bean.SenderBean;

public interface SenderDao {
    SenderBean fetchSender(int senderId) throws Exception;
    int deleteSenderById(int senderId) throws Exception;
    int updateSender(SenderBean senderBean) throws Exception;
    int addSender(SenderBean senderBean) throws  Exception;
}
