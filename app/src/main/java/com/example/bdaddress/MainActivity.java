package com.example.bdaddress;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bdaddress.getDistrict.DistrictCustomAdapter;
import com.example.bdaddress.getDistrict.GetDistrictData;
import com.example.bdaddress.getDistrict.GetDistrictResponseData;
import com.example.bdaddress.getDivision.DivisionCustomAdapter;
import com.example.bdaddress.getDivision.GetDivisionData;
import com.example.bdaddress.getDivision.GetDivisionResponseData;
import com.example.bdaddress.retrofit.ApiInterface;
import com.example.bdaddress.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DivisionCustomAdapter.OnContactClickListener1,DistrictCustomAdapter.OnContactClickListener2{
    ApiInterface apiInterface;
    List<GetDivisionData> divisionDataList;
    List<GetDistrictData> districtDataList;
    List<String> thanaDataList;

    TextView divisionTextView,districtTextView,thanaTextView;

    AlertDialog alertDialog;

    RecyclerView divisionRecyclerView;

    DivisionCustomAdapter divisionCustomAdapter;
    DistrictCustomAdapter districtCustomAdapter;
    DivisionCustomAdapter.OnContactClickListener1 onContactClickListener1;
    DistrictCustomAdapter.OnContactClickListener2 onContactClickListener2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onContactClickListener1=this;
        onContactClickListener2=this;


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
        districtTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String division=divisionTextView.getText().toString();
              if (division.isEmpty()){
                  Toast.makeText(MainActivity.this, "Please select your division", Toast.LENGTH_SHORT).show();
                    return;
              }else {
                  getDistrict(division.toLowerCase());

              }
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
                       showDivisionData(divisionDataList);

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

    private void showDivisionData(List<GetDivisionData> divisionDataList){
        AlertDialog.Builder builder     =new AlertDialog.Builder(MainActivity.this);
        LayoutInflater layoutInflater   =LayoutInflater.from(MainActivity.this);
        View view                       =layoutInflater.inflate(R.layout.recyclerview,null);
        builder.setView(view);
        alertDialog   = builder.create();
        alertDialog.setCancelable(false);

        divisionRecyclerView=view.findViewById(R.id.recyclerViewId);
        divisionCustomAdapter = new DivisionCustomAdapter(MainActivity.this,divisionDataList,onContactClickListener1);
        divisionRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        divisionRecyclerView.setAdapter(divisionCustomAdapter);


        alertDialog.show();


    }

    public void  getDistrict(String id){
        apiInterface.getDistrict(id).enqueue(new Callback<GetDistrictResponseData>() {
            @Override
            public void onResponse(Call<GetDistrictResponseData> call, Response<GetDistrictResponseData> response) {


                if (response.code()==200){
                    districtDataList=new ArrayList<>();
                    thanaDataList=new ArrayList<>();
                    assert response.body() != null;
                    districtDataList.addAll(response.body().getGetDistrictData());


                    if (districtDataList.size()>0){


                        showDistrict(districtDataList);
                       // Toast.makeText(MainActivity.this, String.valueOf(thanaDataList.size()), Toast.LENGTH_SHORT).show();


                    }


                }

                else {
                    Toast.makeText(MainActivity.this, "fff", Toast.LENGTH_SHORT).show();

                }            }

            @Override
            public void onFailure(Call<GetDistrictResponseData> call, Throwable t) {

            }
        });
}

    private void showDistrict(List<GetDistrictData> districtDataList){
        AlertDialog.Builder builder     =new AlertDialog.Builder(MainActivity.this);
        LayoutInflater layoutInflater   =LayoutInflater.from(MainActivity.this);
        View view                       =layoutInflater.inflate(R.layout.recyclerview,null);
        builder.setView(view);
        alertDialog   = builder.create();
        alertDialog.setCancelable(false);

        divisionRecyclerView=view.findViewById(R.id.recyclerViewId);
        districtCustomAdapter = new DistrictCustomAdapter(MainActivity.this,districtDataList,onContactClickListener2);
        divisionRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        divisionRecyclerView.setAdapter(districtCustomAdapter);


        alertDialog.show();


    }




    // division item click
    @Override
    public void onContactClick1(int position) {
        divisionTextView.setText(String.valueOf(divisionDataList.get(position).getDivision()));
        alertDialog.dismiss();
    }

    @Override
    public void onContactClick2(int position) {
        thanaDataList.addAll(districtDataList.get(position).getUpazilla());
        districtTextView.setText(String.valueOf(districtDataList.get(position).getDistrict()));
        alertDialog.dismiss();
    }
}