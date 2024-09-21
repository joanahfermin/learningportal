import React, { useEffect, useState } from "react";
import axios from '../../api/axiosConfig'; // Import the configured axios instance
import { Course } from "../../model/Course";

const CoursePage: React.FC = () => {
  const [courses, setCourses] = useState<Course[]>([]);
  const [newCourse, setNewCourse] = useState<Course>({ id: 0, name: '', description: '' });
  const [editCourse, setEditCourse] = useState<Course | null>(null);

  useEffect(() => {
    fetchCourses();
  }, []);

  const fetchCourses = async () => {
    try {
      const response = await axios.get('/courses');
      setCourses(response.data);
    } catch (error) {
      console.error('Error fetching courses', error);
    }
  };

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = event.target;
    if (editCourse) {
      setEditCourse({ ...editCourse, [name]: value });
    } else {
      setNewCourse({ ...newCourse, [name]: value });
    }
  };

  const handleCourseSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (editCourse) {
      try {
        await axios.put(`/courses/${editCourse.id}`, editCourse);
        fetchCourses();
        setEditCourse(null);
      } catch (error) {
        console.error('Error updating course', error);
      }
    } else {
      try {
        await axios.post('/courses', newCourse);
        fetchCourses();
        setNewCourse({ id: 0, name: '', description: '' });
      } catch (error) {
        console.error('Error creating course', error);
      }
    }
  };

  const handleEdit = (course: Course) => {
    setEditCourse(course);
  };

  const handleDelete = async (id: number) => {
    try {
      await axios.delete(`/courses/${id}`);
      fetchCourses();
    } catch (error) {
      console.error('Error deleting course', error);
    }
  };

  return (
    <div className="container">
      <h1 className="title">Course Management</h1>
      <form onSubmit={handleCourseSubmit}>
        <div className="field">
          <label className="label">Name</label>
          <div className="control">
            <input
              className="input"
              type="text"
              name="name"
              value={editCourse ? editCourse.name : newCourse.name}
              onChange={handleInputChange}
              required
            />
          </div>
        </div>
        <div className="field">
          <label className="label">Description</label>
          <div className="control">
            <textarea
              className="textarea"
              name="description"
              value={editCourse ? editCourse.description : newCourse.description}
              onChange={handleInputChange}
            ></textarea>
          </div>
        </div>
        <div className="control">
          <button className="button is-primary" type="submit">
            {editCourse ? 'Update Course' : 'Create Course'}
          </button>
        </div>
      </form>

      <table className="table is-fullwidth is-striped">
        <thead>
          <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {courses.map(course => (
            <tr key={course.id}>
              <td>{course.name}</td>
              <td>{course.description}</td>
              <td>
                <button className="button is-small is-info" onClick={() => handleEdit(course)}>
                  Edit
                </button>
                <button className="button is-small is-danger" onClick={() => handleDelete(course.id)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default CoursePage;
