import React, { useEffect, useState } from "react";
import facade from "./apiFacade";
import Table from "./Table";

const url = "http://localhost:8080/jpareststarter/api/dog/dog-breed";

const ListOfBreeds = ({ setBreedHook }) => {
  const [breeds, setBreeds] = useState([]);

  useEffect(() => {
    let options = facade.makeOptions("GET", true);
    fetch(url, options)
      .then((res) => res.json())
      .then((data) => {
        setBreeds(data);
      });
  }, []);
  const handleClick = (event) => {
    const target = event.target.id;
    setBreedHook(target);
  };

  return (
    <div className="col-sm">
      <ul className="list-group">
        {breeds.map((dog) => (
          <li key={dog.breed} id={dog.breed} onClick={handleClick}>
            {dog.breed}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListOfBreeds;
