import React, { Component } from "react";
import facade from "./apiFacade";
import './UserPage.css';


export default class UserPage extends Component {
  constructor(props) {
    super(props);
    this.state = { value: "" };
    this.state = { targetId: ""};
    this.state = { myId: ""};
  }

  // fetch to "GET" the ID of the user that is currently logged in

  componentDidMount(){
    var URL ="http://localhost:8084/RoomieRoamer/api/User/uid/";
    fetch(URL, facade.makeOptions("GET", true))
      .then(response => response.json())
      .then(json => {
        console.log("Current users ID who is logged in: " + json.id);
        this.setState({ myId : json.id });
      });
  }

  userLiked() {
    var URL = "http://localhost:8084/RoomieRoamer/api/User/like/"+this.state.myId+"/"+this.state.targetId;
    fetch(URL, facade.makeOptions("PUT", true))
    .then(response => response.json())
    .then(json => {
      console.log("I liked "+json);
  })
}
  userIgnored() {
    var URL = "http://localhost:8084/RoomieRoamer/api/User/ignored/"+this.state.myId+"/"+this.state.targetId;
    fetch(URL, facade.makeOptions("PUT", true))
    .then(response => response.json())
    .then(json => {
      console.log("I disliked "+json);
  })
}


  handleChangeUP = event => {
    console.log("this is event.target.value: " + event.target.value);
    this.setState({ value: event.target.value });
  };
  componentWillMount() {
    this.handleSubmitUP();
  }

  componentDidUpdate() {
    var URL = "http://localhost:8084/RoomieRoamer/api/User/poma";
    fetch(URL, facade.makeOptions("GET", true))
      .then(response => response.json())
      .then(json => {
        console.log(json);
        try {
          if (json === undefined) {
            document.getElementById("desc1").innerHTML =
              "Out of luck. Can't find a user.";
          } else {
            console.log(nr);
            document.getElementById("desc1").innerHTML =
              json.results[nr].Name +
              "</br>" +
              json.results[nr].Id +
              "</br>" +
              json.results[nr].Desc;
             // this.setState({ targetId : json.results[nr].Id });
             if(json.results[nr].Id !== this.state.targetId){
              this.setState({ targetId : json.results[nr].Id});
              }
            console.log("THIS IS THE ID: "+this.state.targetId);
            console.log( "and id should be= " + json.results[nr].Id)
            //nr--;
            
          }
        } catch {
          document.getElementById("desc1").innerHTML = "Out of luck. Can't find a user"; 
        }
      });
  }





  handleSubmitUP() {
    var URL = "http://localhost:8084/RoomieRoamer/api/User/poma";
    fetch(URL, facade.makeOptions("GET", true))
      .then(response => response.json())
      .then(json => {
        console.log(json);
        try {
          if (json === undefined) {
            document.getElementById("desc1").innerHTML =
              "Out of luck. Can't find a user";
          } else {
            console.log(nr);
            document.getElementById("desc1").innerHTML =
              json.results[nr].Name +
              "</br>" +
              json.results[nr].Id +
              "</br>" +
              json.results[nr].Desc;
              
              this.setState({targetId: json.results[nr].Id})
              console.log("THIS IS THE ID: "+this.state.targetId);
            //nr--;
            
          }
        } catch {
          document.getElementById("desc1").innerHTML = "Out of luck. Can't find a user";
        }
      });
  }

  likeThat = () => {
    this.userLiked();
    nr++;
    console.log(nr);
  }
  dislikeThat = () => {
    this.userIgnored();
    nr++;
  }

  render() {
    return (
        <div id="userpagebody">
        
        <div id="funktionbox">
        <p id="desc1">
          Loading ...
        </p>
        <button onClick={facade.logout}>Logout</button>
        <form onClick={this.likeThat}>
          <input id="likebtn" type="submit" value="Like" />
        </form>
        <form onClick={this.dislikeThat}>
          <input id="dislikebtn" type="submit" value="Dislike" />
        </form>
        </div>
        </div>
    );
  }
}
let nr = 0;