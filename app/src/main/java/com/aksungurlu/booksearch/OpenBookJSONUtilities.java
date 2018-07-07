package com.aksungurlu.booksearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class OpenBookJSONUtilities {

    public static int getBookCount(String jsonString){
        final String keyBookCount = "numFound";

        JSONObject jsonQuery = null;
        try {
            jsonQuery = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int bookCount = 0;

        try {
            bookCount = jsonQuery.getInt(keyBookCount);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bookCount;
    }

    public static String[] getBookDetails(String jsonString) {
        final String keyBookList = "docs";
        final String keyBookTitle =  "title_suggest";

        JSONArray bookJSONArray = null;

        JSONObject jsonQuery = null;
        try {
            jsonQuery = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            bookJSONArray = jsonQuery.getJSONArray(keyBookList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] parsedBookData = new String [bookJSONArray.length()];
        for(int i = 0; i < bookJSONArray.length(); i++){
            try {
                parsedBookData[i] = bookJSONArray.getJSONObject(i).getString(keyBookTitle);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return parsedBookData;
    }

}
