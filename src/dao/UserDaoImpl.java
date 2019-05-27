package dao;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import bean.*;
import util.DBUtil;
public class UserDaoImpl implements UserDao{
    private Connection connection=null;
    private DBUtil dbutil = new DBUtil();
    private ResultSet resultSet =null;
    private PreparedStatement preparedStatement = null;

    public UserBean fetchUser(String account, String password) throws Exception{
        UserBean result = null;
        connection = dbutil.getConnection();
        String sql = "select * from users where username = ?"//;
                + "and pwd = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, account);
        preparedStatement.setString(2, password);
        try {
            System.out.println("Start to get user in dao.");
            System.out.printf("input username = %s, password = %s\n", account, password);

            resultSet = preparedStatement.executeQuery();
            System.out.println("Get user resultSet in dao.");
            while (resultSet.next()) {
                result = new UserBean(
                        resultSet.getInt("userid"),
                        resultSet.getString("username"),
                        resultSet.getString("pwd"),
                        resultSet.getString("telephone"),
                        resultSet.getString("address"),
                        resultSet.getString("realname")
                );
            }

            System.out.println("result = " + (result == null ? -1 : result.toString()));
            System.out.println("Finish getting user in dao!");
        }catch (Exception e){
            System.out.println("Fail to get user in dao.");
            System.out.println(e);
            result = null;
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }


    public int addUser(UserBean userinfoBean) throws Exception {
        int result = 0;
        connection = dbutil.getConnection();
        String sql = "insert into users (username, pwd, telephone, address, realname) values (?,?,?,?,?)";
        System.out.println("Start to get user in dao.");

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userinfoBean.getUserName());
        preparedStatement.setString(2, userinfoBean.getPassword());
        preparedStatement.setString(3, userinfoBean.getTelephone());
        preparedStatement.setString(4, userinfoBean.getAddress());
        preparedStatement.setString(5, userinfoBean.getName());

        result = preparedStatement.executeUpdate();
        System.out.println("Finish adding user in dao.");
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
