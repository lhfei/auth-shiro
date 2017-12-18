import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class DataService {

  constructor(private http: Http){}

  getData(): Promise<any> {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        this.http.get("assets/data/data.json")
          .subscribe((data) => {resolve(data.json())})
      }, 25);
    });
  }
}
