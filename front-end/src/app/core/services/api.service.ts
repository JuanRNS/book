import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpServiceAbstract } from '../abstract/http-service.abstract';
import { environment } from '../../../environment/environment';
import { IRequestContact, IResponseContact } from '../interfaces/contact.interface';

@Injectable({
  providedIn: 'root'
})
export class ApiService extends HttpServiceAbstract {

  constructor(http:HttpClient) { 
    super(environment.baseUrl,http);
  }

  public createContact(contact: IRequestContact){
    return this.post<IResponseContact>('contact/create', contact);
  }

  


}
