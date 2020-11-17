import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-comment-section-create',
  templateUrl: './comment-section-create.component.html',
  styleUrls: ['./comment-section-create.component.sass']
})
export class CommentSectionCreateComponent implements OnInit {
  commentForm: FormGroup;


  constructor(public dialogRef: MatDialogRef<CommentSectionCreateComponent>, @Inject(MAT_DIALOG_DATA) public data, fb: FormBuilder) {
    this.commentForm = fb.group({
      title: ['', [Validators.required, Validators.maxLength(30), Validators.minLength(5)]],
      text: ['', [Validators.required, Validators.maxLength(200), Validators.minLength(30)]],
      rating: ['', Validators.required],
      hotelId: [data.hotelId]
    });
  }


  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
