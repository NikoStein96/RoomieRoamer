import React, { Component } from "react"
import facade from "./apiFacade";

export default class UserPage extends Component {
    constructor(props) {
      super(props);
      this.state = { dataFromServer: ""}
    }
  
  // fetch user and admin details from backend
    componentDidMount() {
      facade.fetchData().then(res => this.setState({ dataFromServer: res }));
      facade.fetchDataAdmin().then(res => this.setState({ dataFromServer: res}));
    }
  
    render() {
      return (
        <div>
          Welcome to the userpage. If you are logged in, a message will be displayed below with your role and name.
          <div>
            {this.state.dataFromServer}
          </div>
          <br>
          </br>
          <button onClick={facade.logout}>Logout</button>
        </div>
        
        
      )
  
    }
  }