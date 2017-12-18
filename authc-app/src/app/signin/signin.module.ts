import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { TranslateModule } from '@ngx-translate/core';

import { LoginRoutingModule } from './signin-routing.module';
import { LoginComponent } from './signin.component';

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        LoginRoutingModule
    ],
    declarations: [LoginComponent]
})
export class LoginModule {
}
