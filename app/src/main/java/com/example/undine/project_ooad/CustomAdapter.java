package com.example.undine.project_ooad;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    List<Event> mPosts;
    private ViewHolder mViewHolder;


    private Bitmap mBitmap;
    private Event mPost;
    private Activity mActivity;

    //by nook 2015/11/03
    private static ArrayList<Event> listContact;
    public CustomAdapter(Context photosFragment, ArrayList<Event> results){
        listContact = results;
        mInflater = LayoutInflater.from(photosFragment);
        mPosts=results;
    }



    @Override
    public int getCount() {
        return mPosts.size();
    }

    @Override
    public Object getItem(int position) {
        return mPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.post, parent, false);
            mViewHolder = new ViewHolder();
            mViewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.post_thumbnail);
            mViewHolder.author = (TextView) convertView.findViewById(R.id.post_author);
            mViewHolder.title = (TextView) convertView.findViewById(R.id.post_title);
            mViewHolder.date = (TextView) convertView.findViewById(R.id.post_date);

            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }


        mPost = mPosts.get(position);




        mViewHolder.author.setText(mPost.getStartDate()+" to "+mPost.getEndDate());
        mViewHolder.title.setText(mPost.getNameTitle()+"");
        //mViewHolder.date.setText(mPost.getStartDateString());

        return convertView;
    }

    private static class ViewHolder{
        ImageView thumbnail;
        TextView title;
        TextView author;
        TextView date;
    }
}

