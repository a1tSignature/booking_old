import {Injectable, Provider} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class CredentialsInterceptor implements HttpInterceptor {
  constructor() {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      withCredentials: true
    });
    return next.handle(req);
  }



}
export const CREDENTIALS_INTERCEPTOR: Provider = {
  provide: HTTP_INTERCEPTORS,
  useClass: CredentialsInterceptor,
  multi: true
};
