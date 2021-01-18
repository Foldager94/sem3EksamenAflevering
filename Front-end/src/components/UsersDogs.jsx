import React, { useState } from "react";
import Table from "./Table";

const UsersDogs = ({ array, setEditDog, setIsEdit }) => {
  const editDog = (e) => {
    setEditDog(JSON.parse(e.target.id));
    setIsEdit(true);
    console.log(e.target);
  };

  return (
    <>
      <Table array={array} className="table" clickHandler={editDog} />
    </>
  );
};

export default UsersDogs;
