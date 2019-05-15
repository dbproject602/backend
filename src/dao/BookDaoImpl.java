package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bean.BookBean;
import util.DBUtil;
import java.util.Date;
import java.text.SimpleDateFormat;
public class BookDaoImpl implements BookDao{
    DBUtil dbutil = new DBUtil();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;
    @Override
    public List<BookBean> fetchBookList() throws Exception{
        List<BookBean> bookBeanList=null;
        connection = dbutil.getConnection();
        String sql="select * from book";
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement.executeQuery();
        bookBeanList=new ArrayList<BookBean>();
        while(resultSet.next()){
            BookBean bookBean=new BookBean();
            bookBean.setId(resultSet.getInt("bookid"));
            bookBean.setBookName(resultSet.getString("bookname"));
            bookBean.setAuthor(resultSet.getString("author"));
            bookBean.setPrice(resultSet.getDouble("price"));
            bookBean.setAddingDate(resultSet.getString("dateAdded"));
            bookBeanList.add(bookBean);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return bookBeanList;
    }
    public List<BookBean> searchBookList(String bookName,String author) throws Exception{
        List<BookBean> bookBeanList = null;
        connection = dbutil.getConnection();
        if(author!=null&&!author.equals("")&&bookName!=null&&!bookName.equals("")) {
            String sql = "select * from book where author=? and bookname=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, author);
            preparedStatement.setString(2, bookName);
        }
        else if(author!=null&&!author.equals("")&&(bookName==null||bookName.equals(""))){
            String sql = "select * from book where author=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, author);
        }else if((author==null||author.equals(""))&&bookName!=null&&!bookName.equals("")) {
            String sql = "select * from book where bookname=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookName);
        }else{
            String sql = "select * from book";
            preparedStatement = connection.prepareStatement(sql);
        }
        resultSet=preparedStatement.executeQuery();
        bookBeanList = new ArrayList<BookBean>();
        while(resultSet.next()){
            BookBean bookBean=new BookBean();
            bookBean.setId(resultSet.getInt("bookid"));
            bookBean.setBookName(resultSet.getString("bookname"));
            bookBean.setAuthor(resultSet.getString("author"));
            bookBean.setPrice(resultSet.getDouble("price"));
            bookBean.setAddingDate(resultSet.getString("dateAdded"));
            bookBeanList.add(bookBean);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return bookBeanList;
    }
    public int deleteBookById(int id) throws Exception {
        int result=0;
        connection=dbutil.getConnection();
        String sql="delete from book where bookId=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        result=preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }
    public int updateBook(BookBean bookBean) throws Exception {
        int result=0;
        connection=dbutil.getConnection();
        if (bookBean.getBookName()!=null&&!bookBean.getBookName().equals("")){
            String sql="update book set bookname=? where bookId=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,bookBean.getBookName());
            preparedStatement.setInt(2, bookBean.getId());
            result=preparedStatement.executeUpdate();
        }
        if (bookBean.getAuthor()!=null&&!bookBean.getAuthor().equals("")){
            String sql="update book set author=? where bookId=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,bookBean.getAuthor());
            preparedStatement.setInt(2, bookBean.getId());
            result=preparedStatement.executeUpdate();
        }
        if (bookBean.getPrice()!=-1){
            String sql="update book set price=? where bookId=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setDouble(1,bookBean.getPrice());
            preparedStatement.setInt(2, bookBean.getId());
            result=preparedStatement.executeUpdate();
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }
    public int addBook(BookBean bookBean) throws Exception {
        int result=0;
        Date date = new Date();
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        connection=dbutil.getConnection();
        System.out.println(df1.format(date));
        String sql="INSERT INTO book (bookname,author,price,dateAdded) VALUES(?,?,?,?)";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,bookBean.getBookName());
        preparedStatement.setString(2, bookBean.getAuthor());
        preparedStatement.setDouble(3, bookBean.getPrice());
        preparedStatement.setString(4,df1.format(date));
        result=preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }
}
