import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Response } from '@angular/http';
import { Person } from '../domain/person';
import { OnInit} from '@angular/core';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class PersonService implements OnInit {

    httpOptions;
    headers: HttpHeaders;

    constructor(private http: HttpClient) { }

    ngOnInit() {
      this.httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Accept':  'application/json'
          ,'Authorization': 'my-auth-token'
        })
      };
    }

    consultarPersonas() {
        return this.http.get<any>('http://localhost:8080/proxy/CONSULTAR_PERSONAS')
            .toPromise()
            .then(res => <Person[]> res.lPersons)
            .then(data => data);
    }

    guardarPersona(persona : Persona) {
        return this.http.post<any>('http://localhost:8080/proxy/GUARDAR_PERSONA',persona ,this.httpOptions)
            .toPromise()
            .then(res => <any> res)
            .then(data => data);
    }
    
    actualizarPersona(persona : Persona) {
        return this.http.post<any>('http://localhost:8080/proxy/ACTUALIZAR_PERSONA',persona ,this.httpOptions)
            .toPromise()
            .then(res => <any> res)
            .then(data => data);
    }
    
    eliminarPersona(persona : Persona) {
        return this.http.post<any>('http://localhost:8080/proxy/ELIMINAR_PERSONA',persona ,this.httpOptions)
            .toPromise()
            .then(res => <any> res)
            .then(data => data);
    }

}

export class Persona implements Person {

    constructor(public id?, public name?) { }
}
