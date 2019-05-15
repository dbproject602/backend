package service;

import bean.BookBean;

import java.util.List;

public interface BookService {
    List<BookBean> fetchBookList();
    int deleteBookById(int id);
    int updateBook(BookBean bookBean);
    int addBook(BookBean bookBean);
    List<BookBean> searchBookList(String bookName,String author);
}
