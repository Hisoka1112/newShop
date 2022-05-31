package com.example.shop1;

import java.math.BigDecimal;

public class Book {
    private int  id;
    private String name;
    private BigDecimal price;
    private String author;
    private String genre;
    private int count;

    public Book(String name, BigDecimal price, String author, String genre,int count) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.genre = genre;
        this.count=count;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Book(){

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

}
