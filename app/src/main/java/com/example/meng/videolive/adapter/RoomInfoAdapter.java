package com.example.meng.videolive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.meng.videolive.R;
import com.example.meng.videolive.bean.BitmapCache;
import com.example.meng.videolive.bean.RoomInfo;

import java.util.List;

/**
 * Created by 小萌神_0 on 2016/5/27.
 */
public class RoomInfoAdapter extends RecyclerView.Adapter<RoomInfoAdapter.MyViewHolder> {
    private Context context;
    private List<RoomInfo> roomInfos;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public RoomInfoAdapter(Context context, List<RoomInfo> roomInfos) {
        this.context = context;
        this.roomInfos = roomInfos;
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, new BitmapCache());
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).
                inflate(R.layout.room_info_item, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.roomSrc,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(roomInfos.get(position).getRoomSrc(), listener);

        handleClick(holder);
    }

    private void handleClick(final MyViewHolder holder) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return roomInfos.size();
    }

    public void setData(List<RoomInfo> roomInfos) {
        this.roomInfos = roomInfos;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView roomSrc;

        public MyViewHolder(View itemView) {
            super(itemView);
            roomSrc = (ImageView) itemView.findViewById(R.id.iv_room);
        }
    }
}