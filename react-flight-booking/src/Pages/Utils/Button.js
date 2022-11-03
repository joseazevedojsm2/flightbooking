import { BrowserRouter, Routes, Route, Link } from "react-router-dom";

const Button = (props) => {
    return(
        // <Link to={'/price/'+props.dataQuery.origin+'/'+props.dataQuery.destination+'/'+props.dataQuery.date+'/'+props.dataQuery.group}>
        //   {console.log(props.dataQuery)}
          <button type="submit" form="searchForm">
            Fetch Flights
          </button>
        // </Link>
    );
}
export default Button;