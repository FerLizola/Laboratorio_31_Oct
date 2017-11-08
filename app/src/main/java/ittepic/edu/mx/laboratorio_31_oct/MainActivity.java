package ittepic.edu.mx.laboratorio_31_oct;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView txt_tablas;
    Button btn_calcular;
    EditText edt_valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_calcular=(Button)findViewById(R.id.btn_Calcular);
        edt_valor=(EditText)findViewById(R.id.edt_limite);
        txt_tablas=(TextView)findViewById(R.id.txt_tablas);

        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTarea hilo=new AsyncTarea(txt_tablas,Integer.parseInt(String.valueOf(edt_valor.getText())));
                hilo.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
    }


    private class  AsyncTarea extends AsyncTask<Integer, Integer,Boolean> {
        TextView txt_tabla;
        int limite;
        public AsyncTarea(TextView tabla, int limite){
            txt_tabla=tabla;
            this.limite=limite;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(Integer... params) {

            for (int i=1; i<=10; i++){

                publishProgress(i);
                UnSegundo();
                if (isCancelled()){
                    break;
                }
            }
            return true;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            //Actualizar la barra de progreso

            txt_tabla.setText(txt_tabla.getText()+" "+limite+" * "+values[0].intValue()+" = "+ (limite*values[0].intValue())+"\n");

        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            //super.onPostExecute(aVoid);

            if (aVoid){
                Toast.makeText(getApplicationContext(),"Tarea finaliza AsyncTask",Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();

            Toast.makeText(getApplicationContext(),"Tarea NO finaliza AsyncTask",Toast.LENGTH_SHORT).show();

        }
        private void UnSegundo() {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
