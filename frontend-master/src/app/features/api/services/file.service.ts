import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private readonly http: HttpClient) {
  }

  // tslint:disable-next-line:no-any
  addFiles(files: File[], hotelId: number): Observable<any> {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', undefined);
    const formData = new FormData();
    for (const file of files) {
      formData.append('files', file, file.name);
    }
    return this.http.post(`http://localhost:8080/image/${hotelId}`, formData);

  }
}
