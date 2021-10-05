export class Client {

    id: number | undefined;
    nomComplet: string | undefined;
    telephone: string | undefined;
    email: string | undefined;
    adresse: string | undefined;

    constructor(_id?: number | undefined, nomComplet?: string | undefined, telephone?: string | undefined, email?: string | undefined, adresse?: string | undefined) {
        this.id = _id;
        this.nomComplet = nomComplet;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;

    }
}
