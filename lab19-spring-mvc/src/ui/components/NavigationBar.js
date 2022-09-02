import React from 'react';
import {Container, Navbar, Nav} from 'react-bootstrap';
import {Link, Outlet} from 'react-router-dom'

class NavigationBar extends React.Component {

    render(){
        return(
        <>
            <Navbar bg="primary" variant="dark">
                <Container>
                    <Navbar.Brand href="/">Book Library</Navbar.Brand>
                    <div className="mr-auto"></div>
                     <Nav className="navbar-brand">
                        <Link to="/list" className="nav-link">Book List</Link>
                        <Link to="/add" className="nav-link">Add Book</Link>
                        <Link to="search" className="nav-link">Search</Link>
                    </Nav>
                </Container>
            </Navbar>
            <Outlet />
            </>
        );
    }
}
export default NavigationBar