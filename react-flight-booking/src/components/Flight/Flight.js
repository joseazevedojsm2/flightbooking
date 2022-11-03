import React from 'react';

const Flight = (props) => {
    return(
        <div>
            <h3> Departure {props.flight.route.origin.name} </h3>
            <h3> Arrival {props.flight.route.destination.name} </h3>
            <h3> Departure date {props.flight.departureDate} </h3>
            <h3> Arrival date {props.flight.arrivalDate} </h3>
            <h3> Airline {props.flight.company.name} </h3>
        </div>
    );
}

export default Flight;