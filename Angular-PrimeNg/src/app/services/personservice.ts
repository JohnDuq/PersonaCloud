import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Response } from '@angular/http';
import { Person } from '../domain/person';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class PersonService {

    constructor(private http: HttpClient) { }

    consultarPersonas() {
        return this.http.get<any>('http://localhost:8085/CONSULTAR_PERSONAS')
            .toPromise()
            .then(res => <Person[]> res.lPersons)
            .then(data => data);
    }

    guardarPersona(persona : Persona) {
        
        const httpOptions = {
          headers: new HttpHeaders({
            'Content-Type':  'application/json',
            'Accept':  'application/json'
            //,'Authorization': 'my-auth-token'
          })
        };
    
        return this.http.post<any>('http://localhost:8085/GUARDAR_PERSONA',persona , httpOptions)
            .toPromise()
            .then(res => <any> res)
            .then(data => data);
    }
    
    actualizarPersona(persona : Persona) {
        
        const httpOptions = {
          headers: new HttpHeaders({
            'Content-Type':  'application/json',
            'Accept':  'application/json'
            //,'Authorization': 'my-auth-token'
          })
        };
    
        return this.http.post<any>('http://localhost:8085/ACTUALIZAR_PERSONA',persona , httpOptions)
            .toPromise()
            .then(res => <any> res)
            .then(data => data);
    }
    
    eliminarPersona(persona : Persona) {
        
        const httpOptions = {
          headers: new HttpHeaders({
            'Content-Type':  'application/json',
            'Accept':  'application/json'
            //,'Authorization': 'my-auth-token'
          })
        };
    
        return this.http.post<any>('http://localhost:8085/ELIMINAR_PERSONA',persona , httpOptions)
            .toPromise()
            .then(res => <any> res)
            .then(data => data);
    }

}

export class Persona implements Person {

    constructor(public id?, public name?) { }
}
