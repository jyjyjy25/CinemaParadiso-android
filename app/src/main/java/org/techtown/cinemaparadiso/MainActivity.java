package org.techtown.cinemaparadiso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button likeButton;
    Button dislikeButton;
    TextView likeCountView;
    TextView dislikeCountView;

    int likeCount = 15;
    int dislikeCount = 1;
    boolean likeState = false;
    boolean dislikeState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likeButton = (Button) findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(likeState) {
                    decrLikeCount();
                } else {
                    incrLikeCount();
                }

                likeState = !likeState;
            }
        });
        likeCountView = (TextView) findViewById(R.id.likeCountView);

        dislikeButton = (Button) findViewById(R.id.dislikeButton);
        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dislikeState) {
                    decrDislikeCount();
                } else {
                    incrDislikeCount();
                }

                dislikeState = !dislikeState;
            }
        });
        dislikeCountView = (TextView) findViewById(R.id.dislikeCountView);

        ListView listView = (ListView) findViewById(R.id.listView);

        CommentAdapter adapter = new CommentAdapter();
        adapter.addItem(new CommentItem("kym71**", "10", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
        adapter.addItem(new CommentItem("kym71**", "10", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));

        listView.setAdapter(adapter);

    }

    class CommentAdapter extends BaseAdapter {
        ArrayList<CommentItem> items = new ArrayList<CommentItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(CommentItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            CommentItemView Cview = null;
            if (view == null) {
                Cview = new CommentItemView(getApplicationContext());
            } else {
                Cview = (CommentItemView) view;
            }

            CommentItem item = items.get(i);
            Cview.setUser_id(item.getUser_id());
            Cview.setUser_time(item.getUser_time());
            Cview.setUser_comment(item.getUser_comment());

            return Cview;
        }


    }

    public void incrLikeCount() {
        likeCount += 1;
        likeCountView.setText(String.valueOf(likeCount));

        likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected);
        if (dislikeState) {
            decrDislikeCount();
            dislikeState = !dislikeState;
        }
    }

    public void decrLikeCount() {
        likeCount -= 1;
        likeCountView.setText(String.valueOf(likeCount));

        likeButton.setBackgroundResource(R.drawable.thumbs_up_selector);
    }

    public void incrDislikeCount() {
        dislikeCount += 1;
        dislikeCountView.setText(String.valueOf(dislikeCount));

        dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected);
        if (likeState){
            decrLikeCount();
            likeState = !likeState;
        }
    }

    public void decrDislikeCount() {
        dislikeCount -= 1;
        dislikeCountView.setText(String.valueOf(dislikeCount));

        dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down);
    }
}