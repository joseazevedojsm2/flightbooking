import React from 'react';

import classes from './FlightList.module.css';
import FlightPrice from './FlightPrice';

const FlightPriceList = (props) => {
  return (
    <ul className={classes['movies-list']}>
      {props.flightsPrice.map((flightPrice) => (
        <FlightPrice
          id={flightPrice.id}
          flight = {flightPrice.flight}
          price={flightPrice.price}
        />
      ))}
    </ul>
  );
};

export default FlightPriceList;
