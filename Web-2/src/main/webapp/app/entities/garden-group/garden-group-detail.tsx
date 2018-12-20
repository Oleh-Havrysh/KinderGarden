import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './garden-group.reducer';
import { IGardenGroup } from 'app/shared/model/garden-group.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IGardenGroupDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class GardenGroupDetail extends React.Component<IGardenGroupDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { gardenGroupEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="myApp2App.gardenGroup.detail.title">GardenGroup</Translate> [<b>{gardenGroupEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">
                <Translate contentKey="myApp2App.gardenGroup.name">Name</Translate>
              </span>
            </dt>
            <dd>{gardenGroupEntity.name}</dd>
            <dt>
              <Translate contentKey="myApp2App.gardenGroup.teacher">Teacher</Translate>
            </dt>
            <dd>{gardenGroupEntity.teacher ? gardenGroupEntity.teacher.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/garden-group" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/garden-group/${gardenGroupEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ gardenGroup }: IRootState) => ({
  gardenGroupEntity: gardenGroup.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(GardenGroupDetail);
