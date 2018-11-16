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
                    <body>
                            <p>
                            {data.map(item => <Dummy key={item.ID} input = {item}/>)}
                            </p>
                            <p>
                            {all.map(value => <AllData key={value.name} input = {value}/>)}
                            </p>
                            <button onClick = {this.nextResult}>Press me for next 20 items</button>
                            <button onClick = {this.previousResult}>Press me for previous 20 items</button>
                            <button onClick = {this.getalldata}>Press me for all</button>
                    </body>
                </div>
        )
    }
}

const Dummy = (props) => <p>{props.input.Desc}</p>
const AllData = (props) => <p>{props.input.name}</p>

export default DummyData;