package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */
public class DeleteNeighbourEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;
    public String provenanceClassName;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteNeighbourEvent(Neighbour neighbour, String className) {
        this.neighbour = neighbour;
        this.provenanceClassName = className;
    }

    public boolean canDeleteNeighbour(String lol){
        return provenanceClassName.equals(lol);
    }
}
