package com.example.miniproj;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.CellInfoGsm;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.TelephonyManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;
    TextView textView;
    TelephonyManager telephonyManager;
    ListView l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{READ_SMS, READ_PHONE_NUMBERS, READ_PHONE_STATE,ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
        } else {
//            CellInfoGsm cellInfoGsm = (CellInfoGsm)telephonyManager.getAllCellInfo().get(0);
//            CellSignalStrengthGsm cellSignalStrengthGsm = cellInfoGsm.getCellSignalStrength();


            String SIM_CARRIER = (String) telephonyManager.getSimCarrierIdName();
            String Net_operator = (String) telephonyManager.getNetworkOperatorName();
            String Number  = telephonyManager.getLine1Number();
            String gsmstrength = ""+telephonyManager.getSignalStrength().getGsmSignalStrength();
            String Network_Country = ""+telephonyManager.getNetworkCountryIso();






            String tutorials[]
                    = {
                    "SIM CARRIER : "+SIM_CARRIER,
                    "Network Operator : "+Net_operator,
                    "Number : "+Number,
                    "Network Strength (GSM) : "+gsmstrength,
                    "Network Country : "+Network_Country,


            };



            l = findViewById(R.id.list);
            ArrayAdapter<String> arr;
            arr = new ArrayAdapter<String>(
                    this,
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    tutorials);
            l.setAdapter(arr);

        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (ActivityCompat.checkSelfPermission(this, READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        textView.setText(""+telephonyManager.getSignalStrength());
                    }
                    textView.setText("");
                }
        }
    }


}