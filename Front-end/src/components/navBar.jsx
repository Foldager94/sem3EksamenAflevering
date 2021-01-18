import React, { useState } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import { Navbar, Nav, NavItem, NavDropdown } from "react-bootstrap";
import Login from "./login";
import Home from "./home";
import facade from "./apiFacade";
import ValidateRoleSite from "./validateRoleSite";
import UserSite from "./userSite";
import AdminSite from "./adminSite";

const NavBarIO = () => {
  const [loggedIn, setLoggedIn] = useState(false);

  return (
    <>
      <Router>
        <Header loggedIn={loggedIn} />
        <div>
          <Content setLoggedIn={setLoggedIn} loggedIn={loggedIn} />
        </div>
      </Router>
    </>
  );
};

const Header = (props) => {
  return (
    <>
      <div>
        <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
          <Navbar.Brand as={Link} to="/">
            CA-3 Gruppe 5
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="responsive-navbar-nav" />
          <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="mr-auto">
              <NavItem href="/">
                <Nav.Link as={Link} to="/">
                  Home
                </Nav.Link>
              </NavItem>
              <ValidateRoleSite loggedIn={props.loggedIn} />
            </Nav>
            <Nav>
              <NavItem href="/Login">
                <Nav.Link as={Link} to="/Login">
                  <IsLoggedIn loggedIn={props.loggedIn} />
                </Nav.Link>
              </NavItem>
            </Nav>
          </Navbar.Collapse>
        </Navbar>
      </div>
    </>
  );
};

const Content = (props) => {
  return (
    <Switch>
      <Route exact path="/" component={Home} />
      <Route path="/AdminSite" component={AdminSite} />
      <Route path="/UserSite" component={UserSite} />
      <Route path="/Login">
        <Login setLoggedIn={props.setLoggedIn} loggedIn={props.loggedIn} />
      </Route>
      <Route path="*" component={NoMatch} />
    </Switch>
  );
};

const NoMatch = () => {
  return <p>There was no match</p>;
};

const IsLoggedIn = (props) => {
  if (props.loggedIn) {
    const userName = facade.getUserName();
    console.log("IsLoggedIn(), ", userName);
    return <>{userName}</>;
  } else {
    return <>Login</>;
  }
};

export default NavBarIO;
