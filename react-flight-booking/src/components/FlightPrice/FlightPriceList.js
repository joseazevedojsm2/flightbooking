import React from 'react';

import classes from './FlightList.module.css';
import FlightPrice from './FlightPriceItem';

const FlightPriceList = (props) => {
  return (
    <ul className={classes['movies-list']}>
      {props.flightsPrice.map((flightPrice) => (
        <FlightPrice
          id={flightPrice.id}
          flight = {flightPrice.flight}
          price={flightPrice.price}
          addCost={flightPrice.additionalCost}
        />
      ))}
      {console.log(props)}
    </ul>
  );
};

export default FlightPriceList;
