import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/classes/user/user';
import { UserserviceService } from 'src/app/services/user/userservice.service';

@Component({
  selector: 'app-mod-password',
  templateUrl: './mod-password.component.html',
  styleUrls: ['./mod-password.component.css']
})
export class ModPasswordComponent implements OnInit {
  password: String = '';
  token :String="";
  string: String= "";
  id: number | undefined;
  user:User = new User;
  constructor( private userService: UserserviceService,
    private router:Router,
    private route: ActivatedRoute)
    {}
  ngOnInit(): void {
    this.token=this.route.snapshot.params['token'];
    this.id=this.route.snapshot.params['id'];
  }
  onSubmit() {
    this.string="/swapPassword?id="+this.id+"&password="+this.password;
    this.userService.getUser(this.id ,this.token).subscribe(data=>{
        console.log(data);
        this.user=data;
    })
    this.user.password=this.password;
    this.userService.modPassword(this.string,this.user,this.token).subscribe(data=>{
        console.log(data);
    },
      error => console.log(error));
    this.router.navigate(['admin',this.token]);
  }
}
