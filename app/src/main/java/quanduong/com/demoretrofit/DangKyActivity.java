package quanduong.com.demoretrofit;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import quanduong.com.demoretrofit.RetrofitService.APIUtils;
import quanduong.com.demoretrofit.RetrofitService.DataClientListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyActivity extends AppCompatActivity {

    ImageView imgHinh_Dangky;
    EditText edtUserName_Dangky, edtPassword_Dangky;
    Button btnXacNhan_Dangky, btnHuy_Dangky;
    final int REQUEST_CODE_IMAGE = 100;
    String realpath123 = "";
    String taikhoan = "";
    String matkhau = "";

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
        btnXacNhan_Dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                taikhoan = edtUserName_Dangky.getText().toString();
                matkhau = edtPassword_Dangky.getText().toString();

                if(taikhoan.isEmpty() || matkhau.isEmpty()){

                    Toast.makeText(DangKyActivity.this, "Nhap Emal or Password", Toast.LENGTH_SHORT).show();

                } else {
                    File file = new File(realpath123);
                    String filePath = file.getAbsolutePath();


                    // Ví dụ: filePath = /AAA/BBB/CCC/file123.png
                    // => arrayTenFileDaXoaDauCham sẽ thành mảng gồm 2 phần tử: {/AAA/BBB/CCC/file123 , png}
                    String[] arrayTenFileDaXoaDauCham = filePath.split("\\.");

                    filePath = arrayTenFileDaXoaDauCham[0] + System.currentTimeMillis() + "." + arrayTenFileDaXoaDauCham[1];

                    // Cái này phần requestBođy của bản tin HTTP
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart.form-data"), file);

                    // Cái này là body content của bản tin HTTP
                    // Với tham số của bản tin HTTP ví dụ như : "uploaded_file=1238973259857.png"

                    // Bản in HTTP sẽ có dạng :
//                POST http://172.18.128.58/Quanlysinhvien/uploadhinh.php HTTP/1.1
//                Content-Type: multipart/form-data; boundary=3081fa53-2526-4695-bd84-267e75ce813e
//                Content-Length: 5195
//                Host: 172.18.128.58
//                Connection: Keep-Alive
//                Accept-Encoding: gzip
//                User-Agent: okhttp/3.3.0
//
//                Content-Disposition: form-data; name="uploaded_file"; filename="/storage/emulated/0/Download/images-11530511355733.png"
//                Content-Length: 4966

                    // uploađe_file: là tên của tham số mà API yêu cầu truyền vào. Để server dựa vào tham số này lấy ra data cần xử lý.
                    MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file", filePath, requestBody);

                    DataClientListener uploadPhoTo123 = APIUtils.getDataRetrofit();
                    Call<String> callback = uploadPhoTo123.UploadPhoto123(body);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response !=null ){
                                String message =response.body();
                                Log.d("QUAN123", "onResponse: " + message);
                                if(message.length() > 0){
                                    DataClientListener insertData123 = APIUtils.getDataRetrofit();
                                    Call<String> callbackInsertData = insertData123.InsertData123(taikhoan, matkhau, (APIUtils.BASE_URL + "image/" + message));
                                    callbackInsertData.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            if (response !=null ) {
                                                String result = response.body();
                                                Log.d("QUAN123", "onResponse: " + result);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {
                                            Log.d("QUAN123", "onFailure: " + "FAIL");
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }




            }
        });
    }

    // To get full path của 1 file từ URI.
    public String getRealPathFromURI123 (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK){
            if(data != null){
                Uri uri = data.getData();
                realpath123 = getRealPathFromURI123(uri);

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
