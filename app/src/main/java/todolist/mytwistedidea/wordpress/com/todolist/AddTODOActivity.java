package todolist.mytwistedidea.wordpress.com.todolist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddTODOActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tVaddtime,tVadddate;

    EditText eTassignment;

    DatabaseHelper MyHelper;

    Button bAdd;

    String ReminderDate = "",ReminderTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        initailize();

        tVadddate.setOnClickListener(this);
        tVaddtime.setOnClickListener(this);

        bAdd.setOnClickListener(this);

    }

    private void initailize() {
        tVadddate = (TextView) findViewById(R.id.tVinputassignmentdate);
        tVaddtime = (TextView) findViewById(R.id.tVinputassignmenttime);
        bAdd = (Button) findViewById(R.id.baddassignment);
        eTassignment = (EditText) findViewById(R.id.eTinputassignment);

        MyHelper = new DatabaseHelper(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.tVinputassignmentdate:

                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(AddTODOActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        ReminderDate = ("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                        tVadddate.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();

                break;
            case R.id.tVinputassignmenttime:
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddTODOActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        ReminderTime += ( selectedHour + ":" + selectedMinute);
                        tVaddtime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                break;
            case R.id.baddassignment:
                MyHelper.insertTodo(eTassignment.getText().toString(),
                        tVadddate.getText().toString(),
                        tVaddtime.getText().toString());
                Toast.makeText(this, "Added for " + tVadddate.getText().toString(), Toast.LENGTH_SHORT).show();


                break;
        }
    }
}
