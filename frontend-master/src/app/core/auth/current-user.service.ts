import {
  APP_INITIALIZER,
  Injectable,
  Provider
} from '@angular/core';
import {
  Anonymous,
  CurrentUser,
  LoggedUser
} from './current-user.model';
import {BehaviorSubject, Observable} from 'rxjs';
import {
  HttpClient
} from '@angular/common/http';
import {switchMap, tap} from 'rxjs/operators';
import {Role} from './role.model';
import {environment} from '../../../environments/environment.prod';
import {UserModel} from '../../features/users/models/user-model';

interface ApiProfile {
  username: string;
  role: Role;
}

export class AnonymousUserImpl implements Anonymous {
  readonly authenticated: false = false;

  hasRole(role: Role): boolean {
    return false;
  }

  getUsername(): string {
    return undefined;
  }
}

export class CurrentUserImpl implements LoggedUser {
  readonly authenticated: true = true;
  readonly username = this.profile.username;
  role: Role;


  constructor(readonly profile: ApiProfile) {
  }

  hasRole(role: Role): boolean {
    return (this.profile.role === role);
  }

  getUsername(): string {
    return this.username;
  }
}


@Injectable({
  providedIn: 'root'
})
export class CurrentUserService {
  readonly user$ = new BehaviorSubject<CurrentUser>(
    new AnonymousUserImpl()
  );

  constructor(private http: HttpClient) {
    console.log('Current User created');
  }

  refreshCurrentUser(): Observable<void> {
    return this.http
      // tslint:disable-next-line:no-any
      .get<any>(
        `${environment.api}/user/current`)
      .pipe(
        tap((profile) => {
          if (profile == undefined) {
            this.user$.next(new AnonymousUserImpl());
          } else {
            this.user$.next(new CurrentUserImpl(profile));
          }
        })
      ) as Observable<void>;
  }

  login(
    username: string,
    password: string
  ): Observable<void> {
    return this.http
      .post<void>(
        `${environment.api}/auth/login`,
        {username, password}
      )
      .pipe(switchMap(() => this.refreshCurrentUser()));
  }

  logout(): Observable<void> {
    return this.http
      .get<void>(
        `${environment.api}/auth/logout`
      )
      .pipe(
        tap((res) => this.user$.next(new AnonymousUserImpl()))
      );
  }

  // tslint:disable-next-line:no-any
  register(newUser: UserModel): Observable<any> {
    return this.http.post(`${environment.api}/user`, newUser);
  }
}

export function currentUserInitializerFactory(
  currentUserService: CurrentUserService
): () => Promise<void> {
  return () => {
    return currentUserService
      .refreshCurrentUser()
      .toPromise();
  };
}

export const CURRENT_USER_INITIALIZER: Provider = {
  provide: APP_INITIALIZER,
  useFactory: currentUserInitializerFactory,
  deps: [CurrentUserService],
  multi: true
};
