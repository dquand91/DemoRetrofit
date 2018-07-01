package quanduong.com.demoretrofit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class DangKyActivity extends AppCompatActivity {

    ImageView imgHinh_Dangky;
    EditText edtUserName_Dangky, edtPassword_Dangky;
    Button btnXacNhan_Dangky, btnHuy_Dangky;
    final int REQUEST_CODE_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        initView();
        imgHinh_Dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK){
            if(data != null){
                Uri uri = data.getData();

                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imgHinh_Dangky.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void initView() {

        imgHinh_Dangky = findViewById(R.id.imgDangKy_dangky);
        edtUserName_Dangky = findViewById(R.id.edtTaiKhoan_dangky);
        edtPassword_Dangky = findViewById(R.id.edtMatKhau_dangky);
        btnXacNhan_Dangky = findViewById(R.id.btnXacNhan_dangky);
        btnHuy_Dangky = findViewById(R.id.btnHuy_dangky);

    }
}
