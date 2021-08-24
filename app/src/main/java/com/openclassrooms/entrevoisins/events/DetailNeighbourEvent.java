package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DetailNeighbourEvent {

    public Neighbour neighbour;
    public String className;

    public DetailNeighbourEvent(Neighbour neighbour, String className) {
        this.neighbour = neighbour;
        this.className = className;
    }
}
