import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: 'e2f089dc-fb3c-4af1-afa2-c136cb17bf60',
};

export const sampleWithPartialData: IAuthority = {
  name: 'a0e975ce-3669-4a8c-8494-f5f7f003863b',
};

export const sampleWithFullData: IAuthority = {
  name: '83b302b3-1e83-408f-99be-a5eac1a8021e',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
