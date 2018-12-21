import axios from 'axios';
import {
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IGardenGroup, defaultValue } from 'app/shared/model/garden-group.model';

export const ACTION_TYPES = {
  FETCH_GARDENGROUP_LIST: 'gardenGroup/FETCH_GARDENGROUP_LIST',
  FETCH_GARDENGROUP: 'gardenGroup/FETCH_GARDENGROUP',
  CREATE_GARDENGROUP: 'gardenGroup/CREATE_GARDENGROUP',
  UPDATE_GARDENGROUP: 'gardenGroup/UPDATE_GARDENGROUP',
  DELETE_GARDENGROUP: 'gardenGroup/DELETE_GARDENGROUP',
  RESET: 'gardenGroup/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IGardenGroup>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type GardenGroupState = Readonly<typeof initialState>;

// Reducer

export default (state: GardenGroupState = initialState, action): GardenGroupState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_GARDENGROUP_LIST):
    case REQUEST(ACTION_TYPES.FETCH_GARDENGROUP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_GARDENGROUP):
    case REQUEST(ACTION_TYPES.UPDATE_GARDENGROUP):
    case REQUEST(ACTION_TYPES.DELETE_GARDENGROUP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_GARDENGROUP_LIST):
    case FAILURE(ACTION_TYPES.FETCH_GARDENGROUP):
    case FAILURE(ACTION_TYPES.CREATE_GARDENGROUP):
    case FAILURE(ACTION_TYPES.UPDATE_GARDENGROUP):
    case FAILURE(ACTION_TYPES.DELETE_GARDENGROUP):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_GARDENGROUP_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_GARDENGROUP):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_GARDENGROUP):
    case SUCCESS(ACTION_TYPES.UPDATE_GARDENGROUP):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_GARDENGROUP):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'groups';

// Actions

export const getEntities: ICrudGetAllAction<IGardenGroup> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_GARDENGROUP_LIST,
    payload: axios.get<IGardenGroup>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IGardenGroup> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_GARDENGROUP,
    payload: axios.get<IGardenGroup>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IGardenGroup> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_GARDENGROUP,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const updateEntity: ICrudPutAction<IGardenGroup> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_GARDENGROUP,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IGardenGroup> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_GARDENGROUP,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
