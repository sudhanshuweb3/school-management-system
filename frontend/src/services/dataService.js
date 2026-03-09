import api from './api';

export const authService = {
  login: (credentials) => api.post('/auth/login', credentials),
  register: (data) => api.post('/auth/register', data),
};

export const dashboardService = {
  getStats: () => api.get('/api/dashboard/stats'),
};

export const studentService = {
  getAll: () => api.get('/students'),
  getById: (id) => api.get(`/students/${id}`),
  create: (data) => api.post('/students', data),
  update: (id, data) => api.put(`/students/${id}`, data),
  delete: (id) => api.delete(`/students/${id}`),
};

export const teacherService = {
  getAll: () => api.get('/api/teachers'),
  getById: (id) => api.get(`/api/teachers/${id}`),
  create: (data) => api.post('/api/teachers', data),
  update: (id, data) => api.put(`/api/teachers/${id}`, data),
  delete: (id) => api.delete(`/api/teachers/${id}`),
};

export const courseService = {
  getAll: () => api.get('/courses'),
  create: (data) => api.post('/courses', data),
  update: (id, data) => api.put(`/courses/${id}`, data),
};

export const attendanceService = {
  getAll: () => api.get('/api/attendance'),
  getByStudent: (studentId) => api.get(`/api/attendance/student/${studentId}`),
  getByDate: (date) => api.get(`/api/attendance/date/${date}`),
  mark: (data) => api.post('/api/attendance', data),
};
