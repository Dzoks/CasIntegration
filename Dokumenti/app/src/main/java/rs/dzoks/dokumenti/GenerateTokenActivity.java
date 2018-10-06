package rs.dzoks.dokumenti;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.dzoks.dokumenti.model.GetInfo;
import rs.dzoks.dokumenti.services.RestClient;
import rs.dzoks.dokumenti.services.RestInterface;

public class GenerateTokenActivity extends AppCompatActivity {

    EditText fldUsername;
    EditText fldPassword;
    Button btnGenerateToken;
    private RestInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_token);
        service = RestClient.getApiService(this);
        fldUsername = findViewById(R.id.tokenUsername);
        fldPassword = findViewById(R.id.tokenPassword);
        btnGenerateToken = findViewById(R.id.tokenButton);
        btnGenerateToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        if (fldPassword.getText().toString().isEmpty() || fldUsername.getText().toString().isEmpty()) {
            Toast.makeText(GenerateTokenActivity.this, "Morate unijeti sva polja!", Toast.LENGTH_LONG).show();
            return;
        }
        Call<ResponseBody> call = service.generateToken(new GetInfo(fldUsername.getText().toString().trim(), fldPassword.getText().toString().trim(), null, null));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 401) {
                    Toast.makeText(GenerateTokenActivity.this, "Unesite pravilno korisničko ime i lozinku!", Toast.LENGTH_LONG).show();

                } else if (response.code() == 200) {
                    Toast.makeText(GenerateTokenActivity.this, "Uspješno ste generisali token!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(GenerateTokenActivity.this, "Neuspješna prijava!", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(GenerateTokenActivity.this, "Neuspješna prijava!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
