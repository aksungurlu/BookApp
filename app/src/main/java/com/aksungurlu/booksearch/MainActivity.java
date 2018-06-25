package com.aksungurlu.booksearch;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchEditText;
    private TextView mURLTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditText
        mSearchEditText = (EditText) findViewById(R.id.et_search);
        //URL TextView
        mURLTextView =(TextView) findViewById(R.id.tv_search_url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search){
            //Context context = MainActivity.this;
            Toast.makeText(MainActivity.this, mSearchEditText.getText(), Toast.LENGTH_LONG).show();
            mURLTextView.setText(NetworkUtils.buildUrl(mSearchEditText.getText().toString()).toString());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
