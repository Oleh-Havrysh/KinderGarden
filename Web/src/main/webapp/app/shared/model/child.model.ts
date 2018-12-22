import { Moment } from 'moment';
import { IParent } from 'app/shared/model//parent.model';
import { IGardenGroup } from 'app/shared/model//garden-group.model';

export interface IChild {
  id?: number;
  name?: string;
  surname?: string;
  birthDate?: Moment;
  parent?: IParent;
  group?: IGardenGroup;
}

export const defaultValue: Readonly<IChild> = {};
