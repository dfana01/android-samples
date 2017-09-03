package dfb.com.practicarestclient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Dante on 8/5/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecycleItemViewHolder> {

    private final List<Item> items;
    private final Context context;

    public RecyclerAdapter(Context context, List<Item> items){
        this.items = items;
        this.context = context;
    }

    @Override
    public RecycleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        RecycleItemViewHolder holder = new RecycleItemViewHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecycleItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.lblName.setText(item.name);
        holder.lblDescription.setText(item.description);
        Glide.with(context)
                .load(item.imageUrl)
                .into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class RecycleItemViewHolder extends RecyclerView.ViewHolder {
        public TextView lblName;
        public TextView lblDescription;
        public ImageView ivImage;

        public RecycleItemViewHolder(View itemView) {
            super(itemView);
            lblName = (TextView) itemView.findViewById(R.id.lbl_name);
            lblDescription = (TextView) itemView.findViewById(R.id.lbl_description);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }

}