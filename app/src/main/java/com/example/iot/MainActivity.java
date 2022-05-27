package com.example.iot;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mydata;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("LED_STATUS");

    ImageView img_led;
    Boolean tt_led = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_led = findViewById(R.id.imageView);

        img_led.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                tt_led =! tt_led;
                if(tt_led == true) {
                    ref.setValue("ON");
                    img_led.setImageResource(R.drawable.ic_toggle_on);
                }
                else {
                    ref.setValue("OFF");
                    img_led.setImageResource(R.drawable.ic_toggle_off);
                }
            }
        });
    }
}
