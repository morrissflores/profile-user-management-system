import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Profile {
  id?: number;
  firstName: string;
  middleName?: string;
  lastName: string;
  birthDate: string;
  gender?: string;
}
@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private apiUrl = 'http://localhost:8080/api/profiles';
  constructor(private http: HttpClient) { }

  getProfiles(): Observable<Profile[]> {
    return this.http.get<Profile[]>(this.apiUrl);
  }
  
  getProfileById(id: number): Observable<Profile>{
    return this.http.get<Profile>('${this.apiUrl}/${id}');
  }

  createProfile(profile: Profile): Observable<Profile>{
    return this.http.post<Profile>(this.apiUrl, profile);
  }

  updateProfile(id: number, profile: Profile): Observable<Profile>{
    return this.http.put<Profile>('${this.apiUrl}/${id}', profile);
  }

  deleteProfile(id: number): Observable<any>{
    return this.http.delete('${this.apiUrl}/${id}');
  }
}
