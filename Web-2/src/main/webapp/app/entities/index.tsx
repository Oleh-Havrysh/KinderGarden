import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import GardenGroup from './garden-group';
import Child from './child';
import Parent from './parent';
import Teacher from './teacher';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/garden-group`} component={GardenGroup} />
      <ErrorBoundaryRoute path={`${match.url}/child`} component={Child} />
      <ErrorBoundaryRoute path={`${match.url}/parent`} component={Parent} />
      <ErrorBoundaryRoute path={`${match.url}/teacher`} component={Teacher} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
