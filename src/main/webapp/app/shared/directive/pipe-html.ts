import {DomSanitizer, SafeHtml} from '@angular/platform-browser';
import {Pipe, PipeTransform} from '@angular/core';

@Pipe({standalone: true, name: 'safeHtml'})
export class SafeHtmlPipe implements PipeTransform {
  constructor(private sanitized: DomSanitizer) {
  }

  transform(value: any): SafeHtml {
    return this.sanitized.bypassSecurityTrustHtml(value);
  }
}
