import React, { Component } from 'react';
import './Admin.css';

const url = 'http://localhost:8080/RoomieRoamer/api/User/allasmap';


class Admin extends Component {

    constructor(props) {
        super(props);
        this.state = { dataAll: []};

    }
    // get all data
    componentDidMount(){
        fetch(url)
        .then(response => response.json())
        .then((({Result: dataAll}) =>this.setState({dataAll})));
    }
    // renders list from database
    render() {
    let all = this.state.dataAll;
    return (
        <div>
            <div>
                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Delete Description</th>
                                </tr>
                            </thead>
                        {all.map(value => <AllData key={value.id}  input = {value}/>)}
            </div>
            
        </div>
    )
}
}
// items to be printed from the database
const AllData = (props) => <table>
                            
                            <tbody>
                                <tr>
                                    <td>{props.input.id}</td>
                                    <td>{props.input.Name}</td>
                                    <td>{props.input.Desc}</td>
                                    <td>Delete</td>
                                </tr>
                            </tbody> 
                            </table>
export default Admin;