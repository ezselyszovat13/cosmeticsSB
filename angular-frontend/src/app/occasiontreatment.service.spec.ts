import { TestBed } from '@angular/core/testing';

import { OccasionTreatmentService } from './occasiontreatment.service';

describe('OccasiontreatmentService', () => {
  let service: OccasionTreatmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OccasionTreatmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
