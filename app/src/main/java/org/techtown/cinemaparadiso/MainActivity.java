package org.techtown.cinemaparadiso;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static CommentAdapter adapter;
    Button likeButton;
    Button dislikeButton;
    TextView likeCountView;
    TextView dislikeCountView;
    ListView listView;

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

        listView = (ListView) findViewById(R.id.listView);

        adapter = new CommentAdapter();
        adapter.addItem(new CommentItem("kym71**", "10", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", 5));
        adapter.addItem(new CommentItem("angel**", "15", "웃긴 내용보다는 좀 더 진지한 영화.", 4.5F));

        listView.setAdapter(adapter);

        Button write_review_btn = (Button) findViewById(R.id.write_review_button);
        write_review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCommentWriteActivity();
            }
        });

        Button view_all_btn = (Button) findViewById(R.id.view_all);
        view_all_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CommentAllViewActivity.class);
                startActivity(intent);
            }
        });

    }

    public void showCommentWriteActivity() {
        Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
        startActivityResult.launch(intent);
    }

    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        if (result != null) {
                            Intent intent = result.getData();

                            float rating = intent.getFloatExtra("rating", 1.5f);
                            String contents = intent.getStringExtra("contents");

                            // ListView에 추가
                            adapter.addItem(new CommentItem("angel**", "15", contents, rating));
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);
                        }
                    }
                }
            });

    class CommentAdapter extends BaseAdapter {
        ArrayList<CommentItem> items = new ArrayList<CommentItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(CommentItem item) {
            items.add(item);
            this.notifyDataSetChanged();
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
            Cview.setUser_rating(item.getUser_rating());

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