package com.procreation.filedownloaderbyurlexample_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = findViewById(R.id.btn_downliad);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTask myTask = new MyTask();
                myTask.execute();
            }
        });



    }

    public class MyTask extends AsyncTask<Void, Void, Void> {

        boolean download = true;

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                String urllll = "https://s3-us-west-2.amazonaws.com/mastercheck-files/645/inspectionForm/55cb14028301feaac95e250c3318e1ee.pdf";

                File directory = new File(getFilesDir().getPath());
                String fileName = "myPdf" + ".pdf";

                InputStream input;

                    URL url = new URL(urllll);
                    input = url.openStream();
                    OutputStream output = new FileOutputStream(new File(directory, fileName));
                    download = true;


                        byte[] buffer = new byte[4096];
                        int bytesRead = 0;
                        while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0)
                        {
                            output.write(buffer, 0, bytesRead);
                            download = true;
                        }
                        output.close();


                        input.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }
    }


}
