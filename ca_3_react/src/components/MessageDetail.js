import React, { Component } from "react";
import "./MessageDetail.css";
import facade from "../apiFacade";

class MessageDetail extends Component {
  state = { msgHistory: [] };

  componentDidUpdate() {
    if (this.state.msgHistory !== this.props.chatHistory) {
      this.setState({ msgHistory: this.props.chatHistory });
    }
  }

  renderMsgList = () => {
    if (!this.state.msgHistory !== []) {
      return this.state.msgHistory.map(message => {
        if (message.sender !== this.props.pressed) {
          return (
            <div class="outgoing_msg">
              <div class="sent_msg">
                <p>{message.msg}</p>
              </div>
            </div>
          );
        }
      });
    }
  };

  render() {
    return (
      <div>
        <div className="messaging">
          <div className="msg_history">
            <div className="inc_msg">
              <img
                className="ui avatar image msg"
                src="https://www.w3schools.com/howto/img_avatar.png"
                alt="avatar"
              />
              <div className="received_width_msg">
                <p>Hej jeg hedder sebastian og jeg er scrum master</p>
                <span class="time_date"> 11:01 AM | June 9</span>
              </div>
            </div>
            <div class="outgoing_msg">
              <div class="sent_msg">
                <p>Test which is a new approach to have all solutions</p>
                <span class="time_date"> 11:01 AM | June 9</span>
              </div>
            </div>
            {this.renderMsgList()}
          </div>
        </div>
      </div>
    );
  }
}

export default MessageDetail;
