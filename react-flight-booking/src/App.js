import React, { useState } from 'react';
import './App.css';
import FlightPriceList from './components/FightPrice/FlightPriceList';


function App() {

  const [flightsPrice,setFlightsPrice] = useState([]);

  async function fetchFlightsHandler(){
    const response = await fetch('http://localhost:8080/flights/lisboa/madrid/groupndate?date=2022-11-01&group=2')
    
    const data = await response.json();
    setFlightsPrice(data);
  }

  return (
  <React.Fragment>
      <section>
        <button onClick={fetchFlightsHandler}>Fetch Movies</button>
      </section>
      <section>
        <FlightPriceList flightsPrice={flightsPrice} />
      </section>
    </React.Fragment>
  );
}

export default App;
