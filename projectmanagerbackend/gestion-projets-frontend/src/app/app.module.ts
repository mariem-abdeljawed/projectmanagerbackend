import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { EmployeListComponent } from './modules/employes/employe-list/employe-list.component';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    EmployeListComponent // Import the standalone component here
  ],
  exports: [
    EmployeListComponent // Export it if other modules need to use it
  ]
})
export class EmployeModule { }