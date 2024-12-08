package com.example.app_healthylifestyle.chisosuckhoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.app_healthylifestyle.R;

import java.text.DecimalFormat;

public class bmi extends AppCompatActivity {
    EditText height, weight;
    TextView txt_result;
    Button result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        result = findViewById(R.id.result);
        txt_result = findViewById(R.id.txt_result);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double Height = Double.parseDouble(height.getText().toString());
                double Weight = Double.parseDouble(weight.getText().toString());
                //định dạng lấy đến 2 con số
                DecimalFormat df = new DecimalFormat("0.00");
                //chiều cao * chiều cao
                double BMI = Weight / Math.pow(Height, 2) * 10000;
                //result
                if (BMI < 18)
                    txt_result.setText(df.format(BMI) + "\n Dựa vào kết quả BMI cho thấy bạn đang bị suy dinh dưỡng.\nLời khuyên: Cần tăng cân để đạt được mức cân nặng hợp lý. Hãy ăn nhiều bữa ăn nhỏ và bổ dưỡng trong ngày, bao gồm các thực phẩm giàu protein, chất béo lành mạnh và carbohydrate phức tạp. Nên tập thể dục đều đặn để tăng cường cơ bắp.");
                else if (18 <= BMI && BMI < 25)
                    txt_result.setText(df.format(BMI) + "\n Dựa vào kết quả BMI cho thấy bạn bình thường.\nLời khuyên: Duy trì cân nặng hiện tại bằng cách tiếp tục có một chế độ ăn uống cân đối và lành mạnh, kết hợp với việc tập luyện thể dục thường xuyên. Nên kiểm tra sức khỏe định kỳ để theo dõi tình trạng sức khỏe."+"");
                else if (25 <= BMI && BMI < 30)
                    txt_result.setText(df.format(BMI) + "\n Dựa vào kết quả BMI cho thấy bạn đang béo phì cấp độ 1.\nLời khuyên: Cần giảm cân để tránh các nguy cơ sức khỏe liên quan đến thừa cân. Hãy thực hiện một chế độ ăn kiêng hợp lý, giảm lượng calo tiêu thụ, tăng cường hoạt động thể chất và tránh các thực phẩm chứa nhiều đường và chất béo.");
                else if (30 <= BMI && BMI < 35)
                    txt_result.setText(df.format(BMI) + "\n Dựa vào kết quả BMI cho thấy bạn đang béo phì cấp độ 2.\nLời khuyên: Cần giảm cân nghiêm túc để giảm nguy cơ mắc các bệnh lý như tiểu đường, tim mạch, huyết áp cao và nhiều bệnh khác. Hãy tham khảo ý kiến của bác sĩ hoặc chuyên gia dinh dưỡng để có kế hoạch giảm cân khoa học và an toàn.");
                else if (35 <= BMI)
                    txt_result.setText(df.format(BMI) + "\n Dựa vào kết quả BMI cho thấy bạn đang béo phì cấp độ 3.\n" +
                            "1.Thay đổi chế độ ăn uống: Ăn uống cân bằng, hạn chế calo, đường, và chất béo bão hòa, kiểm soát khẩu phần ăn.\n" +
                            "2.Tăng cường hoạt động thể chất: Tập luyện thường xuyên với các hoạt động nhẹ nhàng và dần dần tăng cường độ.\n" +
                            "3.Theo dõi và quản lý cân nặng: Ghi chép nhật ký ăn uống và tập luyện, tham khảo ý kiến chuyên gia.\n" +
                            "4.Can thiệp y tế (đối với béo phì cấp độ 3): Có thể cần điều trị bằng thuốc hoặc phẫu thuật giảm cân.\n" +
                            "5.Hỗ trợ tâm lý: Tham vấn tâm lý và tham gia nhóm hỗ trợ.\n" +
                            "6.Theo dõi y tế chặt chẽ: Kiểm tra sức khỏe định kỳ và điều chỉnh kế hoạch giảm cân dưới sự hướng dẫn của bác sĩ.");
            }
        });

    }
}