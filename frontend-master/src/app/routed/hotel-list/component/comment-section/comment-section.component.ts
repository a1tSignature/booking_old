import {Component, Input, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {CommentModel} from '../../../../features/comments/models/comment.model';
import {CommentSectionCreateComponent} from '../comment-section-create/comment-section-create.component';
import {CommentSectionService} from '../../../../features/comments/services/comment-section.service';
import {Role} from '../../../../core/auth/role.model';

@Component({
  selector: 'app-comment-section',
  templateUrl: './comment-section.component.html',
  styleUrls: ['./comment-section.component.sass']
})
export class CommentSectionComponent implements OnInit {
  @Input()
  hotelId: number;
  comments: CommentModel[] = [];
  disabled: boolean;
  Role = Role;

  constructor(private readonly commentService: CommentSectionService, public dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.refresh();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(CommentSectionCreateComponent, {data: {hotelId: this.hotelId}});
    dialogRef.afterClosed().subscribe(
      result => {
        if (result != undefined){
      this.commentService.addItem(result as CommentModel).subscribe(
        (res) => {
          this.refresh();
        }
      ); }}
    );
  }

  refresh(): void {
    this.commentService.getItems(this.hotelId).subscribe(
      (res) => {
        this.comments = res.list;
      });
  }
}
