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
    var URL = "http://localhost:8080/RoomieRoamer/api/User/" + this.state.value;
    fetch(URL)
      .then(response => response.json())
      .then(json => {
        console.log(json.results[this.state.value].Name);
        if(json.Name === undefined){
          document.getElementById("info1").innerHTML = "No user with that userID";
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
        json.Name;
        
        document.getElementById("info2").innerHTML =
        // eslint-disable-next-line
        json.Mass;

        }
      });
  };

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        

          <input type="number" />

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
