package dfb.com.greendaoexample.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

import dfb.com.greendaoexample.App;
import dfb.com.greendaoexample.R;
import dfb.com.greendaoexample.model.Activity;
import dfb.com.greendaoexample.model.ActivityDao;
import dfb.com.greendaoexample.model.DaoSession;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityDao daoActivity;

    private EditText edtName;
    private EditText edtDescription;
    private EditText edtDueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        DaoSession dao = ((App)getApplication()).getDaoSession();
        daoActivity = dao.getActivityDao();

        edtName = (EditText) findViewById(R.id.edt_name);
        edtDescription = (EditText) findViewById(R.id.edt_description);
        edtDueDate = (EditText) findViewById(R.id.edt_due_date);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_cancel)
            startActivity(new Intent(this, MainActivity.class));
        else if(view.getId() == R.id.btn_save){
            Activity activity = new Activity();
            activity.setName(edtName.getText().toString());
            activity.setDescription(edtDescription.getText().toString());
            activity.setDueDate(new Date());
            daoActivity.insert(activity);
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
