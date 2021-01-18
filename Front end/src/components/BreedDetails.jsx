import React, { useEffect, useState } from "react";
import facade from "./apiFacade";
import Table from "./Table";
import { DogBreedDetails } from "./../sites";

const BreedDetails = ({ breedHook }) => {
  const [breedDetails, setBreedDetails] = useState({
    breed: "",
    info: "",
    facts: "",
    wiki: "",
  });

  useEffect(() => {
    if (breedHook !== undefined) {
      let options = facade.makeOptions("GET", true);

      fetch(DogBreedDetails + breedHook, options)
        .then((res) => res.json())
        .then((data) => {
          setBreedDetails(data);
        });
    }
  }, [breedHook]);

  return (
    <div className="col-sm">
      <h3>{breedDetails.breed}</h3>
      <br />
      {breedDetails.info}
      <br />
      <br />
      {breedDetails.facts}
      <br />
      <img src={breedDetails.Image} width="500" height="600"></img>
      <br />
      <a href={breedDetails.wikipedia} target="_blank">
        {breedDetails.wikipedia}
      </a>
      <br />
    </div>
  );
};

export default BreedDetails;
