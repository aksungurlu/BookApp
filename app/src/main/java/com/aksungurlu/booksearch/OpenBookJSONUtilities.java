package com.aksungurlu.booksearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        final String keyBookTitle =  "title";
        final String keyAuthor = "author_name";

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
            String stringAuthor = null;
            JSONArray arrayJSONAuthor = null;
            List<String> authorList = new ArrayList<String>();
            try {
                arrayJSONAuthor = bookJSONArray.getJSONObject(i).getJSONArray(keyAuthor);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(arrayJSONAuthor != null){
                for(int k = 0; k < arrayJSONAuthor.length(); k++){
                    try {
                        authorList.add(arrayJSONAuthor.getString(k));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                parsedBookData[i] += " - Author";
                if(authorList.size() > 1){
                    parsedBookData[i] += "s: ";
                    for(int k = 0; k < authorList.size(); k++){
                        parsedBookData[i] += authorList.get(k);
                        if(k < authorList.size() - 1){
                            parsedBookData[i] += ", ";
                        }
                    }
                }
                else{
                    parsedBookData[i] += ": " + authorList.get(0);
                }
            }

            //parsedBookData[i] += " - " + stringAuthor;
        }
        return parsedBookData;
    }

}
