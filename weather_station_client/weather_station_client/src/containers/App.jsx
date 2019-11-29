import React, {Component} from 'react';
import {history} from '../../src/store/configureStore';
import {ConnectedRouter} from 'connected-react-router';
import {MainRoute} from '../router';

class App extends Component {
    render() {
        return (
            <ConnectedRouter history={history}>
                <MainRoute/>
            </ConnectedRouter>
        );
    }
}

export default App;
