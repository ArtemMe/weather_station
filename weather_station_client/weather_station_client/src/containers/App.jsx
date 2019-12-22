import React, {Component} from 'react';
import {history} from '../../src/store/configureStore';
import {ConnectedRouter} from 'connected-react-router';
import {MainRoute} from '../router';
import {getResource} from "../services/ResourceService";
import './App.css';
import SockJsClient from 'react-stomp'
import SockJS from 'sockjs-client'
import {Stomp} from "@stomp/stompjs"

class App extends Component {
    constructor(props){
        super(props);

        this.state = {stationMetrics:{}}
    }

    componentDidMount(){
        // const stationData = getResource("http://localhost:8082/observation/036170-99999?maxCount=10");
        // stationData.then(res => {
        //     this.setState({stationMetrics : res.data})
        // });
        var stompClient = null;
        var message = null;

        function connect() {
            var socket = new SockJS('http://localhost:8082/chat');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                setConnected(true);
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/messages', function(messageOutput) {
                    showMessageOutput(JSON.parse(messageOutput.body));
                });
            });
        }

        function setConnected(connected) {
            // document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('conversationDiv').style.visibility
                = connected ? 'visible' : 'hidden';
            document.getElementById('response').innerHTML = '';
        }

        function showMessageOutput(messageOutput) {
            console.log("showMesssageOutput: ")
            console.log(messageOutput);
            message = messageOutput;
            // this.setState({stationMetrics: message});

            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(messageOutput.from + ": "
                + messageOutput.text + " (" + messageOutput.time + ")"));
            response.appendChild(p);
        }

        connect();
        this.setState({stompClient : stompClient});
        this.setState({stationMetrics: message});
    }

    updateMetrics = (msg) => {

        console.log(msg);
        this.setState({stationMetrics: msg.data});
    }
    render() {
        var {stompClient} = this.state;
        var {stationMetrics} = this.state;

        function disconnect() {
            if(stompClient != null) {
                stompClient.disconnect();
            }
            // setConnected(false);
            console.log("Disconnected");
        }

        var sendMessage = ()=> {
            var from = "";//document.getElementById('from').value;
            var text = "";//document.getElementById('text').value;
            stompClient.send("/app/chat", {},
                    JSON.stringify({'from': from, 'text': text}));

        };

        // while(true) {
        //     sendMessage();
        //     setTimeout(()=>console.log("dsafa"), 1000);
        // }
        // console.log("app: "+stationMetrics);
        return (

            <div>
                <div>
                    <input type="text" id="from" placeholder="Choose a nickname"/>
                </div>
                <br/>
                <div>
                    {/*<button id="connect" onClick={connect}>Connect</button>*/}
                    <button id="disconnect" disabled="disabled" onClick={disconnect}>
                        Disconnect
                    </button>
                </div>
                <br/>
                <div id="conversationDiv">
                    <input type="text" id="text" placeholder="Write a message..."/>
                    <button id="sendMessage" onClick={sendMessage}>Send</button>
                    <p id="response"></p>
                </div>

                <Table list = {stationMetrics}/>
                <SampleComponent updateMetrics = {this.updateMetrics}/>
            </div>
        );
    }
}

class SampleComponent extends React.Component {
    constructor(props) {
        super(props);
    }

    sendMessage = (msg) => {
        this.clientRef.sendMessage('/app/chat', msg);
    };

    render() {
        return (
            <div>
                <SockJsClient url='http://localhost:8082/chat' topics={['/topic/messages']}
                              onMessage={(msg) => {this.props.updateMetrics(msg)}}
                              ref={ (client) => { this.clientRef = client }} />
                {/*<button id="sendMessage" onClick={this.sendMessage('gfhbdfgh')}>Send</button>*/}
            </div>
        );
    }
}

class Table extends Component {
    render() {
        var { list, pattern, onDismiss } = this.props;
        if(list === null) list = {}

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

class TestWebSocket extends Component {
    render() {
        return (
            <div>

            </div>
        );
    }
}

export default App;
