package com.androidvigo.cloud;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    private List<MemeEntity> memes;
    private Context context;

    public Adapter(List<MemeEntity> m, Context c){
        memes=m;
        context=c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parentViewGroup, int i) {

        View rowView = LayoutInflater.from (parentViewGroup.getContext())
                .inflate(R.layout.item_meme, parentViewGroup, false);

        return new ViewHolder (rowView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final MemeEntity rowData = memes.get(position);

        viewHolder.text.setText(rowData.getTitle());
        viewHolder.emotion.setText(rowData.getEmotion());

        Ion.with(context)
                .load(rowData.getPng())
                .intoImageView(viewHolder.img);


        viewHolder.itemView.setTag(rowData);
    }


    @Override
    public int getItemCount() {

        return memes.size();
    }

    public void removeData (int position) {

        memes.remove(position);
        notifyItemRemoved(position);
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView text;
        private final TextView emotion;
        private final RoundedImageView img;

        public ViewHolder(View itemView) {
            super(itemView);

            text = (TextView) itemView.findViewById(
                    R.id.item_meme_title);
            emotion = (TextView) itemView.findViewById(
                    R.id.item_meme_emotion);
            img = (RoundedImageView) itemView.findViewById(R.id.item_meme_image);

        }
    }

}
