package dao;

import bean.SenderBean;
import bean.ShopBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SenderDaoImpl implements SenderDao {
    Connection connection = null;
    DBUtil dbutil = new DBUtil();
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    public SenderBean fetchSender(String senderName, String password) throws Exception {
        SenderBean result = null;
        connection = dbutil.getConnection();
        String sql = "select * from senders where sendername = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, senderName);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            List<ShopBean> shopItems = new ArrayList<ShopBean>();
            result = new SenderBean(
                    resultSet.getInt("senderid"),
                    resultSet.getString("sendername"),
                    resultSet.getString("password"),
                    resultSet.getInt("state"),
                    resultSet.getString("telephone"),
                    resultSet.getDouble("reputation"),
                    shopItems
            );
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }

    public int deleteSenderById(int senderId) throws Exception {
        return 0;
    }

    public int updateSender(SenderBean senderBean) throws Exception {
        return 0;
    }

    public int addSender(SenderBean senderBean) throws Exception {
        int result = 0;
        connection = dbutil.getConnection();
        String sql = "insert into senders(senderid, sendername, password, state, telephone, reputation) " +
                "values (?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, senderBean.getSenderId());
        preparedStatement.setString(2, senderBean.getSenderName());
        preparedStatement.setString(3, senderBean.getPassword());
        preparedStatement.setInt(4, senderBean.getState());
        preparedStatement.setString(5, senderBean.getTelephone());
        preparedStatement.setDouble(6, senderBean.getReputation());
        result = preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }
    //用于order抓取空闲sender 不需要servlet
    public int fetchAvailSenderId() throws Exception {
        int sender = 0;
        String name = null;
        String key = null;
        connection = dbutil.getConnection();
        String sql = "select * from senders where state = 0";
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        if(resultSet!=null){
            sender = resultSet.getInt("senderid");
            name = resultSet.getString("sendername");
            key = resultSet.getString("password");
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);

        SenderBean senderbean = fetchSender(name,key);
        if(senderbean!=null){
            senderbean.setState(1);
            updateSender(senderbean);
        }

        return sender;
    }
    //用于恢复sender状态 不需要servlet
    public void recoverSenderById(int senderid)throws Exception {
        String name = null;
        String key = null;
        connection = dbutil.getConnection();
        String sql = "select * from senders where senderid = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, senderid);
        resultSet = preparedStatement.executeQuery();
        if(resultSet!=null){
            name = resultSet.getString("sendername");
            key = resultSet.getString("password");
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);

        SenderBean senderbean = fetchSender(name,key);
        senderbean.setState(0);
        updateSender(senderbean);
    }
}
