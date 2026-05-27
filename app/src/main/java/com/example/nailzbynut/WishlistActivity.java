package com.example.nailzbynut;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WishlistActivity extends AppCompatActivity {

    private RecyclerView rvWishlist;
    private LinearLayout layoutEmptyState;
    private ImageView btnBack;
    private ExploreNailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        // Inisialisasi Komponen UI
        rvWishlist = findViewById(R.id.rv_wishlist);
        layoutEmptyState = findViewById(R.id.layout_empty_state);
        btnBack = findViewById(R.id.btn_back_wishlist);

        rvWishlist.setLayoutManager(new LinearLayoutManager(this));

        // INTERAKTIF: Fungsi klik tombol kembali ke halaman sebelumnya
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Pasang adapter ke RecyclerView
        adapter = new ExploreNailAdapter(NailModel.globalWishlist);
        rvWishlist.setAdapter(adapter);

        // Cek kondisi data saat pertama kali dibuka
        checkWishlistData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        checkWishlistData();
    }

    private void checkWishlistData() {
        if (NailModel.globalWishlist == null || NailModel.globalWishlist.isEmpty()) {
            if (layoutEmptyState != null) layoutEmptyState.setVisibility(View.VISIBLE);
            if (rvWishlist != null) rvWishlist.setVisibility(View.GONE);
        } else {
            if (layoutEmptyState != null) layoutEmptyState.setVisibility(View.GONE);
            if (rvWishlist != null) rvWishlist.setVisibility(View.VISIBLE);
        }
    }
}