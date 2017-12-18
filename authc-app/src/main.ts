import { enableProdMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

//import { DataTablesModule } from './app/tables/dataTables/dataTables.module';
//import { TabsModule } from './app/layout/tabs/tabs.module'

if (environment.production) {
  enableProdMode();
}

//platformBrowserDynamic().bootstrapModule(DataTablesModule);

//platformBrowserDynamic().bootstrapModule(TabsModule);

platformBrowserDynamic().bootstrapModule(AppModule);
