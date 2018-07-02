package quanduong.com.demoretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import quanduong.com.demoretrofit.Model.SinhVien;
import quanduong.com.demoretrofit.RetrofitService.APIUtils;
import quanduong.com.demoretrofit.RetrofitService.DataClientListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnDangky_main;
    Button btnDangNhap_main;
    EditText edtUser_main;
    EditText edtPassword_main;

    String taikhoan_main;
    String matkhau_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUser_main = findViewById(R.id.edtTaiKhoan_main);
        edtPassword_main = findViewById(R.id.edtMatKhau_main);
        btnDangNhap_main = findViewById(R.id.btnDangNhap_main);


        btnDangky_main = findViewById(R.id.btnDangKy_main);
        btnDangky_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDangKy = new Intent(MainActivity.this, DangKyActivity.class);
                startActivity(intentDangKy);
            }
        });

        btnDangNhap_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taikhoan_main = edtUser_main.getText().toString();
                matkhau_main = edtPassword_main.getText().toString();

                if(taikhoan_main.length() > 0 && matkhau_main.length() > 0){
                    DataClientListener loginDataClient = APIUtils.getDataRetrofit();
                    Call<List<SinhVien>> callback = loginDataClient.LoginData123(taikhoan_main, matkhau_main);
                    callback.enqueue(new Callback<List<SinhVien>>() {
                        @Override
                        public void onResponse(Call<List<SinhVien>> call, Response<List<SinhVien>> response) {
                            ArrayList<SinhVien> mangSinhVien123 = (ArrayList<SinhVien>) response.body();
                            if(mangSinhVien123.size() > 0){
                                Log.d("QUAN123", "onResponse: " + mangSinhVien123.get(0).getTaikhoan());
                                Log.d("QUAN123", "onResponse: " + mangSinhVien123.get(0).getMatkhau());
                                Log.d("QUAN123", "onResponse: " + mangSinhVien123.get(0).getHinhanh());

                            }
                        }

                        @Override
                        public void onFailure(Call<List<SinhVien>> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "FAIL!!!!!!!!!!!!!! Login", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}
