import { useEffect,useState } from "react";

const AirLine = (props) => {
  const [airline, setAirline] = useState([]);

  useEffect(() => {
    async function fetchAirline() {
      const response = await fetch("http://localhost:8080/flights/airplanes");

      const data = await response.json();
      data.unshift("");
      setAirline(data);
    }
    fetchAirline();
  }, []);

  const airlineChangeHandler = (event) => {
    props.onAirLineChange(event.target.value)
}

  return (
    <div>
      <select id="airline" name="airline" onChange={airlineChangeHandler}  required>
        {airline.map((option) => {
          return <option value={option.name}> {option.name} </option>;
        })}
      </select>
    </div>
  );
};
export default AirLine;
