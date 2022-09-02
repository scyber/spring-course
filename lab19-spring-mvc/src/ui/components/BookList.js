import React from 'react'
import NavigationBar from './NavigationBar'
import Footer from './Footer'
import BookComponent from './BookComponent'
import {Link, Outlet} from 'react-router-dom'

class BookList extends React.Component {

    render(){
        return(
            <div>
              <div className="BookList">
                  <NavigationBar />
                                    <div className="text-black">
                                         <BookComponent />
                                    </div>
                  <Footer />
              </div>
              <Outlet />
            </div>
        );
    }
}
export default BookList