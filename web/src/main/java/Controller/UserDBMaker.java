/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.User;

/**
 *
 * @author simon
 */
public class UserDBMaker {
    public Connection con = null;
    
    private PreparedStatement yoyo(User user) throws IOException, SQLException{
        String addUserString = "INSERT INTO t_user (name, username, password, seclevel) VALUES(?,?,?,?);";
        PreparedStatement addUserRtnStmt = con.prepareStatement(addUserString, Statement.RETURN_GENERATED_KEYS);
        
        addUserRtnStmt.setString(1, user.getFirstname());
        addUserRtnStmt.setString(2, user.getUsername());
        addUserRtnStmt.setString(3, " "); //??????????
        addUserRtnStmt.setInt(4, 0);    //??????????
        return addUserRtnStmt;
    }
}

/*
    public PreparedStatement addBook(Book book) throws IOException, SQLException {
        // int bookId = -1;
        String sql = "INSERT INTO t_book (ISBN, Title, Published,OverallScore,Genre) VALUES(?,?,?,?,?);";
        PreparedStatement addBookRtnStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        // allStmts.add(addBookStmt);
        addBookRtnStmt.setString(1, book.getIsbn());
        addBookRtnStmt.setString(2, book.getTitle());
        addBookRtnStmt.setDate(3, (java.sql.Date)book.getPublished());
        addBookRtnStmt.setInt(4, book.getScore());
        addBookRtnStmt.setString(5, book.getGenre().name());
//        addBookStmt.executeUpdate();
//        ResultSet rs = addBookStmt.getGeneratedKeys();
//        if (rs.next()) {
//            bookId = rs.getInt(1);
//        }
        return addBookRtnStmt;
    }


    public void addFullEntry(Book book, User user) throws IOException, SQLException {
        List<Author> authors = book.getAuthors();
        PreparedStatement addBookStmt = null;
        PreparedStatement updateTAddedBookStmt = null;
        PreparedStatement addAuthors = null;
        PreparedStatement addBookToAuthor = null;
        PreparedStatement updateTAddedAuthorStmt = null;
        int bookID = -1;
        try {
            con.setAutoCommit(false);
            try {
                addBookStmt = addBook(book);
                addBookStmt.executeUpdate();
                ResultSet rsBook = addBookStmt.getGeneratedKeys();
                if (rsBook.next()) {
                    bookID = rsBook.getInt(1);
                }
                updateTAddedBookStmt = updateTableAddedBooks(bookID, /*user.getUserID() -1);
                updateTAddedBookStmt.executeUpdate();

                for (Author a : authors) {
                    try {
                        int authorID = -1;
                        addAuthors = addAuthor(a);
                        addAuthors.executeUpdate();
                        ResultSet rs = addAuthors.getGeneratedKeys();
                        while (rs.next()) {
                            authorID = rs.getInt(1);
                        }
                        addBookToAuthor = connectBookToAuthor(bookID, authorID);
                        addBookToAuthor.executeUpdate();
                        updateTAddedAuthorStmt = updateTableAddedAuthors(authorID, /*user.getUserID() -1);
                        updateTAddedAuthorStmt.executeUpdate();
                    } finally {
                        if (addAuthors != null) {
                            addAuthors.close();
                        }
                        if (addBookToAuthor != null) {
                            addBookToAuthor.close();
                        }
                        if (updateTAddedAuthorStmt != null) {
                            updateTAddedAuthorStmt.close();
                        }
                    }
                }
            } finally {
                if (updateTAddedBookStmt != null) {
                    updateTAddedBookStmt.close();
                }
                if (addBookStmt != null) {
                    addBookStmt.close();
                }
            }
            con.commit();
        } catch (Exception e) {
            if (con != null) {
                con.rollback();
            }
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }











    public List<Book> getAllBooks() throws IOException, SQLException {
        String sql = "SELECT * FROM T_Book";
        getBooksStmt = con.prepareStatement(sql);
        allStmts.add(getBooksStmt);
        ResultSet rs = getBooksStmt.executeQuery();
        List<Book> allBooks = new ArrayList<>();
        while (rs.next()) {
            int bookId = rs.getInt("BookID");
            String isbnString = rs.getString("ISBN");
            String title = rs.getString("Title");
            Date published = rs.getDate("published");
            Genre genre = Genre.valueOf(rs.getString("Genre"));
            Integer score = rs.getInt("OverallScore");
            String storyline = rs.getString("StoryLine");
            Book book = new Book(bookId, isbnString, title, published, genre, score);
            book.setStoryLine(storyline);
            ArrayList<Author> authors = getAuthors(bookId);
            for (Author a : authors) {
                book.addAuthor(a);
            }
            allBooks.add(book);
        }
        return allBooks;
    }













USE db_bookstore;

CREATE TABLE IF NOT EXISTS T_Book(
BookID INT NOT NULL auto_increment primary key,
ISBN VARCHAR(15) NOT NULL,
Title VARCHAR(250) NOT NULL,
OverallScore INT(5),
Published DATE NOT NULL,
StoryLine LONGTEXT,
Genre ENUM('FANTASY', 'SCI-FI', 'THRILLER', 'HORROR', 'ROMANCE', 'CRIME', 'BIOGRAPHY', 'YOUNG ADULT', 'PROGRAMMING')
);

CREATE TABLE IF NOT EXISTS T_Author(
AuthorID INT NOT NULL auto_increment primary key,
firstName varchar(250) not null,
lastName varchar(250) not null,
dateOfBirth date not null
);

CREATE TABLE IF NOT EXISTS T_Created(
BookID INT NOT NULL,
AuthorID INT NOT NULL,
CONSTRAINT t_created_pk PRIMARY KEY(BookID, AuthorID),
CONSTRAINT t_created_bookid_fk FOREIGN KEY(BookID) REFERENCES T_Book(BookID) ON DELETE CASCADE,
CONSTRAINT t_created_authorid_fk FOREIGN KEY(AuthorID) REFERENCES T_Author(AuthorID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS T_User(
UserID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
UserName VARCHAR(250) NOT NULL UNIQUE,
Email VARCHAR(250) NOT NULL,
Pword VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS T_Review(
rDate DATE NOT NULL,
UserID INT NOT NULL,
BookID INT NOT NULL,
Score INT NOT NULL,
ReviewText LONGTEXT,
CONSTRAINT t_review_pk PRIMARY KEY(UserID, BookID),
CONSTRAINT t_review_userid_fk FOREIGN KEY(UserID) references T_User(UserID) ON DELETE CASCADE,
CONSTRAINT t_review_bookid_fk FOREIGN KEY(BookID) references T_Book(BookID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS T_AddedBooks(
UserID INT NOT NULL,
BookID INT NOT NULL,
CONSTRAINT T_AddedBooks_pk PRIMARY KEY(UserID, BookID),
CONSTRAINT T_AddedBooks_userid_fk FOREIGN KEY(UserID) references T_User(UserID) ON DELETE CASCADE,
CONSTRAINT T_AddedBooks_bookid_fk FOREIGN KEY(BookID) references T_Book(BookID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS T_AddedAuthors(
UserID INT NOT NULL,
AuthorID INT NOT NULL,
CONSTRAINT T_AddedAuthors_pk PRIMARY KEY(UserID, AuthorID),
CONSTRAINT T_AddedAuthors_userid_fk FOREIGN KEY(UserID) references T_User(UserID) ON DELETE CASCADE,
CONSTRAINT T_AddedAuthors_authorid_fk FOREIGN KEY(AuthorID) references T_Author(AuthorID) ON DELETE CASCADE
);


-----


INSERT INTO T_Book (ISBN, Title, Published, Genre) VALUES ('1',
'The Lord of the Rings: The Fellowship of the Ring', '1954-06-29','FANTASY');
INSERT INTO T_Book (ISBN, Title, Published, Genre) VALUES ('2',
'The Lord of the Rings: The Two Towers', '1954-11-11','FANTASY');
INSERT INTO T_Book (ISBN, Title, Published, Genre) VALUES ('3',
'The Lord of the Rings: The Return of the King', '1955-11-20','FANTASY');
INSERT INTO T_Book (ISBN, Title, Published, Genre) VALUES ('9',
'Harry Potter and the Philosophers Stone', '1997-06-26', 'FANTASY');

INSERT INTO T_Author (firstName, lastName, dateOfBirth) VALUES ('J.R.R.', 'Tolkien', '1892-01-03');
INSERT INTO T_Author (firstName, lastName, dateOfBirth) VALUES ('J.K.', 'Rowling', '1965-07-31');

INSERT INTO T_Created (BookID, AuthorID) Values ((SELECT Bookid from T_BOOK where BOOKID = 1),
(select AuthorID from T_author where AuthorID = 1));
INSERT INTO T_Created (BookID, AuthorID) Values ((SELECT Bookid from T_BOOK where BOOKID = 2),
(select AuthorID from T_author where AuthorID = 1));
INSERT INTO T_Created (BookID, AuthorID) Values ((SELECT Bookid from T_BOOK where BOOKID = 3),
(select AuthorID from T_author where AuthorID = 1));
INSERT INTO T_Created (BookID, AuthorID) Values ((SELECT Bookid from T_BOOK where BOOKID = 4),
(select AuthorID from T_author where AuthorID = 2));

INSERT INTO T_User (UserName, Email, Pword) VALUES ('test','test@testmail.com','test123');

*/