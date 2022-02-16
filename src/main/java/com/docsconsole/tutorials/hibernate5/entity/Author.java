package com.docsconsole.tutorials.hibernate5.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "AUTHOR")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTHOR_ID")
    private long prodDetailsId;

    @Column(name = "AUTHOR_NAME")
    private String authorName;

    @OneToMany(mappedBy = "bookId")
    private Set<Book> Books;

    public long getProdDetailsId() {
        return prodDetailsId;
    }

    public void setProdDetailsId(long prodDetailsId) {
        this.prodDetailsId = prodDetailsId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Set<Book> getBooks() {
        return Books;
    }

    public void setBooks(Set<Book> books) {
        Books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "prodDetailsId=" + prodDetailsId +
                ", authorName='" + authorName + '\'' +
                ", Books=" + Books +
                '}';
    }
}