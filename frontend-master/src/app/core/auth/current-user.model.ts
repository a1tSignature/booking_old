import { Role } from './role.model';

export interface LoggedUser {
  readonly authenticated: true;
  readonly username: string;

  hasRole(role: Role): boolean;

  getUsername(): string;
}

export interface Anonymous {
  readonly authenticated: false;

  hasRole(role: Role): boolean;

  getUsername(): string;
}

export type CurrentUser = LoggedUser | Anonymous;
