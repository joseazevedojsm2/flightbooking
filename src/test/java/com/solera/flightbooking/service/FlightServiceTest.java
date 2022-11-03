package com.solera.flightbooking.service;

import com.solera.flightbooking.entity.Company;
import com.solera.flightbooking.entity.Flight;
import com.solera.flightbooking.entity.Place;
import com.solera.flightbooking.entity.Route;
import com.solera.flightbooking.repository.CompanyRepository;
import com.solera.flightbooking.repository.FlightRepository;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @MockBean
    private FlightRepository repository;
    @MockBean
    private RouteRepository routeRepository;
    @MockBean
    private CompanyRepository companyRepository;

    @InjectMocks
    private FlightService service;
    @InjectMocks
    private RouteService routeService;

    @ParameterizedTest
    @ValueSource(strings = {"wrong",""})
    void whenUserGetAllFlights_withWrongCompanyName_shouldFail(String companyName){

        Mockito.when(repository.findAll()).thenReturn(List.of());

        List<Flight> flightList = service.getAllFlights(companyName);

        assertEquals(null,flightList);
    }

    @ParameterizedTest
    @ValueSource(strings = {"AirFrance"})
    void whenUserGetAllFlights_withCorrectCompanyName_shouldFail(String companyName){
        Flight flight = new Flight(1, LocalDate.now(),LocalDate.now(),LocalTime.now(),LocalTime.now());
        Company company = new Company(companyName,true);
        flight.setCompany(company);

        Mockito.when(repository.findAll()).thenReturn(List.of(flight));

        List<Flight> flightList = service.getAllFlights(companyName);

        assertEquals(flight,flightList.get(0));
    }

    @ParameterizedTest
    @CsvSource({"AirFrance,1999-11-03,Madrid,Lisboa"})
    void whenUserRequestAllFlights_withNonExistantDatesAndRoute_shouldFail(String companyName, String dateDep,  String origin, String destination){
        LocalDate dateDeparture = LocalDate.parse(dateDep);

        Flight flight = new Flight(1, LocalDate.now(),dateDeparture,LocalTime.now(),LocalTime.now());
        Company company = new Company(companyName,true);
        flight.setCompany(company);

        Mockito.when(repository.findAll()).thenReturn(List.of());

        List<Flight> flightList = service.getAllFlightsByDateAndRoute(companyName,origin,destination,dateDeparture);

        assertEquals(null,flightList);
    }

    @ParameterizedTest
    @CsvSource({"AirFrance,2022-11-03,Madrid,Lisboa"})
    void whenUserRequestAllFlights_withExistantDatesAndRoute_shouldSucess(String companyName, String dateDep, String origin, String destination){
        LocalDate dateDeparture = LocalDate.parse(dateDep);
        Route route = new Route(new Place(2,origin),new Place(1,destination));

        Flight flight = new Flight(1, LocalDate.now(),dateDeparture,LocalTime.now(),LocalTime.now());
        Company company = new Company(companyName,true);
        flight.setCompany(company);
        flight.setRoute(route);

        Mockito.when(repository.findAll()).thenReturn(List.of(
                flight
        ));

        List<Flight> flightList = service.getAllFlightsByDateAndRoute(companyName,origin,destination,dateDeparture);

        assertEquals(flight,flightList.get(0));
    }

    @Test
    void whenUserCreatesFlight_withWrongDates_shouldFail(){
        LocalDate dateDeparture = LocalDate.parse("2022-10-31");
        Flight flight = new Flight(1, dateDeparture,dateDeparture,LocalTime.now(),LocalTime.now());
        Company company = new Company("AirFrance",true);
        flight.setCompany(company);

        Flight newFlight = service.createFlight(flight,flight.getCompany().getName());

        assertEquals(null,newFlight);
    }

    @Test
    void whenUserCreatesFlight_withWrongCompanyName_shouldFail(){
        LocalDate dateDeparture = LocalDate.parse("2022-11-30");
        Flight flight = new Flight(1, dateDeparture,dateDeparture,LocalTime.now(),LocalTime.now());
        Company company = new Company("AirFrance",true);
        flight.setCompany(company);

        Mockito.when(companyRepository.findAll()).thenReturn(List.of());

        Flight newFlight = service.createFlight(flight,flight.getCompany().getName());

        assertEquals(null,newFlight);
    }

    @Test
    void whenUserCreatesFlight_withNoRoute_shouldFail(){
        LocalDate dateDeparture = LocalDate.parse("2022-11-30");
        Flight flight = new Flight(1, dateDeparture,dateDeparture,LocalTime.now(),LocalTime.now());
        Company company = new Company("AirFrance",true);
        flight.setCompany(company);

        Flight newFlight = service.createFlight(flight,flight.getCompany().getName());

        assertEquals(null,newFlight);
    }

    @Test
    void whenUserCreatesFlight_withCorrect_shouldSucess(){
        LocalDate dateDeparture = LocalDate.parse("2022-11-30");
        Flight flight = new Flight(1, dateDeparture,dateDeparture,LocalTime.now(),LocalTime.now());
        Company company = new Company("AirFrance",true);
        flight.setCompany(company);

        Mockito.when(routeRepository.findAll()).thenReturn(List.of());
        Mockito.when(routeRepository.save(any(Route.class))).thenReturn(new Route(3,new Place(), new Place()));

        Flight newFlight = service.createFlight(flight,flight.getCompany().getName());

        assertEquals(3,newFlight.getRoute().getId());
    }

}
