
import React, { Component } from 'react';
import './Admin.css';




const url = 'http://localhost:8080/RoomieRoamer/api/User/allasmap';


class Admin extends Component {

    constructor(props) {
        super(props);
        this.state = { dataAll: [] };


    }
    deleteDesc = (id) => {
        fetch('http://localhost:8080/RoomieRoamer/api/User/' + id, {
  method: 'put',
  headers: {'Content-Type': 'application/json'},
  body: JSON.stringify({desc: ''})
})
.then(res => res.text())
.then(res => alert(res))


    }
    // get all data
    componentDidMount() {
        fetch(url)
            .then(response => response.json())
            .then((({ Result: dataAll }) => this.setState({ dataAll })));
    }
    // renders list from database
    render() {
        let all = this.state.dataAll;
        return (
            <div>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Delete Description</th>
                    </tr>
                </thead>
                {all.map(value => <AllData key={value.id} knap={this.deleteDesc} input={value} />)}


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
            <td> <button type="button" onClick={() => props.knap(props.input.id)} >Delete Desc</button> </td>
        </tr>
    </tbody>
</table>






export default Admin;