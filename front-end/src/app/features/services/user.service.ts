import { Injectable } from '@angular/core';
import { environment } from '../../../environment/environment';
import { HttpServiceAbstract } from '../../core/abstract/http-service.abstract';
import { HttpClient } from '@angular/common/http';
import { IUserRequestLogin, IUserRequestRegister } from '../../core/interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService extends HttpServiceAbstract {
  constructor(http: HttpClient) {
    super(environment.baseUrl,http);
  }

  public login(user: IUserRequestLogin) {
    return this.post<any>('auth/login', user );
  }

  public register(user: IUserRequestRegister){
    return this.post<any>('auth/register', user);
  }

  public logout(){
    localStorage.removeItem('token');
  }

  setToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }


}
