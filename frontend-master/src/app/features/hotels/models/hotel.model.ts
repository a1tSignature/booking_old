import { LocationModel } from './location.model';

export interface HotelModel {
  id: number;
  name: string;
  createdAt: string;
  location: LocationModel;
  // TODO ???
  amenities: string[];
  description: string;
}
export interface HotelDetailModel extends HotelModel{
  amenities: string[];
  description: string;
  creatorName: string;
  creatorId:number;
}
