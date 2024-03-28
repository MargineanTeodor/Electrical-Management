import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class SensorService {
  getArray(string: String):Observable<number[]> {
    return this.httpClient.get<number[]>(`${this.baseURL}${string}`)
  }
  private baseURL = 'http://localhost:8082/sensor'
  constructor(private httpClient: HttpClient) { }
}
