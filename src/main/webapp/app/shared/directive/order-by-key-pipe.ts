import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  standalone: true,
  name: 'orderBySpecificKey'
})
export class OrderBySpecificKeyPipe implements PipeTransform {
  transform(value: any, order: string[]): any {
    const orderedKeys = order.filter(key => key in value); // Lọc các key có trong value
    return orderedKeys.map(key => ({key, value: value[key]}));
  }
}
