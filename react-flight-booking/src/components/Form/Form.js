import { useState } from "react";
import City from "./City";
import "./Form.css";


const Form = (props) => {
  
  const[dep,setDep] = useState("");
  const[arr,setArr] = useState("");
  const [enteredDate, setEnteredDate] = useState("");
  const [enteredGroup,setEnteredGroup] = useState("3");

  const dateChangeHandler = (event) => {
    setEnteredDate(event.target.value);
  };
  const groupChangeHandler = (event) => {
    setEnteredGroup(event.target.value);
  };

  const departureHandler = (departure) => {
    setDep(departure);
  };

  const arrivalHandler = (arrival) => {
    setArr(arrival);
  };

  function submitHandler(event){
    event.preventDefault();
    const data = {
      origin:dep,
      destination:arr,
      date:enteredDate,
      group:enteredGroup
    };
    props.onSearchFormConfirm(data);
  }

  return (
    <form id="searchForm"  onMouseLeave={submitHandler}>
      <City type={"Departure City"} onCityChange={departureHandler}></City>
      <City type={"Arrival City"} onCityChange={arrivalHandler}></City>
      <div class="elem-group inlined">
        <label for="checkin-date">Departure date</label>
        <input type="date" id="checkin-date" name="checkin" onChange={dateChangeHandler} required></input>
      </div>
      <div class="elem-group inlined">
        <label for="checkout-date">Return date</label>
        <input type="date" id="checkout-date" name="checkout" ></input>
      </div>
      <div class="elem-group">
        <label for="room-selection">Select Age group</label>
        <select id="room-selection" name="room_preference" onChange={groupChangeHandler}  required>
          <option value="3">{">"} 9 years</option>
          <option value="1">{"< 2"} years</option>
          <option value="2">between 2 and 9 years</option>
        </select>
      </div>
      {/* <button type="submit"> Fetch Flights </button> */}
    </form>
  );
};

export default Form;
