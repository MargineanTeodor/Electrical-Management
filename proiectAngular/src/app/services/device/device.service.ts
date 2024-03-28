import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Device } from 'src/app/classes/device/device';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  deleteDevice(id: number | undefined, id1: number) {
    return this.httpClient.delete(`${this.baseURL}/deleteDevice?Id=${id}&IdUser=${id1}`);
  }
  getListOfDevices(id:number|undefined):Observable<Device[]> {
    return this.httpClient.get<Device[]>(`${this.baseURL}/deviceList?id=${id}`);
  }
  constructor(private httpClient: HttpClient) {
  }
  addDevice(string: String, device: Device):Observable<Object> {
    return this.httpClient.put<Device>(`${this.baseURL}${string}`,device);
  }
  private baseURL = 'http://localhost:8081/device'
}
