import React, {Component} from 'react'
import NavigationBar from './NavigationBar'
import Footer from './Footer'
import BookComponent from './BookComponent'
import {Link, Outlet} from 'react-router-dom'
import {Card, Table, Button, ButtonGroup} from 'react-bootstrap'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEdit, faList, faTrash} from "@fortawesome/free-solid-svg-icons";
import axios from 'axios'

export default class BookEditor extends Component {
    constructor(props) {
        super(props);
        this.baseURL = "http://localhost:9091";
        this.state = {
            contentBook: []
        };
    }

    componentDidMount() {
        this.getBookById(id);
    }

    getBookById(id) {
        axios.get(this.baseURL + "/api/books",
        {
        params :{ id : bookId}
        })
        .then(response => {
                console.log(response.data);
                this.setState({
                    contentBook: response.data
                });
            })
        .catch(function (response) {
            console.log('response')
        })
    }

    render() {
        return (
            <div>
                <div className="EditBookList">

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
                                            <th>Actions</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {
                                            this.state.content.length === 0 ?
                                                <tr align="center">
                                                    <td colSpan="6">No Books Available</td>
                                                </tr> :
                                                this.state.content.map(
                                                    (book) => (
                                                        <tr key={book.id}>
                                                            <td>{book.title}</td>
                                                            <td>{book.authors.map((author) =><li>{author.name}</li>)}</td>
                                                            <td>{book.genres.map((genre) => <li>{genre.name}</li>)}</td>
                                                            <td>
                                                                <ButtonGroup>
                                                                    <Button size="sm"
                                                                            variant="outline-primary"><FontAwesomeIcon
                                                                        icon={faEdit} className={"border"}/></Button>
                                                                    <Button size="sm"
                                                                            variant="outline-danger"><FontAwesomeIcon
                                                                        icon={faTrash} className={"border"}/></Button>
                                                                </ButtonGroup>
                                                            </td>
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