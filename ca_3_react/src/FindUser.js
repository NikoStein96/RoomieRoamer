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
        document.getElementById("info").innerHTML =
          "Name: " + json.name + "  mass: " + json.mass;
      });
  };

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <p>{this.state.value}</p>

        <input
          type="number"
          value={this.state.value}
          onChange={this.handleChange}
        />

        <input type="submit" value="Submit" />

        <p id="info" />
      </form>
    );
  }
}
