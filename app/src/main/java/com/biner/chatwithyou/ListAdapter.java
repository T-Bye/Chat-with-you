package com.biner.chatwithyou;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Function
 * <p/>
 * Created by Biner on 2016/3/7.
 */
public class ListAdapter extends BaseAdapter {
    private List<ListData> listDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    public ListAdapter(List<ListData> listDatas, Context context) {
        this.listDatas = listDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        ListData data = listDatas.get(position);
        return data.getFlag();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            if (getItemViewType(position) == 0) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.right_item, null);
                holder.tv = (TextView) convertView.findViewById(R.id.tv_Right_content);
                holder.time = (TextView) convertView.findViewById(R.id.tv_Right_time);
            } else {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.left_item, null);
                holder.tv = (TextView) convertView.findViewById(R.id.tv_Left_content);
                holder.time = (TextView) convertView.findViewById(R.id.tv_Left_time);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(listDatas.get(position).getContent());
        holder.time.setText(listDatas.get(position).getTime());
        return convertView;
    }
    public final class ViewHolder {
        public TextView tv;
        public TextView time;
    }
}
