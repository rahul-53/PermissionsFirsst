package com.example.permissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE=0;
    private View mBtnRequestPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnRequestPermission = findViewById(R.id.btnRequestPermission);
        mBtnRequestPermission.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String[] permissions ={Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(MainActivity.this, permissions,REQUEST_CODE);
            }
        });

    }@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1]== PackageManager.PERMISSION_GRANTED){
            showToast("Both the permissions are granted");
        }
        else if (grantResults[0] == PackageManager.PERMISSION_DENIED && grantResults[1]== PackageManager.PERMISSION_GRANTED){
            showToast("Camera permission is accepted but Storage is denied");
        }
        else if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1]== PackageManager.PERMISSION_DENIED){
            showToast("Storage permission is accepted but Camera is denied");
        }
        else {
            showToast("Both the permissions are granted");
        }

    }
    private void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}