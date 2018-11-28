import React, { Component } from "react";
import "./MatchedUsers.css";
import MessageDetail from "./MessageDetail";

class MatchedUsers extends Component {
  state = {};
  render() {
    return (
      <div>
        <div className="ui bottom attached segment pushable">
          <div className="ui visible inverted left vertical sidebar menu">
            <div className="ui segment users">
              <img
                className="ui avatar image"
                src="https://www.w3schools.com/howto/img_avatar.png"
                alt="avatar"
              />
              <span>Username</span>
            </div>
            <div className="ui segment users">
              <img
                className="ui avatar image"
                src="https://www.w3schools.com/howto/img_avatar.png"
                alt="avatar"
              />
              <span>Username</span>
            </div>
            <div className="ui segment users">
              <img
                className="ui avatar image"
                src="https://www.w3schools.com/howto/img_avatar.png"
                alt="avatar"
              />
              <span>Username</span>
            </div>
          </div>
          <div className="pusher">
            <div className="ui basic segment" />
            <MessageDetail />
            <div className="ui form">
              <div className="field">
                <h2>Type a message</h2>
                <textarea rows="8" cols="50" />
                <i class="circular large paper plane icon" />
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default MatchedUsers;
