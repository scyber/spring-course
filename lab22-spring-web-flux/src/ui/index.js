import React from 'react'
import ReactDOM from 'react-dom'
import App from './components/App'
import Book from './components/Book'
import BookEditor from './components/BookEditor'
import ListOfBooks from './components/ListOfBooks'
import BookComponent from './components/BookComponent'
import Layout from './components/Layout'
import Welcome from './components/Welcome'
import {BrowserRouter as Router, Routes, Route } from 'react-router-dom'

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(

    <Router>
        <Routes>
             <Route path="/" element={<Layout />}>
                <Route index element={<App />} />
                <Route path="/add" element={<Book />}/>
                <Route path="/editlist" element={<BookEditor />}/>
                <Route path="/listofbooks" element={<ListOfBooks />}/>
             </Route>
        </Routes>
    </Router>
)