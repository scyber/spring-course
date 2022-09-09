import axios from 'axios'
import React , {Component} from 'react'
import { Button, Row } from 'react-bootstrap'
import { Link } from 'react-router-dom'
//import { ceil } from 'mathjs'


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
            first: true
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
        //this.state.currentPage += 1;
        console.log('currentPage ', currentPage);
    }

    //Writing All the pagination functions
    //Show Next page
    showNextPage = () =>{
        console.log('call show next Page and currentPage is ', this.state.currentPage);
        console.log(' first state', this.state.first);
        console.log(' last state', this.state.last);
        console.log('first state ', this.state.first);
        if(!this.state.last){
            this.getBooksByPagination(this.state.currentPage + 1);
            console.log('this.state.currentPage ' + this.state.currentPage);
        }
    };

    //Show Last Page
    showLastPage = () =>{
        console.log('call show last Page and currentPage is ', this.state.currentPage)
        if(this.state.currentPage < Math.ceil(this.state.totalElements/this.state.recordPerPage)){
            this.getBooksByPagination(Math.ceil(this.state.totalElements/this.state.recordPerPage));
        }
    };
    //Show First page
    showFirstPage = () => {
        let firstPage = 1;
        if(this.state.currentPage > firstPage){
            this.getBooksByPagination(firstPage);
            console.log('this.state.currentPage ' + this.state.currentPage);
        }
    };

    //Show previous page
    showPrevPage = () =>{
        let prevPage = 1
        if(this.state.currentPage > prevPage){
            this.getBooksByPagination(this.state.currentPage - prevPage);
            console.log('this.state.currentPage ' + this.state.currentPage);
        }
    };

    render(){
        const {books, currentPage, totalPages, recordPerPage} = this.state;
        return(
        <div>

            <h1 className="text-center mt-5 ">List of Books</h1>
            <div className="container mt-2">
            <table className="table table-bordered border-info shadow">
                <thead>
                    <tr>
                    <th>#</th>
                    <th>Book Title</th>
                    </tr>
                </thead>
                <tbody>
                    {books.length===0?
                        <tr align="center"><td colSpan="5">No Record Found</td></tr>:
                        books.map(
                            (book,index) =>(
                                <tr>
                                    <td>{book.id}</td>
                                    <td>{book.title}</td>
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
                <nav aria-label="Page navigation example">
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
        )
    }
}