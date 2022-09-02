import React from 'react'
import NavigationBar from './NavigationBar'
import Footer from './Footer'
import {Link, Outlet} from 'react-router-dom'

class AddBook extends React.Component {

    render(){
        return(
        <div>
               <div className="BookList">
                  <NavigationBar />
                     <div className="text-black">
                           Here we add Book to the Library
                     </div>
                  <Footer />
               </div>
        <Outlet />
        </div>
        );
    }
}
export default AddBook