import React, { Component } from "react";
export default class FindUser extends Component {
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
    var URL = "https://swapi.co/api/people/" + this.state.value;
    fetch(URL)
      .then(response => response.json())
      .then(json => {
        console.log(json);
        if(json.name === undefined){
          document.getElementById("info").innerHTML = "No user with that userID";
        }
        else{
        document.getElementById("info1").innerHTML =
        // eslint-disable-next-line
        json.name + "<br/>" + "Mass: " + json.mass;

        document.getElementById("th1").innerHTML =
        // eslint-disable-next-line
        "<h3>Name</h3> "
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
        <h3>We will fetch user with ID: {this.state.value}</h3>

        <table>
        <p id="th1" align="center"/>
        <p id="info1" align="center"/>
        <p id="th2" align="center"/>
        <p id="info2" align="center"/>
        </table>
      </form>
    );
  }
}
