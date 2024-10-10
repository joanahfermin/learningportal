export interface Course {
    id: number;
    name: string;
    description: string;
}

export interface Lesson {
    id: number;
    name: string;
    description?: string;
    courseId: number;
}

export interface Topic {
    id: number;
    content: string;
    name: string;
    lessonId: number;
}

export interface Client {
    id: number;
    firstName: string;
    lastName: string;
    username: string;
}

export interface ClientGroup {
    id: number;
    name: string;
    description?: string;
}

export interface Question {
    id: number;
    questionText: string;
    choiceA: string;
    choiceB: string;
    choiceC: string;
    choiceD: string;
    answer: AnswerChoice;
    solution: string;
    topicId: number;
    lessonId: number;
}

export enum AnswerChoice {
    A = 'A',
    B = 'B',
    C = 'C',
    D = 'D'
}
