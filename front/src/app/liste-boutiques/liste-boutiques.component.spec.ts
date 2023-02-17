import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeBoutiquesComponent } from './liste-boutiques.component';

describe('ListeBoutiquesComponent', () => {
  let component: ListeBoutiquesComponent;
  let fixture: ComponentFixture<ListeBoutiquesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListeBoutiquesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeBoutiquesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
