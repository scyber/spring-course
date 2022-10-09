import React, {Component} from 'react'
import NavigationBar from './NavigationBar'
import Footer from './Footer'
import {Outlet} from 'react-router-dom'
import {Button, Card, Col, Form} from 'react-bootstrap'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlusSquare, faSave } from "@fortawesome/free-solid-svg-icons";

class AddBook extends Component {
    constructor(props) {
        super(props);
        this.state = {title: '', authors: '', sample: ''};
        this.bookChange = this.bookChange.bind(this);
        this.submitBook = this.submitBook.bind(this);
    }

    submitBook(event) {
        event.preventDefault();
        alert("title " + this.state.title + " authors " + this.state.authors + " sample " + this.state.sample);
    }

    bookChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    render() {
        return (
            <div>
                <div className="AddBook">

                    <div>
                        <Card className={"border text-black"}>
                            <Card.Header className={"border text-black"}><FontAwesomeIcon icon={faPlusSquare} /> Add Book</Card.Header>
                            <Form id="bookFormId" onSubmit={this.submitBook}>
                                <Card.Body>

                                    <Form.Group as={Col} controlId="formGridTitle" className="mb-3">
                                        <Form.Label>Book Title</Form.Label>
                                        <Form.Control required
                                                      type="test"
                                                      placeholder="Enter Book Title"
                                                      name="title"
                                                      value={this.state.title}
                                                      onChange={this.bookChange}/>
                                        <Form.Text className="text-muted">
                                            This is a Title of Book
                                        </Form.Text>
                                    </Form.Group>
                                    <Form.Group as={Col} controlId="formGridTitle" className="mb-3">
                                        <Form.Label>Book Author</Form.Label>
                                        <Form.Control required
                                                      type="test"
                                                      placeholder="Enter Book Title"
                                                      name="authors"
                                                      value={this.state.authors}
                                                      onChange={this.bookChange}/>
                                        <Form.Text className="text-muted">
                                            This is a Author of Book
                                        </Form.Text>
                                    </Form.Group>
                                    <Form.Group as={Col} controlId="selectGridAuthor"  className="mb-3">
                                        <Form.Label>select menu</Form.Label>
                                        <Form.Select id="authorSelect">
                                            <option>Author 1 select</option>
                                            <option>Author 2 select </option>
                                        </Form.Select>
                                    </Form.Group>
                                    <Form.Group as={Col} controlId="formGridSample" className="mb-3">
                                        <Form.Label>Book Title</Form.Label>
                                        <Form.Control required
                                                      type="test"
                                                      placeholder="Enter Book Sample"
                                                      name="sample"
                                                      value={this.state.sample}
                                                      onChange={this.bookChange}/>
                                        <Form.Text className="text-muted">
                                            This is a Sample of Book
                                        </Form.Text>
                                    </Form.Group>
                                </Card.Body>
                                <Card.Footer>
                                    <Button size={"sm"} variant="success" type="submit">
                                        <FontAwesomeIcon icon={faSave} /> Submit
                                    </Button>
                                </Card.Footer>
                            </Form>
                        </Card>
                    </div>

                </div>

            </div>
        );
    }
}

export default AddBook