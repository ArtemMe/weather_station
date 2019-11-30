import React, {Component} from 'react';
import {history} from '../../src/store/configureStore';
import {ConnectedRouter} from 'connected-react-router';
import {MainRoute} from '../router';
import {getResource} from "../services/ResourceService";

class App extends Component {
    constructor(props){
        super(props);

        this.state = {stationMetrics:{}}
    }

    componentDidMount(){
        const stationData = getResource("http://localhost:8080/observation/036170-99999?maxCount=10");

        stationData.then(res => {
            this.setState({stationMetrics : res.data})
        });

    }
    render() {
        const {stationMetrics} = this.state;
        console.log(stationMetrics);
        var k = stationMetrics["data"];
        // console.log(k[-2114663417000]);

        // console.log(Object.keys(k));

        return (

            <div>
                {/*<Table list={k}/>*/}
            </div>
        );
    }
}

class Table extends Component {
    render() {
        const { list, pattern, onDismiss } = this.props;
        return (
            <div>
                <div>
                    <h1>Информация о станции</h1>
                </div>
                <div>
                {Object.keys(list).forEach(item =>
                    <div>
                    <span>item</span>
                    <span>{list[item]}</span>
                        <span>
                            <button onClick={() => onDismiss(item.objectID)} type="button">
                                Dismiss
                            </button>
                        </span>
                    </div>
                    )}
                </div>
            </div>
        );
    }
}
export default App;
