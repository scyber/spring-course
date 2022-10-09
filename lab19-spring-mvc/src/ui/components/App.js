import "./App.css"
import React from 'react'
import NavigationBar from './NavigationBar'
import Welcome from './Welcome'
import Footer from './Footer'
import AddBook from './AddBook'
import 'bootstrap/dist/css/bootstrap.min.css'
import {Row, Container, Col, Button} from 'react-bootstrap'
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'

function App() {
    const marginTop = {
        marginTop: "20px"
    };
    return (
        <div className="App">
            <Container>
                <Welcome/>
            </Container>
        </div>

    );

}

export default App