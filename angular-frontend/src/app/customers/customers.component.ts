import {Component, OnInit} from '@angular/core';
import {NgForOf} from "@angular/common";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {environment} from "../../environments/environment.development";
import {findIndex} from "rxjs";
interface customer {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
}
@Component({
  selector: 'app-customers',
  standalone: true,
  imports: [
    NgForOf,
    HttpClientModule,
  ],
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit {

  customers?: customer[];

  public i:number = 1;

  constructor(private http:HttpClient) { }
  ngOnInit() {
    this.http.get<customer[]>(`${environment.customer_host}/customers`).subscribe({
      next: data =>{
        this.customers = data;
      }, error: error => console.log(error)
    })
  }

  protected readonly findIndex = findIndex;
}
