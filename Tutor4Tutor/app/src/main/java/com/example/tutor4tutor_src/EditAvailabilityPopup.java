package com.example.tutor4tutor_src;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

/**
 * Created by slgreco on 3/4/20.
 */

public class EditAvailabilityPopup extends AppCompatActivity {

    private EditText editText;
    private Button saveText;
    private FileInputStream fis = null;
    private FileOutputStream fos = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateavailabilitypopup);

        editText = findViewById(R.id.updateAvailTextBox);
        load("abc123");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        //Set popup window size, adjusting width and height with scalar adjustment
        getWindow().setLayout((int)(width*.8), (int)(height*.8));

        saveText = findViewById(R.id.saveAvail);

        saveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save("abc123");
            }
        });

    }

    //public String reload(String uid) {} // This function will be used to get/refresh the availability text outside this window.

    protected void save(String uid) {
        String text = editText.getText().toString();
        try {
            fos = openFileOutput(uid.concat("_availability.txt"),MODE_PRIVATE);
            fos.write(text.getBytes());
            //Toast.makeText(this, "Changes have been saved!", Toast.LENGTH_LONG);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // Save updates to uid_availability.txt

    protected void load(String uid) {

        try{
            fis = openFileInput(uid.concat("_availability.txt"));

            InputStreamReader streamReader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            String text;

            while((text = bufferedReader.readLine()) != null) {
                stringBuilder.append(text).append("\n");
            }

            editText.setText(stringBuilder.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    } // Load existing uid_availability.txt
}
