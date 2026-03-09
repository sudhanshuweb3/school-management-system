import { useState, useEffect } from 'react';
import { teacherService } from '../services/dataService';

export default function Teachers() {
  const [teachers, setTeachers] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [editing, setEditing] = useState(null);
  const [form, setForm] = useState({ name: '', email: '', phone: '', subject: '' });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const fetchData = async () => {
    try {
      const res = await teacherService.getAll();
      setTeachers(res.data);
    } catch {
      setError('Failed to load teachers');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { fetchData(); }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      if (editing) {
        await teacherService.update(editing, form);
      } else {
        await teacherService.create(form);
      }
      setForm({ name: '', email: '', phone: '', subject: '' });
      setShowForm(false);
      setEditing(null);
      fetchData();
    } catch (err) {
      setError(err.response?.data?.message || 'Operation failed');
    }
  };

  const handleEdit = (t) => {
    setForm({ name: t.name, email: t.email, phone: t.phone, subject: t.subject });
    setEditing(t.id);
    setShowForm(true);
  };

  const handleDelete = async (id) => {
    if (!confirm('Delete this teacher?')) return;
    try {
      await teacherService.delete(id);
      fetchData();
    } catch {
      setError('Delete failed');
    }
  };

  return (
    <div>
      <div className="flex items-center justify-between mb-8">
        <div>
          <h1 className="text-3xl font-bold">Teachers</h1>
          <p className="text-text-muted mt-1">Manage teacher records</p>
        </div>
        <button
          onClick={() => { setShowForm(!showForm); setEditing(null); setForm({ name: '', email: '', phone: '', subject: '' }); }}
          className="px-5 py-2.5 rounded-xl bg-gradient-to-r from-primary to-primary-dark text-white font-medium hover:shadow-lg hover:shadow-primary/25 transition-all cursor-pointer"
        >
          {showForm ? 'Cancel' : '+ Add Teacher'}
        </button>
      </div>

      {error && (
        <div className="mb-4 px-4 py-3 rounded-xl bg-danger/10 border border-danger/30 text-danger text-sm">{error}</div>
      )}

      {showForm && (
        <div className="bg-bg-light rounded-2xl border border-surface-light/20 p-6 mb-6">
          <h3 className="text-lg font-semibold mb-4">{editing ? 'Edit Teacher' : 'Add New Teacher'}</h3>
          <form onSubmit={handleSubmit} className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <input type="text" placeholder="Full Name" value={form.name} onChange={(e) => setForm({...form, name: e.target.value})} className="px-4 py-3 rounded-xl bg-surface-card border border-surface-light/30 text-text placeholder:text-text-muted/50 focus:outline-none focus:ring-2 focus:ring-primary/50" required />
            <input type="email" placeholder="Email" value={form.email} onChange={(e) => setForm({...form, email: e.target.value})} className="px-4 py-3 rounded-xl bg-surface-card border border-surface-light/30 text-text placeholder:text-text-muted/50 focus:outline-none focus:ring-2 focus:ring-primary/50" required />
            <input type="text" placeholder="Phone" value={form.phone} onChange={(e) => setForm({...form, phone: e.target.value})} className="px-4 py-3 rounded-xl bg-surface-card border border-surface-light/30 text-text placeholder:text-text-muted/50 focus:outline-none focus:ring-2 focus:ring-primary/50" required />
            <input type="text" placeholder="Subject" value={form.subject} onChange={(e) => setForm({...form, subject: e.target.value})} className="px-4 py-3 rounded-xl bg-surface-card border border-surface-light/30 text-text placeholder:text-text-muted/50 focus:outline-none focus:ring-2 focus:ring-primary/50" required />
            <div className="md:col-span-2">
              <button type="submit" className="px-6 py-3 rounded-xl bg-gradient-to-r from-success to-emerald-700 text-white font-medium hover:shadow-lg transition-all cursor-pointer">
                {editing ? 'Update' : 'Create'} Teacher
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
                <th className="text-left px-6 py-4 text-sm font-medium text-text-muted">Subject</th>
                <th className="text-right px-6 py-4 text-sm font-medium text-text-muted">Actions</th>
              </tr>
            </thead>
            <tbody>
              {teachers.length === 0 ? (
                <tr><td colSpan="5" className="text-center py-12 text-text-muted">No teachers found. Add your first teacher!</td></tr>
              ) : (
                teachers.map((t) => (
                  <tr key={t.id} className="border-b border-surface-light/10 hover:bg-surface-light/10 transition-colors">
                    <td className="px-6 py-4 font-medium">{t.name}</td>
                    <td className="px-6 py-4 text-text-muted">{t.email}</td>
                    <td className="px-6 py-4 text-text-muted">{t.phone}</td>
                    <td className="px-6 py-4"><span className="px-3 py-1 rounded-full bg-emerald-500/15 text-emerald-400 text-sm">{t.subject}</span></td>
                    <td className="px-6 py-4 text-right space-x-2">
                      <button onClick={() => handleEdit(t)} className="px-3 py-1.5 rounded-lg bg-primary/15 text-primary-light text-sm hover:bg-primary/25 transition-colors cursor-pointer">Edit</button>
                      <button onClick={() => handleDelete(t.id)} className="px-3 py-1.5 rounded-lg bg-danger/15 text-danger text-sm hover:bg-danger/25 transition-colors cursor-pointer">Delete</button>
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
