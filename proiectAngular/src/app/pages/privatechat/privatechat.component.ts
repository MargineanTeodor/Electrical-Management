import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { each } from 'chart.js/dist/helpers/helpers.core';
import { Chat } from 'src/app/classes/chat/chat';
import { ChatService } from 'src/app/services/chat/chat.service';
import { SensorService } from 'src/app/services/sensor/sensor.service';

@Component({
  selector: 'app-privatechat',
  templateUrl: './privatechat.component.html',
  styleUrls: ['./privatechat.component.css']
})
export class PrivatechatComponent implements OnInit {
  constructor(private chatService: ChatService,private router: Router,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.token=this.route.snapshot.params['token'];
    this.id2=this.route.snapshot.params['id2'];
    this.startMessageUpdates();
    
  }
  getMessages() {
    this.string2= "/getChat?id="+this.id+"&id2="+this.id2;
    this.chatService.getListOfDevices(this.string2).subscribe(data =>
      {
        this.messages = data;
      });
  }
  token : String | undefined;
  messages: Chat[] = [];
  id: number | undefined;
  id2: number | undefined;
  string : String = "";
  string2 : String = "";
  string3 : String = "";
  newMessage: Chat = new Chat;
  message2: Chat=  new Chat;
  currentUserId: number | undefined;
  updateInterval:any;
isOwnMessage(message: Chat): boolean {
  return message.sender === this.id;
}
isFromId1ToId2(message: Chat): boolean {
  return message.sender ==  this.id && message.receiver == this.id2;
}
setSeen()
{
  this.messages.forEach(item => {
    if (item.seen == false && item.receiver == this.id2)
    {
      this.string3= "/setSeen?id="+item.id;
      item.seen = true;
      console.log(item)
      this.chatService.setSeen(this.string3,item).subscribe(data =>
        {
        });
    }
  });
}
sendMessage() {
  this.setSeen()
  this.message2.receiver=this.id2;
  this.message2.sender=this.id;
  this.message2.message=this.newMessage.message;
  this.string="/addChat?message="+this.newMessage.message+"&receiver="+this.id2+"&sender="+this.id;
  this.chatService.addChat(this.string,this.message2).subscribe(data =>
    {
    });
}
private startMessageUpdates() {
  this.getMessages(); // Initial call
  this.updateInterval = setInterval(() => this.getMessages(), 1000);
}
}
