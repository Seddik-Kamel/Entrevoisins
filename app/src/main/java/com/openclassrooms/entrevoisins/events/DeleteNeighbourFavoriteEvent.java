package com.openclassrooms.entrevoisins.events;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteNeighbourFavoriteEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */

    public DeleteNeighbourFavoriteEvent(Neighbour neighbour) {
        Log.d("Test",getClass().getSimpleName());
        this.neighbour = neighbour;
    }
}
