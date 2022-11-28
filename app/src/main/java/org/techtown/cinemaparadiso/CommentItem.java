package org.techtown.cinemaparadiso;

public class CommentItem {

    String user_id;
    String user_time;
    String user_comment;
    float user_rating;

    public CommentItem(String user_id, String user_time, String user_comment, float user_rating) {
        this.user_id = user_id;
        this.user_time = user_time;
        this.user_comment = user_comment;
        this.user_rating = user_rating;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_time() {
        return user_time;
    }

    public void setUser_time(String user_time) {
        this.user_time = user_time;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }

    public float getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(float user_rating) {
        this.user_rating = user_rating;
    }

}
