import React, { useState } from "react";
import facade from "./apiFacade";
import { AddDog } from "./../sites";

const UserDogInput = ({
  editDog,
  setEditDog,
  isEdit,
  setIsEdit,
  setActivate,
  activate,
}) => {
  const [dogInfo, setDogInfo] = useState({});

  const handleSubmit = (e) => {
    e.preventDefault();
    let option = facade.makeOptions("POST", true, dogInfo);
    fetch(AddDog, option);
    setActivate(activate++);
    console.log("activate: ", activate);
  };

  const handleChange = (e) => {
    if (isEdit == false) {
      setDogInfo({ ...dogInfo, [e.target.id]: e.target.value });
    } else {
      setEditDog({ ...editDog, [e.target.id]: e.target.value });
    }
  };

  const handleCancle = () => {
    setDogInfo({ name: "", breed: "", info: "", dateOfBirth: "" });
    setIsEdit(false);
  };

  const handleDelete = () => {
    console.log(editDog);
    facade.deleteDog(editDog);
    setDogInfo({ name: "", breed: "", info: "", dateOfBirth: "" });
    setEditDog({});
    setIsEdit(false);
  };

  const handleEdit = (e) => {
    facade.editDog(editDog);
    setDogInfo({ name: "", breed: "", info: "", dateOfBirth: "" });
    setEditDog({});
    setIsEdit(false);
  };

  if (isEdit == false) {
    return (
      <form onChange={handleChange}>
        <lable>Name </lable>
        <input placeholder="Fido" value={dogInfo.name} id="name"></input>
        <br />
        <lable>Breed </lable>
        <input placeholder="Boxer" value={dogInfo.breed} id="breed"></input>
        <br />
        <lable>Info </lable>
        <input placeholder="He is sweet" value={dogInfo.info} id="info"></input>
        <br />
        <lable>Date of birth </lable>
        <input
          placeholder="xx/xx/xxxx"
          value={dogInfo.dateOfBirth}
          id="dateOfBirth"
        ></input>
        <br />
        <button onClick={handleSubmit} type="button" value="Add Dog">
          Add
        </button>
      </form>
    );
  } else {
    return (
      <form>
        <lable>Id </lable>
        <input value={editDog.id} id="id"></input>
        <br />
        <lable>Name </lable>
        <input onChange={handleChange} value={editDog.name} id="name"></input>
        <br />
        <lable>Breed </lable>
        <input onChange={handleChange} value={editDog.breed} id="breed"></input>
        <br />
        <lable>Info </lable>
        <input onChange={handleChange} value={editDog.info} id="info"></input>
        <br />
        <lable>Date of birth </lable>
        <input
          onChange={handleChange}
          value={editDog.dateOfBirth}
          id="dateOfBirth"
        ></input>
        <br />
        <button onClick={handleCancle} type="button" value="Add Dog">
          Cancle
        </button>
        <button onClick={handleEdit} type="button" value="Add Dog">
          Edit
        </button>
        <button onClick={handleDelete} type="button" value="Add Dog">
          Delete
        </button>
      </form>
    );
  }
};

export default UserDogInput;
