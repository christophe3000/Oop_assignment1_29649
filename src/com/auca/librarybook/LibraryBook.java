package com.auca.librarybook;

import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.exceptions.ValidationException;

public class LibraryBook implements Identifiable, Registrable {
    private String bookId;
    private String isbn;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;

    public LibraryBook(String bookId, String isbn, String title, String author, int totalCopies, int availableCopies) {
        this.bookId=bookId;
        this.isbn=isbn;
        this.title=title;
        this.author=author;
        this.totalCopies=totalCopies;
        this.availableCopies=availableCopies;
    }

    public String getBookId(){ return bookId; }
    public void setBookId(String value){ this.bookId=value; }
    public String getIsbn(){ return isbn; }
    public void setIsbn(String value){ this.isbn=value; }
    public String getTitle(){ return title; }
    public void setTitle(String value){ this.title=value; }
    public String getAuthor(){ return author; }
    public void setAuthor(String value){ this.author=value; }
    public int getTotalCopies(){ return totalCopies; }
    public void setTotalCopies(int value){ this.totalCopies=value; }
    public int getAvailableCopies(){ return availableCopies; }
    public void setAvailableCopies(int value){ this.availableCopies=value; }

    @Override public String getId(){ return bookId; }
    @Override public String getDisplayName(){ return title; }
    @Override public boolean isActive(){ return true; }
    @Override public void validate() throws ValidationException {
        if(bookId==null || bookId.isBlank()) throw new ValidationException("bookId is required.");
        if(bookId==null || bookId.isBlank()) throw new ValidationException("bookId is required.");
        if(isbn==null || isbn.isBlank()) throw new ValidationException("isbn is required.");
        if(title==null || title.isBlank()) throw new ValidationException("title is required.");
        if(author==null || author.isBlank()) throw new ValidationException("author is required.");
        if(totalCopies<0) throw new ValidationException("totalCopies cannot be negative.");
        if(availableCopies<0) throw new ValidationException("availableCopies cannot be negative.");
    }
    @Override public void printDetails(){ System.out.println(this); }
    @Override public String getRegistrationSummary(){ return getId()+" - "+getDisplayName(); }
    @Override public String toString(){ return "LibraryBook[" + getRegistrationSummary() + "]"; }
}
