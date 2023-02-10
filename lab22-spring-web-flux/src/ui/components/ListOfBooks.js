import axios from 'axios';
import React, {Component} from 'react';
import {Button, Row} from 'react-bootstrap';
import {Link,Outlet} from 'react-router-dom';
import BookComponent from './BookComponent';
import NavigationBar from './NavigationBar';
import Footer from './Footer';


class ListOfBooks extends Component {

    render() {
        return (
            <div className="ListOfBooks">

                    <BookComponent />

                <Outlet/>
            </div>
        );
    }
}
export default ListOfBooks