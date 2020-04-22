package com.example.tutor4tutor_src;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LecturesActivity extends AppCompatActivity {

    // video data is for displaying a video title on listview
    // video list is a class list for containing the object with a video title AND url
    // THUS video data and video list should be updated simultaneously
    ArrayList<String> videodata = new ArrayList<String>();
    ArrayList<Video> videolist = new ArrayList<Video>();
    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures);

        TextView sub = (TextView) findViewById(R.id.subject_inLectures);
        TextView ins = (TextView) findViewById(R.id.instructor_inLectures);

        //get classinfo string from classActivity and split it to display classinfo on this page
        Intent intent = getIntent();
        String str = intent.getExtras().getString("classinfo"); //str: title \n subkect \n username
        String[] split_str = str.split("\\n");
        if (split_str.length == 3) {
            setTitle(split_str[0]);
            sub.setText(split_str[1]);
            ins.setText(split_str[2]);
        }

        setListView();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LecturesActivity.this, AddNewLectureActivity.class);
                startActivity(intent);
            }
        });

    }

    public void setListView() {

        listView = (ListView) findViewById(R.id.lecturelist);

        //instances of lectures; it will be gotten from web server according to classinfo
        Video v = new Video("Intro", "https://youtu.be/fwmvF5ffmhg");
        videodata.add(v.getTitle());
        videolist.add(v);
        v = new Video("Lecture 1", "https://youtu.be/4pUc7SD0PmU");
        videodata.add(v.getTitle());
        videolist.add(v);
        v = new Video("Lecture 2", "https://youtu.be/h3aP1cxwCyk");
        videodata.add(v.getTitle());
        videolist.add(v);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,videodata);
        listView.setAdapter(adapter);

        //TODO: WHEN clicking a certain video -> open this link using YOUTUBE app
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //IMPLEMENT HERE
            }
        });
        //TODO: End

        //DELETE
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {

                //Dialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setTitle("Delete a Lecture")
                        .setMessage("Are you sure to delete a lecture video?")
                        .setNegativeButton("Cancel",null)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                videodata.remove(position);
                                videolist.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .show();
                return true;
            }
        }); //DELETE END
    }
}
