package com.example.tutor4tutor_src;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ClassesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Take a Lecture");
        setContentView(R.layout.activity_classes);

        //this is temp content for post
        Button button = (Button)findViewById(R.id.button2);
        button.setText("Let's PHYSICS! \nPhysics \nEllen Yang ");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LecturesActivity.class);
                startActivity(intent);
            }
        });

        button = (Button)findViewById(R.id.button);
        button.setText("You got Stat \nStatistics \nJenna Smith ");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LecturesActivity.class);
                startActivity(intent);
            }
        });

        button = (Button)findViewById(R.id.button7);
        button.setText("Advanced Marketing \nBusiness \nLui Pearce ");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LecturesActivity.class);
                startActivity(intent);
            }
        });

        button = (Button)findViewById(R.id.button3);
        button.setText("A Tutoring for Tech Report \nWriting \nEllen Yang ");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LecturesActivity.class);
                startActivity(intent);
            }
        });

        button = (Button)findViewById(R.id.button6);
        button.setText("Physics Should Go on \nPhysics \nKate Taylor ");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LecturesActivity.class);
                startActivity(intent);
            }
        });
        //temp content for post END



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Sprint2: Post New functionality will be added", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent openProfile = new Intent(ClassesActivity.this, AddNewClassActivity.class);
                startActivity(openProfile);
            }
        });

        Button filter = findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
    }

    void show() {
        final List<String> ListItems = new ArrayList<>();
        ListItems.add("Physics");
        ListItems.add("Writing");
        ListItems.add("Statistics");
        ListItems.add("Business");
        final CharSequence[] items =  ListItems.toArray(new String[ ListItems.size()]);

        final List SelectedItems  = new ArrayList();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Which Subjects Do You Find?");
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        if (isChecked) {
                            SelectedItems.add(which);
                        } else if (SelectedItems.contains(which)) {
                            SelectedItems.remove(Integer.valueOf(which));
                        }
                    }
                });
        builder.setPositiveButton("Done",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String msg="";
                        for (int i = 0; i < SelectedItems.size(); i++) {
                            int index = (int) SelectedItems.get(i);

                            msg=msg +"\n" +ListItems.get(index);
                        }
                        Toast.makeText(getApplicationContext(),
                                SelectedItems.size() +" Subjects Selected:\n"+ msg , Toast.LENGTH_LONG)
                                .show();
                    }
                });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_classes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}