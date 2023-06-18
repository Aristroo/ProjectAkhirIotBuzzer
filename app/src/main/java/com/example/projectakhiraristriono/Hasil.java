package com.example.projectakhiraristriono;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class Hasil extends AppCompatActivity implements View.OnClickListener {

    ToggleButton mButtonPower;
    ImageView mImageStatus;

    FirebaseDatabase mDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);


        mDatabase = FirebaseDatabase.getInstance();


        mButtonPower = findViewById(R.id.button_power);
        mImageStatus = findViewById(R.id.imageView);


        mButtonPower.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_power:
                toggleLed();
                break;
        }
    }

    void toggleLed() {
        if (mButtonPower.isChecked()) {
            turnOnLed();
        } else {
            turnOffLed();
        }
    }


    void turnOnLed() {
        mButtonPower.setEnabled(false);
        mDatabase.getReference("manual").
                setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Hasil.this, "LAMPU MENYALA", Toast.LENGTH_SHORT).show();
                            mButtonPower.setChecked(true);
                            mImageStatus.setImageResource(R.drawable.pngitem_25457);
                        }
                        mButtonPower.setEnabled(true);
                    }
                });
    }

    void turnOffLed() {
        mButtonPower.setEnabled(false);
        mDatabase.getReference("manual").setValue(0).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Hasil.this, "LAMPU MATI", Toast.LENGTH_SHORT).show();
                    mButtonPower.setChecked(false);
                    mImageStatus.setImageResource(R.drawable.sound_off_icon_40940);
                }
                mButtonPower.setEnabled(true);
            }
        });
    }

}