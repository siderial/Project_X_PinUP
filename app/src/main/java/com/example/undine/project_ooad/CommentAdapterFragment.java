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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15/11/2558.
 */
public class CommentAdapterFragment extends BaseAdapter {
    private LayoutInflater mInflater;
    List<Comment> mPosts;
    private ViewHolder mViewHolder;


    private Bitmap mBitmap;
    private Comment mPost;
    private Activity mActivity;

    //by nook 2015/11/03
    private static ArrayList<Comment> listContact;
    public CommentAdapterFragment(Context photosFragment, ArrayList<Comment> results){
        listContact = results;
        mInflater = LayoutInflater.from(photosFragment);
        mPosts=results;
    }

   /* public CustomAdapter(Activity activity, List<Event> posts) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mPosts = posts;
        mActivity = activity;
    }*/

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

        mViewHolder.author.setText(mPost.getAccount().getName());
        mViewHolder.title.setText(mPost.getDescription()+"");
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
