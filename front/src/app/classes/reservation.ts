import { Client } from "./client";
import { Hotel } from "./hotel";

export class Reservation {

    id: number | undefined;
    client: Client | undefined;
    hotel: Hotel | undefined;
    dateDebut: Date | undefined;
    dateFin: Date | undefined;
    numChambre: number | undefined;

    constructor(_id?: number | undefined, client?: Client | undefined, hotel?: Hotel | undefined, dateDebut?: Date | undefined,
        dateFin?: Date | undefined, numChambre?: number | undefined) {
        this.id = _id;
        this.client = client;
        this.hotel = hotel;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.numChambre = numChambre;

    }
}
