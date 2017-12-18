import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';

import { DataService } from './dataTables.service';

@Component({
  selector: 'data-tables',
  templateUrl: './dataTables.html',
  animations: [routerTransition()]
})

export class DataTablesComponent implements OnInit {
  public data;

  constructor(private service: DataService){
    this.service.getData().then((data) => {
      this.data = data;
    })
  }

  ngOnInit() {}
}
