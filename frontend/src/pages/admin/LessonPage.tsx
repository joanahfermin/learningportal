import React, { useState, useEffect } from 'react';
import { useParams /*, useNavigate*/ } from 'react-router-dom';
import LessonService from '../../services/LessonService';
import { Lesson } from '../../model/Lesson';
import ConfirmDialog from '../../components/ConfirmDialog'; // Adjust the path accordingly
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';

const LessonPage: React.FC = () => {
  const { courseId } = useParams<{ courseId: string }>();
  /*const navigate = useNavigate();*/

  const [lessons, setLessons] = useState<Lesson[]>([]);
  const [selectedLesson, setSelectedLesson] = useState<Partial<Lesson> | null>(null);
  const [isModalActive, setModalActive] = useState(false);
  const [isConfirmDelete, setIsConfirmDelete] = useState(false);
  const [lessonToDelete, setLessonToDelete] = useState<Lesson | null>(null);

  useEffect(() => {
    fetchLessons();
  }, [courseId]);

  const fetchLessons = async () => {
    const numericCourseId = Number(courseId);
    const data = await LessonService.getLessonsByCourseId(numericCourseId);
    setLessons(data);
  };

  const openModal = (lesson?: Lesson) => {
    setSelectedLesson(lesson || { name: '', description: '' });
    setModalActive(true);
  };

  const closeModal = () => {
    setModalActive(false);
    setSelectedLesson(null);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setSelectedLesson((prev) => ({ ...prev, [name]: value }));
  };

  const saveLesson = async () => {
    if (selectedLesson) {
      if (selectedLesson.id) {
        await LessonService.updateLesson(selectedLesson as Lesson); // Update existing lesson
      } else {
        await LessonService.createLesson(Number(courseId), selectedLesson as Omit<Lesson, 'id'>); // Create new lesson
      }
      closeModal();
      fetchLessons();
    }
  };

  const requestDeleteLesson = (lesson: Lesson) => {
    setIsConfirmDelete(true);
    setLessonToDelete(lesson);
  };

  const confirmDeleteLesson = async () => {
    if (lessonToDelete) {
      await LessonService.deleteLesson(lessonToDelete);
      setIsConfirmDelete(false);
      setLessonToDelete(null);
      fetchLessons();
    }
  };

  const cancelDeleteLesson = () => {
    setIsConfirmDelete(false);
    setLessonToDelete(null);
  };

  return (
    <div className="container">
      <h1 className="title">Lessons for Course {courseId}</h1>
      <button className="button is-primary" onClick={() => openModal()}>Add Lesson</button>

      <table className="table is-fullwidth mt-4">
        <thead>
          <tr>
            <th style={{ width: 250 }}>Name</th>
            <th>Description</th>
            <th style={{ width: 200 }}>Actions</th>
          </tr>
        </thead>
        <tbody>
          {lessons.map(lesson => (
            <tr key={lesson.id}>
              <td>{lesson.name}</td>
              <td>{lesson.description}</td>
              <td>
                <button className="button is-small is-info mr-2" onClick={() => openModal(lesson)} title="Edit">
                  <FontAwesomeIcon icon={faEdit} />
                </button>
                <button className="button is-small is-danger" onClick={() => requestDeleteLesson(lesson)} title="Delete">
                  <FontAwesomeIcon icon={faTrash} />
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {isModalActive && selectedLesson && (
        <div className={`modal ${isModalActive ? 'is-active' : ''}`}>
          <div className="modal-background" onClick={closeModal}></div>
          <div className="modal-card">
            <header className="modal-card-head">
              <p className="modal-card-title">{selectedLesson.id ? 'Edit Lesson' : 'Add Lesson'}</p>
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
                    value={selectedLesson.name || ''}
                    onChange={handleInputChange}
                  />
                </div>
              </div>
              <div className="field">
                <label className="label">Description</label>
                <div className="control">
                  <textarea
                    className="textarea"
                    name="description"
                    value={selectedLesson.description || ''}
                    onChange={handleInputChange}
                  ></textarea>
                </div>
              </div>
            </section>
            <footer className="modal-card-foot">
              <button className="button is-success" onClick={saveLesson}>Save changes</button>
              <button className="button" onClick={closeModal}>Cancel</button>
            </footer>
          </div>
        </div>
      )}

      <ConfirmDialog
        message="Are you sure you want to delete this lesson?"
        isActive={isConfirmDelete}
        onConfirm={confirmDeleteLesson}
        onCancel={cancelDeleteLesson}
      />
    </div>
  );
};

export default LessonPage;
