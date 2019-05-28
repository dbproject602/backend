package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class DBUtil {
    public Connection getConnection() throws Exception{
        Connection connection=null;
        String diverClass="com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/CS307?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//        String url="jdbc:mysql://10.21.127.125:3306/project?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String name="root";
        String password="zxccxz123";
//        String name="dingsiyi";
//        String password="dingsiyi";
        Class.forName(diverClass);
        connection=DriverManager.getConnection(url,name,password);
        return connection;
    }
    /**
     * 关􏰀数据􏰀􏰀接
     * @param connection
     * @param preparedStatement * @param resultSet
     * @throws Exception
     */
    public void closeDBResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws Exception{
        if(resultSet!=null){ try{
            resultSet.close();
        }finally{
            resultSet=null;
        }
        } if(preparedStatement!=null){
            try{
                preparedStatement.close();
            }finally{
                preparedStatement=null;
            }
        }

        if(connection!=null){
            try{
                connection.close();
            }finally{
                connection=null;
            }
        }
    }
}
