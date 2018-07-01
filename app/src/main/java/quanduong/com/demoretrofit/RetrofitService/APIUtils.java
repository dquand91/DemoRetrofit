package quanduong.com.demoretrofit.RetrofitService;

public class APIUtils {

    public static final String BASE_URL = "http://192.168.1.4/Quanlysinhvien/";

    public static DataClientListener getDataRetrofit(){
        return RetrofitClient.getRetrofitClient(BASE_URL).create(DataClientListener.class);
    }

}
