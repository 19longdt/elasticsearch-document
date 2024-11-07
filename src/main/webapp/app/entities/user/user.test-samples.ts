import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 27006,
  login: 'Ba',
};

export const sampleWithPartialData: IUser = {
  id: 9481,
  login: "D*b}4H@c-7yA\\xt5lJ\\'xrFS\\{ea\\NbR4\\=eZi",
};

export const sampleWithFullData: IUser = {
  id: 17072,
  login: '.sO.M',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
