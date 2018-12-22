import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './parent.reducer';
import { IParent } from 'app/shared/model/parent.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IParentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ParentDetail extends React.Component<IParentDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { parentEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="myApp.parent.detail.title">Parent</Translate> [<b>{parentEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">
                <Translate contentKey="myApp.parent.name">Name</Translate>
              </span>
            </dt>
            <dd>{parentEntity.name}</dd>
            <dt>
              <span id="surname">
                <Translate contentKey="myApp.parent.surname">Surname</Translate>
              </span>
            </dt>
            <dd>{parentEntity.surname}</dd>
            <dt>
              <span id="address">
                <Translate contentKey="myApp.parent.address">Address</Translate>
              </span>
            </dt>
            <dd>{parentEntity.address}</dd>
            <dt>
              <span id="phone">
                <Translate contentKey="myApp.parent.phone">Phone</Translate>
              </span>
            </dt>
            <dd>{parentEntity.phone}</dd>
            <dt>
              <span id="email">
                <Translate contentKey="myApp.parent.email">Email</Translate>
              </span>
            </dt>
            <dd>{parentEntity.email}</dd>
            <dt>
              <span id="login">
                <Translate contentKey="myApp.parent.login">Login</Translate>
              </span>
            </dt>
            <dd>{parentEntity.login}</dd>
          </dl>
          <Button tag={Link} to="/entity/parent" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/parent/${parentEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ parent }: IRootState) => ({
  parentEntity: parent.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ParentDetail);
