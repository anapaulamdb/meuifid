package com.example.meuid.activityViews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.meuid.R;

import java.util.List;

public class QrCode extends AppCompatActivity {

    private ImageView code_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
    }

}