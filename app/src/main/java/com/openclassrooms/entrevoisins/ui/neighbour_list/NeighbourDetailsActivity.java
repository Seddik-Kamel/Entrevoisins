package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NeighbourDetailsActivity extends AppCompatActivity {

    private Neighbour neighbour;

    @BindView(R.id.neighbour_avatar)
    ImageView mNeighbourAvatar;
    @BindView(R.id.last_name)
    TextView lastName;
    @BindView(R.id.location_description)
    TextView locationDescription;
    @BindView(R.id.last_name_card)
    TextView lastNameCard;
    @BindView(R.id.phoneNumber_description)
    TextView phoneNumberDescription;
    @BindView(R.id.site_web_description)
    TextView siteWebDescription;
    @BindView(R.id.about_me_description)
    TextView aboutMeDescription;
    @BindView(R.id.add_favorite_button)
    FloatingActionButton addFavorite;
    @BindView(R.id.delete_favorite_button)
    FloatingActionButton deleteFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
        Objects.requireNonNull(getSupportActionBar()).hide();
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        neighbour = bundle.getParcelable(getString(R.string.neighbour_key));

        initFloatingActionButton();

        lastName.setText(neighbour.getName());
        locationDescription.setText(neighbour.getAddress());
        lastNameCard.setText(neighbour.getName());
        phoneNumberDescription.setText(neighbour.getPhoneNumber());
        aboutMeDescription.setText(neighbour.getAboutMe());

        String neighbourWebAddress = "www.facebook.fr/" + neighbour.getName().toLowerCase();
        siteWebDescription.setText(neighbourWebAddress);

        Glide.with(mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .into(mNeighbourAvatar);
    }

    public static void startNeighbourDetailsActivity(Context context, Neighbour neighbour) {
        Intent intent = new Intent(context, NeighbourDetailsActivity.class);
        intent.putExtra(context.getString(R.string.neighbour_key), neighbour);
        context.startActivity(intent);
    }

    private void initFloatingActionButton() {
        if (NeighbourFavoritesFragment.mApiService.checkNeighbourExist(neighbour)) {
            deleteFavorite.setVisibility(View.VISIBLE);
            addFavorite.setVisibility(View.GONE);
        } else {
            addFavorite.setVisibility(View.VISIBLE);
            deleteFavorite.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.come_back_button)
    void comeBack() {
        finish();
    }

    @OnClick(R.id.add_favorite_button)
    void addToFavorite() {
        NeighbourFavoritesFragment.mApiService.createNeighbour(neighbour);
        addFavorite.setVisibility(View.GONE);
        deleteFavorite.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.delete_favorite_button)
    void deleteToFavorite() {
        NeighbourFavoritesFragment.mApiService.deleteNeighbour(neighbour);
        addFavorite.setVisibility(View.VISIBLE);
        deleteFavorite.setVisibility(View.GONE);
    }
}