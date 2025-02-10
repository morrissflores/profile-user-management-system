import { CommonModule } from '@angular/common';
import { Component} from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router'

@Component({
  selector: 'app-profile-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './profile-form.component.html',
  styleUrl: './profile-form.component.css'
})
export class ProfileFormComponent {
    profileForm: FormGroup;

    constructor(private router: Router) {
      this.profileForm = new FormGroup({
        firstName: new FormControl('', Validators.required),
        lastName: new FormControl('', Validators.required),
        birthDate: new FormControl('',[ Validators.required, Validators.pattern(/⌃\d{4}-\d{2}-\d{2}$/)
      ]),
        gender: new FormControl('')
      });
    }
      
      saveProfile(){
        if(this.profileForm.valid) {
          console.log('Profile saved', this.profileForm.value);
          this.router.navigate(['/']);
        }
      }
      
}

