import { Component, OnInit } from '@angular/core';
import { OccasionTreatment } from './occasiontreatment';
import { OccasionTreatmentService } from './occasiontreatment.service';

@Component({
  selector: 'app-occasiontreatment',
  templateUrl: './occasiontreatment.component.html',
  styleUrls: ['./occasiontreatment.component.css']
})
export class OccasionTreatmentComponent implements OnInit {
  occasionTreatments!: OccasionTreatment[];

  constructor(private occasionTreatmentService: OccasionTreatmentService) { }

  ngOnInit(): void {
    this.occasionTreatmentService.getOccasionTreatments().subscribe((data: OccasionTreatment[]) => {
      this.occasionTreatments = data;
    });

  }

}
