import axiosInstance from "../api/axiosConfig";
import { Lesson } from "../model/Models";

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
  updateLesson: async (lesson: Lesson): Promise<Lesson> => {
    const response = await axiosInstance.put(`/courses/${lesson.courseId}/lessons/${lesson.id}`, lesson);
    return response.data;
  },

  // Delete a lesson by ID under a specific course
  deleteLesson: async (lesson: Lesson): Promise<void> => {
    await axiosInstance.delete(`/courses/${lesson.courseId}/lessons/${lesson.id}`);
  },
};

export default LessonService;