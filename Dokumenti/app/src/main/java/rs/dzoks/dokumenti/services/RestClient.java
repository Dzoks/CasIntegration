package rs.dzoks.dokumenti.services;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rs.dzoks.dokumenti.R;
import rs.dzoks.dokumenti.util.HTTPSSetup;

public class RestClient {
    private static Retrofit retrofit = null;

    private static Retrofit getRetrofitInstance(Context context) {
        if (retrofit == null) {
            String url = context.getString(R.string.protocol) + "://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port) + context.getString(R.string.rest_path);
            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .build();
                    return chain.proceed(newRequest);
                }
            };

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = HTTPSSetup.getOkHttpClient(context).newBuilder();
            httpClient.addInterceptor(interceptor);
            httpClient.addInterceptor(logging);

            return new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(buildGsonConverterFactory())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    public static RestInterface getApiService(Context context) {
        return getRetrofitInstance(context).create(RestInterface.class);
    }

    private static GsonConverterFactory buildGsonConverterFactory() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        Gson myGson = gsonBuilder.create();
        return GsonConverterFactory.create(myGson);
    }
}
