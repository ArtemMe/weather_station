import { all } from 'redux-saga/effects';
import { saga as testSaga } from '../ducks/test';

export default function* rootSaga() {
    yield all([
        testSaga(),
    ]);
}
