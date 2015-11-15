package com.example.undine.project_ooad;

/**
 * Created by Administrator on 15/11/2558.
 */
public class Comment {
    private int commentID;
    private String description;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //private Topic topic;
    private Account account;
}
