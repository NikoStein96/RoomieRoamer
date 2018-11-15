import React, { Component } from "react"
import facade from "./apiFacade";
import UserPage from './UserPage';
import LogIn from './LogIn';


class AppRouter extends Component {
    constructor(props) {
      super(props);
      this.state = { loggedIn: false }
    }
    logout = () => {facade.logout();
     this.setState({ loggedIn: false });} //TODO
    login = (user, pass) => {facade.login(user,pass)
     .then(res =>this.setState({ loggedIn: true }));} 
    render() {
      return (
        <div>
          {!this.state.loggedIn ? (<LogIn login={this.login} />) :
            ( <div>
                <UserPage/>
                <button onClick={this.logout}>Logout</button>
              </div>)}
        </div>
      )
    }
   }

   export default AppRouter;