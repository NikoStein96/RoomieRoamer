import React, { Component } from "react";
import "./MatchedUsers.css";
import MessageDetail from "./MessageDetail";
import facade from "../apiFacade";

class MatchedUsers extends Component {
  state = { matchedList: [], clickedUser: 0, chatId: 0, msgHistory: [] };

  componentDidMount() {
    fetch(
      "http://localhost:8084/RoomieRoamer/api/User/usermatches",
      facade.makeOptions("GET", true)
    )
      .then(response => response.json())
      .then(json => {
        this.setState({ matchedList: json });
      });
  }

  setClickedUserAndChatId = userId => {
    fetch(
      `http://localhost:8084/RoomieRoamer/api/chat/${userId}/chat`,
      facade.makeOptions("GET", true)
    )
      .then(response => response.json())
      .then(json => {
        this.setState({ chatId: json, clickedUser: userId });
        this.getChatHistory(json);
      });
  };

  getChatHistory = chatid => {
    fetch(
      `http://localhost:8084/RoomieRoamer/api/chat/${chatid}/history`,
      facade.makeOptions("GET", true)
    )
      .then(response => response.json())
      .then(json => {
        this.setState({ msgHistory: json });
      });
  };

  showUserList = () => {
    return this.state.matchedList.map(user => {
      return (
        <div
          onClick={() => {
            this.setClickedUserAndChatId(user.id);
          }}
          key={user.id}
          className="ui segment users"
        >
          <img
            className="ui avatar image"
            src="https://www.w3schools.com/howto/img_avatar.png"
            alt="avatar"
          />
          <span>{user.userName}</span>
        </div>
      );
    });
  };

  /*
  sendMessage = (event) =>{
    fetch(
      `http://localhost:8084/RoomieRoamer/api/chat/${this.state.clickedUser}/send`,
      facade.makeOptions("POST", true), { body: {

      }}
    )
      .then(response => response.json())
      .then(json => {
        console.log(json);
        this.setState({ matchedList: json });
      });
  }
  }
*/

  render() {
    return (
      <div>
        <div className="ui bottom attached segment pushable">
          <div className="ui visible inverted left vertical sidebar menu">
            {this.showUserList()}
          </div>
          <div className="pusher">
            <div className="ui basic segment" />
            <MessageDetail
              chatHistory={this.state.msgHistory}
              pressed={this.state.clickedUser}
            />
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
