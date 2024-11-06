import {Component, OnInit} from '@angular/core';
import {RouterLink, RouterOutlet} from "@angular/router";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {environment} from "../../environments/environment.development";
import {NgForOf} from "@angular/common";

enum accountType {
  SAVING_ACCOUNT,
  CURRENT_ACCOUNT,
}
interface customer {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
}
interface account {
  balance: number;
  accountType: accountType;
  currency: string;
  customerInfo: customer;
}

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [
    RouterLink,
    RouterOutlet,
    NgForOf,
    HttpClientModule,
  ],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css'
})

export class AccountsComponent implements OnInit {
  accounts?: account[];

  constructor(private http:HttpClient) {
  }
  ngOnInit() {
    this.http.get<account[]>(`${environment.account_host}/accounts`).subscribe({
      next: data =>{
        this.accounts = data;
      }, error: error => console.log(error)
    })
  }

}
