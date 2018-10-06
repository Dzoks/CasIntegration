package rs.dzoks.dokumenti;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rs.dzoks.dokumenti.model.DocumentSOAP;
import rs.dzoks.dokumenti.model.DrivingCategorySOAP;

public class DocumentActivity extends AppCompatActivity {

    private TextView fname;
    private TextView lname;
    private TextView jmbg;
    private TextView sex;
    private TextView dob;
    private TextView birthplace;
    private TextView type;
    private TextView validUntil;
    private TextView issuer;
    private TextView issueDate;
    private TextView entityCitizenship;
    private TextView citizenship;
    private TextView countryCode;
    private TextView residence;
    private TextView serialNumber;
    private ListView categories;
    private TextView lblEntity;
    private TextView lblCountry;

    public static void setListViewHeightBasedOnChildren
            (ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.UNSPECIFIED);

        int totalHeight = 0;
        View view = null;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);

            if (i == 0) view.setLayoutParams(new
                    ViewGroup.LayoutParams(desiredWidth,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        fname = findViewById(R.id.firstName);
        lname = findViewById(R.id.lastName);
        jmbg = findViewById(R.id.detailsJmbg);
        sex = findViewById(R.id.sex);
        dob = findViewById(R.id.birthday);
        type = findViewById(R.id.documentType);
        birthplace = findViewById(R.id.birthPlace);
        validUntil = findViewById(R.id.validUntil);
        issueDate = findViewById(R.id.issueDate);
        issuer = findViewById(R.id.issuer);
        citizenship = findViewById(R.id.citizenship);
        entityCitizenship = findViewById(R.id.entityCitizenship);
        countryCode = findViewById(R.id.countryCode);
        residence = findViewById(R.id.residence);
        serialNumber = findViewById(R.id.serialNumber);
        categories = findViewById(R.id.categories);
        lblCountry = findViewById(R.id.countryCodeLbl);
        lblEntity = findViewById(R.id.entityCitizenshipLbl);
        DocumentSOAP document = (DocumentSOAP) getIntent().getExtras().getSerializable("document");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        fname.setText(document.getFname());
        lname.setText(document.getLname());
        jmbg.setText(document.getJmbg());
        sex.setText(document.getMale().equals((byte) 1) ? "Muško" : "Žensko");
        dob.setText(sdf.format(new Date(document.getBirthDate())));
        birthplace.setText(document.getPlaceOfBirth());
        validUntil.setText(sdf.format(new Date(document.getValidUntil())));
        issueDate.setText(sdf.format(new Date(document.getDateOfIssue())));
        issuer.setText(document.getIssuingAuthority());
        citizenship.setText(document.getCitizenship());
        residence.setText(document.getResidence());
        serialNumber.setText(document.getSerialNumber());
        type.setText(document.getDocumentType());
        switch (document.getDocumentType()) {
            case "Lična karta":
                categories.setVisibility(View.GONE);
                entityCitizenship.setText(document.getEntityCitizenship());
                countryCode.setVisibility(View.GONE);
                lblCountry.setVisibility(View.GONE);
                break;
            case "Vozačka dozvola":
                countryCode.setVisibility(View.GONE);
                lblCountry.setVisibility(View.GONE);
                entityCitizenship.setVisibility(View.GONE);
                lblEntity.setVisibility(View.GONE);
                List<String> cats = new ArrayList<>();
                for (DrivingCategorySOAP cat : document.getCategories()) {
                    cats.add("Kategorija " + cat.getCategory() + " - " + sdf.format(cat.getExamDate()));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, cats);
                categories.setAdapter(adapter);
                setListViewHeightBasedOnChildren(categories);
                break;
            case "Pasoš":
                categories.setVisibility(View.GONE);
                entityCitizenship.setVisibility(View.GONE);
                lblEntity.setVisibility(View.GONE);
                countryCode.setText(document.getCountryCode());
        }

    }
}
