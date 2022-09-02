import React from 'react'
import ReactDOM from 'react-dom'
import App from './components/App'
import AddBook from './components/AddBook'
import BookList from './components/BookList'
import BookComponent from './components/BookComponent'
import {BrowserRouter as Router , Routes, Route } from 'react-router-dom'

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(

    <Router>
        <Routes>
            <Route path="/" element={<App />}/>
            <Route path="/add" element={<AddBook />}/>
            <Route path="/list" element={<BookList />} />
        </Routes>
    </Router>
)