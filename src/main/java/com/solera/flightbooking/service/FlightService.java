package com.solera.flightbooking.service;

import com.solera.flightbooking.entity.Company;
import com.solera.flightbooking.entity.Flight;
import com.solera.flightbooking.entity.Route;
import com.solera.flightbooking.repository.CompanyRepository;
import com.solera.flightbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class FlightService {
    private FlightRepository repository;
    private CompanyRepository companyRepository;
    private RouteService routeService;

    @Autowired
    public FlightService(FlightRepository flightRepository, CompanyRepository companyRepository,RouteService routeService){
        repository = flightRepository;
        this.companyRepository = companyRepository;
        this.routeService = routeService;
    }

    public long getDaysDifference(long differenceDays){
        long xDays = 3 - differenceDays;
        if(differenceDays < 0)
            xDays = Math.abs(differenceDays);
        else if (differenceDays==0)
            xDays = 0;

        return xDays;
    }
    public Boolean actualDateIsBetweenDates(LocalDate actualDate, LocalDate departureAfterDate,
                                    LocalDate departureBeforeDate,long after,long before){
        return ( actualDate.isBefore(departureBeforeDate.plusDays(before)) &&
                actualDate.isAfter(departureAfterDate.minusDays(after)) );

    }

    public List<Flight> listBetweenDates(List<Flight> flightList, LocalDate dateDeparture){
        List<Flight> flightListBetweenDates;

        long differenceDays = DAYS.between(LocalDate.now(), dateDeparture);

        // {>= -1L  - +1L  <=}
        if( differenceDays < 3) {
            flightListBetweenDates = flightList.stream().filter( flight ->
                    actualDateIsBetweenDates(flight.getDepartureDate(),LocalDate.now(),dateDeparture,1L,(3L+getDaysDifference(differenceDays)+1L))).collect(Collectors.toList());
        }else {
            flightListBetweenDates = flightList.stream().filter(flight ->
                    actualDateIsBetweenDates(flight.getDepartureDate(),dateDeparture,dateDeparture,(3L+1L),(3L+1L))).collect(Collectors.toList());
        }

        return flightListBetweenDates;
    }
    public List<Flight> getAllFlights(String company){
        if(company.isEmpty())
            return null;

        List<Flight> flightList = repository.findAll().stream().filter(flight -> flight.getCompany().getName().equalsIgnoreCase(company))
                .collect(Collectors.toList());

        if(flightList.isEmpty())
            return null;

        return flightList;
    }

    public List<Flight> getAllFlightsByDateAndRoute(String companyName, String origin, String destination, LocalDate dateDeparture) {
        List<Flight> flightList = repository.findAll().stream()
                .filter(flight -> {
                    return flight.getCompany().getName().equalsIgnoreCase(companyName)
                            && flight.getRoute().isRoute(origin,destination);
                }).collect(Collectors.toList());

        if(flightList.isEmpty())
            return null;

        List<Flight> flightListBetweenDates = listBetweenDates(flightList,dateDeparture);

        if(flightListBetweenDates.isEmpty())
            return null;

        return flightListBetweenDates;
    }

    public Flight createFlight(Flight flight, String companyName) {

        if(flight.getDepartureDate().isBefore(LocalDate.now()) || flight.getArrivalDate().isBefore(LocalDate.now()))
            return null;

        Optional<Company> company = companyRepository.findAll()
                .stream().filter(company1 -> company1.getName().equalsIgnoreCase(companyName)).findFirst();

        if(company.isEmpty())
            return null;

        if(flight.getRoute() == null || flight.getRoute().getOrigin().getName().isEmpty()
                || flight.getRoute().getDestination().getName().isEmpty())
            return null;

        Route route = routeService.getRouteByNames(flight.getRoute().getOrigin().getName(),flight.getRoute().getDestination().getName());

        if(route==null){
            route = routeService.createRoute(flight.getRoute());
        }

        flight.setRoute(route);
        flight.setCompany(company.get());

        Flight newFlight = repository.save(flight);

        return newFlight;
    }
}

/*

    //LocalDate depDate = flight.getDepartureDate();
    //return ( depDate.isBefore(dateDeparture.plusDays(3L + finalXDays + 1L)) &&
    //        depDate.isAfter(LocalDate.now().minusDays(1L)) );



    LocalDate depDate = flight.getDepartureDate();
                return ( depDate.isAfter(dateDeparture.minusDays(3+1)) &&
                        depDate.isBefore(dateDeparture.plusDays(3+1)) );*/
