import React, { useState } from "react";
import FlightPriceList from "../components/FlightPrice/FlightPriceList";

function FlightList(props) {
  
  console.log(props);

  const [flightsPrice, setFlightsPrice] = useState([]);

  async function fetchFlightsHandler() {

    const url = "http://localhost:8080/flights/"+props.data.origin+"/"+props.data.destination+
    "/groupndate?date="+props.data.date+"&group="+props.data.group;
    const response = await fetch(url);
    const data = await response.json();
    console.log(props);
    setFlightsPrice(data);
  }

  return (
    <React.Fragment>
      <section>
        <button onClick={fetchFlightsHandler}>Fetch Flights</button>
        <FlightPriceList flightsPrice={flightsPrice} />
      </section>
    </React.Fragment>
  );
}

export default FlightList;

// 
  // const { origin, destination, date, group } = useParams();

  // useEffect( ()=> {
  //   async function fetchFlightsHandler() {

  //     const url = "http://localhost:8080/flights/"+origin+"/"+destination+"/groupndate?date="+date+"&group="+group;
  //     const response = await fetch(url);
  //     const data = await response.json();
  //     console.log(url);
  //     setFlightsPrice(data);
  //   }
  //   fetchFlightsHandler();
  // },[]);

