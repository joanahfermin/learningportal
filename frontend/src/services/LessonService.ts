import axiosInstance from "../api/AxiosConfig";
import { Lesson } from "../model/Lesson";

// Service for interacting with the lesson API
const LessonService = {
  
  // Get all lessons for a specific course
  getLessonsByCourseId: async (courseId: number): Promise<Lesson[]> => {
    const response = await axiosInstance.get(`/courses/${courseId}/lessons`);
    return response.data;
  },

  // Get a lesson by lesson ID and course ID
  getLessonById: async (courseId: number, lessonId: number): Promise<Lesson> => {
    const response = await axiosInstance.get(`/courses/${courseId}/lessons/${lessonId}`);
    return response.data;
  },

  // Create a new lesson under a specific course
  createLesson: async (courseId: number, lesson: Omit<Lesson, "id">): Promise<Lesson> => {
    const response = await axiosInstance.post(`/courses/${courseId}/lessons`, lesson);
    return response.data;
  },

  // Update a lesson by ID under a specific course
  updateLesson: async (courseId: number, lessonId: number, lesson: Lesson): Promise<Lesson> => {
    const response = await axiosInstance.put(`/courses/${courseId}/lessons/${lessonId}`, lesson);
    return response.data;
  },

  // Delete a lesson by ID under a specific course
  deleteLesson: async (courseId: number, lessonId: number): Promise<void> => {
    await axiosInstance.delete(`/courses/${courseId}/lessons/${lessonId}`);
  },
};

export default LessonService;