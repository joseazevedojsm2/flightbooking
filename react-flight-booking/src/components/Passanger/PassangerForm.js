import "./PassangerForm.css";
import React, { useState } from "react";


const PassangerForm = (props) => {
  console.log(props);
  const[fName,setFName] = useState("");
  const[lName,setLName] = useState("");
  const [nacionality, setNacionality] = useState("");
  const [identification,setIdentification] = useState("");
  const [bags, setBags] = useState("");
  const [group,setGroup] = useState("");
  const [price,setPrice] = useState(props.price);

  const fNameChangeHandler = (event) => {
    setFName(event.target.value);
  };

  const lNameChangeHandler = (event) => {
    setLName(event.target.value);
  };

  const nacionalChangeHandler = (event) => {
    setNacionality(event.target.value);
  };

  const indentChangeHandler = (event) => {
    setIdentification(event.target.value);
  };

  const bagshangeHandler = (event) => {
    setPrice(props.total);
    setBags(event.target.value);
  };

  const groupChangeHandler = (event) => {
    setGroup(event.target.value);
  };

  const submitHandler = (event) => {
    event.preventDefault();
    const data = {
      fName:fName,
      lName:lName,
      nacionality:nacionality,
      identification: identification,
      bags:bags,
      groupAge:group
    } 
    props.onSubmit(data);
  };


  return (
    <form onBlur={submitHandler}>

      <h1>Passanger Details</h1>

      <fieldset>
        <label for="fname">First Name:</label>
        <input type="text" id="fname" name="first_name" onChange={fNameChangeHandler} />

        <label for="lname">Last Name:</label>
        <input type="text" id="lname" name="last_name" onChange={lNameChangeHandler} />

        <label for="nacionality">Nacionality:</label>
        <input type="text" id="nacionality" name="nacional" onChange={nacionalChangeHandler} />

        <label for="identification">Identification :</label>
        <input type="text" id="identification" name="identify" onChange={indentChangeHandler} />

        <label for="group" >Bags:</label>
        <input class="light" type="radio" id="bags" value="bags" onChange={bagshangeHandler} />

        <label for="group" class="light">  Age Group     </label>
      
        <select
          id="group"
          name="room_preference"
          required
          onChange={groupChangeHandler}
        >
          <option value="3">{">"} 9 years</option>
          <option value="1">{"< 2"} years</option>
          <option value="2">between 2 and 9 years</option>
        </select>
      </fieldset>

      <fieldset>
        <h1>{price}$</h1>
      </fieldset>
    </form>
  );
};

export default PassangerForm;
