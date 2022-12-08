import axios from 'axios';
import React, {Component} from 'react';
import { Link } from 'react-router-dom';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEdit, faList, faTrash} from "@fortawesome/free-solid-svg-icons";
import {Card, Table, Button, ButtonGroup, Row } from 'react-bootstrap';
import  CustomToast from './CustomToast';

export default class BookComponent extends Component{

    constructor(props){
        super(props)
        this.state ={
            books:[],
            currentPage: 1,
            totalPages: 0,
            totalElements: 0,
            recordPerPage: 3,
            last: false,
            first: true,
            show: false,
            type: 'success'
        }
    }

    componentDidMount(){
        this.getBooksByPagination(this.state.currentPage);
    }
    getBooksByPagination(currentPage){
        axios.get("/api/books",{
            params: {
            page: currentPage,
            size : this.state.recordPerPage
            }})
        .then(response => response.data)
        .then((data) =>{
            console.log('data', data);
            this.setState({books: data.content,
                                      currentPage: currentPage,
                                      totalPages: data.totalPages,
                                      totalElements: data.totalElements,
                                      recordPerPage: data.size,
                                      last: data.last,
                                      first: data.first
                                     });

        }
        );
        console.log('currentPage ', currentPage);
    }
    //Writing All the pagination functions
    //Show Next page
    showNextPage = () =>{
        if(!this.state.last){
            this.getBooksByPagination(this.state.currentPage + 1);
        }
    };

    //Show Last Page
    showLastPage = () =>{
        if(this.state.currentPage < Math.ceil(this.state.totalElements/this.state.recordPerPage)){
            this.getBooksByPagination(Math.ceil(this.state.totalElements/this.state.recordPerPage));
        }
    };
    //Show First page
    showFirstPage = () => {
        let firstPage = 1;
        if(this.state.currentPage > firstPage){
            this.getBooksByPagination(firstPage);
        }
    };

    //Show previous page
    showPrevPage = () =>{
        let prevPage = 1
        if(this.state.currentPage > prevPage){
            this.getBooksByPagination(this.state.currentPage - prevPage);
        }
    };
    deleteBook = (bookId) =>{
        axios.delete("/api/books/", {
            params :{id : bookId}
        })
        .then(response => response.data).then(data =>{ if(data !== null) {
            this.setState({show: true});
            setTimeout(() => this.setState({show: false}), 3000);
            this.getBooksByPagination(this.state.currentPage);
            } else {
                this.setState({show: false});
            }}
        );
    };

    render(){
        const {books, currentPage, totalPages, recordPerPage} = this.state;
        return(
        <div>
        <div style={{"display" : this.state.show ? "block" : "none"}}>
            <CustomToast children ={{show: this.state.show }}/>
        </div>
        <div>
            <h1 className="text-center mt-5 ">List of Books</h1>
            <div className="container mt-2">
            <table className="table table-bordered border-info shadow">
                <thead>
                    <tr>
                    <th>#</th>
                    <th>Book Title</th>
                    <th>Book Authors</th>
                    <th>Book Genres</th>
                    <th>Actions </th>
                    </tr>
                </thead>
                <tbody>
                    {books.length === 0 ? <tr align="center"><td colSpan="5">No Books Available </td></tr> :
                        books.map(
                            (book,index) =>(
                                <tr>
                                    <td>{book.id}</td>
                                    <td>{book.title}</td>
                                    <td>{book.authors.map((author) =><li>{author.name}</li>)}</td>
                                    <td>{book.genres.map((genre) =><li>{genre.name}</li>)}</td>
                                    <td>
                                        <ButtonGroup>
                                         <Button size="sm" variant="outline-primary"><FontAwesomeIcon icon={faEdit} className={"border"}/></Button>
                                         <Button size="sm" variant="outline-danger" onClick={this.deleteBook.bind(this, book.id)}><FontAwesomeIcon icon={faTrash} className={"border"}/></Button>
                                         </ButtonGroup>
                                    </td>
                                </tr>
                            )
                        )
                    }
                </tbody>

            </table>
            <table className="table">
                <div style={{float:'left',fontFamily: 'monospace',color: '#0275d8'}}>
                    Page {currentPage} of {totalPages}
                </div>
                <div style={{float:'right'}}>
                <div class="clearfix"></div>
                <nav aria-label="Page navigation">
                <ul class="pagination">
                <li class="page-item"><a type="button" class="page-link"  disabled={currentPage===1?true:false} onClick={this.showPrevPage}>Previous</a></li>
                <li class="page-item"><a type="button" class="page-link"  disabled={currentPage===1?true:false } onClick={this.showFirstPage}>First</a></li>
                <li class="page-item"><a type="button" class="page-link"  disabled={currentPage===totalPages?true:false } onClick={this.showNextPage}>Next</a></li>
                <li class="page-item"><a type="button" class="page-link"  disabled={currentPage===totalPages?true:false} onClick={this.showLastPage}>Last</a></li>
                    </ul>
                </nav>
                </div>
            </table>
            </div>
        </div>
        </div>
        )
    }
}