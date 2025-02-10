import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profile-list.component.html',
  styleUrl: './profile-list.component.css'
})
export class ProfileListComponent {
  profiles: any[] = [];
  
  constructor(private router: Router, private http: HttpClient) {}
  
  ngOnInit(){
    this.loadProfiles();
  }
loadProfiles(){
  this.http.get<any[]>('http://localhost:8080/api/profiles')
  .subscribe(data => {
    this.profiles = data;
  });
}

  goToAddProfile() {
    this.router.navigate(['/add-profile']);
  }

  deleteProfile(id: number) {
    this.http.delete('http://localhost:8080/api/profiles/${id}')
    .subscribe(() => {
      this.loadProfiles();
    });
  }
}
