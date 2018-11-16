import React, { Component } from "react"
import facade from "./apiFacade";
import DummyData from "./DummyData";
import { HashRouter as Router, Route, Link } from "react-router-dom";

class Home extends Component {
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


class UserPage extends Component {
  constructor(props) {
    super(props);
    this.state = { dataFromServer: ""}
  }

// fetch user and admin details from backend
  componentDidMount() {
    facade.fetchData().then(res => this.setState({ dataFromServer: res }));
    facade.fetchDataAdmin().then(res => this.setState({ dataFromServer: res}));
  }

  render() {
    return (
      <div>
        Welcome to the userpage. If you are logged in, a message will be displayed below with your role and name.
        <div>
          {this.state.dataFromServer}
        </div>
        <br>
        </br>
        <button onClick={facade.logout}>Logout</button>
      </div>
      
      
    )

  }
}

// static login
class LogIn extends Component {
  constructor(props) {
    super(props);
    this.state = { username: "", password: "" }
  }
  
  login = (evt) => {
    evt.preventDefault();
    facade.login(this.state.username, this.state.password);
  }
  onChange = (evt) => {
    this.setState({ [evt.target.id]: evt.target.value })
  }
  render() {
    return (
      <div>
        <h2>Login</h2>
        <form onSubmit={this.login} onChange={this.onChange} >
          <input placeholder="User Name" id="username" />
          <input type="password" placeholder="Password" id="password" />
          <button>Login</button>
        </form>
      </div>
    )
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
        </ul>
      </nav>

      <Route path="/" exact component={Home} />
      <Route path="/login" exact component={LogIn} />
      <Route path='/userpage' exact component={UserPage} />
      <Route path="/dummydata" exact component={DummyData} />
    </div>
  </Router>
);
export default App;