export interface Course {
    id: number;
    name: string;
    description: string;
}

export interface Lesson {
    id: number;
    name: string;
    description?: string; // Optional with default value as an empty string
    courseId: number;
}

export interface Topic {
    id: number;
    content: string;
    name: string;
    lessonId: number;
}
