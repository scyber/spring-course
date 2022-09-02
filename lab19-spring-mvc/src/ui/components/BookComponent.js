import axios from 'axios';
import React from 'react'
import { Button, Row } from 'react-bootstrap';
import { Link } from 'react-router-dom';



class BookComponent extends React.Component{

    constructor(props){
        super(props)
        this.state ={
            books:[],
            currentPage: 1,
            totalPages: 0,
            totalElements: 0,
            recordPerPage: 3,
        }
    }

    componentDidMount(){
        this.getBooksByPagination(this.state.currentPage);
    }
    getBooksByPagination(currentPage){
        currentPage -= currentPage;
        axios.get("/api/sampleList",{
            params: {
            page: this.state.currentPage,
            size : this.state.recordPerPage
            }})
        .then(response => response.data)
        .then((data) =>{this.setState({books: data.content,
                                     recordPerPage: data.pageSize,
                                     currentPage: data.number + 1,
                                     totalElements: data.totalElements,
                                     totalPages: data.totalPages});
                      console.log('books', this.state.books);
        }
        );
        currentPage += 1;
        console.log('state.currentPage ', this.state.currentPage);
    }

    //Writing All the pagination functions
    //Show Next page
    showNextPage = () =>{
        if(this.state.currentPage < Math.ceil(this.state.totalElements/this.state.recordPerPage)){
            this.getBooksByPagination(this.state.currentPage + 1);
            console.log('this.state.currentPage ' + this.state.currentPage);
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
export default BookComponent