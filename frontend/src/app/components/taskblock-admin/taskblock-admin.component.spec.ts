import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskblockAdminComponent } from './taskblock-admin.component';

describe('TaskblockAdminComponent', () => {
  let component: TaskblockAdminComponent;
  let fixture: ComponentFixture<TaskblockAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TaskblockAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TaskblockAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
