package com.nnn.ngocnam.giaiphuongtrinh;

/**
 * Created by Nam on 04/03/2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    Double numberA;
    Double numberB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        // thiết lặp bắt sự kiện cho nút
        Button btnResult =findViewById(R.id.btn_back);
        btnResult.setOnClickListener(this);
        calculate();
    }
    // hàm tính toán
    private void calculate(){
        TextView tvResult = findViewById(R.id.tv_result);
        Intent myIntent = getIntent();
        // Lấy các tham số được gửi từ mainactivity
        numberA = myIntent.getDoubleExtra("a",0);
        numberB = myIntent.getDoubleExtra("b",0);

        // a=0, b=0 phương  trình có vô số nghiệm

        if (numberA==0&& numberB==0) {
            tvResult.setText("Vô số nghiệm");
            return;
        }
        // a=0, b!=0 phương trình vô nghiệm
        if (numberA==0&& numberB!=0) {
            tvResult.setText("Vô nghiệm");
            return;
        }
        // a!=0 phương trình có nghiệm x=-b/a
        if (numberA!=0) {
            String result = String.valueOf(-numberB/numberA);
            // cắt .0 nếu kết quả nguyên.
            if (result.endsWith(".0")) {
                result= result.substring(0,result.length()-2);
            }
            tvResult.setText(result);
        }
    }
    // Bắt sự kiệm nút nhấn
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_back){
            // Tạo intent mới, thêm các tham số trả vê
            Intent myIntent = new Intent();
            myIntent.putExtra("a",numberA);
            myIntent.putExtra("b",numberB);
            // trả kết quả và kết thúc activity
            setResult(RESULT_OK,myIntent);
            finish();
        }
    }
}
