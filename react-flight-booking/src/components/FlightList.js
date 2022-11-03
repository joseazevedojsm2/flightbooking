import React from 'react';

import Flight from './Flight';
import classes from './FlightList.module.css';

const FlightList = (props) => {
  return (
    <ul className={classes['movies-list']}>
      {props.flights.map((flightPrice) => (
        <Flight
          id={flightPrice.id}
          flight = {flightPrice.flight}
          price={flightPrice.price}
        />
      ))}
    </ul>
  );
};

export default FlightList;
