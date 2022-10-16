import React, {Component} from 'react'
import NavigationBar from './NavigationBar'
import Footer from './Footer'
import {Outlet} from 'react-router-dom'
import {Button, Card, Col, Form} from 'react-bootstrap'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlusSquare, faSave } from "@fortawesome/free-solid-svg-icons";
import Select from 'react-select'
import makeAnimated from 'react-select/animated'
import axios from 'axios'

const animatedComponents = makeAnimated();

export default class AddBook extends Component {
    constructor(props) {
        super(props);
        this.state = {
        authors : [],
        genres :[]
//        title: '',
//        selectedAuthor : '',
//        fruits : [
//                                 { value: 'chocolate', label: 'Chocolate' },
//                                 { value: 'strawberry', label: 'Strawberry' },
//                                 { value: 'vanilla', label: 'Vanilla' }]
        };

        this.bookChange = this.bookChange.bind(this);
        this.submitBook = this.submitBook.bind(this);
    }
    componentDidMount(){
            this.getAuthors();
    }
    submitBook(event) {
        event.preventDefault();
        //this.state.fruits.map(fruit => console.log('option ' + fruit.value));
        //console.log('event target value ' + event.target.value);
        alert("title " + this.state.title + " authors " + this.state.authors + " selectedAuthor " + this.state.selectedAuthor);
    }

    bookChange(event) {
        console.log('event.target.name ' + event.target.name);
        console.log('event.target.value ' + event.target.value);
        this.setState({[event.target.name]: event.target.value});
    }
    getAuthors(){
         axios.get("/api/authors")
                .then(response => response.data)
                .then((data) => {
                this.setState({authors: data});
                console.log('data ' + data);
                });
                }
//                .then((data) => {
//                    console.log('data ' + data);
//                    this.setState({authors: data});
//                    console.log('authors ' + this.state.authors);
//                });
//    }

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

                                    <Form.Group as={Col} controlId="selectGridAuthor"  className="mb-3">
                                        <Form.Label>select author</Form.Label>
                                        <Form.Control required
                                                      name = "selectedAuthor"
                                                      value={this.state.selectedAuthor}
                                                      as="select"
                                                      custom
                                                      components={animatedComponents}
                                                      onChange={this.bookChange}>
                                                      {this.state.authors.map((author) => (
                                                          <option value={author.id}>{author.name}</option>
                                                        ))
                                                        }
                                        </Form.Control>
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
