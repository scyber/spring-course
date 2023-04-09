import React from 'react'
import Footer from './Footer'
import 'bootstrap/dist/css/bootstrap.min.css'
import {Row, Container, Col, Button} from 'react-bootstrap'
import {Link, Outlet} from 'react-router-dom'
import NavigationBar from './NavigationBar'

function Layout() {
    const marginTop = {
            marginTop: "20px"
        };
        return (
            <div className="Layout">
                    <NavigationBar/>
                    <Footer/>
            </div>
        );
}
export default Layout