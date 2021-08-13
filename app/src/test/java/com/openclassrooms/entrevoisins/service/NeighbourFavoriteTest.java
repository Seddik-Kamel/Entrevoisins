package com.openclassrooms.entrevoisins.service;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class NeighbourFavoriteTest {

    private NeighbourFavoriteApiService serviceFavorite;
    private NeighbourApiService service;

    @Before
    public void setup(){
        serviceFavorite = DI.getNewInstanceApiFavoriteService();
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursFavoriteWithSuccess(){
        Neighbour neighbour = service.getNeighbours().get(0);
        serviceFavorite.createNeighbour(neighbour);
        List<Neighbour> neighbourListService = serviceFavorite.getNeighbours();
        DummyNeighbourGenerator.createNeighboursFavorite(neighbour);
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.generateNeighboursFavorite();
        assertThat(neighbourListService,IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        serviceFavorite.createNeighbour(neighbourToDelete);
        serviceFavorite.deleteNeighbour(neighbourToDelete);
        assertTrue(serviceFavorite.getNeighbours().isEmpty());
    }

    @Test
    public void CheckNeighbourWithSuccess() {
        Neighbour neighbourToCheck = service.getNeighbours().get(0);
        serviceFavorite.createNeighbour(neighbourToCheck);
        assertTrue(serviceFavorite.getNeighbours().contains(neighbourToCheck));
    }
}
