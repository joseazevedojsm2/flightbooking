import React, { useState } from "react";
import "./App.css";
import Form from "./components/Form/Form";
import FlightList from "./Pages/FlightList";

function App() {
  const datery = {
    origin: "",
    destination: "",
    date: "",
    group: "",
  };

  const [dataQuery, setQueryData] = useState(datery);

  function formHandler(data) {
    setQueryData(data);
  }

  return (
    <React.Fragment>
      <section>
        <Form onSearchFormConfirm={formHandler}></Form>
        <FlightList data={dataQuery}></FlightList>
      </section>
    </React.Fragment>
  );
}

export default App;

// return (
//   <React.Fragment>
//       <section>
//         <Form onSearchFormConfirm={formHandler}></Form>
//         <button type="submit" form='searchForm' onClick={fetchFlightsHandler} o>Fetch Movies</button>
//       </section>
//       <Route path="/prices">
//         <FlightPriceList flightsPrice={flightsPrice} link={link} />
//       </Route>

//     </React.Fragment>

// setLink(
//   "http://localhost:8080/flights/" +
//     data.origin +
//     "/" +
//     data.destination +
//     "/groupndate?date=" +
//     data.date +
//     "&group=" +
//     data.group
// );
