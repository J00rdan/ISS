package com.example.library.Model;

public class Borrow {
    private int id;

    private int subscriberId;
    private int bookId;

    public Borrow(int id, int subscriberId, int bookId) {
        this.id = id;
        this.subscriberId = subscriberId;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", subscriberId=" + subscriberId +
                ", bookId=" + bookId +
                '}';
    }
}
