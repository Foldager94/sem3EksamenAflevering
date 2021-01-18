import jwt_decode from "jwt-decode";
import { Site, EditDog, DeleteDog } from "./../sites"


const URL = Site;

function handleHttpErrors(res) {
    if (!res.ok) {
        alert("Forkert brugernavn eller password!");
        return Promise.reject({ status: res.status, fullError: res.json() })
    }
    return res.json();
}
const getRoles = () => {
    if (getToken()) {
        const decode = jwt_decode(getToken());
        return decode.roles
    }
}

const getUserName = () => {
    if (getToken()) {
        const decode = jwt_decode(getToken());
        return decode.username
    }

};

const setToken = (token) => {
    localStorage.setItem('jwtToken', token)
}

const getToken = () => {
    return localStorage.getItem('jwtToken')
}
const loggedIn = () => {
    const loggedIn = getToken() != null;
    return loggedIn;
}

const logout = () => {
    localStorage.removeItem("jwtToken");
}

function ApiFacade() {

    const login = (user, password) => {
        const options = makeOptions("POST", true, { username: user, password: password });
        return fetch(URL + "/api/login", options)
            .then(handleHttpErrors)
            .then(res => {
                setToken(res.token)


            })
    }


    const fetchData = () => {
        const options = makeOptions("GET", true); //True add's the token
        return fetch(URL + "/api/" + getRoles() + "/" + getRoles(), options).then(handleHttpErrors);
    }
    const makeOptions = (method, addToken, body) => {
        var opts = {
            method: method,
            headers: {
                "Content-type": "application/json",
                'Accept': 'application/json',
            }
        }
        if (addToken && loggedIn()) {
            opts.headers["x-access-token"] = getToken();
        }

        if (body) {
            opts.body = JSON.stringify(body);
        }
        return opts;
    }

    const deleteDog = (dog) => {
        console.log(dog)
        const option = makeOptions("POST", true, dog)
        fetch(DeleteDog, option);

    }

    const editDog = (dog) => {
        const option = makeOptions("POST", true, dog)
        fetch(EditDog, option);
    }

    return {
        makeOptions,
        setToken,
        getToken,
        loggedIn,
        login,
        logout,
        fetchData,
        getUserName,
        getRoles,
        deleteDog,
        editDog
    }
}
const facade = ApiFacade();




export default facade;