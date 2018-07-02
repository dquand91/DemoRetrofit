package quanduong.com.demoretrofit.RetrofitService;

public class APIUtils {

    public static final String BASE_URL = "http://172.18.128.58/Quanlysinhvien/";

    public static DataClientListener getDataRetrofit(){
        return RetrofitClient.getRetrofitClient(BASE_URL).create(DataClientListener.class);
    }

}
