package com.example.app_healthylifestyle.chisosuckhoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.app_healthylifestyle.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Giac_ngu extends AppCompatActivity {
    Button check;
    TextView loikhuyen,txtNgu,txtDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giac_ngu);
        //ánh xạ
        txtNgu = findViewById(R.id.txt_ngu);
        txtDay = findViewById(R.id.txt_day);
        check = findViewById(R.id.bnt_check);
        loikhuyen = findViewById(R.id.txt_loikhuyen);
        //Ngủ
        txtNgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialogNgu();
            }
        });
        //Dậy
        txtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialogDay();
            }
        });
        // Kiểm tra
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSleepDuration();
            }
        });
    }
//show time day
    private void showTimeDialogDay() {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(Giac_ngu.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String s=hourOfDay +":"+minute;
                int hourTam = hourOfDay;
                if(hourTam>12)
                    hourTam=hourTam-12;
                txtDay.setText(hourTam +":"+minute +(hourOfDay>12?" Tối":" Sáng"));
                //lưu giờ thực vào tag
                txtDay.setTag(s);
            }
        },hour, minute, true);
        timePickerDialog.show();
    }
    //time ngu
    private void showTimeDialogNgu() {
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(Giac_ngu.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String s=hourOfDay +":"+minute;
                int hourTam = hourOfDay;
                if(hourTam>12)
                    hourTam=hourTam-12;
                txtNgu.setText(hourTam +":"+minute +(hourOfDay>12?" Tối":" Sáng"));
                //lưu giờ thực vào tag
                txtNgu.setTag(s);
            }
        },hour, minute, true);
        timePickerDialog.show();
    }

    private void checkSleepDuration() {
        if (txtNgu.getTag() != null && txtDay.getTag() != null) {
            String[] nguTime = ((String) txtNgu.getTag()).split(":");
            String[] dayTime = ((String) txtDay.getTag()).split(":");

            int nguHour = Integer.parseInt(nguTime[0]);
            int nguMinute = Integer.parseInt(nguTime[1]);
            int dayHour = Integer.parseInt(dayTime[0]);
            int dayMinute = Integer.parseInt(dayTime[1]);

            Calendar nguCalendar = Calendar.getInstance();
            nguCalendar.set(Calendar.HOUR_OF_DAY, nguHour);
            nguCalendar.set(Calendar.MINUTE, nguMinute);

            Calendar dayCalendar = Calendar.getInstance();
            dayCalendar.set(Calendar.HOUR_OF_DAY, dayHour);
            dayCalendar.set(Calendar.MINUTE, dayMinute);

            if (dayCalendar.before(nguCalendar)) {
                dayCalendar.add(Calendar.DAY_OF_MONTH, 1);
            }

            long durationInMillis = dayCalendar.getTimeInMillis() - nguCalendar.getTimeInMillis();
            long hours = TimeUnit.MILLISECONDS.toHours(durationInMillis);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(durationInMillis) % 60;

            String advice = getSleepAdvice(hours);
            loikhuyen.setText("Bạn đã ngủ " + hours + " giờ " + minutes + " phút.\n" + advice);
        }
    }

    private String getSleepAdvice(long hours) {
        if (hours < 7) {
            return "Bạn cần ngủ thêm để có đủ năng lượng!";
        } else if (hours <= 9) {
            return "Thời gian ngủ của bạn rất tốt!";
        } else {
            return "Bạn đã ngủ quá nhiều. Hãy cân nhắc ngủ ít lại.";
        }
    }

}