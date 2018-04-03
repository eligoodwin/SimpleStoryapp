package com.example.eligoodwin.simplestoryapp.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eligoodwin.simplestoryapp.R;
import com.example.eligoodwin.simplestoryapp.model.Page;
import com.example.eligoodwin.simplestoryapp.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {
    public static final String TAG = StoryActivity.class.getCanonicalName();
    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private String name;
    private Stack<Integer> pageStack = new Stack<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView = findViewById(R.id.storyImageView);
        storyTextView = findViewById(R.id.storyTextView);
        choice1Button = findViewById(R.id.choice1Button);
        choice2Button = findViewById(R.id.choice2Button);
        Intent intent = getIntent();

        name = intent.getStringExtra(getString(R.string.key_name));
        if(name == null || name.isEmpty()){
            name = "Friend";
        }
        story = new Story();
        Log.d(TAG, name);
        loadPage(0);

    }



    private void loadPage(int pageNumber) {
        pageStack.push(pageNumber);
        //pass the page number in that we want and then get that page
        final Page page = story.getPage(pageNumber);
        //get drawable from the resource id
        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        //set the image in page
        storyImageView.setImageDrawable(image);
        //set the text view -- this will only add the name in if the token place hodler is present
        String pageText = getString(page.getTextId());
        pageText = String.format(pageText, name);
        storyTextView.setText(pageText);
        if(page.isFinalPage()){
            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText(R.string.play_again_button_txt);
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadPage(0);
                }
            });
        }
        else{
            loadButtons(page);
        }
    }

    private void loadButtons(final Page page) {

        choice1Button.setVisibility(View.VISIBLE);
        choice1Button.setText(page.getChoice1().getTextId());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });
        choice1Button.setVisibility(View.VISIBLE);
        choice2Button.setText(page.getChoice2().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed(){
        pageStack.pop();
        //go back to the very beginning when the stack is empty
        if(pageStack.isEmpty()){
            super.onBackPressed();
        }
        //if on page 1 we go back and pop 0 and then push page 0 back on when we load the page
        else{
          loadPage(pageStack.pop());
        }
    }

}
