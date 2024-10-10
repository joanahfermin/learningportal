import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import TopicService from '../../services/TopicService';
import QuestionService from '../../services/TopicQuestionService';
import { Topic, Question, AnswerChoice } from '../../model/Models';
import ConfirmDialog from '../../components/ConfirmDialog';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';

const TopicQuestionPage: React.FC = () => {
    const navigate = useNavigate();
    const { courseId, lessonId, topicId } = useParams<{ courseId: string; lessonId: string; topicId: string }>();

    const [questions, setQuestions] = useState<Question[]>([]);
    const [topic, setTopic] = useState<Topic>();
    const [selectedQuestion, setSelectedQuestion] = useState<Partial<Question> | null>(null);
    const [isModalActive, setModalActive] = useState(false);
    const [isConfirmDelete, setIsConfirmDelete] = useState(false);
    const [questionToDelete, setQuestionToDelete] = useState<Question | null>(null);

    useEffect(() => {
        fetchTopic();
        fetchQuestions();
    }, [courseId, lessonId, topicId]);

    const fetchQuestions = async () => {
        const numericCourseId = Number(courseId);
        const numericLessonId = Number(lessonId);
        const numericTopicId = Number(topicId);
        const data = await QuestionService.getQuestionsByTopicId(numericCourseId, numericLessonId, numericTopicId);
        setQuestions(data);
    };

    const fetchTopic = async () => {
        const numericCourseId = Number(courseId);
        const numericLessonId = Number(lessonId);
        const numericTopicId = Number(topicId);
        const data = await TopicService.getTopicById(numericCourseId, numericLessonId, numericTopicId);
        setTopic(data);
    };

    const openModal = (question?: Question) => {
        setSelectedQuestion(question || { questionText: '', solution: '' });
        setModalActive(true);
    };

    const closeModal = () => {
        setModalActive(false);
        setSelectedQuestion(null);
    };

    const handleInputChange = (name: string, value: string) => {
        setSelectedQuestion((prev) => ({ ...prev, [name]: value }));
    };

    const saveQuestion = async () => {
        if (selectedQuestion) {
            if (selectedQuestion.id) {
                await QuestionService.updateQuestion(Number(courseId), Number(lessonId), Number(topicId), selectedQuestion as Question);
            } else {
                await QuestionService.createQuestion(Number(courseId), Number(lessonId), Number(topicId), selectedQuestion as Omit<Question, 'id'>);
            }
            closeModal();
            fetchQuestions();
        }
    };

    const requestDeleteQuestion = (question: Question) => {
        setIsConfirmDelete(true);
        setQuestionToDelete(question);
    };

    const confirmDeleteQuestion = async () => {
        if (questionToDelete) {
            await QuestionService.deleteQuestion(Number(courseId), Number(lessonId), Number(topicId), questionToDelete);
            setIsConfirmDelete(false);
            setQuestionToDelete(null);
            fetchQuestions();
        }
    };

    const cancelDeleteQuestion = () => {
        setIsConfirmDelete(false);
        setQuestionToDelete(null);
    };

    return (
        <div className="container">
            <h1 className="title">{topic?.name} Questions</h1>
            <div className="buttons">
                <button className="button is-primary" onClick={() => openModal()}>Add Question</button>
                <div className="has-text-right" style={{ marginLeft: 'auto' }}>
                    <button className="button is-light" onClick={() => navigate(`/admin-course/${courseId}/lessons`)}>Back</button>
                </div>
            </div>

            <table className="table is-fullwidth mt-4">
                <thead>
                    <tr>
                        <th>Question</th>
                        <th>Solution</th>
                        <th style={{ width: 150 }}>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {questions.map(question => (
                        <tr key={question.id}>
                            <td>{question.questionText}</td>
                            <td>{question.solution}</td>
                            <td>
                                <button className="button is-small is-info mr-2" onClick={() => openModal(question)} title="Edit">
                                    <FontAwesomeIcon icon={faEdit} />
                                </button>
                                <button className="button is-small is-danger" onClick={() => requestDeleteQuestion(question)} title="Delete">
                                    <FontAwesomeIcon icon={faTrash} />
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {isModalActive && selectedQuestion && (
                <div className={`modal ${isModalActive ? 'is-active' : ''}`}>
                    <div className="modal-background" onClick={closeModal}></div>
                    <div className="modal-card is-topic-modal">
                        <header className="modal-card-head">
                            <p className="modal-card-title">{selectedQuestion.id ? 'Edit Question' : 'Add Question'}</p>
                            <button className="delete" aria-label="close" onClick={closeModal}></button>
                        </header>
                        <section className="modal-card-body">
                            <div className="field">
                                <label className="label">Question</label>
                                <div className="control">
                                    <input
                                        className="input"
                                        type="text"
                                        name="questionText"
                                        value={selectedQuestion.questionText || ''}
                                        onChange={(e) => handleInputChange('questionText', e.target.value)}
                                    />
                                </div>
                            </div>
                            <div className="field">
                                <label className="label">Solution</label>
                                <div className="control">
                                    <input
                                        className="input"
                                        type="text"
                                        name="solution"
                                        value={selectedQuestion.solution || ''}
                                        onChange={(e) => handleInputChange('solution', e.target.value)}
                                    />
                                </div>
                            </div>
                            <div className="field">
                                <label className="label">Choice A</label>
                                <div className="control">
                                    <input
                                        className="input"
                                        type="text"
                                        name="choiceA"
                                        value={selectedQuestion.choiceA || ''}
                                        onChange={(e) => handleInputChange('choiceA', e.target.value)}
                                    />
                                </div>
                            </div>
                            <div className="field">
                                <label className="label">Choice B</label>
                                <div className="control">
                                    <input
                                        className="input"
                                        type="text"
                                        name="choiceB"
                                        value={selectedQuestion.choiceB || ''}
                                        onChange={(e) => handleInputChange('choiceB', e.target.value)}
                                    />
                                </div>
                            </div>
                            <div className="field">
                                <label className="label">Choice C</label>
                                <div className="control">
                                    <input
                                        className="input"
                                        type="text"
                                        name="choiceC"
                                        value={selectedQuestion.choiceC || ''}
                                        onChange={(e) => handleInputChange('choiceC', e.target.value)}
                                    />
                                </div>
                            </div>
                            <div className="field">
                                <label className="label">Choice D</label>
                                <div className="control">
                                    <input
                                        className="input"
                                        type="text"
                                        name="choiceD"
                                        value={selectedQuestion.choiceD || ''}
                                        onChange={(e) => handleInputChange('choiceD', e.target.value)}
                                    />
                                </div>
                            </div>
                            <div className="field">
                                <label className="label">Answer</label>
                                <div className="control">
                                    <select
                                        className="select"
                                        name="answer"
                                        value={selectedQuestion.answer || ''}
                                        onChange={(e) => handleInputChange('answer', e.target.value as AnswerChoice)}
                                    >
                                        <option value={AnswerChoice.A}>A</option>
                                        <option value={AnswerChoice.B}>B</option>
                                        <option value={AnswerChoice.C}>C</option>
                                        <option value={AnswerChoice.D}>D</option>
                                    </select>
                                </div>
                            </div>
                        </section>
                        <footer className="modal-card-foot">
                            <button className="button is-success" onClick={saveQuestion}>Save changes</button>
                            <button className="button" onClick={closeModal}>Cancel</button>
                        </footer>
                    </div>
                </div>
            )}

            <ConfirmDialog
                message="Are you sure you want to delete this question?"
                isActive={isConfirmDelete}
                onConfirm={confirmDeleteQuestion}
                onCancel={cancelDeleteQuestion}
            />
        </div>
    );
};

export default TopicQuestionPage;
