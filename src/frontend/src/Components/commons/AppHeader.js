import React from 'react';
import { Navbar, Container} from "react-bootstrap";
import { Link } from "react-router-dom";

function AppHeader() {
  return (
    <header className="App-header">
      <Navbar bg="dark" variant="dark">
        <Container>
          <Navbar.Brand>
            <Link to={"/"} className="nav-link">
              Patient
            </Link>
          </Navbar.Brand>
        </Container>
      </Navbar>
    </header>
  );
}

export default AppHeader;