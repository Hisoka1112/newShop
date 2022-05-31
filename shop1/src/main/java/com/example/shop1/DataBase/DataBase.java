package com.example.shop1.DataBase;
import com.example.shop1.Book;
import com.example.shop1.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    public DataBase() throws SQLException {
    }

    public static void main(String[] args) throws SQLException {

    }
    public static Connection base() throws SQLException {
        String url="jdbc:mysql://localhost:3306/shop";
        String login="root";
        String password="";
        Connection data=DriverManager.getConnection(url,login,password);
        return data;
    }
    public static void AddUser(User user) throws SQLException {
        PreparedStatement a=base().prepareStatement
                ("insert into users(login,Password,country,email,gender,role)values(?, ?, ?, ?, ?,? );");
        a.setString(1, user.getLogin());
        a.setString(2, user.getPassword());
        a.setString(3, user.getCountry());
        a.setString(4, user.getEmail());
        a.setString (5, user.getGender().toString());
        a.setString(6,"user");
        a.executeUpdate();
    }
    public static boolean AddBse(String login,String password) throws SQLException {
        PreparedStatement a= base().prepareStatement("select id from users where login=? and password=?");
        a.setString(1,login);
        a.setString(2,password);
        ResultSet s=a.executeQuery();
        int count=0;
        while(s.next()) {
            count++;
        }
        if(count==1){
            return true ;
        }
        return false;

    }
    public static boolean checkBase(String login,String password) throws SQLException {
        PreparedStatement a= base().prepareStatement("select * from users where login=? and password=?;");
        int count=0;
        a.setString(1,login);
        a.setString(2,password);
        ResultSet s=a.executeQuery();
        while (s.next()){
            count++;
        }
        if(count>0){
            return false;
        }
        return true;
    }
    public static void AddBook(Book vova) throws SQLException {
        PreparedStatement a= base().prepareStatement("insert into book(name,price,author,genre,count)values(?,?,?,?,?);");
        a.setString(1,vova.getName());
        a.setBigDecimal(2,vova.getPrice());
        a.setString(3, vova.getAuthor());
        a.setString(4, vova.getGenre());
        a.setInt(5,vova.getCount());
        int s=a.executeUpdate();

    }
    public static void updateBook(BigDecimal price,int id,int count) throws SQLException {
        PreparedStatement a= base().prepareStatement("update book set price=?,count=? where id=?;");
        a.setBigDecimal(1,price);
        a.setInt(2,count);
        a.setInt(3,id);
        a.executeUpdate();
    }
    public static ArrayList<Book> ListBook() throws SQLException {
        PreparedStatement a= base().prepareStatement("select * from book;");
        ResultSet s=a.executeQuery();
        ArrayList<Book>list=new ArrayList<>();
        while(s.next()){
            Book book=new Book();
            book.setId(s.getInt(1));
            book.setName(s.getString(2));
            book.setPrice(s.getBigDecimal(3));
            book.setAuthor(s.getString(4));
            book.setGenre(s.getString(5));
            book.setCount(s.getInt(6));
            list.add(book);
        }
        return list;
    }
    public static String checkUsersRole(String login,String password) throws SQLException {
        PreparedStatement a= base().prepareStatement("select role from users where login=? and password=?");
        a.setString(1,login);
        a.setString(2,password);
        ResultSet s=a.executeQuery();
        String role="";
        while(s.next()){
            role=s.getString(1);
        }
        return role;
    }
    public static void UpdateBookCount(int id) throws SQLException {
        PreparedStatement a= base().prepareStatement("update book set count=count-1 where id=?");
        a.setInt(1,id);
        a.executeUpdate();
    }
    public static Integer getId(String login,String password) throws SQLException {
        int id=0;
        PreparedStatement a= base().prepareStatement("select id from users where login=? and password=?");
        a.setString(1,login);
        a.setString(2,password);
        ResultSet s=a.executeQuery();
        while(s.next()){
            id=s.getInt(1);
        }
        return id;
    }
    public static void BuyUser(int bookid,int UserId) throws SQLException {
        int count=0;
        PreparedStatement b= base().prepareStatement("select count from userbooks where bookid=? and UserId=?;");
        b.setInt(1,bookid);
        b.setInt(2,UserId);
        ResultSet s=b.executeQuery();
        while(s.next()){
            count=s.getInt(1);
        }
        if(count>0){
            PreparedStatement w= base().prepareStatement("update userbooks set Count=Count+1 where bookid=? and UserId=?;");
            w.setInt(1,bookid);
            w.setInt(2,UserId);
            w.executeUpdate();
        }
        else{
            PreparedStatement t= base().prepareStatement("insert into userbooks (bookId,UserId,Count)values(?, ?, ?);");
            t.setInt(1,bookid);
            t.setInt(2,UserId);
            t.setInt(3,1);
            t.executeUpdate();
        }
    }
    public static ArrayList<Book> GetBooksByUser(int UserId) throws SQLException {
        ArrayList<Book> chel=new ArrayList<>();
        PreparedStatement q= base().prepareStatement("select b.id,b.name,b.price,b.author,b.genre,ub.Count from userbooks as ub inner join book as b on b.id=ub.bookId where UserId=?;");
        q.setInt(1,UserId);
        ResultSet s=q.executeQuery();
        while(s.next()){
            Book book=new Book();
            book.setId(s.getInt(1));
            book.setName(s.getString(2));
            book.setPrice(s.getBigDecimal(3));
            book.setAuthor(s.getString(4));
            book.setGenre(s.getNString(5));
            book.setCount(s.getInt(6));
            chel.add(book);
        }
        return chel;
    }
    public static  BigDecimal PriceRedact(int id) throws SQLException {
        PreparedStatement a= base().prepareStatement("select price from book where id=?;");
        a.setInt(1,id);
        BigDecimal price = null;
        ResultSet s=a.executeQuery();
        while(s.next()){
        price=s.getBigDecimal(1);
        }
        return price;
    }
    public static void deleteUserBook(int bookId) throws SQLException {
     PreparedStatement a= base().prepareStatement("delete from userbooks where bookId=?");
     a.setInt(1,bookId);
     a.executeUpdate();

     PreparedStatement b= base().prepareStatement("delete from  book  where id=?");
     b.setInt(1,bookId);
     b.executeUpdate();
    }

    public static void addMoney(BigDecimal money,int id) throws SQLException {
        PreparedStatement a= base().prepareStatement("update users set money=money+? where id=?");
        a.setBigDecimal(1,money);
        a.setInt(2,id);
        a.executeUpdate();
    }
    public static BigDecimal shopMoney(int id) throws SQLException {
        PreparedStatement a= base().prepareStatement("select money from users where id=?");
        BigDecimal money=null;
        a.setInt(1,id);
       ResultSet s= a.executeQuery();
       while(s.next()){
           money=s.getBigDecimal(1);
       }
       return money;
    }
    public static void deleteMoney(BigDecimal buy,int id) throws SQLException {
        PreparedStatement a= base().prepareStatement("update users set money=money-? where id=?;");
        a.setBigDecimal(1,buy);
        a.setInt(2,id);
        a.executeUpdate();
    }

}
