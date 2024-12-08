package com.example.app_healthylifestyle.CRUD;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.app_healthylifestyle.R;
import com.example.app_healthylifestyle.thong_tin_ca_nhan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Update extends AppCompatActivity {
    FirebaseFirestore db;
    String pName,pAddress,pPhone,pDate,pEmail,pGender;
    EditText uEmail,uAddress,uPhone,uDate,uName;
    RadioGroup uGender;
    RadioButton uMen,uWomen;
    Button update;
    DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        uEmail = findViewById(R.id.uEmail);
        uAddress = findViewById(R.id.uAddress);
        uPhone = findViewById(R.id.uPhone);
        uName = findViewById(R.id.uName);
        uDate = findViewById(R.id.uDate);
        uGender = findViewById(R.id.uGender);
        uMen = findViewById(R.id.uGender_men);
        uWomen = findViewById(R.id.uGender_women);
        update = findViewById(R.id.uSubmit);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            //get data
            pName = bundle.getString("pName");
            pAddress = bundle.getString("pAddress");
            pPhone = bundle.getString("pPhone");
            pEmail = bundle.getString("pEmail");
            pDate = bundle.getString("pDate");
            pGender = bundle.getString("pGender");
            //set data
            uEmail.setText(pEmail);
            uPhone.setText(pPhone);
            uDate.setText(pDate);
            uName.setText(pName);
            uAddress.setText(pAddress);
            if ("Nam".equalsIgnoreCase(pGender)) {
                uMen.setChecked(true);
            } else {
                uWomen.setChecked(true);
            }
            //set ngày
            uDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    picker = new DatePickerDialog(Update.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            uDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        }
                    }, year, month, day);
                    picker.show();
                }
            });
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nEmail = uEmail.getText().toString().trim();
                    String nName = uName.getText().toString().trim();
                    String nAddress = uAddress.getText().toString().trim();
                    String nPhone = uPhone.getText().toString().trim();
                    String nDate = uDate.getText().toString().trim();
                    //lấy gender
                    int selectedGenderId = uGender.getCheckedRadioButtonId();
                    String nGender;
                    if (selectedGenderId == R.id.uGender_men) {
                        nGender = "Nam";
                    } else {
                       nGender = "Nữ";
                    }
                    //update
                    db = FirebaseFirestore.getInstance();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    DocumentReference df = db.collection("Users").document(user.getUid());
                    //put trường dữ liệu
                    Map<String, String> userInfo = new HashMap<>();
                    userInfo.put("Phone",nPhone);
                    userInfo.put("Birthday",nDate);
                    userInfo.put("Name",nName);
                    userInfo.put("Address",nAddress);
                    userInfo.put("Gender",nGender);
                    userInfo.put("Email",nEmail);
                    //update
                    df.set(userInfo, SetOptions.merge());
                }
            });
        }
    }
}