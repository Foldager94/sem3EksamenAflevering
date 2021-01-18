import React, { useState } from "react";

const GetTableBody = ({ array, clickHandler }) => {
  let countOne = 0;

  const tableBody = array.map((object) => {
    let countTwo = 0;
    countOne++;
    let theObject = JSON.stringify(object);

    let objectValues = Object.values(object).map((value) => {
      countTwo++;

      return <td key={countOne + "." + countTwo}>{value}</td>;
    });
    objectValues.push(
      <td key={countOne + ".b"} onClick={clickHandler} id={theObject}>
        edit/delete
      </td>
    );

    return <tr key={countOne}>{objectValues}</tr>;
  });

  return <tbody>{tableBody}</tbody>;
};

const GetTableHeaders = ({ array }) => {
  const tableHeaders = Object.keys(array[0]).map((name) => (
    <th key={name}>{name}</th>
  ));

  return (
    <thead>
      <tr key="0">{tableHeaders}</tr>
    </thead>
  );
};

const Table = ({ array, className, clickHandler }) => {
  if (!Array.isArray(array) || array.length == 0) {
    console.log("Error. Object is eather not an array or it is empty");
    return <p>Error. check console</p>;
  }

  return (
    <table className={className}>
      <GetTableHeaders array={array} />
      <GetTableBody array={array} clickHandler={clickHandler} />
    </table>
  );
};

export default Table;
