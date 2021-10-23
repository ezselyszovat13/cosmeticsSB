import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OccasionTreatmentComponent } from './occasiontreatment.component';

describe('OccasiontreatmentComponent', () => {
  let component: OccasionTreatmentComponent;
  let fixture: ComponentFixture<OccasionTreatmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OccasionTreatmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OccasionTreatmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
