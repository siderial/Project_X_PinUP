package com.example.undine.project_ooad;

/**
 * Created by Administrator on 3/11/2558.
 */
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Blog {
    String status;

    int count;

    @SerializedName("count_total")
    int totalCount;

    int pages;

    List<Post> posts;

    public int getCount() {
        return count;
    }

    public int getPages() {
        return pages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public String getStatus() {
        return status;
    }
}
