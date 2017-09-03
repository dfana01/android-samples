package dfb.com.dia3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView lblCurrentNum;
    private TextView lblResult;
    private Integer number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblCurrentNum = (TextView) findViewById(R.id.lblCurrentNum);
        lblResult = (TextView) findViewById(R.id.lblResult);

        findViewById(R.id.btnStart).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText txtNum = (EditText) findViewById(R.id.txtNum);
        if(!txtNum.getText().toString().isEmpty()) {
            new PrimeNumberAsyncTask().execute(Integer.parseInt(txtNum.getText().toString()));
            number = Integer.parseInt(txtNum.getText().toString());
            return;
        }
        Toast.makeText(this, "Favor incluir indicar el numero a verificar", Toast.LENGTH_SHORT).show();
    }

    class PrimeNumberAsyncTask extends AsyncTask<Integer, Integer, Integer>{

        private int isPrime(int n) {
            if (n%2==0) return 0;
            for(int i=3;i*i<=n;i+=2) {
                if(n%i==0)
                    return 0;
            }
            return 1;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            Integer result = 0;
            for (Integer integer: integers){
                for(int i = 0; i <= integer; i++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(i,isPrime(i));
                    result = integer;
                }
            }
            return isPrime(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            lblResult.setText(null);
            lblCurrentNum.setText(null);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            lblCurrentNum.setText(values[1] == 0? String.format("\"%d\" no es primo",values[0]):String.format("\"%d\" es primo",values[0]));
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            lblResult.setText(result == 1? String.format("\"%d\" es primo",number):String.format("\"%d\" no es primo",number));
        }
    }

}



