package dfb.com.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Dante on 7/22/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecycleItemViewHolder> {

    private final String[] items;
    private final Context context;

    public RecyclerAdapter(Context context, String... items){
        this.items = items;
        this.context = context;
    }

    @Override
    public RecycleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if(2000 == viewType)
            v = LayoutInflater.from(context).inflate(R.layout.item_layout_2, parent, false);
        else
            v = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        RecycleItemViewHolder holder = new RecycleItemViewHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecycleItemViewHolder holder, int position) {
        holder.txt.setText(items[position]);
        holder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ((TextView)view).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    @Override
    public int getItemViewType(int position) {
        String s = items[position];
        if("2".equals(s))
            return 2000;
        return 1000;
    }

    public static class RecycleItemViewHolder extends ViewHolder{
        public TextView txt;
        public RecycleItemViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.txt);
        }
    }

}
