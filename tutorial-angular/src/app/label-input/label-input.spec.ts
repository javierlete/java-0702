import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LabelInput } from './label-input';

describe('LabelInput', () => {
  let component: LabelInput;
  let fixture: ComponentFixture<LabelInput>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LabelInput]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LabelInput);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
