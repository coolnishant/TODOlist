package todolist.mytwistedidea.wordpress.com.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.name;

public class ToDoMainActivity extends AppCompatActivity {


    ArrayList<String> assignmentAL;
    ArrayList<String> dateAL;
    ArrayList<String> timeAL;
    ArrayList<String> assignmentallAL;

    DatabaseHelper MyHelpers;

    String assignment[] = new String[50];
    String date[] = new String[50];
    String time[] = new String[50];

    String assignmentall[][] = new String[50][4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent = new Intent(ToDoMainActivity.this,AddTODOActivity.class);
                startActivity(intent);

            }
        });

        refreshingData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshingData();
    }

    private void refreshingData() {
        MyHelpers = new DatabaseHelper(getApplicationContext());

        assignmentallAL = MyHelpers.getAllAssignments();

        assignmentAL = MyHelpers.getAssignmentAs();
        dateAL = MyHelpers.getAssignmentDates();
        timeAL = MyHelpers.getAssignmentTimeHour();

        int l =assignmentAL.size();
        for(int i=0;i< l;i++){
            assignment[i] = assignmentAL.get(i);
            date[i] = dateAL.get(i);
            time[i] = timeAL.get(i);
        }

        final List<HashMap<String,String>> aList = new ArrayList<>();

        for(int i = 0;i<l;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("at",assignment[i]);
            hashMap.put("de",date[i]);
            hashMap.put("te",time[i]);
            aList.add(hashMap);
        }

        String[] from = {"at","de","te"};
        int to[] = {R.id.tVassignment,R.id.tVassignmentdate,R.id.tVassignmenttime};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(),aList,R.layout.custom_lv,from,to);
        final ListView listView = (ListView) findViewById(R.id.lVToDo);
        listView.setAdapter(simpleAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_do_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
