import React, {Component} from 'react'
import NavigationBar from './NavigationBar'
import Footer from './Footer'
import BookComponent from './BookComponent'
import {Link, Outlet} from 'react-router-dom'
import { Card, Table} from 'react-bootstrap'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faList} from "@fortawesome/free-solid-svg-icons";

export default class BookList extends Component {

    render() {
        return (
            <div>
                <div className="BookList">
                    <NavigationBar/>
                    <div className="text-black">
                        <div>
                            <Card className={"border text-black"}>
                                <Card.Header><FontAwesomeIcon icon={faList} className={"border text-black"} />Add Book </Card.Header>
                                <Card.Body>
                                    <Table striped bordered hover bg="primary" variant="dark">
                                        <thead>
                                        <tr>
                                            <th>id</th>
                                            <th>Title</th>
                                            <th>Authors</th>
                                            <th>Genres</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr align="center">
                                            <td colSpan="6">No Book Available</td>
                                        </tr>
                                        </tbody>
                                    </Table>
                                </Card.Body>
                            </Card>
                        </div>
                    </div>
                    <Footer/>
                </div>
                <Outlet/>
            </div>
        );
    }
}