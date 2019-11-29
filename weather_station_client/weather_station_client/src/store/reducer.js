import {combineReducers} from 'redux';

import {connectRouter} from 'connected-react-router';
import {default as test} from '../ducks/test';

export default history =>
    combineReducers({
        test,
        router: connectRouter(history),
    });
