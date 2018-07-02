package quanduong.com.demoretrofit.RetrofitService;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DataClientListener {

	// @Multipart: khi gửi data dạng FILE, âm thanh, hình ảnh. Gửi Text thì dùng cái khác.
	// @POST dùng dạng POST. và "uploadhinh.php" là link API. Ví dụ API có tên là: get_userlist => thì truyền vào @POST("get_userlist")
	// Call<String> : vì ở đây server trả về response dạng chuỗi. Nên dùng String.
	// Đây là phương thức có tên là "UploadPhoto123" do mình đặt
	// đối số @Part MultipartBody.Part photo123 vì ta muốn truyền vào 1 cái MultipartBody.Part để đẩy lên server.
	@Multipart
	@POST("uploadhinh.php")
	Call<String> UploadPhoto123(@Part MultipartBody.Part photo123);


	// @FormUrlEncoded gửi data dạng TEXT
	// Dùng POST và gọi API "insert.php"
	// Call<String> : vì ở đây server trả về response dạng chuỗi. Nên dùng String.
	// Phương thức tên là "InsertData123" do mình đặt. Có 3 đối số cần truyền vào: taiKhoanTruyen, matKhauTruyen, hinhanhTruyen
	// Từ 3 đối số ở trên, sẽ gắn vào tương ứng với các tham số mà API yêu cầu như bên dưới. Ví dụ: taiKhoanTruyen gắn vào tham số "taikhoan"
	// @Field("taikhoan") => là tham số mà API server yêu cầu mình truyền vào. Ở đây tham số tên là taikhoan
	// @Field("matkhau") => tham số tên là matkhau
	@FormUrlEncoded
	@POST("insert.php")
	Call<String> InsertData123(@Field("taikhoan") String taiKhoanTruyen,
							   @Field("matkhau") String matKhauTruyen,
							   @Field("hinhanh") String hinhanhTruyen);



}
