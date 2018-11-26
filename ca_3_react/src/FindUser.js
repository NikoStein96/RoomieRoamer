import React, { Component } from "react"
export default class FindUser extends Component{

    constructor(props) {
        super(props);
        this.state = {value: ''};
    
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }
    
      handleChange(event) {
        this.setState({value: event.target.value});
      }
    
      handleSubmit(event) {
        event.preventDefault();
        console.log(event.target.value);
        var URL = 'https://swapi.co/api/people/'+event.target.value;
            fetch(URL)
            .then(response =>response.json())
            .then(json => {
                console.log(json);
                document.getElementById("info").innerHTML = 
                "Name: " + json.name + "  mass: " +
                json.mass;
    
            });
        
        
      }
      
    
      render() {
        
        
        return (
          
          <form >
          
          <p>{this.state.value}</p>

          <input type = 'number' value = {this.state.value} onChange={this.handleChange}/>

          <input type = 'submit' value = "Submit" onSubmit={this.handleSubmit}/>

          <p id="info"/>

          </form>
        
          
        );
      }
}
