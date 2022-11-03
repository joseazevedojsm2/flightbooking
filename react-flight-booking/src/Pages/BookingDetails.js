import React from "react";
import { useParams } from "react-router-dom";
import { useState,useEffect,useRef } from "react";


const BookingDetails = () => {

  const dataFetchedRef = useRef(false);

  const params = useParams();

  const [booking, setBooking] = useState();

  useEffect(() => {
    if (dataFetchedRef.current) return;
      dataFetchedRef.current = true;

    async function fetchCities() {
        const url = "http://localhost:8080/booking/"+params.priceId+"/"+params.idPassanger;
        const response = await fetch(url,{
            method:"POST"
        });

        const data = await response.json();
        console.log(data)
        setBooking(data);
    }
    fetchCities();
  }, []);

  return (
    <React.Fragment>
      <h1>
        {params.priceId}:{params.idPassanger}
      </h1>
    </React.Fragment>
  );
};
export default BookingDetails;
