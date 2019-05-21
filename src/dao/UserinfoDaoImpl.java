package dao;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import bean.*;
import util.DBUtil;
public class UserinfoDaoImpl implements UserinfoDao{
    Connection connection=null;
    DBUtil dbutil = new DBUtil();
    ResultSet resultSet =null;
    PreparedStatement preparedStatement = null;
    public UserinfoBean login(String account, String password) throws Exception{
        UserinfoBean result = null;
        connection = dbutil.getConnection();
        String sql = "select * from userinfo where account=? and password=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, account);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result = new UserinfoBean(
                    resultSet.getInt("userid"),
                    resultSet.getString("account"),
                    resultSet.getString("password"),
                    resultSet.getString("username"),
                    resultSet.getInt("phone"),
                    resultSet.getBoolean("issubscribe")
            );
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }
    public int registerUserinfo(UserinfoBean userinfoBean) throws Exception {
        int result = 0;
        connection = dbutil.getConnection();
        String sql = "insert into userinfo (account, password) values (?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userinfoBean.getAccount());
        preparedStatement.setString(2, userinfoBean.getPassword());
        result = preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }
}
