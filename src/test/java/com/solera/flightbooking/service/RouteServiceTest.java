package com.solera.flightbooking.service;

import com.solera.flightbooking.entity.Place;
import com.solera.flightbooking.entity.Route;
import com.solera.flightbooking.repository.PlaceRepository;
import com.solera.flightbooking.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RouteServiceTest {

    @MockBean
    private RouteRepository routeRepository;
    @MockBean
    private PlaceRepository placeRepository;

    @InjectMocks
    private RouteService service;

    @Test
    void whenCompanyCreateRoute_withEmptyOrigin_shouldFail(){
        Place origin = new Place(1,"");
        Place destination = new Place(1,"Madrid");


        Mockito.when(placeRepository.findByName(origin.getName())).thenReturn(List.of());

        Route route = new Route(origin,destination);
        route = service.createRoute(route);

        assertEquals(null,route);
    }

    @Test
    void whenCompanyCreateRoute_withEmptyDestination_shouldFail(){
        Place origin = new Place(1,"Lisboa");
        Place destination = new Place(1,"");


        Mockito.when(placeRepository.findByName(destination.getName())).thenReturn(List.of());

        Route route = new Route(origin,destination);
        route = service.createRoute(route);

        assertEquals(null,route);
    }

    @Test
    void whenCompanyInsertRoute_withCorrectExistentData_shouldSucess(){
        Place origin = new Place(1,"Lisboa");
        Place destination = new Place(2,"Madrid");
        Route newRoute = new Route(1,origin,destination);

        Mockito.when(placeRepository.findByName(origin.getName())).thenReturn(List.of());
        Mockito.when(placeRepository.findByName(destination.getName())).thenReturn(List.of());
        Mockito.when(routeRepository.save(any(Route.class))).thenReturn(newRoute);

        Route route = service.createRoute(newRoute);

        assertEquals(newRoute,route);
    }

    @Test
    void whenCompanyInsertRoute_withCorrectNonExistantData_shouldSucess(){
        Place origin = new Place(1,"Lisboa");
        Place destination = new Place(2,"Madrid");
        Route newRoute = new Route(origin,destination);


        Mockito.when(placeRepository.findByName(origin.getName())).thenReturn(List.of());
        Mockito.when(placeRepository.findByName(destination.getName())).thenReturn(List.of());

        Mockito.when(placeRepository.save(origin)).thenReturn(origin);
        Mockito.when(placeRepository.save(destination)).thenReturn(destination);

        Mockito.when(routeRepository.save(any(Route.class))).thenReturn(newRoute);

        Route route = service.createRoute(newRoute);

        assertEquals(newRoute,route);
    }

    @ParameterizedTest
    @CsvSource({"Wrong,Madrid","Madrid,Wrong"})
    void whenCompanyGetIdRouteByNames_whenWrongNames_shouldFail(String origin,String destination){
        Mockito.when(routeRepository.findAll()).thenReturn(List.of());
        Route id = service.getRouteByNames(origin,destination);

        assertEquals(null,id);
    }

    @ParameterizedTest
    @CsvSource({"Lisboa,Madrid","Madrid,Lisboa"})
    void whenCompanyGetRouteByNames_whenCorrectNames_shouldSucess(String origin,String destination){
        Place originP = new Place(1,origin);
        Place destinationP = new Place(2,destination);

        Mockito.when(routeRepository.findAll()).thenReturn(List.of(
                new Route(1,originP,destinationP)
                ));

        Route idRoute = service.getRouteByNames(origin,destination);

        assertEquals(1,idRoute.getId());
    }

}
