export class Hotel {

    id: number | undefined;
    nom: string | undefined;
    etoiles: number | undefined;
    adresse: string | undefined;
    telephone: string | undefined;
    email: string | undefined;
    ville: string | undefined;

    constructor(_id?: number | undefined, nom?: string | undefined, etoiles?: number | undefined, adresse?: string | undefined, telephone?: string | undefined,
         email?: string | undefined, ville?: string | undefined) {
        this.id = _id;
        this.nom = nom;
        this.etoiles = etoiles;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
        this.ville = ville;

    }
}
