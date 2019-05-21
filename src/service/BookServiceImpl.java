package service;
import dao.BookDao;
import dao.BookDaoImpl;
import bean.*;
import java.util.List;
public class BookServiceImpl implements  BookService {
    BookDao bookDao=new BookDaoImpl();
    public List<BookBean> fetchBookList() {
        List<BookBean> bookList=null;
        try{
            bookList=bookDao.fetchBookList();
        }
            catch(Exception e){
            e.printStackTrace();
        }
        return bookList;
    }
    public List<BookBean> searchBookList(String bookName,String author){
        List<BookBean> bookList=null;
        try{
            bookList=bookDao.searchBookList(bookName,author);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return bookList;
    }
    public int deleteBookById(int id) {
        int result = 0;
       try{
          result = bookDao.deleteBookById(id);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public int addBook(BookBean bookBean) {
        int result = 0;
        try{
            result = bookDao.addBook(bookBean);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public int updateBook(BookBean bookBean) {
        int result = 0;
        try{
            result = bookDao.updateBook(bookBean);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
