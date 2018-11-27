import React, { Component } from "react";

class MatchedUsers extends Component {
  state = {};
  render() {
    return (
      <div>
        <div className="ui placeholder segment">
          <div className="ui icon header">
            Here you can contact a potential new room mate!
          </div>
        </div>

        <div class="ui grid">
          <div class="row">
            <div class="eight wide column">
              <img
                src="https://react.semantic-ui.com/images/wireframe/paragraph.png"
                class="ui image"
              />
            </div>
            <div class="eight wide column">
              <img
                src="https://react.semantic-ui.com/images/wireframe/paragraph.png"
                class="ui image"
              />
            </div>
          </div>
          <div class="row">
            <div class="eight wide column">
              <img
                src="https://react.semantic-ui.com/images/wireframe/paragraph.png"
                class="ui image"
              />
            </div>
            <div class="eight wide column">
              <img
                src="https://react.semantic-ui.com/images/wireframe/paragraph.png"
                class="ui image"
              />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default MatchedUsers;
