import {inject, Injectable} from "@angular/core";
import {HttpClient, HttpParams, HttpResponse} from "@angular/common/http";
import {ApplicationConfigService} from "../core/config/application-config.service";
import {Observable} from "rxjs";
import {DocumentModel} from "../model/DocumentModel";

export type EntityResponseType = HttpResponse<DocumentModel>;


@Injectable({providedIn: 'root'})
export class HomeService {
  private readonly http = inject(HttpClient);
  private readonly applicationConfigService = inject(ApplicationConfigService);

  getAll(keyword: string): Observable<any> {
    return this.http.get(this.applicationConfigService.getEndpointFor('api/document/get-all'), {
      params: new HttpParams().set('keyword', keyword),
    });
  }

  save(request: DocumentModel): Observable<any> {
    return this.http.post<any>('api/document/save', request);
  }
}
