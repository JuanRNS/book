import { Component } from '@angular/core';
import { SideBarComponent } from "../../../core/components/side-bar/side-bar.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterOutlet, SideBarComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

}
