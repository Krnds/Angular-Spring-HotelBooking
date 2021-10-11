import { Component, OnInit, ViewChild } from '@angular/core';
import { Hotel } from '../classes/hotel';
import { HotelService } from '../service/hotel.service';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {

  newHotel: Hotel = new Hotel();

  hotels: Array<Hotel> = [];
  @ViewChild('closebutton') closebuttonelement: any;
  success: boolean = false;
  error: boolean = false;
  errMessage: string = "";

  constructor(private hotelService: HotelService) { }

  ngOnInit(): void {
    this.loadHotels();
  }

  loadHotels(): void {
    this.hotelService.loadHotels().subscribe(
      data => {
        this.hotels = data;
      }
    );
  }

  editHotel(id?: number): void {
    this.hotelService.getHotel(id).subscribe(data => {
      this.newHotel = data;
      this.loadHotels();
    })
  }

  deleteHotel(id?: number): void {
    if (confirm("Voulez-vous vraiment supprimer ce hotel ? Cela va supprimer aussi les réservations associées.")) {
      this.hotelService.deleteHotel(id).subscribe(data => {
        this.loadHotels();
        this.success = true;
      },
        error => {
          this.success = false;
          this.error = true;
        });
    }
  }

  submitForm(): void {
    this.errMessage = "";
    if (this.newHotel.id == undefined) {
      console.log("in add new hotel");
      this.hotelService.addHotel(this.newHotel).subscribe(data => {
        this.closebuttonelement.nativeElement.click();
        this.loadHotels();
        this.success = true;
        this.resetForm();
        console.log(this.newHotel);
      },

        error => {
          this.error = true;
          this.errMessage = error.error.message;
        })
    } else {
      console.log("in edithotel");
      this.hotelService.editHotel(this.newHotel).subscribe(data => {
        this.closebuttonelement.nativeElement.click();
        this.loadHotels();
        this.success = true;
        this.resetForm();
        console.log(this.newHotel);
      },
        error => {
          this.error = true;
          this.errMessage = error.error.message;
        })
    }

  }

  resetForm(): void {
    this.success = false;
    this.error = false;
    this.errMessage = "";
    this.newHotel = new Hotel();

  }

}
