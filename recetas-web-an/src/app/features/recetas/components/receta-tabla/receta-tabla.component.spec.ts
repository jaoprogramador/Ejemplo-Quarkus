import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecetaTablaComponent } from './receta-tabla.component';

describe('RecetaTablaComponent', () => {
  let component: RecetaTablaComponent;
  let fixture: ComponentFixture<RecetaTablaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecetaTablaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecetaTablaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
