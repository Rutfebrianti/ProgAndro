package com.example.progandro;

import com.google.firebase.firestore.PropertyName;

public class DataMahasiswa {
    private String nama;
    private String phone;
    private String nim;

    //empty constructor needed
    public DataMahasiswa() {

    }

    public DataMahasiswa(String nama, String phone, String nim) {
        this.nama = nama;
        this.phone = phone;
        this.nim = nim;
    }

    @PropertyName("Nama")
    public String getNama() {
        return nama;
    }
    @PropertyName("Phone")
    public String getPhone() {
        return phone;
    }
    @PropertyName("NIM")
    public String getNim() {
        return nim;
    }
}
