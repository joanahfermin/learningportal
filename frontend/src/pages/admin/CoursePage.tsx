import React, { useState, useEffect } from 'react';
import CourseService from '../../services/CourseService';
import { Course } from '../..//model/Course';
import ConfirmDialog from '../../components/ConfirmDialog'; // Update the path as needed
import { useNavigate } from 'react-router-dom';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faTrash, faBook } from '@fortawesome/free-solid-svg-icons';

const CoursePage: React.FC = () => {
  const navigate = useNavigate();

  const [courses, setCourses] = useState<Course[]>([]);
  const [selectedCourse, setSelectedCourse] = useState<Partial<Course> | null>(null);
  const [isModalActive, setModalActive] = useState(false);
  const [isConfirmDelete, setIsConfirmDelete] = useState(false);
  const [courseToDelete, setCourseToDelete] = useState<number | null>(null);

  useEffect(() => {
    fetchCourses();
  }, []);

  const fetchCourses = async () => {
    const data = await CourseService.getAllCourses();
    setCourses(data);
  };

  const openModal = (course?: Course) => {
    setSelectedCourse(course || { name: '', description: '' });
    setModalActive(true);
  };

  const closeModal = () => {
    setModalActive(false);
    setSelectedCourse(null);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setSelectedCourse((prev) => ({ ...prev, [name]: value }));
  };

  const saveCourse = async () => {
    if (selectedCourse) {
      if (selectedCourse.id) {
        await CourseService.updateCourse(selectedCourse.id, selectedCourse as Course);
      } else {
        await CourseService.createCourse(selectedCourse as Omit<Course, 'id'>);
      }
      closeModal();
      fetchCourses();
    }
  };

  const requestDeleteCourse = (id: number) => {
    setIsConfirmDelete(true);
    setCourseToDelete(id);
  };

  const confirmDeleteCourse = async () => {
    if (courseToDelete !== null) {
      await CourseService.deleteCourse(courseToDelete);
      setIsConfirmDelete(false);
      setCourseToDelete(null);
      fetchCourses();
    }
  };

  const cancelDeleteCourse = () => {
    setIsConfirmDelete(false);
    setCourseToDelete(null);
  };

  return (
    <div className="container">
      <h1 className="title">Courses</h1>
      <div className="buttons">
        <button className="button is-primary" onClick={() => openModal()}>Add Course</button>
      </div>

      <table className="table is-fullwidth mt-4">
        <thead>
          <tr>
            <th style={{ width: 250 }}>Name</th>
            <th>Description</th>
            <th style={{ width: 200 }}>Actions</th>
          </tr>
        </thead>
        <tbody>
          {courses.map(course => (
            <tr key={course.id}>
              <td>{course.name}</td>
              <td>{course.description}</td>
              <td>
                <button className="button is-small is-info mr-2" onClick={() => openModal(course)} title="Edit">
                  <FontAwesomeIcon icon={faEdit} />
                </button>
                <button className="button is-small is-danger" onClick={() => requestDeleteCourse(course.id)} title="Delete">
                  <FontAwesomeIcon icon={faTrash} />
                </button>
                <button className="button is-small is-primary" onClick={() => navigate(`/admin-course/${course.id}/lessons`)} title="Lessons">
                  <FontAwesomeIcon icon={faBook} />
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {isModalActive && selectedCourse && (
        <div className={`modal ${isModalActive ? 'is-active' : ''}`}>
          <div className="modal-background" onClick={closeModal}></div>
          <div className="modal-card">
            <header className="modal-card-head">
              <p className="modal-card-title">{selectedCourse.id ? 'Edit Course' : 'Add Course'}</p>
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
                    value={selectedCourse.name || ''}
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
                    value={selectedCourse.description || ''}
                    onChange={handleInputChange}
                  ></textarea>
                </div>
              </div>
            </section>
            <footer className="modal-card-foot">
              <button className="button is-success" onClick={saveCourse}>Save changes</button>
              <button className="button" onClick={closeModal}>Cancel</button>
            </footer>
          </div>
        </div>
      )}

      <ConfirmDialog
        message="Are you sure you want to delete this course?"
        isActive={isConfirmDelete}
        onConfirm={confirmDeleteCourse}
        onCancel={cancelDeleteCourse}
      />
    </div>
  );
};

export default CoursePage;