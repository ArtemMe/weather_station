import React from 'react';
import { applyMiddleware, compose, createStore } from 'redux';
import { createLogger } from 'redux-logger';
import createSagaMiddleware from 'redux-saga';
import { routerMiddleware } from 'connected-react-router';
import { createBrowserHistory } from 'history';
import reducer from './reducer';
import rootSaga from './saga';

export const history = createBrowserHistory();

const sagaMiddleware = createSagaMiddleware();

const logger = createLogger({
    level: 'log',
    collapsed: true,
});

const enhancer = compose(applyMiddleware(logger, sagaMiddleware, routerMiddleware(history)));

const initialState = {};

const store = createStore(reducer(history), initialState, enhancer);

sagaMiddleware.run(rootSaga);

export default store;
