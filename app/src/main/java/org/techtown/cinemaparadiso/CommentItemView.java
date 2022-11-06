package org.techtown.cinemaparadiso;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CommentItemView extends LinearLayout {
    TextView user_id;
    TextView user_time;
    TextView user_comment;

    public CommentItemView(Context context) {
        super(context);

        init(context);
    }

    public CommentItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item_view, this, true);

        user_id = (TextView) findViewById(R.id.user_id);
        user_time = (TextView) findViewById(R.id.user_time);
        user_comment = (TextView) findViewById(R.id.user_comment);
    }

    public void setUser_id(String user_id) {
        this.user_id.setText(user_id);
    }

    public void setUser_time(String user_time) {
        this.user_time.setText(user_time);
    }

    public void setUser_comment(String user_comment) {
        this.user_comment.setText(user_comment);
    }
}
