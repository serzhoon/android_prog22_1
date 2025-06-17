package com.example.lab12_13;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class RecordsFragment extends Fragment {

    public RecordsFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_records, container, false);
        ListView recordsListView = root.findViewById(R.id.recordsListView);
        ArrayList<String> records = new ArrayList<>();
        records.add("Score: 10");
        records.add("Score: 20");
        records.add("Score: 15");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, records);
        recordsListView.setAdapter(adapter);
        return root;
    }
}