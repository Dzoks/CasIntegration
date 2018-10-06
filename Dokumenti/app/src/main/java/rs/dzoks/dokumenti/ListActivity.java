package rs.dzoks.dokumenti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.w3c.dom.Document;

import java.util.List;

import rs.dzoks.dokumenti.model.DocumentSOAP;
import rs.dzoks.dokumenti.model.DocumentsListSOAP;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DocumentsAdapter adapter;
    private List<DocumentSOAP> documents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView=findViewById(R.id.recycler_view);
        DocumentsListSOAP documentList=(DocumentsListSOAP)getIntent().getExtras().getSerializable("documents");
        documents=documentList.getDocuments();
        adapter = new DocumentsAdapter(documents, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
