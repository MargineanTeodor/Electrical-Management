
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Token } from 'src/app/classes/token/token';
import { User } from 'src/app/classes/user/user';
import { UserserviceService } from 'src/app/services/user/userservice.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
onRegister() {
  this.router.navigate(['register']);
}
  user : User = new User();
  id!: number;
  string: String="";
  bool :Boolean = new Boolean();
  token: Token = new Token();
  constructor(private userService:UserserviceService,
    private router: Router){}
    ngOnInit(): void {
      this.getUser();
    }
    getUser()
  {
    this.string="/findUserByUsername?username="+this.user.username;
    const x= this.userService.findUserByname(this.string).subscribe(data =>{
      this.user=data;
    },
    error=> console.log(error));
  }
  onSubmit() {
    this.getToken();
    this.getUser();
      if(this.user.role =="normal")
        this.router.navigate(['listDeviceNormal',this.user.id,this.token.token]);
      else
        this.router.navigate(['admin',this.token.token]);
  }
  getToken() {
    this.string="/login?username="+this.user.username+"&password="+this.user.password;
    const x= this.userService.getToken(this.string).subscribe(data =>{
      this.token=data;
    },
    error=> console.log(error));
  }
}

