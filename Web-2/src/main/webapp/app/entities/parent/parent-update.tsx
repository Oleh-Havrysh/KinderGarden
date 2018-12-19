import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './parent.reducer';
import { IParent } from 'app/shared/model/parent.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IParentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IParentUpdateState {
  isNew: boolean;
}

export class ParentUpdate extends React.Component<IParentUpdateProps, IParentUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { parentEntity } = this.props;
      const entity = {
        ...parentEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/parent');
  };

  render() {
    const { parentEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="myApp2App.parent.home.createOrEditLabel">
              <Translate contentKey="myApp2App.parent.home.createOrEditLabel">Create or edit a Parent</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : parentEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="parent-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameLabel" for="name">
                    <Translate contentKey="myApp2App.parent.name">Name</Translate>
                  </Label>
                  <AvField id="parent-name" type="text" name="name" />
                </AvGroup>
                <AvGroup>
                  <Label id="surnameLabel" for="surname">
                    <Translate contentKey="myApp2App.parent.surname">Surname</Translate>
                  </Label>
                  <AvField id="parent-surname" type="text" name="surname" />
                </AvGroup>
                <AvGroup>
                  <Label id="addressLabel" for="address">
                    <Translate contentKey="myApp2App.parent.address">Address</Translate>
                  </Label>
                  <AvField id="parent-address" type="text" name="address" />
                </AvGroup>
                <AvGroup>
                  <Label id="phoneLabel" for="phone">
                    <Translate contentKey="myApp2App.parent.phone">Phone</Translate>
                  </Label>
                  <AvField id="parent-phone" type="text" name="phone" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="email">
                    <Translate contentKey="myApp2App.parent.email">Email</Translate>
                  </Label>
                  <AvField id="parent-email" type="text" name="email" />
                </AvGroup>
                <AvGroup>
                  <Label id="loginLabel" for="login">
                    <Translate contentKey="myApp2App.parent.login">Login</Translate>
                  </Label>
                  <AvField id="parent-login" type="text" name="login" />
                </AvGroup>
                <AvGroup>
                  <Label id="passwordLabel" for="password">
                    <Translate contentKey="myApp2App.parent.password">Password</Translate>
                  </Label>
                  <AvField id="parent-password" type="text" name="password" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/parent" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  parentEntity: storeState.parent.entity,
  loading: storeState.parent.loading,
  updating: storeState.parent.updating,
  updateSuccess: storeState.parent.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ParentUpdate);
