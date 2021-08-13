package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DetailNeighbourFavoriteEvent {

    public Neighbour neighbour;

    public DetailNeighbourFavoriteEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
