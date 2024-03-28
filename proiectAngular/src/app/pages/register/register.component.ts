import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/classes/user/user';
import { UserserviceService } from 'src/app/services/user/userservice.service';
import { FormsModule } from '@angular/forms'
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User = new User();
  string: String="";
  token: String="";
  ngOnInit(): void {
    this.token=this.route.snapshot.params['token'];
  }
  constructor( private userService: UserserviceService,
    private router:Router,
    private route: ActivatedRoute)
    {}
    
  register()
    {
      this.userService.register(this.string,this.user,this.token).subscribe(data=>{
        console.log(data);
      },
      error => console.log(error));
    }
    onSubmit()
    {
      this.string="/addUser?username="+this.user.username+"&password="+this.user.password+"&name="+this.user.name;
      this.register();
      this.router.navigate(['login']);
    }
  }
  

