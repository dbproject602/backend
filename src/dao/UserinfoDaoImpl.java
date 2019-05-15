package dao;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import bean.*;
import util.DBUtil;
public class UserinfoDaoImpl implements UserinfoDao{
    Connection connection=null;
    DBUtil dbutil = new DBUtil();
    ResultSet resultSet =null;
    PreparedStatement preparedStatement = null;
    @Override
    public int login(String username, String password) throws Exception{
        int result = 0;
        connection = dbutil.getConnection();
        String sql = "select count(*) from userinfo where username=? and password=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result = resultSet.getInt(1);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }
    public int registerUserinfo(UserinfoBean userinfoBean) throws Exception {
        int result = 0;
        connection = dbutil.getConnection();
        String sql = "insert into userinfo (username, password) values (?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userinfoBean.getUsername());
        preparedStatement.setString(2, userinfoBean.getPassword());
        result = preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }
}
