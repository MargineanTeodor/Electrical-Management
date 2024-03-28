import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Device } from 'src/app/classes/device/device';
import { User } from 'src/app/classes/user/user';
import { DeviceService } from 'src/app/services/device/device.service';
import { UserserviceService } from 'src/app/services/user/userservice.service';

@Component({
  selector: 'app-listdevices',
  templateUrl: './listdevices.component.html',
  styleUrls: ['./listdevices.component.css']
})
export class ListdevicesComponent implements OnInit{
  ngOnInit(): void {
    this.token=this.route.snapshot.params['token'];
    this.id=this.route.snapshot.params['id'];
    this.getDevices();
  }
  constructor(private userService: UserserviceService,private deviceService: DeviceService,
    private router: Router,
    private route: ActivatedRoute){}
  listOfDevice: Device[] | undefined;
  id!: number;
  token : String | undefined;
  device: Device= new Device;
  string: String="";
  deleteDevice(id: number|undefined)
  {
    this.deviceService.deleteDevice(id,this.id).subscribe(data=>
      {
        console.log(data);
        this.getDevices();
      })
  }
  private getDevices(){
    this.deviceService.getListOfDevices(this.id).subscribe(data =>
    {
      this.listOfDevice = data;
    });
  }
   back()
  {
    this.router.navigate(['admin',this.token]);
  }
}
