package top.tobin.basic.net.upload;

import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface UploadService {

    @Multipart
    @POST
    Observable<JsonObject> upload(@Url String url, @Part List<MultipartBody.Part> params);

}
