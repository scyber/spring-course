import React, {Component} from 'react'
import NavigationBar from './NavigationBar'
import Footer from './Footer'
import {Link, Outlet, useParams} from 'react-router-dom'
import {Card, Table, Button, ButtonGroup} from 'react-bootstrap'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEdit, faList, faTrash} from "@fortawesome/free-solid-svg-icons";
import axios from 'axios'

export default class BookEditor extends Component {
    constructor(props) {
        super(props);
        this.state = {
            contentBook: []
        };
    }

    componentDidMount() {
        let { id } = useParams();
        console.log('id ' + id);
        this.getBookById(id);
        console.log("component mount with id " + bookId);
    }

    getBookById(id) {
        axios.get("/api/book", {params :{ id : bookId}})
        .then(response => {
                this.setState({
                    contentBook: response.data
                });
                console.log('contentBook ' + contentBook);
            })
    }

    render() {

        return (
            <div>
                <div className="BookEditor">
                    <div>
                        <div>
                            <Card className={"border"}>
                                <Card.Header><FontAwesomeIcon icon={faList} className={"border"}/>Add Book
                                </Card.Header>
                                <Card.Body>
                                    <Table striped bordered hover bg="primary" variant="primary">
                                        <thead>
                                        <tr>
                                            <th>Title</th>
                                            <th>Authors</th>
                                            <th>Genres</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {
                                            this.state.contentBook.length === 0 ?
                                                <tr align="center">
                                                    <td colSpan="6">Book Not Available</td>
                                                </tr> :
                                                this.state.contentBook.map(
                                                    (book) => (
                                                        <tr key={book.id}>
                                                            <td>{book.title}</td>
                                                            <td>{book.authors.map((author) =><li>{author.name}</li>)}</td>
                                                            <td>{book.genres.map((genre) => <li>{genre.name}</li>)}</td>
                                                        </tr>
                                                    ))
                                        }
                                        </tbody>
                                    </Table>
                                </Card.Body>
                            </Card>
                        </div>
                    </div>

                </div>

            </div>
        );
    }
}