package com.example.pc_b_013.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    TextView t_1,t_2,t_3,t_4,t_5,t_6,t_7,t_8,t_9,t_10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t_1 = findViewById(R.id.textView);
        t_2 = findViewById(R.id.textView2);
        t_3 = findViewById(R.id.textView3);
        t_4 = findViewById(R.id.textView4);
        t_5 = findViewById(R.id.textView5);
        t_6 = findViewById(R.id.textView6);
        t_7 = findViewById(R.id.textView7);
        t_8 = findViewById(R.id.textView8);
        t_9 = findViewById(R.id.textView9);
        t_10 = findViewById(R.id.textView10);

        final Context c = getApplicationContext();
        String base_url = "http://www.programadoresperuanos.com/test_app/webservice/";
        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.post(c, base_url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200)
                {
                    try {
                        String r = new String(responseBody);
                        JSONObject respuesta = new JSONObject(r);
                        String status = respuesta.getString("status");
                        if(status.equals("success"))
                        {
                            JSONObject data = respuesta.getJSONObject("data");
                            JSONObject alumno1 = data.getJSONObject("alumno1");
                            JSONObject alumno2 = data.getJSONObject("alumno2");
                            JSONObject alumno3 = data.getJSONObject("alumno3");
                            JSONObject alumno4 = data.getJSONObject("alumno4");
                            JSONObject alumno5 = data.getJSONObject("alumno5");
                            t_1.setText(alumno1.getString("nombre"));
                            t_2.setText(alumno1.getString("telefono"));
                            t_3.setText(alumno2.getString("nombre"));
                            t_4.setText(alumno2.getString("telefono"));
                            t_5.setText(alumno3.getString("nombre"));
                            t_6.setText(alumno3.getString("telefono"));
                            t_7.setText(alumno4.getString("nombre"));
                            t_8.setText(alumno4.getString("telefono"));
                            t_9.setText(alumno5.getString("nombre"));
                            t_10.setText(alumno5.getString("telefono"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(c, "La respuesta no es JSON", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(c, "El statusCode no es 200", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(c, "Sin conexion al servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
