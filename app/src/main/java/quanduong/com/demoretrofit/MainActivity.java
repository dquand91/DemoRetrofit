package quanduong.com.demoretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnDangky_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDangky_main = findViewById(R.id.btnDangKy_main);
        btnDangky_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDangKy = new Intent(MainActivity.this, DangKyActivity.class);
                startActivity(intentDangKy);
            }
        });
    }
}
