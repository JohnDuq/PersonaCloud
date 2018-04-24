import {Component, OnInit} from '@angular/core';
import { Person } from './domain/person';
import { PersonService} from './services/personservice';
import { Message } from 'primeng/components/common/api';
import { MessageService } from 'primeng/components/common/messageservice';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    providers: [PersonService, MessageService]
})
export class AppComponent implements OnInit {

    pantallaBloqueada: boolean;
    barraProgreso: boolean;

    mostrarDialogoPerson: boolean;

    persona: Persona = new Persona();

    personaSeleccionada: Persona;

    newPerson: boolean;

    personas: Person[];

    columnasPersonas: any[];
    
    msgs: Message[] = [];
    msgsErrorCon: Message[] = [];
    msgsErrorConB: boolean;

    constructor(private personService: PersonService, private messageService: MessageService) { }

    ngOnInit() {
        this.msgsErrorConB = false;
        this.pantallaBloqueada = true;
        this.barraProgreso = true;
        this.personService
            .consultarPersonas()
                .then(personas => {
                    this.personas = personas;
                    this.barraProgreso = false;
                }, error => {
                    this.msgsErrorConB = true;
                    console.log(error);
                    this.msgs.push({
                        severity:'warn',
                        summary:'Respuesta', 
                        detail:'Tenemos dificultades en la conexión'});
                });

        this.columnasPersonas = [
            { field: 'id', header: 'ID' },
            { field: 'name', header: 'NAME' }
        ];
        this.pantallaBloqueada = false;
    }

    mostrarDialogoCrearPersona() {
        this.msgs = [];
        this.barraProgreso = true;
        this.newPerson = true;
        this.persona = new Persona();
        this.mostrarDialogoPerson = true;
        this.barraProgreso = false;
    }
        
    mostrarDialogoActualizarPersona(event) {
        this.msgs = [];
        this.barraProgreso = true;
        this.newPerson = false;
        this.persona = this.clonarPersona(event.data);
        this.mostrarDialogoPerson = true;
        this.barraProgreso = false;
    }

    guardarPersona() {
        this.mostrarDialogoPerson = false;
        this.barraProgreso = true;
        this.pantallaBloqueada = true;
        const personas = [...this.personas];
        if (this.newPerson) {
            
            this.personService.guardarPersona(this.persona).then(res => {
                //Petici�n Sincrona esperando respuesta para agregar la persona a la lista
                this.msgs = [];
                
                if(res.transaccionExitosa){
                    this.msgs.push({severity:'success', summary:'Respuesta', detail:res.message});
                    personas.push(res.personaRequest);
                } else {
                    this.msgs.push({severity:'success', summary:'Respuesta', detail:res.message});    
                }
            });
            
        } else {
                
            this.personService.actualizarPersona(this.persona).then(res => {
                //Petici�n Sincrona esperando respuesta para agregar la persona a la lista
                
                this.msgs = [];
                if(res.transaccionExitosa){
                    this.msgs.push({severity:'success', summary:'Respuesta', detail:res.message});
                    personas[this.encontrarPersonaSeleccionadaIndex()] = res.personaRequest;
                } else {
                    this.msgs.push({severity:'error', summary:'Respuesta', detail:res.message});    
                }
            });
        }
        
        //C�digo asincrono, no espera respuesta de la petici�n para ejecutarse
        this.personas = personas;
        this.persona = null;
        this.mostrarDialogoPerson = false;
        this.pantallaBloqueada = false;
        this.barraProgreso = false;
    }

    eliminarPersona() {
        this.mostrarDialogoPerson = false;
        this.pantallaBloqueada = true;
        this.barraProgreso = true;
        this.personService.eliminarPersona(this.persona).then(res => {
            //Petici�n Sincrona esperando respuesta para agregar la persona a la lista
            this.mostrarDialogoPerson = false;
            this.msgs = [];
            if(res.transaccionExitosa){
                const index = this.encontrarPersonaSeleccionadaIndex();
                this.personas = this.personas.filter((val, i) => i != index);
                this.persona = null;
                this.msgs.push({severity:'success', summary:'Respuesta', detail:res.message});
            } else {
            this.msgs.push({severity:'error', summary:'Respuesta', detail:res.message});    
            }
            this.pantallaBloqueada = false;
            this.barraProgreso = false;
        });
        
    }
        
    clonarPersona(p: Person): Person {
        const person = new Persona();
        for (const propiedad in p) {
            person[propiedad] = p[propiedad];
        }
        return person;
    }
        
    encontrarPersonaSeleccionadaIndex(): number {
        return this.personas.indexOf(this.personaSeleccionada);
    }
}

export class Persona implements Person {

    constructor(public id?, public name?) { }
}
