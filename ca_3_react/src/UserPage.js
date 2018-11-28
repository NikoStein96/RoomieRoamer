import React, { Component } from "react"
import facade from "./apiFacade";

export default class UserPage extends Component {
    constructor(props) {
      super(props);
      this.state = { dataFromServer: "",}
      this.state = { value: "" };
    }
  
  // fetch user and admin details from backend
/*    componentDidMount() {
      facade.fetchData().then(res => this.setState({ dataFromServer: res }));
      facade.fetchDataAdmin().then(res => this.setState({ dataFromServer: res}));
    }
*/
    handleChangeUP = event => {
      console.log(event.target.value);
      this.setState({ value: event.target.value });
    };

    
componentDidMount() {
  var URL = "http://localhost:8080/RoomieRoamer/api/User/1/poma";
      fetch(URL)
        .then(response => response.json())
        .then(json => {
          console.log(json);
          if(json === undefined){
            document.getElementById("desc1").innerHTML = "No user with that userID";
          }
          else{
          nr++;  
          
          document.getElementById("desc1").innerHTML = json.results[nr].Name;
}
// eslint-disable-next-line
             


});
}
    /*
    handleSubmitUP = event => {
      event.preventDefault();
      var URL = "http://localhost:8080/RoomieRoamer/api/User/1/poma";
      fetch(URL)
        .then(response => response.json())
        .then(json => {
          console.log(json);
          if(json === undefined){
            document.getElementById("desc1").innerHTML = "No user with that userID";
          }
          else{
            
          
          document.getElementById("desc1").innerHTML = json.results[nr].Name;
        
          // eslint-disable-next-line
             

          }
        });
    };
  */
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
        <br/>
        <br/>
        <br/>
  <form onSubmit={this.componentDidMount()}>
        <input type="submit" value="Search" onChange={this.handleChangeUP}>
        </input>
        <h3>We will fetch user describtion with ID: {nr}</h3>
      </form>
      <p id="desc1" align="center">no data yet</p>
      </div>  
      )
    }
  }
  let nr = 0;

 
      