package com.example.iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mydata;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("LED_STATUS");

    TextView CanhBao;
    ImageView img_led;
    Boolean tt_led = false;
    int LED_STATUS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CanhBao = findViewById(R.id.CanhBao);
        img_led = findViewById(R.id.imageView);

        img_led.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                tt_led =! tt_led;
                if(tt_led == true) {
                    ref.setValue("ON");
                    LED_STATUS = 1;
                    img_led.setImageResource(R.drawable.ic_toggle_on);
                }
                else {
                    ref.setValue("OFF");
                    LED_STATUS = 0;
                    img_led.setImageResource(R.drawable.ic_toggle_off);
                }
            }
        });
        DatabaseReference ref1 = database.getReference("CHECK_LED");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String check_led = snapshot.getValue().toString();
                if(LED_STATUS==0) {
                    CanhBao.setText(" ");
                }
                else {
                    CanhBao.setText(check_led);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Đã xảy ra lỗi "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
