package com.example.tutor4tutor_src;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
    final int REQUEST = 14;
    int number = -1;

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

        if (split_str[0].equals("General Physics 1")){
            number = 0;
        }
        else if (split_str[0].equals("Elementary Linear Algebra")){
            number = 1;
        }
        else if (split_str[0].equals("Essay Review")){
            number = 2;
        }
        else if (split_str[0].equals("JAVA basic")){
            number = 3;
        }

        setListView();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LecturesActivity.this, AddNewLectureActivity.class);
                startActivityForResult(intent, REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == REQUEST);
        {
            if(resultCode == RESULT_OK)
            {
                String titleandurl = data.getStringExtra("newlec");
                String[] split_array = titleandurl.split("\\n");
                if (split_array.length == 2) {
                    videodata.add(split_array[0]);
                    Video newlecture = new Video(split_array[0],split_array[1]);
                    videolist.add(newlecture);
                }
                adapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setListView() {

        listView = (ListView) findViewById(R.id.lecturelist);


        //instances of lectures; it will be gotten from web server according to classinfo
        if (number == 0) {
            Video v = new Video("Intro: what is physics", "https://youtu.be/fwmvF5ffmhg");
            videodata.add(v.getTitle());
            videolist.add(v);
            v = new Video("Lecture 1", "https://youtu.be/4pUc7SD0PmU");
            videodata.add(v.getTitle());
            videolist.add(v);
            v = new Video("Lecture 2", "https://youtu.be/h3aP1cxwCyk");
            videodata.add(v.getTitle());
            videolist.add(v);
        }

        else if (number == 1) {
            Video v = new Video("Lec 1: syllabus and overview", "https://youtu.be/fwmvF5ffmhg");
            videodata.add(v.getTitle());
            videolist.add(v);
            v = new Video("Lec 2", "https://youtu.be/4pUc7SD0PmU");
            videodata.add(v.getTitle());
            videolist.add(v);
            v = new Video("Lec 3", "https://youtu.be/h3aP1cxwCyk");
            videodata.add(v.getTitle());
            videolist.add(v);
        }

        else if (number == 2) {
            Video v = new Video("Intro> Welcome Session", "https://youtu.be/fwmvF5ffmhg");
            videodata.add(v.getTitle());
            videolist.add(v);
            v = new Video("Lecture 1: Peer Editing", "https://youtu.be/4pUc7SD0PmU");
            videodata.add(v.getTitle());
            videolist.add(v);
        }

        else if (number == 3) {
            Video v = new Video("[1] install & set up", "https://youtu.be/fwmvF5ffmhg");
            videodata.add(v.getTitle());
            videolist.add(v);
            v = new Video("[2] print out", "https://youtu.be/4pUc7SD0PmU");
            videodata.add(v.getTitle());
            videolist.add(v);
            v = new Video("[3] loop", "https://youtu.be/h3aP1cxwCyk");
            videodata.add(v.getTitle());
            videolist.add(v);
        }

        else {
            //nothing on list
        }


        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,videodata);
        listView.setAdapter(adapter);

        //TODO: WHEN clicking a certain video -> open this link using YOUTUBE app
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //IMPLEMENT HERE
                String u = videolist.get(position).getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(u));
                startActivity(intent);

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
