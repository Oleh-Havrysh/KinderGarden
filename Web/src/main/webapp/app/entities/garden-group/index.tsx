import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import GardenGroup from './garden-group';
import GardenGroupDetail from './garden-group-detail';
import GardenGroupUpdate from './garden-group-update';
import GardenGroupDeleteDialog from './garden-group-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={GardenGroupUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={GardenGroupUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={GardenGroupDetail} />
      <ErrorBoundaryRoute path={match.url} component={GardenGroup} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={GardenGroupDeleteDialog} />
  </>
);

export default Routes;
