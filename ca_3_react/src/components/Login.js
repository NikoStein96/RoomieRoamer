import React, { Component } from "react"
import "../Login.css";
class Login extends Component {
    render() {
        return (
            <div id="loginform">
                <div class="ui labeled input">
                    <div class="ui label">
                        Username
  </div>
                    <input type="text" />
                </div>
                <br />
                <div class="ui labeled input">
                    <div class="ui label">
                        Password
  </div>
                    <input type="password" />
                </div>
                <div id="signupbutton">
                    <button class="ui orange button" onClick={this.handleSignUpClick} >Sign Up</button>
                </div>
            </div>

        )
    }
}

export default Login;