import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IParent } from 'app/shared/model/parent.model';
import { getEntities as getParents } from 'app/entities/parent/parent.reducer';
import { IGardenGroup } from 'app/shared/model/garden-group.model';
import { getEntities as getGardenGroups } from 'app/entities/garden-group/garden-group.reducer';
import { getEntity, updateEntity, createEntity, reset } from './child.reducer';
import { IChild } from 'app/shared/model/child.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IChildUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IChildUpdateState {
  isNew: boolean;
  parentId: string;
  groupId: string;
}

export class ChildUpdate extends React.Component<IChildUpdateProps, IChildUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      parentId: '0',
      groupId: '0',
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

    this.props.getParents();
    this.props.getGardenGroups();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { childEntity } = this.props;
      const entity = {
        ...childEntity,
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
    this.props.history.push('/entity/child');
  };

  render() {
    const { childEntity, parents, gardenGroups, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="myApp.child.home.createOrEditLabel">
              <Translate contentKey="myApp.child.home.createOrEditLabel">Create or edit a Child</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : childEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="child-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameLabel" for="name">
                    <Translate contentKey="myApp.child.name">Name</Translate>
                  </Label>
                  <AvField id="child-name" type="text" name="name" />
                </AvGroup>
                <AvGroup>
                  <Label id="surnameLabel" for="surname">
                    <Translate contentKey="myApp.child.surname">Surname</Translate>
                  </Label>
                  <AvField id="child-surname" type="text" name="surname" />
                </AvGroup>
                <AvGroup>
                  <Label id="birthDateLabel" for="birthDate">
                    <Translate contentKey="myApp.child.birthDate">Birth Date</Translate>
                  </Label>
                  <AvField id="child-birthDate" type="date" className="form-control" name="birthDate" />
                </AvGroup>
                <AvGroup>
                  <Label for="parent.id">
                    <Translate contentKey="myApp.child.parent">Parent</Translate>
                  </Label>
                  <AvInput id="child-parent" type="select" className="form-control" name="parent.id">
                    <option value="" key="0" />
                    {parents
                      ? parents.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="group.id">
                    <Translate contentKey="myApp.child.group">Group</Translate>
                  </Label>
                  <AvInput id="child-group" type="select" className="form-control" name="group.id">
                    <option value="" key="0" />
                    {gardenGroups
                      ? gardenGroups.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/child" replace color="info">
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
  parents: storeState.parent.entities,
  gardenGroups: storeState.gardenGroup.entities,
  childEntity: storeState.child.entity,
  loading: storeState.child.loading,
  updating: storeState.child.updating,
  updateSuccess: storeState.child.updateSuccess
});

const mapDispatchToProps = {
  getParents,
  getGardenGroups,
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
)(ChildUpdate);
