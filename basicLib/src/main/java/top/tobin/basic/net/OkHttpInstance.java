package top.tobin.basic.net;

import android.annotation.SuppressLint;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpInstance {

    private static class Instance {
        private static OkHttpInstance instance = new OkHttpInstance();
    }

    public static OkHttpClient get() {
        return Instance.instance.client;
    }


    private OkHttpClient client;

    private OkHttpInstance() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS);

            addLogInterceptor(builder);

            // 绕过证书验证
            TrustManager[] trustManagers = {new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {}

                @SuppressLint("TrustAllX509TrustManager")
                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {}
            }};

            //SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, new SecureRandom());
            builder.sslSocketFactory(
                    sslContext.getSocketFactory(),
                    (X509TrustManager) trustManagers[0]
            );

            builder.hostnameVerifier(new SSLHostnameVerifier());

            client = builder.build();
            client.dispatcher().setMaxRequestsPerHost(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加log拦截器，BODY打印信息，NONE不打印信息
     */
    private void addLogInterceptor(OkHttpClient.Builder builder) {
        LoggingInterceptor logging = new LoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
    }

    public static class SSLHostnameVerifier implements HostnameVerifier {
        @SuppressLint("BadHostnameVerifier")
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

}
