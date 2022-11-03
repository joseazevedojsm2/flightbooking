import React from 'react';
import Flight from '../Flight/Flight';

import classes from './Flight.module.css';

const FlightPrice = (props) => {
  return (
    <li className={classes.movie}>
      <h2>{props.id}</h2>
      <h3><Flight flight = {props.flight}> </Flight></h3>
      <p>{props.price}</p>
    </li>
  );
};

export default FlightPrice;
