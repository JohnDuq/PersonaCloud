import {Component, OnInit} from '@angular/core';
import { Car } from './domain/car';
import { Person } from './domain/person';
import { CarService} from './services/carservice';
import { PersonService} from './services/personservice';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    providers: [CarService, PersonService]
})
export class AppComponent implements OnInit {

    displayDialog: boolean;
    mostrarDialogoPerson: boolean;
    disableId: boolean;

    car: Car = new PrimeCar();
    persona: Persona = new Persona();

    selectedCar: Car;

    newCar: boolean;
    newPerson: boolean;

    cars: Car[];
    personas: Person[];

    cols: any[];
    columnasPersonas: any[];

    constructor(private carService: CarService, private personService: PersonService) { }

    ngOnInit() {
        this.carService.getCarsSmall().then(cars => this.cars = cars);
        this.personService.getPersons().then(personas => this.personas = personas);

        this.cols = [
            { field: 'vin', header: 'Vin' },
            { field: 'year', header: 'Year' },
            { field: 'brand', header: 'Brand' },
            { field: 'color', header: 'Color' }
        ];

        this.columnasPersonas = [
            { field: 'id', header: 'ID' },
            { field: 'name', header: 'NAME' }
        ];
    }

    showDialogToAdd() {
        this.newCar = true;
        this.car = new PrimeCar();
        this.displayDialog = true;
    }

    mostrarDialogoCrearPersona() {
        this.newPerson = true;
        this.disableId = true;
        this.persona = new Persona();
        this.mostrarDialogoPerson = true;
    }

    guardarPersona() {

        const personas = [...this.personas];
        if (this.newPerson) {
            
            this.personService.postPersona(this.persona).then(res => this.persona.id = res.id);
            
            personas.push(this.persona);
        }
        
        this.personas = personas;
        this.persona = null;
        this.mostrarDialogoPerson = false;
        
    }

    save() {
        const cars = [...this.cars];
        if (this.newCar) {
            cars.push(this.car);
        } else {
            cars[this.findSelectedCarIndex()] = this.car;
        }
        this.cars = cars;
        this.car = null;
        this.displayDialog = false;
    }

    delete() {
        const index = this.findSelectedCarIndex();
        this.cars = this.cars.filter((val, i) => i != index);
        this.car = null;
        this.displayDialog = false;
    }

    onRowSelect(event) {
        this.newCar = false;
        this.car = this.cloneCar(event.data);
        this.displayDialog = true;
    }

    cloneCar(c: Car): Car {
        const car = new PrimeCar();
        for (const prop in c) {
            car[prop] = c[prop];
        }
        return car;
    }

    findSelectedCarIndex(): number {
        return this.cars.indexOf(this.selectedCar);
    }
}

export class Persona implements Person {

    constructor(public id?, public name?) { }
}

export class PrimeCar implements Car {

    constructor(public vin?, public year?, public brand?, public color?) { }
}
