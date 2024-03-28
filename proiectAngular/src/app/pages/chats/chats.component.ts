import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/classes/user/user';
import { UserserviceService } from 'src/app/services/user/userservice.service';

@Component({
  selector: 'app-chats',
  templateUrl: './chats.component.html',
  styleUrls: ['./chats.component.css']
})
export class ChatsComponent implements OnInit {
  id!: number;
  token : String | undefined;
  listOfUsers: User[] | undefined;
  user: User= new User;
  constructor(private userService: UserserviceService,
    private router: Router,
    private route: ActivatedRoute){}
ngOnInit(): void {
  this.token=this.route.snapshot.params['token'];
  this.id=this.route.snapshot.params['id'];
  this.getUsers();
}
private getUsers(){
  this.userService.getListOfUsers().subscribe(data =>
  {
    this.listOfUsers = data;
  });
}
  chat(id: number | undefined) {
  
    this.router.navigate(['privatechat',this.id,id,this.token]);
  }
  getBack() {
    this.router.navigate(['admin',this.token]);
    }
}
