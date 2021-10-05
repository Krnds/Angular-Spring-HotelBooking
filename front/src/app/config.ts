import { HttpHeaders } from "@angular/common/http";
import { environment } from "src/environments/environment";

export const HttpOptions = {
     headers : new HttpHeaders({
      'Access-Control-Allow-Origin':'*',
      'Authorization': 'Basic ' + environment.basicAuth
     })
}