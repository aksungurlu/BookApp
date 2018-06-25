package com.aksungurlu.booksearch;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchEditText;
    private TextView mURLTextView;
    private TextView mResultsTextView;
    private URL searchURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EditText
        mSearchEditText = (EditText) findViewById(R.id.et_search);
        //URL TextView
        mURLTextView =(TextView) findViewById(R.id.tv_search_url);
        //search results TextView
        mResultsTextView = (TextView) findViewById(R.id.tv_search_results);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_search){
            //Toast the edit text
            //Toast.makeText(MainActivity.this, mSearchEditText.getText(), Toast.LENGTH_LONG).show();
            //get parsed URL

            makeQuery(mSearchEditText.getText().toString());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void makeQuery(String searchString){
        searchURL = NetworkUtils.buildUrl(searchString);
        mURLTextView.setText(searchURL.toString());
        new bookQuerry().execute(searchURL);
    }

    public class bookQuerry extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String searchResult = null;
            try{
                searchResult = NetworkUtils.getResponseFromHttpUrl(searchURL);
             }catch(IOException e){
                e.printStackTrace();
             }
            return searchResult;
        }

        @Override
        protected void onPostExecute(String searchResult) {
            if(searchResult != null && !searchResult.equals("")){
                mResultsTextView.setText(searchResult);
            }
            else{
                //mResultsTextView.setText("Hata");
            }
        }
    }

}
