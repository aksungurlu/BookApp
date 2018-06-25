package com.aksungurlu.booksearch;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;



public class NetworkUtils {

    final static String MAIN_URL = "http://openlibrary.org/search.json";
    final static String QUERY_TITLE = "title";
    final static String QUERY_AUTHOR = "author";

    public static URL buildUrl(String bookSearchQuery) {
        Uri builtUri = Uri.parse(MAIN_URL).buildUpon()
                .appendQueryParameter(QUERY_TITLE, bookSearchQuery)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
}
