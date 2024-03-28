import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ChartConfiguration, ChartData, ChartType } from 'chart.js';
import { SensorService } from 'src/app/services/sensor/sensor.service';

@Component({
  selector: 'app-energy-consumption-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {

  selectedDate: Date | null = null;
  token : String | undefined;
  dateString : string | number = "" ;
  id: number | undefined;
  string: String="";
  data : number[] = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
  public barChartOptions: ChartConfiguration['options'] = {
    responsive: true,
  };
  public barChartType: ChartType = 'bar';
  public barChartData: ChartData<'bar'> = {
    labels: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23], // Hours of the day
    datasets: [
      { 
        data: this.data, // Data for energy consumption
        label: 'Energy Consumption', // Label for the dataset
        backgroundColor: 'rgba(0, 123, 255, 0.5)', // Example: blue color with some transparency
        borderColor: 'rgba(0, 123, 255, 1)', // Border color of the bars
        borderWidth: 1
      }
    ]
  };
  
  constructor(private sensorService: SensorService,private router: Router,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.token=this.route.snapshot.params['token'];
    this.dateString=this.route.snapshot.params['date'];
    this.selectedDate = new Date(this.dateString)
    console.log("cevaaa")
    if (this.selectedDate) {
      console.log("cevaaa1111")
      this.loadDataForDate(this.selectedDate);
    }
  }

  updateChartData(): void {
    this.barChartData = {
      ...this.barChartData,
      datasets: [
        {
          ...this.barChartData.datasets[0],
          data: this.data
        }
      ]
    };
  }
  loadDataForDate(date: Date): void {
    this.string="/getArray?date="+this.dateString+"&id="+this.id;
    this.sensorService.getArray(this.string).subscribe(data =>
      {
        console.log(data)
        this.data = data;
        this.updateChartData();
      });
  }
  onLogout()
  {
    this.router.navigate(['login']);
  }
}
