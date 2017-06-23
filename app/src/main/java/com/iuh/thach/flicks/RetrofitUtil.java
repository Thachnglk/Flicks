package com.iuh.thach.flicks;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitUtil {

    public static Retrofit create(){
       return new Retrofit.Builder()
               .addConverterFactory(GsonConverterFactory.create())
               .client(client())
                .baseUrl(ResourceUtil.BASE_URL)
                .build();
    }

    public static OkHttpClient client(){
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        //thay đổi parameter trong url of request (request.url)
                        HttpUrl url = request.url()
                                .newBuilder()
                                .addQueryParameter("api_key", BuildConfig.API_KEY)
                                .build();
                        request = request.newBuilder()
                                .url(url)
                                .build();

                        return chain.proceed(request); //thực hiện url request
                    }
                })
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    public static Retrofit create(long movie_id){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client())
                .baseUrl(ResourceUtil.BASE_URL + movie_id +"/")
                .build();
    }


}
