package com.example.bdaddress.retrofit;


import com.example.bdaddress.getDivision.GetDivisionResponseData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    // get division name
    @GET("api/v1.0/divisions")
    Call<GetDivisionResponseData> getAllDivision();


//    //get single customer information
//    @GET("api/v1.0/division/{id}")
//    Call<SingleCustomerGetResponse> getSingleCustomerInformation(@Header("Authorization") String authorization,@Path("id") String id);



}
