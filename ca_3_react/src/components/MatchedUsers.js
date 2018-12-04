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
                src="https://react.semantic-ui.com/images/wireframe/paragraph.png"
                alt="dfdsfdsfds"
                class="ui image"
                // eslint-disable-next-line
                alt="missingPicture"
              />
              <span>Username</span>
            </div>
            <div className="ui segment users">
              <img
                src="https://react.semantic-ui.com/images/wireframe/paragraph.png"
                alt="dfdsfdsfds"
                class="ui image"
                // eslint-disable-next-line
                alt="missingPicture"
              />
              <span>Username</span>
            </div>
            <div className="ui segment users">
              <img
                src="https://react.semantic-ui.com/images/wireframe/paragraph.png"
                alt="dfdsfdsfds"
                class="ui image"
                // eslint-disable-next-line
                alt="missingPicture"
              />
              <span>Username</span>
            </div>
            <div class="eight wide column">
              <img
                src="https://react.semantic-ui.com/images/wireframe/paragraph.png"
                alt="dfdsfdsfds"
                class="ui image"
                // eslint-disable-next-line
                alt="missingPicture"
              />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default MatchedUsers;
