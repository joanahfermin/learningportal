import axiosInstance from "../api/AxiosConfig";
import { Course } from "../model/Course";

class CourseService {
  // Fetch all courses
  async getAllCourses(): Promise<Course[]> {
    const response = await axiosInstance.get<Course[]>("/courses");
    return response.data;
  }

  // Fetch a course by id
  async getCourseById(id: number): Promise<Course> {
    const response = await axiosInstance.get<Course>(`/courses/${id}`);
    return response.data;
  }

  // Create a new course
  async createCourse(course: Omit<Course, "id">): Promise<Course> {
    const response = await axiosInstance.post<Course>("/courses", course);
    return response.data;
  }

  // Update a course by id
  async updateCourse(id: number, course: Partial<Course>): Promise<Course> {
    const response = await axiosInstance.put<Course>(`/courses/${id}`, course);
    return response.data;
  }

  // Delete a course by id
  async deleteCourse(id: number): Promise<void> {
    await axiosInstance.delete(`/courses/${id}`);
  }
}

export default new CourseService();
