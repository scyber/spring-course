import React, {Component} from 'react'
import NavigationBar from './NavigationBar'
import Footer from './Footer'
import {Outlet} from 'react-router-dom'
import {Button, Card, Col, Form} from 'react-bootstrap'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlusSquare, faSave, faUndo } from "@fortawesome/free-solid-svg-icons";
import Select from 'react-select'
import makeAnimated from 'react-select/animated'
import axios from 'axios'
import  CustomToast from './CustomToast';

const animatedComponents = makeAnimated();

export default class Book extends Component {
    constructor(props) {
        super(props);
        this.state = {title: "",
                      selectedAuthor: "",
                      selectedGenre: "",
                      authors: [],
                      genres: [],
                      show: false,
                      type: 'success'};
        this.bookChange = this.bookChange.bind(this);
        this.submitBook = this.submitBook.bind(this);
    }
    componentDidMount(){
            this.getAuthors();
            this.getGenres();
    }
    submitBook (event) {
        event.preventDefault();
        var filteredAuthors = this.state.authors.filter((author) => author.id == this.state.selectedAuthor);
        var filteredGenres = this.state.genres.filter((genre) => genre.id == this.state.selectedGenre);
        filteredAuthors.map((author) => console.log('filtered author ' + author.name));
        const book = {
            title : this.state.title,
            authors : filteredAuthors,
            genres: filteredGenres
         };
         axios.post("/api/books", book).then(response => {
                        console.log("response data " + response.data);
                        if(response.data != null){
                            this.setState({show: true});
                            setTimeout(() => this.setState({show: false}), 3000);
                            this.setState({title : '', selectedAuthor : '', selectedGenre: ''});
                        } else {
                            this.setState({show: false});
                        }
         });

    };
    resetBook = () => {
        this.setState({title: '', selectedAuthor: '', selectedGenre: '' });
    };

    bookChange = (event) => {
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
    getGenres(){
        axios.get("/api/genres")
              .then(response => response.data)
              .then((data) => {
                this.setState({genres :data});
                console.log("data " + data);
              });
    }
    render() {
        const {title, selectedAuthor, selectedGenre} = this.state
        return (
            <div>
                <div style={{"display" : false ? "block" : "none"}}>
                    <CustomToast children ={{show: this.state.show, type : this.state.type}}/>
                </div>
                <div className="AddBook">
                    <div>
                        <Card className={"border text-black"}>
                            <Card.Header className={"border text-black"}><FontAwesomeIcon icon={faPlusSquare} /> Add Book</Card.Header>
                            <Form id="bookFormId" onSubmit={this.submitBook} onReset={this.resetBook}>
                                <Card.Body>

                                    <Form.Group as={Col} controlId="title" className="mb-3">
                                        <Form.Label>Book Title</Form.Label>
                                        <Form.Control required autoComplete = "off"
                                                      type="test"
                                                      placeholder="Enter Book Title"
                                                      name="title"
                                                      value={title}
                                                      onChange={this.bookChange}/>
                                        <Form.Text className="text-muted">
                                            This is a Title of Book
                                        </Form.Text>
                                    </Form.Group>
                                    <Form.Group as={Col} controlId="selectedAuthor"  className="mb-3">
                                        <Form.Label>select author</Form.Label>
                                        <Form.Control required
                                                      name = "selectedAuthor"
                                                      value={selectedAuthor}
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
                                        <Form.Group as={Col} controlId="selectedGenre"  className="mb-3">
                                              <Form.Label>select genre</Form.Label>
                                              <Form.Control required
                                                             name = "selectedGenre"
                                                             value={selectedGenre}
                                                             as="select"
                                                             custom
                                                             components={animatedComponents}
                                                             onChange={this.bookChange}>
                                                              {this.state.genres.map((genre) => (
                                                               <option value={genre.id}>{genre.name}</option>))}
                                        </Form.Control>
                                    </Form.Group>
                                </Card.Body>
                                <Card.Footer>
                                    <Button size={"sm"} variant="success" type="submit">
                                        <FontAwesomeIcon icon={faSave} /> Submit
                                    </Button>{' '}
                                    <Button size={"sm"} variant="info" type="reset">
                                        <FontAwesomeIcon icon={faUndo} /> Reset
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
