import logo from './logo.svg';
import './App.css';
import { useState } from 'react';
import React from 'react';
import FlightList from './components/FlightList';


function App() {

  const [flights,setFlights] = useState([]);

  function fetchFlightsHandler(){
    fetch('http://localhost:8080/flights/prices').then(response =>{
      return response.json();
    }).then(data => {
      setFlights(data);
    })
  }

  return (
  <React.Fragment>
      <section>
        <button onClick={fetchFlightsHandler}>Fetch Movies</button>
      </section>
      <section>
        <FlightList flights={flights} />
      </section>
    </React.Fragment>
  );
}

export default App;
