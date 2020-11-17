export interface CommentModel {
  id: number;
  title: string;
  createdBy: string;
  createdAt: Date;
  rating: number;
  text: string;
  hotelId: number;
}
