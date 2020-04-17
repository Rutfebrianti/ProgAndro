package com.example.progandro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class AdapterDataMahasiswa extends FirestoreRecyclerAdapter<DataMahasiswa, AdapterDataMahasiswa.AdapterMahasiswaHolder> {

    private OnClickItemListener listener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterDataMahasiswa(@NonNull FirestoreRecyclerOptions<DataMahasiswa> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterMahasiswaHolder holder, int position, @NonNull DataMahasiswa model) {
        holder.textViewNama.setText(model.getNama());
        holder.textViewNim.setText(model.getNim());
        holder.textViewPhone.setText(model.getPhone());
    }

    @NonNull
    @Override
    public AdapterMahasiswaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new AdapterMahasiswaHolder(view);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public class AdapterMahasiswaHolder extends RecyclerView.ViewHolder {
        TextView textViewNama;
        TextView textViewPhone;
        TextView textViewNim;

        public AdapterMahasiswaHolder(@NonNull View itemView) {
            super(itemView);
            textViewNama = itemView.findViewById(R.id.namaMahasiswa);
            textViewNim = itemView.findViewById(R.id.nimMahasiswa);
            textViewPhone = itemView.findViewById(R.id.phoneMahasiswa);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onClickItem(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }
    public interface OnClickItemListener {
        void onClickItem(DocumentSnapshot documentSnapshot, int pos);
    }
    public void setOnClickItemListener(OnClickItemListener listener ) {
        this.listener = listener;
    }
}
