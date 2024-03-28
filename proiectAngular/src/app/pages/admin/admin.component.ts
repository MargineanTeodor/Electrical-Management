import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/classes/user/user';
import { UserserviceService } from 'src/app/services/user/userservice.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  listOfUsers: User[] | undefined;
  id!: number;
  token : String | undefined;
  user: User= new User;
  string: String="";
  constructor(private userService: UserserviceService,
    private router: Router,
    private route: ActivatedRoute){}
    ngOnInit(): void {
      this.token=this.route.snapshot.params['token'];
      this.id=1;
      this.getUsers();
    }
    private getUsers(){
      this.userService.getListOfUsers().subscribe(data =>
      {
        this.listOfUsers = data;
      });
    }
  addDevice(id: number|undefined)
  {
    this.router.navigate(['addDevice', id, this.token]);
  }
  
  onLogout()
  {
    this.router.navigate(['login']);
  }
  deleteUser(id: number|undefined)
  {
    this.userService.deleteUser(id,this.token).subscribe(data=>
      {
        console.log(data);
        this.getUsers();
      })
  }
  
  seeDevices(id: number|undefined){
    this.router.navigate(['listDevices', id, this.token]);

  }
  modifyPassword(id: number|undefined){
    
    this.router.navigate(['modifyPassword', id, this.token]);
  }
  onChats() {
    this.router.navigate(['chat',this.id, this.token])
    }
}

