import React, { Component } from "react";
import facade from "./apiFacade";
import DummyData from "./DummyData";
import UserPage from "./UserPage";
import Home from "./Home";
import FindUser from "./FindUser";

import MatchedUsers from "./components/MatchedUsers";

import Admin from "./Admin"

import { HashRouter as Router, Route, Link } from "react-router-dom";

class LogIn extends Component {
  constructor(props) {
    super(props);
    this.state = { username: "", password: "" };
  }

  login = evt => {
    evt.preventDefault();
    facade.login(this.state.username, this.state.password);
  };
  onChange = evt => {
    this.setState({ [evt.target.id]: evt.target.value });
  };
  render() {
    return (
      <div>
        <h2>Login</h2>
        <form onSubmit={this.login} onChange={this.onChange}>
          <input placeholder="User Name" id="username" />
          <input type="password" placeholder="Password" id="password" />
          <button>Login</button>
        </form>
      </div>
    );
  }
}

const App = () => (
  <Router>
    <div>
      <nav>
        <ul className="header">
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/login">Login</Link>
          </li>
          <li>
            <Link to="/userpage/">UserPage</Link>
          </li>
          <li>
            <Link to="/dummydata/">DummyData</Link>
          </li>
          <li>
            <Link to="/finduser/">FindUser</Link>
          </li>
          <li>

            <Link to="/messages">Messages</Link>

            <Link to="/admin/">Admin</Link>

          </li>
        </ul>
      </nav>

      <Route path="/" exact component={Home} />
      <Route path="/login" exact component={LogIn} />
      <Route path="/userpage" exact component={UserPage} />
      <Route path="/dummydata" exact component={DummyData} />
      <Route path="/finduser" exact component={FindUser} />

      <Route path="/messages" exact component={MatchedUsers} />

      <Route path="/admin" exact component={Admin} />

    </div>
  </Router>
);
export default App;
