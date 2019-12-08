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

    }
    render() {
        var stompClient = null;

        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('conversationDiv').style.visibility
                = connected ? 'visible' : 'hidden';
            document.getElementById('response').innerHTML = '';
        }

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

        function disconnect() {
            if(stompClient != null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }

        function sendMessage() {
            var from = document.getElementById('from').value;
            var text = document.getElementById('text').value;
            stompClient.send("/app/chat", {},
                    JSON.stringify({'from': from, 'text': text}));

        }

        function showMessageOutput(messageOutput) {
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(messageOutput.from + ": "
                + messageOutput.text + " (" + messageOutput.time + ")"));
            response.appendChild(p);
        }
        return (

            <div>
                <div>
                    <input type="text" id="from" placeholder="Choose a nickname"/>
                </div>
                <br/>
                <div>
                    <button id="connect" onClick={connect}>Connect</button>
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

class TestWebSocket extends Component {
    render() {
        return (
            <div>

            </div>
        );
    }
}

export default App;
