package com.nnn.ngocnam.giaiphuongtrinh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static final int REQ_CODE = 1011;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // thiết lặp bắt sự kiện cho nút
        Button btnResult =findViewById(R.id.btn_result);
        btnResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_result) {
            EditText numberA = findViewById(R.id.edt_numbera);
            EditText numberB = findViewById(R.id.edt_numberb);
            // số a
            double a;
            // số b
            double b;
            // lấy dữ liệu người dùng nhập chuyển qua dạng double rồi gán cho a, nếu dữ liệu nhập vào không phải là số thì báo lỗi
            try {
                a = Double.parseDouble(numberA.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Số a nhập sai", Toast.LENGTH_SHORT).show();
                return;
            }

            // lấy dữ liệu người dùng nhập chuyển qua dạng double rồi gán cho b, nếu dữ liệu nhập vào không phải là số thì báo lỗi
            try {
                b = Double.parseDouble(numberB.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Số b nhập sai", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent myIntent = new Intent(MainActivity.this, ResultActivity.class);
            // thêm các tham số cần gửi qua resultactivity
            myIntent.putExtra("a", a);
            myIntent.putExtra("b", b);
            // bắt đầu activty mới để lấy kết quả
            MainActivity.this.startActivityForResult(myIntent,REQ_CODE);
        }


    }
    // Bắt sự kiện kết quả trả về của các activity được gọi
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ_CODE) {
            Double a,b;
            // Lấy dữ liệu trả lại từ resultActivity
            a = data.getDoubleExtra("a",0);
            b = data.getDoubleExtra("b",0);
            // Make toast thông báo
            Toast.makeText(this,"Wellcome back to MainActivity ! Your last edit text : a="+String.valueOf(a)+",b="+String.valueOf(b),Toast.LENGTH_LONG).show();
            EditText numberA = findViewById(R.id.edt_numbera);
            EditText numberB = findViewById(R.id.edt_numberb);
            // đặt ô nhập dữ liệu về 0
            numberA.setText("0");
            numberB.setText("0");
        }
    }

}
