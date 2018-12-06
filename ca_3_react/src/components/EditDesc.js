import React, { Component } from "react"
class EditDesc extends Component {


    constructor(props) {
        super(props);
        this.state = {
            desc: ""
        };
    }
        componentDidMount() {
            fetch("http://localhost:8084/RoomieRoamer/api/User/allasmap")
                .then(response => response.json())
                .then((({ Result: dataAll }) => this.setState({
                    desc: dataAll

                 })));
        
        
    }

    render() {
        return (
            <textarea rows="4" cols="50">
                {this.state.desc}
                </textarea>


        )
    }
}


export default EditDesc;