import React from 'react';

import classes from './Flight.module.css';

const Flight = (props) => {
  return (
    <li className={classes.movie}>
      <h2>{props.id}</h2>
      <h3>{props.flight}</h3>
      <p>{props.price}</p>
    </li>
  );
};

export default Flight;
