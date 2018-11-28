import React, { Component } from "react"


export default class Home extends Component {
    constructor(props) {
      super(props);
      this.state = { loggedIn: false }
    }
  // welcome message with description
    render() {
      return (
        <div>
        Welcome to our site.<br></br>
        This is the front page. Here we will guide you through the different options you have of navigating around the place.<br></br>
        Up top is a navigation bar where you have "Home", "Login", "UserPage" and "DummyData". Any of these links can be clicked,<br></br> 
        but to get the full experience you will have to log in.<br></br>
        Try with the username "user" and password "test123", or as admin "admin" and "test123".<br></br>
        Clicking on the login-button will redirect you to our "UserPage" with additional info now.<br></br>
        You will be greeted with a message confirming that you have logged in successfully.<br></br>
        On the "DummyData" page is a list of 20 items being listed from top to bottom. Theres two button available to increase or decrease<br></br>
        the next pages of data being rendered. A maximum of 1000 items are available to go through.
        Thank you for testing!<br></br>
        <br></br>
        - GIF/JIF
        </div>
  
      );
    }
  }