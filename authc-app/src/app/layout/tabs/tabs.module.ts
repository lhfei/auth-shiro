import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import {
  MdTabsModule,
  MdToolbarModule
} from '@angular/material';

import {DataTableModule} from "angular2-datatable";
import { DataTablesComponent } from '../../tables/dataTables/dataTables.component';
import { DataService } from '../../tables/dataTables/dataTables.service'
//
//import { DataTablesModule } from '../../tables/dataTables/dataTables.module';

import { TabsRoutingModule } from './tabs.routing';
import { TabsComponent } from './tabs.component';

@NgModule({
  imports: [
    MdTabsModule,
    CommonModule,
    DataTableModule,
    TabsRoutingModule
  ],

  declarations: [TabsComponent, DataTablesComponent],
  bootstrap: [],
  providers: [DataService]
})
export class TabsModule {}
