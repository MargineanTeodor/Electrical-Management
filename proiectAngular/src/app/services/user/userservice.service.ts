import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Token } from 'src/app/classes/token/token';
import { User } from 'src/app/classes/user/user';

@Injectable({
  providedIn: 'root'
})
export class UserserviceService {
  modPassword(string: String, user: User, token: String):Observable<Object> {
    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });
    const requestOptions = {
      headers: headers
    };
    return this.httpClient.put<User>(`${this.baseURL}${string}`,user,requestOptions)
  }
  getUser( id:number|undefined,token: String) {
    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });
    const requestOptions = {
      headers: headers
    };
    return this.httpClient.get<User>(`${this.baseURL}/findUserById2?Id=${id}`,requestOptions)
  }
  deleteUser(id: number | undefined,token: String|undefined) {
    const headers = new HttpHeaders({
      'Authorization': `${token}` 
    });
    const requestOptions = {
      headers: headers
    };
    return this.httpClient.delete(`${this.baseURL}/delete?Id=${id}`,requestOptions);
  }
  getListOfUsers():Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.baseURL}/listOfUsers`)
  }
  register(string: String, user: User,token: String):Observable<Object> {
    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });
    const requestOptions = {
      headers: headers
    };
    return this.httpClient.put<User>(`${this.baseURL}${string}`,user,requestOptions)
  }
  getToken(string: String):Observable<Token> {
    return this.httpClient.get<Token>(`${this.baseURL}${string}`)
  }
  findUserByname(string: String) :Observable<User>{
    return this.httpClient.get<User>(`${this.baseURL}${string}`)
  }
  private baseURL = 'http://localhost:8080/user'
  constructor(private httpClient: HttpClient) { }
}
