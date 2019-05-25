package dao;

import bean.SenderBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SenderDaoImpl implements SenderDao {
    Connection connection = null;
    DBUtil dbutil = new DBUtil();
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    private ArrayList<Integer> getItems(String sql) throws Exception{
        ArrayList<Integer> list = new ArrayList<Integer>();

        Connection connection = dbutil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            list.add(resultSet.getInt(1));
        }

        return list;
    }

    public SenderBean fetchSender(int senderId) throws Exception {
        SenderBean result = null;
        connection = dbutil.getConnection();
        String sql = "select * from senders where senderId = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, String.valueOf(senderId));
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            ArrayList<Integer> shopIdItems =
                    getItems(
                            "select shopid from shops_senders ss where ss.senderid = "
                                    + String.valueOf(resultSet.getInt("senderid"))
                    );
            result = new SenderBean(
                    resultSet.getInt("senderid"),
                    resultSet.getString("sendername"),
                    resultSet.getString("password"),
                    resultSet.getInt("state"),
                    resultSet.getString("telephone"),
                    resultSet.getDouble("reputation"),
                    shopIdItems
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
}
