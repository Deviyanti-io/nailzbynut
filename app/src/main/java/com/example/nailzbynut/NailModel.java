package com.example.nailzbynut;

import java.util.ArrayList;
import java.util.List;

public class NailModel {
    private String name;
    private String price;
    private int imageResId; // Digunakan untuk gambar lokal (drawable)
    private String imageUrl; // TODO: Teman Database - Digunakan saat mengambil URL gambar dari Firebase Storage
    private boolean isFavorite;

    // MANDATORI: List global statis sebagai keranjang penampung kuku yang disukai selama aplikasi berjalan
    public static List<NailModel> globalWishlist = new ArrayList<>();

    /**
     * 1. CONSTRUCTOR KOSONG (Wajib Ada)
     * Firebase Realtime Database / Firestore membutuhkan constructor kosong ini
     * untuk mengubah data JSON dari database menjadi objek Java secara otomatis.
     */
    public NailModel() {
    }

    /**
     * 2. CONSTRUCTOR UTAMA
     * Digunakan sekarang untuk inisialisasi data katalog kuku lokal di halaman Explore.
     */
    public NailModel(String name, String price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.isFavorite = false; // Default awal: tidak masuk favorit
    }

    // ==================== GETTER & SETTER INTERAKTIF ====================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}