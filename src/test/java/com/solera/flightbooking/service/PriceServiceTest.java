package com.solera.flightbooking.service;

import com.solera.flightbooking.entity.Company;
import com.solera.flightbooking.entity.Flight;
import com.solera.flightbooking.entity.GroupsAge;
import com.solera.flightbooking.entity.Price;
import com.solera.flightbooking.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {
    @MockBean
    private PriceRepository repository;

    @InjectMocks
    private PriceService service;

    @Test
    void whenUserRequestPrices_withWrongCompany_shouldSucess(){
        Company company = new Company(1,"AirFrance",true);

        Mockito.when(repository.findAll()).thenReturn(List.of());
        List<Price> prices = service.getAllPricesByCompany(company.getName());

        assertEquals(null,prices);
    }
    @Test
    void whenUserRequestPrices_withCorrectCompany_shouldSucess(){
        Company company = new Company(1,"AirFrance",true);

        Flight flight = new Flight();
        flight.setCompany(company);

        Price price = new Price(1,10.0,10.0);
        price.setFlight(flight);

        Mockito.when(repository.findAll()).thenReturn(List.of(
                price
        ));

        List<Price> prices = service.getAllPricesByCompany(company.getName());
        assertEquals(price,prices.get(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,-2})
    void whenUserRequestAllPrices_withNonExistantFlight_shouldFail(int idFlight){
        Flight flight = new Flight(idFlight, LocalDate.now(),LocalDate.now(), LocalTime.now(),LocalTime.now());

        Mockito.when(repository.findAll()).thenReturn(List.of());

        List<Price> priceList = service.getAllPricesByFlight(flight.getId(),1);

        assertEquals(null,priceList);
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    void whenUserRequestAllPrices_withCorrectFlight_shouldSucess(int idFlight){
        Flight flight = new Flight(idFlight, LocalDate.now(),LocalDate.now(), LocalTime.now(),LocalTime.now());
        Price price = new Price(1,10.0,10.0);
        GroupsAge groupsAge = new GroupsAge(1,"<2");
        price.setGroupsAge(groupsAge);
        price.setFlight(flight);

        Mockito.when(repository.findAll()).thenReturn(List.of(
                price
        ));

        List<Price> priceList = service.getAllPricesByFlight(flight.getId(),1);

        assertEquals(price.getId(),priceList.get(0).getId());
    }

}
