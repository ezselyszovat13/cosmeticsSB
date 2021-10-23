import { Component, OnInit } from '@angular/core';
import { Occasion } from './occasion';
import { OccasionService } from './occasion.service';
import { Status } from './status';

@Component({
  selector: 'app-occasion',
  templateUrl: './occasion.component.html',
  styleUrls: ['./occasion.component.css']
})
export class OccasionComponent implements OnInit {
  occasions!: Occasion[];

  constructor(private occasionService: OccasionService) { }

  ngOnInit(): void {
    this.occasionService.getOccasions().subscribe((data: Occasion[]) => {
      this.occasions = data;
    });
  }

  public get occasionStatus(): typeof Status {
    return Status; 
  }

}
