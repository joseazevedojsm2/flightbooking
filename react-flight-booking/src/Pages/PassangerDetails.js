import { Link, useParams,useLocation } from "react-router-dom";
import React from "react";
import PassangerForm from "../components/Passanger/PassangerForm";
import { useState } from "react";

const PassangerDetails = (props) => {
  const params = useParams();
  const location = useLocation();

  const [dataQuery, setQueryData] = useState();
  const [idPassanger, setIdPassanger] = useState();
  const [idFlight, setIdFlight] = useState();
  

  function formHandler(data) {
    setQueryData(data);
  }
  const price = location.state.price;
  const addCost = location.state.price;
  const total =  price + addCost;

  async function addPassanger() {
    var response = await fetch("http://localhost:8080/booking/passanger", {
      method: "POST",
      body: JSON.stringify(dataQuery),
      headers: {
        "Content-Type": "application/json",
      },
    });

    var data = await response.json();
    console.log(data);
    setIdPassanger(data.id);

    const url =
      "http://localhost:8080/booking/" +
      params.priceId +
      "/" +
      data.id;

    response = await fetch(url, {
      method: "POST",
    });

    data = await response.json();
    console.log(data);
    setIdFlight(data.price.flight.id);
  }


  return (
    <React.Fragment>
      <PassangerForm id={params.priceId} onSubmit={formHandler} price={price} total={total}></PassangerForm>
      <button onClick={addPassanger}>Buy</button>
      <Link to={`/pFlight/${idFlight}/${idPassanger}`}>
        <button>See Reservation</button>
      </Link>
    </React.Fragment>
  );
};

export default PassangerDetails;
