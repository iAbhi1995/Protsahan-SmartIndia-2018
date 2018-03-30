package com.mota.tribal.protsahan.Schemes.Model;

import android.os.Handler;

import com.mota.tribal.protsahan.Schemes.Model.Data.InnerData;
import com.mota.tribal.protsahan.Schemes.Model.Data.SchemeInfo;
import com.mota.tribal.protsahan.Schemes.SchemeCallback;

import java.util.ArrayList;
import java.util.List;

public class MockScheme implements SchemeProvider {
    private List<List<InnerData>> datalist;
    private List<InnerData> innerDataList;
    private List<String> schemename;
    private InnerData data;
    private SchemeInfo mockdata;

    @Override
    public void getSchemes(final SchemeCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockData());
            }
        }, 500);
    }

    public SchemeInfo getMockData() {

        innerDataList = new ArrayList<>();
        datalist = new ArrayList<>();
        schemename = new ArrayList<>();
        schemename.add("TRI");
        schemename.add("Livelihood Support");
        schemename.add("Equity support to NSTFDC/STFDC");
        schemename.add("NGO");
        data = new InnerData("No 1", "Support to Tribal Research Institutes (TRI)", "The basic objective of the scheme is to strengthen the Tribal Research Institutes (TRIs) in their infrastructural needs, Research & documentation activities and Training & Capacity Building programmers etc.More info at https://tribal.nic.in/DivisionsFiles/TRI/ModifiedGuidelines.pdf", "http://www.imgworlds.com/wp-content/themes/IMG/img/phase3/welcome/trex.png", 2017);
        innerDataList.add(data);
        data = new InnerData("No 2", "Financial Assistance for support to Centres of Excellence", "Krishna Vatika Boerdadar Raigarh", "http://www.imgworlds.com/wp-content/themes/IMG/img/phase3/welcome/trex.png", 25);
        innerDataList.add(data);
        innerDataList.add(data);
        innerDataList.add(data);
        innerDataList.add(data);
        innerDataList.add(data);
        innerDataList.add(data);
        innerDataList.add(data);
        datalist.add(innerDataList);
        datalist.add(innerDataList);
        datalist.add(innerDataList);
        datalist.add(innerDataList);
        mockdata = new SchemeInfo(datalist, schemename, "Success", true);

        return mockdata;
    }
}
