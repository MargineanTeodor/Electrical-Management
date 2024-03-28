import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Device } from 'src/app/classes/device/device';
import { DeviceService } from 'src/app/services/device/device.service';

@Component({
  selector: 'app-add-device',
  templateUrl: './add-device.component.html',
  styleUrls: ['./add-device.component.css']
})
export class AddDeviceComponent {
  device: Device = new Device();
  string: String="";
  token: String="";
  id: number| undefined;
  ngOnInit(): void {
    this.token=this.route.snapshot.params['token'];
    this.id=this.route.snapshot.params['id'];
  }
  constructor( private deviceService: DeviceService,
    private router:Router,
    private route: ActivatedRoute)
    {}

  register()
    {
      this.deviceService.addDevice(this.string,this.device).subscribe(data=>{
        console.log(data);
      },
      error => console.log(error));
    }
    onSubmit()
    {
      this.string="/addDevice?description="+this.device.description+"&address="+this.device.address+"&maximHrEnergyConsumption="+this.device.maximHrEnergyConsumption+"&userID="+this.id;
      this.register();
      this.router.navigate(['admin',this.token]);
    }
}
