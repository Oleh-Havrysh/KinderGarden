import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './child.reducer';
import { IChild } from 'app/shared/model/child.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IChildDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ChildDetail extends React.Component<IChildDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { childEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="myApp.child.detail.title">Child</Translate> [<b>{childEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">
                <Translate contentKey="myApp.child.name">Name</Translate>
              </span>
            </dt>
            <dd>{childEntity.name}</dd>
            <dt>
              <span id="surname">
                <Translate contentKey="myApp.child.surname">Surname</Translate>
              </span>
            </dt>
            <dd>{childEntity.surname}</dd>
            <dt>
              <span id="birthDate">
                <Translate contentKey="myApp.child.birthDate">Birth Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={childEntity.birthDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="myApp.child.parent">Parent</Translate>
            </dt>
            <dd>{childEntity.parent ? childEntity.parent.name : ''}</dd>
            <dt>
              <Translate contentKey="myApp.child.group">Group</Translate>
            </dt>
            <dd>{childEntity.group ? childEntity.group.name : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/child" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/child/${childEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ child }: IRootState) => ({
  childEntity: child.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ChildDetail);
