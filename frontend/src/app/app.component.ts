import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div style="padding: 20px; font-family: Arial, sans-serif;">
      <h1>🚀 Release Radar Dashboard</h1>
      <button (click)="triggerDeployment()" style="padding: 10px; background: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer;">
        Simulate New Deployment
      </button>
      
      <h2 style="margin-top: 30px;">Deployment History</h2>
      <table style="width: 100%; border-collapse: collapse; text-align: left;">
        <tr style="background-color: #f2f2f2;">
          <th style="padding: 10px; border: 1px solid #ddd;">ID</th>
          <th style="padding: 10px; border: 1px solid #ddd;">App Name</th>
          <th style="padding: 10px; border: 1px solid #ddd;">Environment</th>
          <th style="padding: 10px; border: 1px solid #ddd;">Status</th>
          <th style="padding: 10px; border: 1px solid #ddd;">Time</th>
        </tr>
        <tr *ngFor="let dep of deployments">
          <td style="padding: 10px; border: 1px solid #ddd;">{{ dep.id }}</td>
          <td style="padding: 10px; border: 1px solid #ddd;">{{ dep.applicationName }}</td>
          <td style="padding: 10px; border: 1px solid #ddd;">{{ dep.environment }}</td>
          <td style="padding: 10px; border: 1px solid #ddd; font-weight: bold;" 
              [ngStyle]="{'color': dep.status === 'SUCCESS' ? 'green' : 'red'}">
            {{ dep.status }}
          </td>
          <td style="padding: 10px; border: 1px solid #ddd;">{{ dep.deploymentTime | date:'short' }}</td>
        </tr>
      </table>
    </div>
  `
})
export class AppComponent implements OnInit {
  deployments: any[] = [];
  private apiUrl = '/api/v1/deployments'; 

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchDeployments();
  }

  fetchDeployments() {
    this.http.get<any[]>(this.apiUrl).subscribe({
      next: (data) => this.deployments = data,
      error: (err) => console.error('Error fetching deployments', err)
    });
  }

  triggerDeployment() {
    const newDep = {
      applicationName: 'payment-service',
      environment: 'production',
      version: 'v1.4.' + Math.floor(Math.random() * 100),
      status: Math.random() > 0.2 ? 'SUCCESS' : 'FAILED' 
    };

    this.http.post(this.apiUrl, newDep).subscribe(() => {
      this.fetchDeployments(); 
    });
  }
}