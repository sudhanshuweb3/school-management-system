import { useState, useEffect } from 'react';
import { studentService, courseService } from '../services/dataService';

export default function Students() {
  const [students, setStudents] = useState([]);
  const [courses, setCourses] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [editing, setEditing] = useState(null);
  const [form, setForm] = useState({ name: '', email: '', phone: '', courseId: '' });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const fetchData = async () => {
    try {
      const [studentsRes, coursesRes] = await Promise.all([
        studentService.getAll(),
        courseService.getAll(),
      ]);
      setStudents(studentsRes.data);
      setCourses(coursesRes.data);
    } catch {
      setError('Failed to load data');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { fetchData(); }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      const payload = { ...form, courseId: Number(form.courseId) };
      if (editing) {
        await studentService.update(editing, payload);
      } else {
        await studentService.create(payload);
      }
      setForm({ name: '', email: '', phone: '', courseId: '' });
      setShowForm(false);
      setEditing(null);
      fetchData();
    } catch (err) {
      setError(err.response?.data?.message || 'Operation failed');
    }
  };

  const handleEdit = (s) => {
    setForm({ name: s.name, email: s.email, phone: s.phone, courseId: '' });
    setEditing(s.id);
    setShowForm(true);
  };

  const handleDelete = async (id) => {
    if (!confirm('Delete this student?')) return;
    try {
      await studentService.delete(id);
      fetchData();
    } catch {
      setError('Delete failed');
    }
  };

  return (
    <div>
      <div className="flex items-center justify-between mb-8">
        <div>
          <h1 className="text-3xl font-bold">Students</h1>
          <p className="text-text-muted mt-1">Manage student records</p>
        </div>
        <button
          onClick={() => { setShowForm(!showForm); setEditing(null); setForm({ name: '', email: '', phone: '', courseId: '' }); }}
          className="px-5 py-2.5 rounded-xl bg-gradient-to-r from-primary to-primary-dark text-white font-medium hover:shadow-lg hover:shadow-primary/25 transition-all cursor-pointer"
        >
          {showForm ? 'Cancel' : '+ Add Student'}
        </button>
      </div>

      {error && (
        <div className="mb-4 px-4 py-3 rounded-xl bg-danger/10 border border-danger/30 text-danger text-sm">{error}</div>
      )}

      {showForm && (
        <div className="bg-bg-light rounded-2xl border border-surface-light/20 p-6 mb-6">
          <h3 className="text-lg font-semibold mb-4">{editing ? 'Edit Student' : 'Add New Student'}</h3>
          <form onSubmit={handleSubmit} className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <input type="text" placeholder="Full Name" value={form.name} onChange={(e) => setForm({...form, name: e.target.value})} className="px-4 py-3 rounded-xl bg-surface-card border border-surface-light/30 text-text placeholder:text-text-muted/50 focus:outline-none focus:ring-2 focus:ring-primary/50" required />
            <input type="email" placeholder="Email" value={form.email} onChange={(e) => setForm({...form, email: e.target.value})} className="px-4 py-3 rounded-xl bg-surface-card border border-surface-light/30 text-text placeholder:text-text-muted/50 focus:outline-none focus:ring-2 focus:ring-primary/50" required />
            <input type="text" placeholder="Phone (10 digits)" value={form.phone} onChange={(e) => setForm({...form, phone: e.target.value})} className="px-4 py-3 rounded-xl bg-surface-card border border-surface-light/30 text-text placeholder:text-text-muted/50 focus:outline-none focus:ring-2 focus:ring-primary/50" required />
            <select value={form.courseId} onChange={(e) => setForm({...form, courseId: e.target.value})} className="px-4 py-3 rounded-xl bg-surface-card border border-surface-light/30 text-text focus:outline-none focus:ring-2 focus:ring-primary/50" required>
              <option value="">Select Course</option>
              {courses.map(c => <option key={c.id} value={c.id}>{c.name}</option>)}
            </select>
            <div className="md:col-span-2">
              <button type="submit" className="px-6 py-3 rounded-xl bg-gradient-to-r from-success to-emerald-700 text-white font-medium hover:shadow-lg transition-all cursor-pointer">
                {editing ? 'Update' : 'Create'} Student
              </button>
            </div>
          </form>
        </div>
      )}

      {loading ? (
        <div className="flex justify-center py-12">
          <div className="animate-spin rounded-full h-10 w-10 border-t-2 border-b-2 border-primary"></div>
        </div>
      ) : (
        <div className="bg-bg-light rounded-2xl border border-surface-light/20 overflow-hidden">
          <table className="w-full">
            <thead>
              <tr className="border-b border-surface-light/20">
                <th className="text-left px-6 py-4 text-sm font-medium text-text-muted">Name</th>
                <th className="text-left px-6 py-4 text-sm font-medium text-text-muted">Email</th>
                <th className="text-left px-6 py-4 text-sm font-medium text-text-muted">Phone</th>
                <th className="text-left px-6 py-4 text-sm font-medium text-text-muted">Course</th>
                <th className="text-right px-6 py-4 text-sm font-medium text-text-muted">Actions</th>
              </tr>
            </thead>
            <tbody>
              {students.length === 0 ? (
                <tr><td colSpan="5" className="text-center py-12 text-text-muted">No students found. Add your first student!</td></tr>
              ) : (
                students.map((s) => (
                  <tr key={s.id} className="border-b border-surface-light/10 hover:bg-surface-light/10 transition-colors">
                    <td className="px-6 py-4 font-medium">{s.name}</td>
                    <td className="px-6 py-4 text-text-muted">{s.email}</td>
                    <td className="px-6 py-4 text-text-muted">{s.phone}</td>
                    <td className="px-6 py-4"><span className="px-3 py-1 rounded-full bg-primary/15 text-primary-light text-sm">{s.courseName}</span></td>
                    <td className="px-6 py-4 text-right space-x-2">
                      <button onClick={() => handleEdit(s)} className="px-3 py-1.5 rounded-lg bg-primary/15 text-primary-light text-sm hover:bg-primary/25 transition-colors cursor-pointer">Edit</button>
                      <button onClick={() => handleDelete(s.id)} className="px-3 py-1.5 rounded-lg bg-danger/15 text-danger text-sm hover:bg-danger/25 transition-colors cursor-pointer">Delete</button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
