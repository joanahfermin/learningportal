import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import LessonService from '../../services/LessonService';
import TopicService from '../../services/TopicService';
import { Lesson } from '../../model/Lesson';
import { Topic } from '../../model/Topic';
import ConfirmDialog from '../../components/ConfirmDialog'; // Adjust the path accordingly
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';

const TopicPage: React.FC = () => {
    const navigate = useNavigate();
    const { courseId, lessonId } = useParams<{ courseId: string; lessonId: string }>();

    const [topics, setTopics] = useState<Topic[]>([]);
    const [lesson, setLesson] = useState<Lesson>();
    const [selectedTopic, setSelectedTopic] = useState<Partial<Topic> | null>(null);
    const [isModalActive, setModalActive] = useState(false);
    const [isConfirmDelete, setIsConfirmDelete] = useState(false);
    const [topicToDelete, setTopicToDelete] = useState<Topic | null>(null);

    useEffect(() => {
        fetchLesson();
        fetchTopics();
    }, [courseId, lessonId]);

    const fetchTopics = async () => {
        const numericCourseId = Number(courseId);
        const numericLessonId = Number(lessonId);
        const data = await TopicService.getTopicsByLessonId(numericCourseId, numericLessonId);
        setTopics(data);
    };

    const fetchLesson = async () => {
        const numericCourseId = Number(courseId);
        const numericLessonId = Number(lessonId);
        const data = await LessonService.getLessonById(numericCourseId, numericLessonId);
        setLesson(data);
    };

    const openModal = (topic?: Topic) => {
        setSelectedTopic(topic || { name: '', content: '' });
        setModalActive(true);
    };

    const closeModal = () => {
        setModalActive(false);
        setSelectedTopic(null);
    };

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setSelectedTopic((prev) => ({ ...prev, [name]: value }));
    };

    const saveTopic = async () => {
        if (selectedTopic) {
            if (selectedTopic.id) {
                await TopicService.updateTopic(Number(courseId), Number(lessonId), selectedTopic as Topic); // Update existing topic
            } else {
                await TopicService.createTopic(Number(courseId), Number(lessonId), selectedTopic as Omit<Topic, 'id'>); // Create new topic
            }
            closeModal();
            fetchTopics();
        }
    };

    const requestDeleteTopic = (topic: Topic) => {
        setIsConfirmDelete(true);
        setTopicToDelete(topic);
    };

    const confirmDeleteTopic = async () => {
        if (topicToDelete) {
            await TopicService.deleteTopic(Number(courseId), Number(lessonId), topicToDelete);
            setIsConfirmDelete(false);
            setTopicToDelete(null);
            fetchTopics();
        }
    };

    const cancelDeleteTopic = () => {
        setIsConfirmDelete(false);
        setTopicToDelete(null);
    };

    return (
        <div className="container">
            <h1 className="title">{lesson?.name} Topics</h1>
            <div className="buttons">
                <button className="button is-primary" onClick={() => openModal()}>Add Topic</button>
                <div className="has-text-right" style={{ marginLeft: 'auto' }}>
                    <button className="button is-light" onClick={() => navigate(`/admin-course/${courseId}/lessons`)}>Back</button>
                </div>
            </div>


            <table className="table is-fullwidth mt-4">
                <thead>
                    <tr>
                        <th style={{ width: 250 }}>Name</th>
                        <th>Content</th>
                        <th style={{ width: 200 }}>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {topics.map(topic => (
                        <tr key={topic.id}>
                            <td>{topic.name}</td>
                            <td>{topic.content}</td>
                            <td>
                                <button className="button is-small is-info mr-2" onClick={() => openModal(topic)} title="Edit">
                                    <FontAwesomeIcon icon={faEdit} />
                                </button>
                                <button className="button is-small is-danger" onClick={() => requestDeleteTopic(topic)} title="Delete">
                                    <FontAwesomeIcon icon={faTrash} />
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            {isModalActive && selectedTopic && (
                <div className={`modal ${isModalActive ? 'is-active' : ''}`}>
                    <div className="modal-background" onClick={closeModal}></div>
                    <div className="modal-card">
                        <header className="modal-card-head">
                            <p className="modal-card-title">{selectedTopic.id ? 'Edit Topic' : 'Add Topic'}</p>
                            <button className="delete" aria-label="close" onClick={closeModal}></button>
                        </header>
                        <section className="modal-card-body">
                            <div className="field">
                                <label className="label">Name</label>
                                <div className="control">
                                    <input
                                        className="input"
                                        type="text"
                                        name="name"
                                        value={selectedTopic.name || ''}
                                        onChange={handleInputChange}
                                    />
                                </div>
                            </div>
                            <div className="field">
                                <label className="label">Content</label>
                                <div className="control">
                                    <textarea
                                        className="textarea"
                                        name="content"
                                        value={selectedTopic.content || ''}
                                        onChange={handleInputChange}
                                    ></textarea>
                                </div>
                            </div>
                        </section>
                        <footer className="modal-card-foot">
                            <button className="button is-success" onClick={saveTopic}>Save changes</button>
                            <button className="button" onClick={closeModal}>Cancel</button>
                        </footer>
                    </div>
                </div>
            )}

            <ConfirmDialog
                message="Are you sure you want to delete this topic?"
                isActive={isConfirmDelete}
                onConfirm={confirmDeleteTopic}
                onCancel={cancelDeleteTopic}
            />
        </div>
    );
};

export default TopicPage;
