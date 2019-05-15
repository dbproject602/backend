package dao;

import bean.BookBean;

import java.util.List;

public interface BookDao {
    List<BookBean> fetchBookList() throws Exception;
    int deleteBookById(int id) throws Exception;
    int updateBook(BookBean bookBean) throws Exception;
    int addBook(BookBean bookBean) throws  Exception;
    List<BookBean> searchBookList(String bookName,String author) throws Exception;
}
