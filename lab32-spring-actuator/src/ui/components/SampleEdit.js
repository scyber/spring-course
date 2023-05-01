import {Link, Outlet, useParams} from 'react-router-dom';
import React, {Component} from 'react';

export default class BookEditor extends Component {

    componentDidMount() {
            let { bookId } = useParams();
            console.log('id ' + id);
            console.log("component mount with id " + bookId);
     };
    render (){
        return (
        <div>
                <h1> Return SampleEdit  </h1>
                <p>Book edit page with bookId  </p>
        </div>
            );
    }
}