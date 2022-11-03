import {Link, useParams } from "react-router-dom";
import React from "react";
import PassangerForm from "../components/Passanger/PassangerForm";
import { useState } from "react";

const PassangerDetails = () => {
  const params = useParams();

  const [dataQuery, setQueryData] = useState();
  const [idPassanger, setIdPassanger] = useState();

  function formHandler(data) {
    setQueryData(data);
  }

  async function addPassanger() {
    console.log(dataQuery);
    const response = await fetch("http://localhost:8080/booking/passanger", {
      method: "POST",
      body: JSON.stringify(dataQuery),
      headers: {
        "Content-Type": "application/json",
      },
    });

    const data = await response.json();
    setIdPassanger(data.id);
  }

  return (
    <React.Fragment>
      <PassangerForm id={params.priceId} onSubmit={formHandler}></PassangerForm>
        <button onClick={addPassanger}>Save</button>    
      <Link to={`/pFlight/${params.priceId}/${idPassanger}`}>
        <button onClick={addPassanger}>See Reservation</button>
      </Link>
    </React.Fragment>
  );
};

export default PassangerDetails;
