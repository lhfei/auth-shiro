import { Component, OnInit } from '@angular/core'; 
import { routerTransition } from '../../router.animations';

@Component({
  selector: 'tabs-layout',
  templateUrl: './tabs.template.html',
  animations: [routerTransition()]
})

export class TabsComponent  implements OnInit {
    constructor() { }
    ngOnInit() {}
}
