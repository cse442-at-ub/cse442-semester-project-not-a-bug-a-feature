package com.example.tutor4tutor_src;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MyProfile extends AppCompatActivity {


    TextView uName;
    ImageButton uAvail,toMenu,editProfile; // ImageButton Object(s) for User Availability Menu, ...

    EditText aboutme,major,identity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //String str = "John Doe";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        aboutme = (EditText)findViewById(R.id.AboutMe);
        major =(EditText)findViewById(R.id.Major);
        identity = (EditText)findViewById(R.id.Identity);

        editProfile = (ImageButton)findViewById(R.id.editProfile_btn);
        uName = findViewById(R.id.myUsername);
        uName.setText(UserStates.INSTANCE.getUsername()); // This is a temporary name.
        AsyncTask<String, Integer, String> jsonstr = new UserProfileTask().execute("get",UserStates.INSTANCE.getUsername());
        try {
            String data = jsonstr.get();
            JSONObject jo  = new JSONObject(data);
            //Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
            aboutme.setText(jo.getString(String.valueOf(DataType.ABOUTME.ordinal())));
            //Toast.makeText(getApplicationContext(),jo.getString(String.valueOf(DataType.ABOUTME.ordinal())), Toast.LENGTH_LONG).show();
            major.setText(jo.getString(String.valueOf(DataType.MAJOR.ordinal())));
            identity.setText(jo.getString(String.valueOf(DataType.IDENTITY.ordinal())));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        uAvail = findViewById(R.id.myAvailability);
        uAvail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadUAvail = new Intent(MyProfile.this, MyAvailability.class);
                startActivity(loadUAvail);
            }
        });

        toMenu = findViewById(R.id.returnMenu);
        toMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openMenu = new Intent(MyProfile.this, MainMenu.class);
                startActivity(openMenu);
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> hm = new HashMap<String, String>();
                hm.put("Key = ClassTitle","value = yourinputvalue");
                hm.put("Key = ClassSubject","value = yourinputvalue");
                String jsonstring = new JSONObject(hm).toString();


                hm.put(String.valueOf(DataType.ABOUTME.ordinal()),aboutme.getText().toString());
                hm.put(String.valueOf(DataType.MAJOR.ordinal()),major.getText().toString());
                hm.put(String.valueOf(DataType.IDENTITY.ordinal()),identity.getText().toString());
                JSONObject jo = new JSONObject(hm);
               // List<String> st = new UserProfileTask().mysplite("yang,{\"1\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\",\"2\":\"asd\",\"3\":\"cs\\n\"}\n");
                //oast.makeText(getApplicationContext(), st.get(0), Toast.LENGTH_LONG).show();
                //String data = JSONParser.INSTANCE.dic2json(hm);
                //Map<Integer,String> obj =  JSONParser.INSTANCE.json2dic(data);
                //String test =obj.get(DataType.ABOUTME.ordinal());
                //Toast.makeText(getApplicationContext(), test, Toast.LENGTH_LONG).show();
                //List<String> s = new UserProfileTask().mysplite("yang,\"asdjhasuidh,asodjiaosj,asodijaoisdj\",,,,");
                //Toast.makeText(getApplicationContext(), jo.toString(), Toast.LENGTH_LONG).show();
                new UserProfileTask().execute("set",UserStates.INSTANCE.getUsername(),jo.toString());

            }
        });
    }
}
