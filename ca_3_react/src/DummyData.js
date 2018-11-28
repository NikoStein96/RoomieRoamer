import React, { Component } from 'react';

const url = 'https://bloodoath-co.dk/CA3/api/info/dummy/0';
const urlall = 'https://bloodoath-co.dk/CA3/api/info/all';
//const testurl = 'https://swapi.co/api/people/';

class DummyData extends Component {

    constructor(props) {
        super(props);
        this.state = { data: [], dataAll: [],
            cur: 0
        };

    }
    // fetch from the dummy url
    componentDidMount (){
        fetch(url)
        .then(response => response.json())
        .then((({Result: data}) =>this.setState({data})));

    }

    // get next 20 items from dummydata
    nextResult = () => {
        const add = 20;
        let next = this.state.cur + add;
        this.setState({cur: next})
        fetch(url+this.state.cur)
        .then(response => response.json())
        .then((({Result: data}) =>this.setState({data})));
      }
    // get prvious 20 items from dummydata
    previousResult = () => {
        const add = 20;
        let next = this.state.cur - add;
        this.setState({cur: next})
        fetch(url+this.state.cur)
        .then(response => response.json())
        .then((({Result: data}) =>this.setState({data})));
    }
    // get all data
    getalldata = () => {
        fetch(urlall)
        .then(response => response.json())
        .then((({results: dataAll}) =>this.setState({dataAll})));
    }

    // renders list from dummy data
    render() {
        let data = this.state.data;
        let all = this.state.dataAll;
        console.log("all", all);
      //  console.log("items from api: " + data);
        return (
                <div >
                    <table>
                        
                    
                            {data.map(item => <Dummy key={item.ID} input = {item}/>)}
                              
                        <tr>  
                            
                            {all.map(value => <AllData key={value.name} input = {value}/>)}
                            
                        </tr>
                            <button onClick = {this.nextResult}>Press me for next 20 items</button>
                            <button onClick = {this.previousResult}>Press me for previous 20 items</button>
                            <button onClick = {this.getalldata}>Press me for all</button>
                    
                    
                    </table>
                </div>
        )
    }
}

const Dummy = (props) => <li>{props.input.Desc}</li>
const AllData = (props) => <td>{props.input.name}</td>

export default DummyData;