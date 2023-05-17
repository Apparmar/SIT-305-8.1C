package com.example.a8_1c;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a8_1c.data.DatabaseHelper;
import com.example.a8_1c.model.User;
import com.example.a8_1c.model.UserPlaylist;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link playlist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class playlist extends Fragment implements playlistAdaptor.ItemClickListener {

    DatabaseHelper db;
    List<UserPlaylist> itemList = new ArrayList<>();

    RecyclerView recyclerview;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    // TODO: Rename and change types of parameters
    private String mParam1;

    public playlist() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static playlist newInstance(String param1) {
        playlist fragment = new playlist();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Integer userId = getArguments().getInt("userId");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Integer userId = getArguments().getInt("userId");

        db= new DatabaseHelper(getContext());
        itemList = db.fetchPl(userId);

        recyclerview = view.findViewById(R.id.playlistItems);
        recyclerview.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        playlistAdaptor playlistAdaptor = new playlistAdaptor(itemList, getContext(), this);
        recyclerview.setAdapter(playlistAdaptor);
        playlistAdaptor.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(UserPlaylist item) {

    }
}