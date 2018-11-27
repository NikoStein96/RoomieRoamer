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
        this.setState({ json: json });
      });
  };

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <input
            type="number"
            value={this.state.value}
            onChange={this.handleChange}
          />

          <input type="submit" value="Submit" />

          <p id="info" />
        </form>

        <p>
          {this.state.json.id} <br />
          {this.state.json.userName} <br />
          {this.state.json.desc} <br />
          {this.state.json.picRef} <br />
          {this.state.json.roleList} <br />
          {this.state.json.interests} <br />
          {this.state.json.liked} <br />
          {this.state.json.ignored} <br />
          {this.state.json.matches}
        </p>
      </div>
    );
  }
}
