package dao;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import bean.*;
import util.DBUtil;
public class UserDaoImpl implements UserDao{
    Connection connection=null;
    DBUtil dbutil = new DBUtil();
    ResultSet resultSet =null;
    PreparedStatement preparedStatement = null;
    public UserBean fetchUser(String account, String password) throws Exception{
        UserBean result = null;
        connection = dbutil.getConnection();
        String sql = "select * from userinfo where account=? and password=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, account);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result = new UserBean(
                    resultSet.getInt("userid"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("telephone"),
                    resultSet.getString("address"),
                    resultSet.getString("name")
            );
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }


    public int addUser(UserBean userinfoBean) throws Exception {
        int result = 0;
        connection = dbutil.getConnection();
        String sql = "insert into userinfo (userid, username, password, telephone, address, name) values (?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userinfoBean.getUserId());
        preparedStatement.setString(2, userinfoBean.getUserName());
        preparedStatement.setString(3, userinfoBean.getPassword());
        preparedStatement.setString(4, userinfoBean.getTelephone());
        preparedStatement.setString(5, userinfoBean.getAddress());
        preparedStatement.setString(6, userinfoBean.getName());
        result = preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }

    public int updateUser(UserBean userBean) throws Exception {
        return 0;
    }

    public int deleteUserbyId(int userId) throws Exception {
        return 0;
    }
}
