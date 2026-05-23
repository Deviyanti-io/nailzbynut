package com.example.nailzbynut;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment {

    private RecyclerView rvExplore;
    private ExploreNailAdapter adapter;
    private List<NailModel> nailList;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_explore, container, false);

        rvExplore = view.findViewById(R.id.rvExplore);
        rvExplore.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        nailList = new ArrayList<>();

        // --- KATEGORI 1: LILAC DREAM (Nuansa Ungu Estetik) ---
        // PERBAIKAN: Angka harga diubah menjadi teks "String" dengan tanda petik
        nailList.add(new NailModel("Lilac Dream Press-On #1", "95000", R.drawable.nail1));
        nailList.add(new NailModel("Lilac Dream Glossy #2", "95000", R.drawable.nail2));
        nailList.add(new NailModel("Lilac Dream Matte #3", "100000", R.drawable.nail3));
        nailList.add(new NailModel("Lilac Dream Chrome #4", "115000", R.drawable.nail4));
        nailList.add(new NailModel("Lilac Dream Diamond #5", "135000", R.drawable.nail5));

        // --- KATEGORI 2: SOFT PETAL (Nuansa Pink & Minimalis) ---
        nailList.add(new NailModel("Soft Petal Minimalist #6", "85000", R.drawable.nail6));
        nailList.add(new NailModel("Soft Petal Blush #7", "85000", R.drawable.nail7));
        nailList.add(new NailModel("Soft Petal French #8", "90000", R.drawable.nail8));
        nailList.add(new NailModel("Soft Petal Floral 3D #9", "120000", R.drawable.nail9));

        // --- KATEGORI 3: MIDNIGHT GLITZ (Nuansa Gelap & Mewah) ---
        nailList.add(new NailModel("Midnight Glitz Chrome #10", "125000", R.drawable.nail10));
        nailList.add(new NailModel("Midnight Glitz Galaxy #11", "125000", R.drawable.nail11));
        nailList.add(new NailModel("Midnight Glitz Shimmer #12", "130000", R.drawable.nail12));
        nailList.add(new NailModel("Midnight Glitz Cat Eye #13", "140000", R.drawable.nail13));

        // --- KATEGORI 4: MILKY ALMOND (Nuansa Klasik & Elegan) ---
        nailList.add(new NailModel("Milky Almond Classic #14", "90000", R.drawable.nail14));
        nailList.add(new NailModel("Milky Almond Ombre #15", "95000", R.drawable.nail15));
        nailList.add(new NailModel("Milky Almond Vanilla #16", "90000", R.drawable.nail16));
        nailList.add(new NailModel("Milky Almond Pearl #17", "110000", R.drawable.nail17));
        nailList.add(new NailModel("Milky Almond Marble #18", "120000", R.drawable.nail18));

        // --- KATEGORI 5: BERRY GLAZE (Nuansa Segar & Berkilau) ---
        nailList.add(new NailModel("Berry Glaze Shimmer #19", "110000", R.drawable.nail19));
        nailList.add(new NailModel("Berry Glaze Syrup #20", "105000", R.drawable.nail20));
        nailList.add(new NailModel("Berry Glaze Jelly #21", "105000", R.drawable.nail21));
        nailList.add(new NailModel("Berry Glaze Glitter #22", "115000", R.drawable.nail22));
        nailList.add(new NailModel("Berry Glaze Luxury 3D #23", "145000", R.drawable.nail23));
        nailList.add(new NailModel("Berry Glaze Special Nut #24", "150000", R.drawable.nail24));

        adapter = new ExploreNailAdapter(nailList);
        rvExplore.setAdapter(adapter);

        return view;
    }
}