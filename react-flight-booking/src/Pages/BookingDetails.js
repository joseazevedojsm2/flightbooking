import React from "react";
import { useLocation, useParams } from "react-router-dom";
import { useState,useEffect } from "react";


const BookingDetails = () => {

  const params = useParams();
  const [booking, setBooking] = useState("");

useEffect( () => {
  async function fetchBooking() {
    const url = "http://localhost:8080/analytics/"+params.idFlight+"/"+params.idPassanger;
    const response = await fetch(url);
    const data = await response.json();
    setBooking(data);
   }
  fetchBooking();
  }, []);


  return (
      <React.Fragment>
        <h3>{JSON.stringify(booking)}</h3>
    </React.Fragment>
  );
}
export default BookingDetails;
