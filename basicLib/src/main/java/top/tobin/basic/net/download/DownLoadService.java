package top.tobin.basic.net.download;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface DownLoadService {

    @Streaming
    @GET
    Observable<ResponseBody> startDownLoad(@Url String fileUrl);

}
