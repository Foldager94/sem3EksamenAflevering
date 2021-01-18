import React, { useEffect, useState } from "react";
import facade from "./apiFacade";
import { UserUrlUserCount } from "./../sites";
import UserDogInput from "./UserDogInput";
import UsersDogs from "./UsersDogs";
import { UserDogList } from "./../sites";

const UserSite = () => {
  const [userDogs, setUserDogs] = useState([]);
  const [editDog, setEditDog] = useState({});
  const [isEdit, setIsEdit] = useState(false);
  const [activate, setActivate] = useState(0);
  useEffect(() => {
    let options = facade.makeOptions("GET", true);
    fetch(UserDogList, options)
      .then((res) => res.json())
      .then((data) => {
        setUserDogs(data);
      });
  }, [isEdit, activate]);
  return (
    <>
      <UserDogInput
        editDog={editDog}
        setEditDog={setEditDog}
        isEdit={isEdit}
        setIsEdit={setIsEdit}
        setActivate={setActivate}
        activate={activate}
      />
      <UsersDogs
        array={userDogs}
        setEditDog={setEditDog}
        setIsEdit={setIsEdit}
      />
    </>
  );
};

export default UserSite;
