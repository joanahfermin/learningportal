export interface Lesson {
  id: number;
  name: string;
  description?: string; // Optional with default value as an empty string
  courseId: number;
}
