import "./PassangerForm.css";

const PassangerForm = (props) => {
  
  return (
    <form >

      <h1>Passanger Details</h1>
      <h1>{props.id}</h1>


      <fieldset>
        <label for="fname">First Name:</label>
        <input type="text" id="fname" name="first_name" />

        <label for="lname">Last Name:</label>
        <input type="text" id="lname" name="last_name" />

        <label for="nacionality">Nacionality:</label>
        <input type="text" id="nacionality" name="nacional" />

        <label for="identification">Identification :</label>
        <input type="text" id="identification" name="identify" />

        <label for="group" >Bags:</label>
        <input class="light" type="radio" id="bags" value="bags"  />
        <label for="group" class="light">  Age Group     </label>
      
        <select
          id="group"
          name="room_preference"
          required
        >
          <option value="3">{">"} 9 years</option>
          <option value="1">{"< 2"} years</option>
          <option value="2">between 2 and 9 years</option>
        </select>
      </fieldset>

      <button type="submit">Sign Up</button>

    </form>
  );
};

export default PassangerForm;
