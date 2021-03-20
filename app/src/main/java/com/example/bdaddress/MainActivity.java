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




    public  void  getDivisionData(){
        apiInterface.getAllDivision().enqueue(new Callback<GetDivisionResponseData>() {
            @Override
            public void onResponse(Call<GetDivisionResponseData> call, Response<GetDivisionResponseData> response) {
                if (response.code()==200){
                    divisionDataList=new ArrayList<>();
                    assert response.body() != null;
                    divisionDataList.addAll(response.body().getDivisionDataList());
                    Log.e("size",String.valueOf(divisionDataList.size()));
                    Toast.makeText(MainActivity.this, "sss", Toast.LENGTH_SHORT).show();

                }

                else {
                    Toast.makeText(MainActivity.this, "fff", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<GetDivisionResponseData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fff", Toast.LENGTH_SHORT).show();

            }
        });
    }



}