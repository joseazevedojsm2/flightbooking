import React from "react";
import Flight from "../Flight/Flight";
import {  Link } from "react-router-dom";

import classes from "./Flight.module.css";

const FlightPriceItem = (props) => {
  return (
    <li className={classes.movie}>
      <h2>
        <Flight flight={props.flight}> </Flight>
      </h2>
      
      <p>{props.price}</p>

      <Link to={`/pFlight/${props.id}`}>
        <button>Select</button>
      </Link>

    </li>
  );
};

export default FlightPriceItem;
