package com.openclassrooms.entrevoisins.di;

import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.DummyNeighbourFavoriteApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourFavoriteApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static final NeighbourApiService service = new DummyNeighbourApiService();
    private static final NeighbourFavoriteApiService neighbourFavoriteApiService = new DummyNeighbourFavoriteApiService();

    /**
     * Get an instance on @{@link NeighbourApiService}
     * @return
     */
    public static NeighbourApiService getNeighbourApiService() {
        return service;
    }

    /**
     * Get an instance on @{@link NeighbourFavoriteApiService}
     * @return
     */
    public static NeighbourFavoriteApiService getNeighbourFavoriteApiService() {
        return neighbourFavoriteApiService;
    }

    /**
     * Get always a new instance on @{@link NeighbourFavoriteApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static NeighbourFavoriteApiService getNewInstanceApiFavoriteService() {
        return new DummyNeighbourFavoriteApiService();
    }


    /**
     * Get always a new instance on @{@link NeighbourApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static NeighbourApiService getNewInstanceApiService() {
        return new DummyNeighbourApiService();
    }
}
