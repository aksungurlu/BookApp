package com.aksungurlu.booksearch;

import org.json.JSONException;
import org.json.JSONObject;

public final class OpenBookJSONUtilities {

    public static int getBookCount(String jsonString){
        final String numFound = "numFound";
        JSONObject jsonQuery = null;
        try {
            jsonQuery = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int bookCount = 0;

        try {
            bookCount = jsonQuery.getInt(numFound);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bookCount;
    }
}
