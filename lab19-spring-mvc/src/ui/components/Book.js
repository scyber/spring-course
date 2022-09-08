import axios from 'axios'
import React, {Component} from 'react'
import {Button, Row} from 'react-bootstrap'
import {Link} from 'react-router-dom'
//import { ceil } from 'mathjs'


export default class Book extends Component {

    render() {
        return (
            <div>
                <div className="Book">
                    <NavigationBar/>

                    <Footer/>
                </div>
                <Outlet/>
            </div>
        );
    }
}