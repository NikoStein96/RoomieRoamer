import React, { Component } from "react"
import facade from "./apiFacade";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

class ValidateUser extends Component {
  constructor(props) {
    super(props);
    this.state = { loggedIn: false }
  }
  logout = () => {facade.logout();
   this.setState({ loggedIn: false });} //TODO
  login = (user, pass) => {facade.login(user,pass)
   .then(res =>this.setState({ loggedIn: true }));} 
  render() {
    return (
      <div>
        {!this.state.loggedIn ? (<LogIn login={this.login} />) :
          ( <div>
              
              <button onClick={facade.logout}>Logout</button>
            </div>)}
      </div>
    )
  }
 }
 class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {loggedIn: false}
  }
    render() {
      return (
        <div>
          
          <br>
          </br><button onClick={facade.logout}>Logout</button>
        </div>
        
      );
    }
     }



 class UserPage extends Component {
  constructor(props) {
    super(props);
    this.state= {dataFromServer: ""}
    this.state= {username: ""}
  }
  
  componentDidMount(){
    facade.fetchData().then(res=> this.setState({dataFromServer: res}));}
    render() {
      return (
        <div>
          <h2>Welcome!</h2>
          <h3>{this.state.dataFromServer}</h3>
          <button onClick={facade.logout}>Logout</button>
        </div>
      )
    }
   }


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
    this.setState({[evt.target.id]: evt.target.value})
  }
  render() {
    return (
      <div>
        <h2>Login</h2>
        <form onSubmit={this.login} onChange={this.onChange} >
          <input placeholder="User Name" id="username" />
          <input placeholder="Password" id="password" />
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
        </ul>
      </nav>

      <Route path="/" exact component={Home} />
      <Route path="/login" exact component={LogIn} />
      <Route path='/userpage' exact component={UserPage} />
    </div>
  </Router>
);
export default App;