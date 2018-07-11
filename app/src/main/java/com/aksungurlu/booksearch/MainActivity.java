package com.aksungurlu.booksearch;

import android.content.Context;
import android.graphics.Path;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BookViewAdapter mBookAdapter;
    private EditText mSearchEditText;
    private TextView mURLTextView;
    private TextView mResultsTextView;
    private ProgressBar mProgressBar;
    private TextView mErrorTextView;
    private URL searchURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_books);
        //EditText
        mSearchEditText = (EditText) findViewById(R.id.et_search);
        //URL TextView
        mURLTextView =(TextView) findViewById(R.id.tv_search_url);
        //search results TextView
        mResultsTextView = (TextView) findViewById(R.id.tv_search_results);
        //progress bar
        mProgressBar = (ProgressBar) findViewById(R.id.pb_loading);
        //error TextView
        mErrorTextView = (TextView) findViewById(R.id.tv_error_message);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);
        mBookAdapter = new BookViewAdapter();
        mRecyclerView.setAdapter(mBookAdapter);

        //loadBookData();
    }

    private void loadBookData(){
        mResultsTextView.setText("");
        makeQuery(mSearchEditText.getText().toString());
    }

    private void showResultView(){
        mResultsTextView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        mErrorTextView.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    private void showRecycleView(){
        mResultsTextView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        mErrorTextView.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showProgressBar(){
        mResultsTextView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        mErrorTextView.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    private void showErrorView(){
        mResultsTextView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        mErrorTextView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
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
            loadBookData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void makeQuery(String searchString){
        searchURL = NetworkUtils.buildUrl(searchString);
        mURLTextView.setText(searchURL.toString());
        new bookQuerry().execute(searchURL);
    }

    public void showBookCount(String bookCountMessage){
        Toast.makeText(this, bookCountMessage + " books are found!", Toast.LENGTH_LONG).show();
    }

    public class bookQuerry extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressBar();
        }

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
                String[] parsedBookList = OpenBookJSONUtilities.getBookDetails(searchResult);

                for(String singleBook : parsedBookList){
                    mResultsTextView.append(singleBook + "\n\n\n");
                }

                //mResultsTextView.setText(searchResult);
                showRecycleView();
                mBookAdapter.setBookList(parsedBookList);
                int bookCount = OpenBookJSONUtilities.getBookCount(searchResult);
                showBookCount(Integer.toString(bookCount));
            }
            else{
                showErrorView();
            }
        }
    }

}
