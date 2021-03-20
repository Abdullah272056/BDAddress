package com.example.bdaddress.getDistrict;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDistrictResponseData{
    @SerializedName("status")
    @Expose
    private GetDistrictStatusData status;
    @SerializedName("data")
    @Expose
    private List<GetDistrictData> data = null;

    public GetDistrictStatusData getStatus() {
        return status;
    }

    public void setStatus(GetDistrictStatusData status) {
        this.status = status;
    }

    public List<GetDistrictData> getData() {
        return data;
    }

    public void setData(List<GetDistrictData> data) {
        this.data = data;
    }
}
