package rs.dzoks.dokumenti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnGenerateToken;
    Button btnSeeWebServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGenerateToken = findViewById(R.id.generateToken);
        btnSeeWebServices = findViewById(R.id.seeServices);
        btnGenerateToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GenerateTokenActivity.class);
                startActivity(intent);
            }
        });
        btnSeeWebServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServicesActivity.class);
                startActivity(intent);
            }
        });
    }
}
