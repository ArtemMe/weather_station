import React from 'react';
import { Switch, Redirect } from 'react-router-dom';
import {TEST_LINK} from "./links";
import PrivateRoute from "./privateRoute";
import Test from "../containers/Test";

export function MainRoute(props) {
    return (
        <Switch>
            <Redirect exact from={`/`} to={TEST_LINK} />
            <PrivateRoute exact path={TEST_LINK} component={Test} />
        </Switch>
    )
}
