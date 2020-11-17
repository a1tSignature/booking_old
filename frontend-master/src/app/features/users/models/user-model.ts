import {Role} from '../../../core/auth/role.model';

export interface UserModel {

  username: string;
  password: string;
  role: Role;
  personalInfo: {
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    location: Location;
  }

}
