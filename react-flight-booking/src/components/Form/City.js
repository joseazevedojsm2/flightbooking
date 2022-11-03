import { useEffect,useState } from "react";

const City = (props) => {
  const [cities, setCities] = useState([]);

  useEffect(() => {
    async function fetchCities() {
      const response = await fetch("http://localhost:8080/routes/places");

      const data = await response.json();
      data.unshift("");
      setCities(data);
    }
    fetchCities();
  }, []);

  const cityChangeHandler = (event) => {
    props.onCityChange(event.target.value)
}

  return (
    <div>
      <label for="city-selection">{props.type}</label>
      <select id="city-selection" name="city_preference" onChange={cityChangeHandler}  required>
        {cities.map((option) => {
          return <option value={option.name}> {option.name} </option>;
        })}
      </select>
    </div>
  );
};
export default City;
