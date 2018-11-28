import React, { Component } from "react";
export default class FindUser extends Component {
  constructor(props) {
    super(props);
    this.state = { value: "", json: [] };
  }

  handleChange = event => {
    console.log(event.target.value);
    this.setState({ value: event.target.value });
  };

  handleSubmit = event => {
    event.preventDefault();
    var URL = "http://localhost:8084/RoomieRoamer/api/User/" + this.state.value;
    fetch(URL)
      .then(response => response.json())
      .then(json => {
        console.log(json);
        if(json.name === undefined){
          document.getElementById("info").innerHTML = "No user with that userID";
        }
        else{
          
        document.getElementById("th1").innerHTML =
        // eslint-disable-next-line
        "<h1>Name</h1> ";

        document.getElementById("th2").innerHTML =
        // eslint-disable-next-line
        "<h1>Mass</h1> ";
        
        document.getElementById("info1").innerHTML =
        // eslint-disable-next-line
        json.name;
        
        document.getElementById("info2").innerHTML =
        // eslint-disable-next-line
        json.mass;

        }
      });
  };

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        

          <input type="submit" value="Submit" />

        <input type="submit" value="Submit" />
        <h3>We will fetch user with ID: {this.state.value}</h3>

        
        <p id="th1" align="center"/>
        <p id="info1" align="center"/>
        <p id="th2" align="center"/>
        <p id="info2" align="center"/>
        
      </form>
    );
  }
}