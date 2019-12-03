import React, {Component} from 'react';
import {history} from '../../src/store/configureStore';
import {ConnectedRouter} from 'connected-react-router';
import {MainRoute} from '../router';
import {getResource} from "../services/ResourceService";
import './App.css';

class App extends Component {
    constructor(props){
        super(props);

        this.state = {stationMetrics:{}}
    }

    componentDidMount(){
        const stationData = getResource("http://localhost:8082/observation/036170-99999?maxCount=10");

        stationData.then(res => {
            this.setState({stationMetrics : res.data})
        });

    }
    render() {
        const {stationMetrics} = this.state;
        console.log(stationMetrics);
        var k = stationMetrics["data"] || {};
        // console.log(k);
        // console.log(k['-2114404217000'])
        // for(var i in k) {
        //     console.log(i);
        // }
        console.log(Object.keys(k));

        return (

            <div>
                <Table list={k}/>
            </div>
        );
    }
}

class Table extends Component {
    render() {
        const { list, pattern, onDismiss } = this.props;
        console.log(Object.keys(list));
        Object.keys(list).forEach(i=>console.log(i));
        return (
            <div>
                <div>
                    <h1>Информация о станции</h1>
                </div>
                <div className="table">
                {Object.keys(list).map( i =>
                    <div className="table-row">
                    <span style={{width:'70%'}}>{i}</span>
                    <span style={{width:'30%'}}>{list[i]}</span>
                    </div>
                    )}
                </div>
            </div>
        );
    }
}
export default App;
