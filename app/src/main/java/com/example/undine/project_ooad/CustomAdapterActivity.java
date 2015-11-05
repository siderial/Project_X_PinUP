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

import java.util.List;


public class CustomAdapterActivity extends BaseAdapter {
    private LayoutInflater mInflater;
    List<Event> mPosts;
    private ViewHolder mViewHolder;

    private Bitmap mBitmap;
    private Event mPost;
    private Activity mActivity;

    public CustomAdapterActivity(Activity activity, List<Event> posts) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mPosts = posts;
        mActivity = activity;
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

        /*if (mPost.getThumbnail() != null) {

            *//*new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        URL url = new URL(mPost.getThumbnail());
                        mBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    } catch (MalformedURLException e) {

                    } catch (IOException e) {

                    }
                    return null;
                }
            }.execute();*//*


            mViewHolder.thumbnail.setImageBitmap(mBitmap);
        }
        Picasso.with(mActivity)
            .load(mPost.getThumbnail())
            .into(mViewHolder.thumbnail);
*/

        mViewHolder.title.setText(mPost.getNameTitle());
        mViewHolder.author.setText(mPost.getStartDate()+" to "+mPost.getEndDate());
        //mViewHolder.date.setText(mPost.getStartDateString());

        return convertView;
    }

    private static class ViewHolder {
        ImageView thumbnail;
        TextView title;
        TextView author;
        TextView date;
    }
}


