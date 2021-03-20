package com.example.bdaddress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.bdaddress.getDivision.GetDivisionData;
import com.example.bdaddress.getDivision.GetDivisionResponseData;
import com.example.bdaddress.retrofit.ApiInterface;
import com.example.bdaddress.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    List<GetDivisionData> divisionDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //https://bdapis.herokuapp.com/

        apiInterface = RetrofitClient.getRetrofit("https://bdapis.herokuapp.com/").create(ApiInterface.class);


    }




  



}