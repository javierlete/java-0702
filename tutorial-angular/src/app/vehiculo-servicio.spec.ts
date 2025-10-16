import { TestBed } from '@angular/core/testing';

import { VehiculoServicio } from './vehiculo-servicio';

describe('VehiculoServicio', () => {
  let service: VehiculoServicio;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VehiculoServicio);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
