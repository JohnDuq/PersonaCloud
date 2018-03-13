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

    mostrarDialogoPerson: boolean;

    persona: Persona = new Persona();

    personaSeleccionada: Persona;

    newPerson: boolean;

    personas: Person[];

    columnasPersonas: any[];
    
    msgs: Message[] = [];

    constructor(private personService: PersonService, private messageService: MessageService) { }

    ngOnInit() {
        
        this.personService.consultarPersonas().then(personas => this.personas = personas);

        this.columnasPersonas = [
            { field: 'id', header: 'ID' },
            { field: 'name', header: 'NAME' }
        ];
    }

    mostrarDialogoCrearPersona() {
        this.newPerson = true;
        this.persona = new Persona();
        this.mostrarDialogoPerson = true;
    }
        
    mostrarDialogoActualizarPersona(event) {
        this.newPerson = false;
        this.persona = this.clonarPersona(event.data);
        this.mostrarDialogoPerson = true;
    }

    guardarPersona() {

        const personas = [...this.personas];
        if (this.newPerson) {
            
            this.personService.guardarPersona(this.persona).then(res => {
                //Petición Sincrona esperando respuesta para agregar la persona a la lista
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
                //Petición Sincrona esperando respuesta para agregar la persona a la lista
                
                this.msgs = [];
                if(res.transaccionExitosa){
                    this.msgs.push({severity:'success', summary:'Respuesta', detail:res.message});
                    personas[this.encontrarPersonaSeleccionadaIndex()] = res.personaRequest;
                } else {
                    this.msgs.push({severity:'error', summary:'Respuesta', detail:res.message});    
                }
            });
        }
        
        //Código asincrono, no espera respuesta de la petición para ejecutarse
        this.personas = personas;
        this.persona = null;
        this.mostrarDialogoPerson = false;
        
    }

    eliminarPersona() {
        this.personService.eliminarPersona(this.persona).then(res => {
            //Petición Sincrona esperando respuesta para agregar la persona a la lista
            this.msgs = [];
            if(res.transaccionExitosa){
                const index = this.encontrarPersonaSeleccionadaIndex();
                this.personas = this.personas.filter((val, i) => i != index);
                this.persona = null;
                this.mostrarDialogoPerson = false;
                this.msgs.push({severity:'success', summary:'Respuesta', detail:res.message});
             } else {
                this.msgs.push({severity:'error', summary:'Respuesta', detail:res.message});    
             }
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
