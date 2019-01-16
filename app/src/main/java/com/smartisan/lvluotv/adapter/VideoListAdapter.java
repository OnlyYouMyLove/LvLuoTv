package com.smartisan.lvluotv.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smartisan.lvluotv.R;
import com.smartisan.netlibrary.entity.response.VideoInfo;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class VideoListAdapter extends SuperAdapter<VideoInfo> {


    public VideoListAdapter(Context context, List<VideoInfo> items) {
        super(context, items, R.layout.item_video_list);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, VideoInfo item) {
        ImageView view = holder.getView(R.id.list_item_btn);
        Glide.with(getContext()).load(item.getVideoImage()).into(view);
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.xxx1);
    }
}