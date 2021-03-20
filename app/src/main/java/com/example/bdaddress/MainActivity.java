package com.example.bdaddress;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bdaddress.getDivision.GetDivisionData;
import com.example.bdaddress.getDivision.GetDivisionResponseData;
import com.example.bdaddress.retrofit.ApiInterface;
import com.example.bdaddress.retrofit.RetrofitClient;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    List<GetDivisionData> divisionDataList;

    TextView divisionTextView,districtTextView,thanaTextView;

    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = RetrofitClient.getRetrofit("https://bdapis.herokuapp.com/").create(ApiInterface.class);

        divisionTextView=findViewById(R.id.divisionTextViewId);
        districtTextView=findViewById(R.id.districtTextViewId);
        thanaTextView=findViewById(R.id.thanaTextViewId);

        divisionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDivisionData();
            }
        });


    }




    public  void  getDivisionData(){
        apiInterface.getAllDivision().enqueue(new Callback<GetDivisionResponseData>() {
            @Override
            public void onResponse(Call<GetDivisionResponseData> call, Response<GetDivisionResponseData> response) {
                if (response.code()==200){
                    divisionDataList=new ArrayList<>();
                    assert response.body() != null;
                    divisionDataList.addAll(response.body().getDivisionDataList());


                   if (divisionDataList.size()>0){
                       Toast.makeText(MainActivity.this, "sss", Toast.LENGTH_SHORT).show();
                     //  showDivisionData(divisionDataList);

                   }


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