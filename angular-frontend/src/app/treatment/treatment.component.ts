import { Component, OnInit } from '@angular/core';
import { Treatment } from './treatment';
import { TreatmentService } from './treatment.service';

@Component({
  selector: 'app-treatment',
  templateUrl: './treatment.component.html',
  styleUrls: ['./treatment.component.css']
})
export class TreatmentComponent implements OnInit {
  treatments!: Treatment[];

  constructor(private treatmentService: TreatmentService) { }

  ngOnInit(): void {
    this.treatmentService.getTreatments().subscribe((data: Treatment[])=>{
      this.treatments = data;
    });
  }

}
