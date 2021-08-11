package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

public
class DummyNeighbourFavoriteApiService implements NeighbourFavoriteApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.neighbours;

    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public boolean checkNeighbourExist(Neighbour neighbour) {
        return neighbours.contains(neighbour);
    }
}
