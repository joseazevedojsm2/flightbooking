import {  useParams } from "react-router-dom";
import React from "react";
import PassangerForm from "../components/Passanger/PassangerForm";


const PassangerDetails = () => {

  const params = useParams();

  return <React.Fragment>
    
    <PassangerForm id={params.priceId}></PassangerForm>

  </React.Fragment>;
};

export default PassangerDetails;
