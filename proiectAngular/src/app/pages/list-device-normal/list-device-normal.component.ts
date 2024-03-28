import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Device } from 'src/app/classes/device/device';
import { DeviceService } from 'src/app/services/device/device.service';
import { UserserviceService } from 'src/app/services/user/userservice.service';

@Component({
  selector: 'app-list-device-normal',
  templateUrl: './list-device-normal.component.html',
  styleUrls: ['./list-device-normal.component.css']
})
export class ListDeviceNormalComponent implements OnInit, OnDestroy {

  listOfDevice: Device[] | undefined;
  id!: number;
  token : String | undefined;
  device: Device= new Device;
  string: String="";
  stock: any = {};
  private webSocket: WebSocket | undefined;
  constructor(private userService: UserserviceService,private deviceService: DeviceService,
    private router: Router,
    private route: ActivatedRoute){
      this.webSocket = new WebSocket('ws://localhost:8082/websocket');
      this.webSocket.onmessage = (event) => {
        this.stock = JSON.parse(event.data)
      };

    }
  ngOnDestroy(): void {
  }
    ngOnInit(): void {
      this.token=this.route.snapshot.params['token'];
      this.id=this.route.snapshot.params['id'];
      this.getDevices();
      console.log(this.stock)
    }
    private getDevices(){
      this.deviceService.getListOfDevices(this.id).subscribe(data =>
      {
        console.log(data)
        this.listOfDevice = data;
      });
    }
    logout()
  {
    this.router.navigate(['login']);
  }
  seeConsumption(id: number|undefined)
  {
    this.router.navigate(['calendar', id, this.token])
  }
  SupportChat() {
    this.router.navigate(['privatechat',this.id,1,this.token]);
    }
}
