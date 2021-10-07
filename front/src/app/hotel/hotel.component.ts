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


  constructor(private hotelService : HotelService) { }

  ngOnInit(): void {
    this.loadHotels();
  }

  loadHotels(): void {
    this.hotelService.loadHotels().subscribe(
      data => {
        this.hotels = data;
        console.log(data);
      }
    );
  }

  editHotel(id?: number): void {
    this.hotelService.getHotel(id).subscribe(data => {
      this.newHotel = data;
      this.loadHotels();
      // this.success = true;
    })
  }

  deleteHotel(id?: number): void {
    if (confirm("Voulez-vous vraiment supprimer ce hotel ?")) {
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
    if (this.newHotel.id == undefined) {
      this.hotelService.addHotel(this.newHotel).subscribe(data => {
        this.closebuttonelement.nativeElement.click();
        this.loadHotels();
        this.success = true;
      })
    } else {
      this.hotelService.editHotel(this.newHotel).subscribe(data => {
        this.closebuttonelement.nativeElement.click();
        this.loadHotels();
        this.success = true;
      })
    }

  }

}
