package rs.dzoks.dokumenti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Document;

import java.util.List;

import rs.dzoks.dokumenti.model.DocumentSOAP;

public class DocumentsAdapter extends RecyclerView.Adapter<DocumentsAdapter.DocumentViewHolder> {

    private List<DocumentSOAP> documents;
    private Activity activity;

    public DocumentsAdapter(List<DocumentSOAP> documents,Activity activity) {
        this.documents=documents;
        this.activity=activity;
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.document_card, parent, false);
        return new DocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder holder, final int position) {
        DocumentSOAP document=documents.get(position);
        holder.fname.setText(document.getFname());
        holder.lname.setText(document.getLname());
        holder.type.setText(document.getDocumentType());
        holder.jmbg.setText(document.getJmbg());
        holder.fname.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentSOAP document=documents.get(position);
                Intent intent=new Intent(activity,DocumentActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("document",document);
                intent.putExtras(bundle);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }


    class DocumentViewHolder extends RecyclerView.ViewHolder{

        TextView fname;
        TextView lname;
        TextView jmbg;
        TextView type;


        DocumentViewHolder(View itemView) {
            super(itemView);
            fname=itemView.findViewById(R.id.fname);
            lname=itemView.findViewById(R.id.lname);
            jmbg=itemView.findViewById(R.id.jmbg);
            type=itemView.findViewById(R.id.type);
        }


    }
}