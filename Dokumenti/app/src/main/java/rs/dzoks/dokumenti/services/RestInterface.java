package rs.dzoks.dokumenti.services;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rs.dzoks.dokumenti.model.GetInfo;

public interface RestInterface {

    @POST("token")
    Call<ResponseBody> generateToken(@Body GetInfo getInfo);

    @POST("documents")
    Call<ResponseBody> getDocuments(@Body GetInfo getInfo);
}