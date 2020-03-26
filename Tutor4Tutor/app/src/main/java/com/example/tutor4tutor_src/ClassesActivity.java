package com.example.tutor4tutor_src;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ClassesActivity extends AppCompatActivity {

    ArrayList<String> classdata = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Take a Lecture");
        setContentView(R.layout.activity_classes);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Sprint2: Post New functionality will be added", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(ClassesActivity.this, AddNewClassActivity.class);
                intent.putExtra("classlist", classdata);
                startActivityForResult(intent, 22);
            }
        });

        Button filter = findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });

        setListView();
    }


    public void setListView()
    {
        //example of one class
        classdata.add("Let's Physics!" +"\n" + "PHY101");
        classdata.add("EASY Statistics" +"\n" + "STA303");
        classdata.add("Check Your Sentences" +"\n" + "WTR101");
        classdata.add("Business Tutor" +"\n" + "Elementary Business");

        listView = (ListView) findViewById(R.id.mList);

        adapter = new ArrayAdapter<String>(ClassesActivity.this, android.R.layout.simple_list_item_1, classdata);
        listView.setAdapter(adapter);

        //Short Click for going to a detailed page (= a Lecture List Page)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent openProfile = new Intent(ClassesActivity.this, LecturesActivity.class);
                startActivity(openProfile);
            }
        });

        //Long Click for DELETE a class
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("Delete a Class")
                        .setMessage("Are you sure to delete this class?")
                        .setNegativeButton("Cancel",null)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                //If click Delete button
                                classdata.remove(position);
                                adapter.notifyDataSetChanged();
                                Snackbar.make(view,"Class is deleted.",2000).show();
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 22)
        {
            if(resultCode == RESULT_OK)
            {
                String text = data.getStringExtra("newclass");
                classdata.add(text);
                adapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //START: a dialog for FILTER
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
    //END: a dialog for FILTER

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