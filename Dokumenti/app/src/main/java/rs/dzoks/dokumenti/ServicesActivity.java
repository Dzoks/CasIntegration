package rs.dzoks.dokumenti;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rs.dzoks.dokumenti.model.DocumentSOAP;
import rs.dzoks.dokumenti.model.DocumentsListSOAP;
import rs.dzoks.dokumenti.model.DocumentsResponse;
import rs.dzoks.dokumenti.model.GetInfo;
import rs.dzoks.dokumenti.services.RestClient;
import rs.dzoks.dokumenti.services.RestInterface;
import rs.dzoks.dokumenti.services.SoapClient;

public class ServicesActivity extends AppCompatActivity {

    private EditText fldUsername;
    private EditText fldPassword;
    private EditText fldToken;
    private EditText fldJmbg;
    private EditText fldDate;
    private Button sendButton;
    private RadioButton jmbgBtn;
    private RadioButton dateBtn;
    private RadioGroup radioGroup;
    DatePickerDialog datePickerDialog;
    private RestInterface restClient;
    private SoapClient soapClient;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson=new Gson();
        restClient=RestClient.getApiService(this);
        soapClient=new SoapClient(this);
        setContentView(R.layout.activity_services);
        fldUsername=findViewById(R.id.serviceUsername);
        fldPassword=findViewById(R.id.servicePassword);
        fldToken=findViewById(R.id.serviceToken);
        fldDate=findViewById(R.id.fldDate);
        fldJmbg=findViewById(R.id.fldJmbg);
        sendButton=findViewById(R.id.serviceSend);
        jmbgBtn=findViewById(R.id.jmbgBtn);
        dateBtn=findViewById(R.id.dateBtn);
        radioGroup=findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fldJmbg.setText("");
                fldDate.setText("");
                if (checkedId==dateBtn.getId()){
                    System.out.println("DATE");
                    fldDate.setEnabled(true);
                    fldJmbg.setEnabled(false);
                }else{
                    fldDate.setEnabled(false);
                    fldJmbg.setEnabled(true);
                }
            }
        });
        fldDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(ServicesActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                fldDate.setText(dayOfMonth + "."
                                        + (monthOfYear + 1) + "." + year+".");

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }

        });
    }

    private void sendData() {
        if (!checkValidity()){
            Toast.makeText(this,"Morate popuniti sva polja",Toast.LENGTH_LONG).show();
            return;
        }
        if (dateBtn.isChecked()){

                new SoapContactTask().execute();
        }else{
            GetInfo info=new GetInfo(fldUsername.getText().toString().trim(),fldPassword.getText().toString().trim(),fldToken.getText().toString().trim(),fldJmbg.getText().toString().trim());
            Call<ResponseBody> call=restClient.getDocuments(info);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    switch (response.code()){
                        case 403:
                            Toast.makeText(ServicesActivity.this,"Nemate dovoljno privilegija za ovaj poziv.",Toast.LENGTH_LONG).show();
                            break;
                        case 401:
                            Toast.makeText(ServicesActivity.this,"Pogrešni podaci za prijavljivanje.",Toast.LENGTH_LONG).show();
                            break;
                        case 400:
                            Toast.makeText(ServicesActivity.this,"Ne postoji korisnik sa tim JMBG-om.",Toast.LENGTH_LONG).show();
                            break;
                        default:
                            String responseText="";
                            try {
                                responseText=response.body().string();
                                System.out.println(responseText);
                                DocumentsListSOAP documents=gson.fromJson(responseText,DocumentsListSOAP.class);

                                Intent intent=new Intent(ServicesActivity.this,ListActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("documents",documents);
                                intent.putExtras(bundle);
                                startActivity(intent);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(ServicesActivity.this,"Nije moguće dobiti odgovor",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private boolean checkValidity(){
        if (fldUsername.getText().toString().isEmpty()||fldPassword.getText().toString().isEmpty()||fldToken.getText().toString().isEmpty())
            return false;
        if (dateBtn.isChecked() && fldDate.getText().toString().isEmpty())
            return false;
        if (jmbgBtn.isChecked() && fldJmbg.getText().toString().isEmpty())
            return false;
        return true;
    }
    private class SoapContactTask extends AsyncTask<Void,Void,DocumentsResponse> {
        @Override
        protected DocumentsResponse doInBackground(Void... news) {
            long time= 0;
            try {
                time = new SimpleDateFormat("dd.MM.yyyy.").parse(fldDate.getText().toString().trim()).getTime()+7_200_000;
                return soapClient.getDocuments(fldUsername.getText().toString().trim(),fldPassword.getText().toString().trim(),fldToken.getText().toString().trim(),time);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(DocumentsResponse response) {
            if (response==null){
                Toast.makeText(ServicesActivity.this,"Nije moguće dobiti odgovor",Toast.LENGTH_LONG).show();
                return;
            }
            if (response.getError()!=null){
                switch (response.getError()){
                    case "401":
                        Toast.makeText(ServicesActivity.this,"Pogrešni podaci za prijavljivanje.",Toast.LENGTH_LONG).show();
                        break;
                    case "400":
                        Toast.makeText(ServicesActivity.this,"Pogrešan datum.",Toast.LENGTH_LONG).show();
                        break;
                }
                return;
            }
            DocumentsListSOAP documents=response.getDocuments();
            Intent intent=new Intent(ServicesActivity.this,ListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("documents",documents);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }



}
