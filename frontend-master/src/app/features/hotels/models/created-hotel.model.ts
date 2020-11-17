export interface CreatedHotelModel {
  name: string;
  description: string;
  location: {
    country: string;
    city: string;
    street: string;
    house: string;
  }
  amenities: string[];
}
