package com.solera.flightbooking.service;

import com.solera.flightbooking.entity.Place;
import com.solera.flightbooking.entity.Route;
import com.solera.flightbooking.repository.PlaceRepository;
import com.solera.flightbooking.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RouteService {
    private RouteRepository repository;
    private PlaceRepository repositoryPlace;

    @Autowired
    public RouteService(RouteRepository routeRepository, PlaceRepository placeRepository){
        repository = routeRepository;
        repositoryPlace = placeRepository;
    }


    public List<Route> getAllRoutes() {
        return repository.findAll();
    }

    public Route getRouteByNames(String origin, String destination) {
        Optional<Route> route = repository.findAll().stream()
                .filter(rts -> (rts.getOrigin().getName().equalsIgnoreCase(origin)
                        && rts.getDestination().getName().equalsIgnoreCase(destination)))
                .findFirst();

        if(route.isEmpty())
            return null;

        return route.get();
    }
    public Route createRoute(Route route) {
        Place origin = route.getOrigin();
        Place destination = route.getDestination();

        Optional<Place> fkOrigin = repositoryPlace.findByName(origin.getName()).stream().findFirst();
        Optional<Place> fkDestination = repositoryPlace.findByName(destination.getName()).stream().findFirst();

        if(fkOrigin.isEmpty()){
            if(route.getOrigin().getName().isEmpty())
                return null;
            route.setOrigin(repositoryPlace.save(route.getOrigin()));
        } else {
            route.setOrigin(fkOrigin.get());
        }

        if(fkDestination.isEmpty()){
            if(route.getDestination().getName().isEmpty())
                return null;
            route.setDestination(repositoryPlace.save(route.getDestination()));
        } else{
            route.setDestination(fkDestination.get());
        }

        Route createdRoute = repository.save(route);

        return createdRoute;
    }


}
