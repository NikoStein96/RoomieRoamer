import React, { Component } from "react";
export default class GetUserDesc extends Component {
  constructor(props) {
    super(props);
    this.state = { value: "" };
  }

  handleChange = event => {
    console.log(event.target.value);
    this.setState({ value: event.target.value });
  };

  handleSubmit = event => {
    event.preventDefault();
    var URL = "http://localhost:8080/RoomieRoamer/api/User/"+this.state.value+"/desc";
    fetch(URL)
      .then(response => response.json())
      .then(json => {
        console.log(json);
        if(json === undefined){
          document.getElementById("desc1").innerHTML = "No user with that userID";
        }
        else{

        document.getElementById("desc1").innerHTML =
        // eslint-disable-next-line
        json;

        }
      });
  };

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        

        <input
          type="number"
          value={this.state.value}
          onChange={this.handleChange}
        />

        <input type="submit" value="Submit" />
        <h3>We will fetch user describtion with ID: {this.state.value}</h3>

        
        <p id="desc1" align="center"/>
        
        
        
        
      </form>
    );
  }
}
