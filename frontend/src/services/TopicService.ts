import axiosInstance from "../api/axiosConfig";
import { Topic } from "../model/Topic";

// Service for interacting with the topic API
const TopicService = {

    // Get all topics for a specific lesson under a course
    getTopicsByLessonId: async (courseId: number, lessonId: number): Promise<Topic[]> => {
        const response = await axiosInstance.get(`/courses/${courseId}/lessons/${lessonId}/topics`);
        return response.data;
    },

    // Get a topic by topic ID, lesson ID, and course ID
    getTopicById: async (courseId: number, lessonId: number, topicId: number): Promise<Topic> => {
        const response = await axiosInstance.get(`/courses/${courseId}/lessons/${lessonId}/topics/${topicId}`);
        return response.data;
    },

    // Create a new topic under a specific lesson and course
    createTopic: async (courseId: number, lessonId: number, topic: Omit<Topic, "id">): Promise<Topic> => {
        const response = await axiosInstance.post(`/courses/${courseId}/lessons/${lessonId}/topics`, topic);
        return response.data;
    },

    // Update a topic by ID under a specific lesson and course
    updateTopic: async (courseId: number, lessonId: number, topic: Topic): Promise<Topic> => {
        const response = await axiosInstance.put(`/courses/${courseId}/lessons/${lessonId}/topics/${topic.id}`, topic);
        return response.data;
    },

    // Delete a topic by ID under a specific lesson and course
    deleteTopic: async (courseId: number, lessonId: number, topic: Topic): Promise<void> => {
        await axiosInstance.delete(`/courses/${courseId}/lessons/${lessonId}/topics/${topic.id}`);
    },
};

export default TopicService;
