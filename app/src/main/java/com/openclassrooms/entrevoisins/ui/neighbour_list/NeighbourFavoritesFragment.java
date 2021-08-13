package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourFavoriteEvent;
import com.openclassrooms.entrevoisins.events.DetailNeighbourFavoriteEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourFavoriteApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NeighbourFavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NeighbourFavoritesFragment extends Fragment {

    public static NeighbourFavoriteApiService mApiService;
    private List<Neighbour> mNeighbours;

    private RecyclerView mRecyclerView;

    public NeighbourFavoritesFragment() {
        // Required empty public constructor
    }

    public static NeighbourFavoritesFragment newInstance() {
        return new NeighbourFavoritesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourFavoriteApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_favorite_neighbour, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }

    private void initList() {
        mNeighbours = mApiService.getNeighbours();
        if (mNeighbours != null)
            mRecyclerView.setAdapter(new MyNeighbourFavoriteRecyclerViewAdapter(mNeighbours));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteFavoriteNeighbour(DeleteNeighbourFavoriteEvent event) {
        Log.d("Test", "onDeleteFavoriteNeighbour");
        mNeighbours.remove(event.neighbour);
        initList();
    }

    /**
     * Fired if the user clicks on a neighbour
     *
     * @param event
     */
    @Subscribe
    public void onDetailNeighbour(DetailNeighbourFavoriteEvent event) {
        NeighbourDetailsActivity.startNeighbourDetailsActivity(getContext(), event.neighbour);
    }
}