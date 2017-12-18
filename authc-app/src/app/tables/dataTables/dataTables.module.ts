import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {DataTableModule} from "angular2-datatable";

import { DataTablesRoutingModule } from "./dataTables.routing";
import { DataTablesComponent } from './dataTables.component';
import { DataService } from './dataTables.service'


@NgModule({
  imports: [CommonModule, DataTableModule, DataTablesRoutingModule],
  declarations: [DataTablesComponent],
  bootstrap: [],

  providers: [DataService]
})

export class DataTablesModule {}
