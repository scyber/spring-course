import React, {Component} from 'react'
import {Row, Container, Col, Button} from 'react-bootstrap'
import { useParams } from "react-router-dom";

export default class Welcome extends Component {
     constructor(props) {
            super(props);
        }

    render() {
        //let {bookId} = useParams();
        return (

            <div>
                <h3>Welcome to Library</h3>
                <p>This is a simple hero unit, a simple style component for calling
                    extra attention to featured content or information.
                </p>
                <br></br>
            </div>
        );
    }
}