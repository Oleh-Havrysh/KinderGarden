import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import gardenGroup, {
  GardenGroupState
} from 'app/entities/garden-group/garden-group.reducer';
// prettier-ignore
import child, {
  ChildState
} from 'app/entities/child/child.reducer';
// prettier-ignore
import parent, {
  ParentState
} from 'app/entities/parent/parent.reducer';
// prettier-ignore
import teacher, {
  TeacherState
} from 'app/entities/teacher/teacher.reducer';

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly gardenGroup: GardenGroupState;
  readonly child: ChildState;
  readonly parent: ParentState;
  readonly teacher: TeacherState;
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  passwordReset,
  password,
  settings,
  gardenGroup,
  child,
  parent,
  teacher,
  loadingBar
});

export default rootReducer;
