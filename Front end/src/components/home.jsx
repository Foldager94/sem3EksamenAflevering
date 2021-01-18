import React, { useEffect, useState } from "react";
import facade from "./apiFacade";
import Table from "./Table";
import ListOfBreeds from "./ListOfBreeds";
import BreedDetails from "./BreedDetails";
const Home = () => {
  const [breedHook, setBreedHook] = useState();
  return (
    <div className="container">
      <div className="row">
        <ListOfBreeds setBreedHook={setBreedHook} />
        <BreedDetails breedHook={breedHook} />
      </div>
    </div>
  );
};

export default Home;
