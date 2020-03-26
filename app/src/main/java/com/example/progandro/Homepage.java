package com.example.progandro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends Fragment {
    private List<MovieRecyclerItem> movieRecyclerItemList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        super.onCreateView(inflater, viewGroup, savedInstanceState);
        View rootView = inflater.inflate(R.layout.home_fragment, viewGroup, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), movieRecyclerItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        return rootView;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieRecyclerItemList = new ArrayList<>();

        movieRecyclerItemList.add(new MovieRecyclerItem(R.drawable.bloodshot, "Bloodshot",
                "Berdasarkan buku komik terlaris, dibintangi Vin Diesel sebagai Ray Garrison, seorang prajurit yang terbunuh dalam tugasnya dan dihidupkan kembali sebagai pahlawan super oleh perusahaan RST dengan sepasukan nanoteknologi di nadinya."));

        movieRecyclerItemList.add(new MovieRecyclerItem(R.drawable.brahms, "Brahms",
                "Sebuah keluarga muda memutuskan pindah untuk memulai hidup baru. Namun di rumah baru tersebut, Liza (Katie Holmes) mendapati anaknya, Jude (Christopher Convery) berteman dengan boneka yang sangat mirip dengan manusia yang diberi nama Brahms."));

        movieRecyclerItemList.add(new MovieRecyclerItem(R.drawable.coldplay, "Coldplay",
                "Coldplay: A Head Full of Dreams adalah film dokumenter musik yang disutradarai oleh Mat Whitecross tentang band Inggris Coldplay, mendokumentasikan awal mereka dan naik ke ketenaran."));

        movieRecyclerItemList.add(new MovieRecyclerItem(R.drawable.endgame, "Avengers Endgame",
                "Melanjutkan Avengers Infinity War, dimana kejadian setelah Thanos berhasil mendapatkan semua infinity stones dan memusnahkan 50% semua mahluk hidup di alam semesta."));

        movieRecyclerItemList.add(new MovieRecyclerItem(R.drawable.godzilla, "Godzilla",
                "ilm ini menceritakan kembali asal muasal Godzilla pada masa kini sebagai \"kekuatan menakutkan dari alam\"."));

        movieRecyclerItemList.add(new MovieRecyclerItem(R.drawable.ironman, "Ironman",
                "Dalam kunjungannya, Toni Stark tertangkap dan disandera oleh militan Afghanistan. Di dalam goa, diam-diam ia membangun sebuah senjata lapis baja anti peluru untuk melawan musuh dan meloloskan diri."));

        movieRecyclerItemList.add(new MovieRecyclerItem(R.drawable.sherlock, "Sherlock Holmes",
                "Sherlock Holmes dan rekannya Dr Watson telah berhasil memenjarakan Lord Blackwood, seorang pembunuh berantai ditakuti yang kemudian dieksekusi. Namun, Blackwood tampaknya telah kembali dari kematian."));
    }
}
