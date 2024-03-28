import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import * as moment from 'moment';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
  currentDate: moment.Moment;
  weeks: moment.Moment[][];
  token : String | undefined;
  id: number | undefined;
  constructor(private router: Router,private route: ActivatedRoute) { 
    this.currentDate = moment(); // Initialize currentDate
    this.weeks = []; // Initialize weeks as an empty array
  }

  ngOnInit(): void {
    this.generateCalendar(this.currentDate);
    this.id=this.route.snapshot.params['id'];
    this.token=this.route.snapshot.params['token'];
  }

  generateCalendar(date: moment.Moment): void {
    const startDay = date.clone().startOf('month').startOf('week');
    const endDay = date.clone().endOf('month').endOf('week');

    const dateIterator = startDay.clone().subtract(1, 'day');
    this.weeks = []; // Reset weeks for the new month

    while (dateIterator.isBefore(endDay, 'day')) {
      this.weeks.push(
        Array(7).fill(0).map(() => dateIterator.add(1, 'day').clone())
      );
    }
  }

  previousMonth(): void {
    this.changeMonth(-1);
  }

  nextMonth(): void {
    this.changeMonth(1);
  }

  changeMonth(num: number): void {
    this.currentDate.add(num, 'months');
    this.generateCalendar(this.currentDate);
  }

  previousYear(): void {
    this.changeYear(-1);
  }

  nextYear(): void {
    this.changeYear(1);
  }

  changeYear(num: number): void {
    this.currentDate.add(num, 'years');
    this.generateCalendar(this.currentDate);
  }

  isCurrentMonth(date: moment.Moment): boolean {
    return date.isSame(this.currentDate, 'month');
  }

  onDayClick(date: moment.Moment): void {
    const formattedDate = date.format('YYYY-MM-DD');
    this.router.navigate(["graph",formattedDate,this.token,this.id])
  }
  onLogout()
  {
    this.router.navigate(['login']);
  }
}
