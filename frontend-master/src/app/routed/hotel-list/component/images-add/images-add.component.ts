import {Component, Inject, OnInit} from '@angular/core';
import {CreatedHotelModel} from '../../../../features/hotels/models/created-hotel.model';
import {FileService} from '../../../../features/api/services/file.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-images-add',
  templateUrl: './images-add.component.html',
  styleUrls: ['./images-add.component.sass']
})
export class ImagesAddComponent implements OnInit {
  files: File[] = [];
  hotelId: number;

  constructor(private readonly fileService: FileService, private readonly  dialogRef: MatDialogRef<ImagesAddComponent>,
              @Inject(MAT_DIALOG_DATA) public data) {
    this.hotelId = data.hotelId;
    console.info(this.hotelId);
  }

  ngOnInit(): void {
  }

  send(): void {
    this.fileService.addFiles(this.files, this.hotelId).subscribe((res) => {
      this.dialogRef.close(true);
    },
     error =>{
       this.dialogRef.close(false);
     }
      );
  }

  close(): void {
    this.dialogRef.close(false);
  }

  onSelect(event) {
    this.files.push(...event.addedFiles);
  }

  onRemove(event) {
    this.files.splice(this.files.indexOf(event), 1);

  }

}
