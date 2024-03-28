import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Chat } from 'src/app/classes/chat/chat';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  getListOfDevices(string: String):Observable<Chat[]> {
    return this.httpClient.get<Chat[]>(`${this.baseURL}${string}`);
  }
  addChat(string: String, chat: Chat):Observable<Object> {
    return this.httpClient.put<Chat>(`${this.baseURL}${string}`,chat);
  }
  setSeen(string: String,chat: Chat):Observable<Object> {
    return this.httpClient.put<Chat>(`${this.baseURL}${string}`,chat);
  }
  constructor(private httpClient: HttpClient) { }
  private baseURL = 'http://localhost:8083/chat'
}
