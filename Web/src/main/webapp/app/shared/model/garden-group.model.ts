import { ITeacher } from 'app/shared/model//teacher.model';

export interface IGardenGroup {
  id?: number;
  name?: string;
  teacher?: ITeacher;
}

export const defaultValue: Readonly<IGardenGroup> = {};
