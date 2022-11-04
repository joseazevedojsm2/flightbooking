package com.solera.flightbooking.service;

import com.solera.flightbooking.entity.Company;
import com.solera.flightbooking.entity.Flight;
import com.solera.flightbooking.entity.Price;
import com.solera.flightbooking.repository.CompanyRepository;
import com.solera.flightbooking.repository.GroupsAgeRepository;
import com.solera.flightbooking.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PriceService {
    private PriceRepository repository;
    private GroupsAgeRepository groupsAgeRepository;
    private CompanyRepository companyRepository;
    private FlightService flightService;

    @Autowired
    public PriceService(PriceRepository priceRepository,GroupsAgeRepository groupsAgeRepository,
                        FlightService flightService, CompanyRepository companyRepository){
        repository = priceRepository;
        this.flightService = flightService;
        this.groupsAgeRepository = groupsAgeRepository;
        this.companyRepository = companyRepository;
    }

    public List<Price> getAllPrices(){
        return repository.findAll();
    }
    public List<Price> getAllPricesByCompany(String company){
        List<Price> prices = repository.findAll().stream()
                .filter(price -> price.getFlight().getCompany().getName().equalsIgnoreCase(company))
                .collect(Collectors.toList());

        if(prices.isEmpty())
            return null;

        return prices;
    }
    public List<Price> getAllPricesByFlight(int idFlight,int ageGroup){
        List<Price> prices = repository.findAll().stream()
                .filter(price -> {
                    return price.getFlight().getId() == idFlight &&
                            price.getGroupsAge().getId() == ageGroup;
                }).collect(Collectors.toList());

        if(prices.isEmpty())
            return null;

        return prices;
    }

    public List<Price> getAllPricesByDateAndGroupAndFlight(String origin, String destination,
                                                           LocalDate departure, int group,String bags,String airplane){

        List<Price> prices = repository.findAll().stream()
                .filter(price -> {
                    return price.getFlight().getRoute().isRoute(origin,destination) &&
                            price.getGroupsAge().getId() == group;
                }).collect(Collectors.toList());
        System.out.println(prices);

        List<Flight> flights = prices.stream().map(Price::getFlight).collect(Collectors.toList());
        System.out.println(flights);

        flights = flightService.listBetweenDates(flights,departure);
        System.out.println(flights);

        List<Flight> finalFlights = flights;

        prices = prices.stream().filter(price -> finalFlights.contains(price.getFlight())).collect(Collectors.toList());
        System.out.println(prices);

        if(bags!=null && airplane!=null)
                return prices.stream().filter(price -> {
                    return price.getFlight().getAllowLuggage()==true &&
                            price.getFlight().getCompany().getName().equalsIgnoreCase(airplane);
                }).collect(Collectors.toList());

        if(bags!=null)
            if(airplane==null)
                return prices.stream().filter(price -> {
                    return price.getFlight().getAllowLuggage().booleanValue() ;
                }).collect(Collectors.toList());

        if(airplane!=null)
            if(bags==null)
                return prices.stream().filter(price -> {
                    return price.getFlight().getCompany().getName().equalsIgnoreCase(airplane);
                }).collect(Collectors.toList());

        return prices;
    }

    public Price createPrice(Price price, int flightNumber,String name){
        Optional<Flight> flight = flightService.getAllFlights(name).stream()
                .filter(flight1 -> flight1.getId()==flightNumber).findFirst();

        if(flight.isEmpty())
            return null;

        price.setGroupsAge(groupsAgeRepository.findById(price.getGroupsAge().getId()).get());
        price.setFlight(flight.get());

        if(price.getPrice()<=0)
            return null;

        Price newPrice = repository.save(price);
        return newPrice;
    }

    public List<Company> getAllCompanys() {
        return companyRepository.findAll();
    }
}
