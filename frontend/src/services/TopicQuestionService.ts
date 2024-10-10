import axiosInstance from "../api/axiosConfig";
import { Question } from "../model/Models";

// Service for interacting with the question API
const TopicQuestionService = {

    // Get all questions for a specific topic under a lesson and course
    getQuestionsByTopicId: async (courseId: number, lessonId: number, topicId: number): Promise<Question[]> => {
        const response = await axiosInstance.get(`/courses/${courseId}/lessons/${lessonId}/topics/${topicId}/questions`);
        return response.data;
    },

    // Get a question by question ID, topic ID, lesson ID, and course ID
    getQuestionById: async (courseId: number, lessonId: number, topicId: number, questionId: number): Promise<Question> => {
        const response = await axiosInstance.get(`/courses/${courseId}/lessons/${lessonId}/topics/${topicId}/questions/${questionId}`);
        return response.data;
    },

    // Create a new question under a specific topic, lesson, and course
    createQuestion: async (courseId: number, lessonId: number, topicId: number, question: Omit<Question, "id">): Promise<Question> => {
        const response = await axiosInstance.post(`/courses/${courseId}/lessons/${lessonId}/topics/${topicId}/questions`, question);
        return response.data;
    },

    // Update a question by ID under a specific topic, lesson, and course
    updateQuestion: async (courseId: number, lessonId: number, topicId: number, question: Question): Promise<Question> => {
        const response = await axiosInstance.put(`/courses/${courseId}/lessons/${lessonId}/topics/${topicId}/questions/${question.id}`, question);
        return response.data;
    },

    // Delete a question by ID under a specific topic, lesson, and course
    deleteQuestion: async (courseId: number, lessonId: number, topicId: number, question: Question): Promise<void> => {
        await axiosInstance.delete(`/courses/${courseId}/lessons/${lessonId}/topics/${topicId}/questions/${question.id}`);
    },
};

export default TopicQuestionService;
