package com.aksungurlu.booksearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BookViewAdapter extends RecyclerView.Adapter<BookViewAdapter.BookViewHolder> {
    private String[] bookList;

    public class BookViewHolder extends RecyclerView.ViewHolder {
        public final TextView mBookTextView;

        public BookViewHolder(View view){
            super(view);
            mBookTextView = (TextView) view.findViewById(R.id.tv_book_item);
        }
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context  = parent.getContext();
        int layoutIdForRecyclerView = R.layout.rv_row;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForRecyclerView, parent, shouldAttachToParentImmediately);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        String bookData = bookList[position];
        holder.mBookTextView.setText(bookData);
    }

    @Override
    public int getItemCount() {
        if (null == bookList) return 0;
        return bookList.length;
    }

    public void setBookList(String[] bookListData){
        bookList = bookListData;
    }
}
