import React, {Component} from 'react';
import {Container, Navbar, Nav} from 'react-bootstrap';
import {Link, Outlet} from 'react-router-dom'

export default class NavigationBar extends Component {

    render() {
        return (
            <>
                <Navbar bg="primary" variant="dark">
                    <Container>
                        <Navbar.Brand href="/">Book Library</Navbar.Brand>
                        <div className="mr-auto"></div>
                        <Nav className="navbar-brand">
                            <Link to="editlist" className="nav-link">BookEditor</Link>
                            <Link to="add"  className="nav-link">Book</Link>
                            <Link to="listofbooks"  className="nav-link">ListOfBooks</Link>
                            <Link to="search" className="nav-link">Search</Link>
                        </Nav>
                    </Container>
                </Navbar>
                <Outlet/>
            </>
        );
    }
}