package dfb.com.greendaoexample.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.List;

import dfb.com.greendaoexample.App;
import dfb.com.greendaoexample.R;
import dfb.com.greendaoexample.model.Activity;
import dfb.com.greendaoexample.model.ActivityDao;
import dfb.com.greendaoexample.model.DaoSession;
import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDao daoActivity;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoSession dao = ((App)getApplication()).getDaoSession();
        daoActivity = dao.getActivityDao();

        rv = (RecyclerView) findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        rv.setAdapter(new Adapter(MainActivity.this, daoActivity.loadAll()));

        findViewById(R.id.btn_add).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rv.setAdapter(new Adapter(MainActivity.this, daoActivity.loadAll()));
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, AddItemActivity.class));
    }

    class Adapter extends  RecyclerView.Adapter<Adapter.Holder>{
        private final List<Activity> items;
        private final Context context;

        public Adapter(Context context, List<Activity> items){
            this.items = items;
            this.context = context;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            Holder holder = new Holder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Activity activity = items.get(position);
            holder.lblName.setText(activity.getName());
            holder.lblDescription.setText(activity.getDescription());
            holder.lblDueDate.setText(activity.getDueDate().toString());
        }

        @Override
        public int getItemCount(){ return items.size(); }


        class Holder extends RecyclerView.ViewHolder {
            TextView lblName;
            TextView lblDescription;
            TextView lblDueDate;

            Holder(View view){
                super(view);
                lblName = (TextView) view.findViewById(R.id.lbl_name);
                lblDescription = (TextView) view.findViewById(R.id.lbl_description);
                lblDueDate = (TextView) view.findViewById(R.id.lbl_due_date);
            }
        }

    }
}
