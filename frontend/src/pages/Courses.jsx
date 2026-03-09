import { useState, useEffect } from 'react';
import { courseService } from '../services/dataService';

export default function Courses() {
  const [courses, setCourses] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [form, setForm] = useState({ name: '', totalFee: '' });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const fetchData = async () => {
    try {
      const res = await courseService.getAll();
      setCourses(res.data);
    } catch {
      setError('Failed to load courses');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { fetchData(); }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      await courseService.create({ ...form, totalFee: Number(form.totalFee) });
      setForm({ name: '', totalFee: '' });
      setShowForm(false);
      fetchData();
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to create course');
    }
  };

  return (
    <div>
      <div className="flex items-center justify-between mb-8">
        <div>
          <h1 className="text-3xl font-bold">Courses</h1>
          <p className="text-text-muted mt-1">View and manage courses</p>
        </div>
        <button
          onClick={() => { setShowForm(!showForm); setForm({ name: '', totalFee: '' }); }}
          className="px-5 py-2.5 rounded-xl bg-gradient-to-r from-primary to-primary-dark text-white font-medium hover:shadow-lg hover:shadow-primary/25 transition-all cursor-pointer"
        >
          {showForm ? 'Cancel' : '+ Add Course'}
        </button>
      </div>

      {error && (
        <div className="mb-4 px-4 py-3 rounded-xl bg-danger/10 border border-danger/30 text-danger text-sm">{error}</div>
      )}

      {showForm && (
        <div className="bg-bg-light rounded-2xl border border-surface-light/20 p-6 mb-6">
          <h3 className="text-lg font-semibold mb-4">Add New Course</h3>
          <form onSubmit={handleSubmit} className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <input type="text" placeholder="Course Name" value={form.name} onChange={(e) => setForm({...form, name: e.target.value})} className="px-4 py-3 rounded-xl bg-surface-card border border-surface-light/30 text-text placeholder:text-text-muted/50 focus:outline-none focus:ring-2 focus:ring-primary/50" required />
            <input type="number" placeholder="Total Fee" value={form.totalFee} onChange={(e) => setForm({...form, totalFee: e.target.value})} className="px-4 py-3 rounded-xl bg-surface-card border border-surface-light/30 text-text placeholder:text-text-muted/50 focus:outline-none focus:ring-2 focus:ring-primary/50" required min="1" />
            <div className="md:col-span-2">
              <button type="submit" className="px-6 py-3 rounded-xl bg-gradient-to-r from-success to-emerald-700 text-white font-medium hover:shadow-lg transition-all cursor-pointer">
                Create Course
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
        <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-6">
          {courses.length === 0 ? (
            <div className="col-span-full text-center py-12 text-text-muted bg-bg-light rounded-2xl border border-surface-light/20">
              No courses found. Add your first course!
            </div>
          ) : (
            courses.map((c) => (
              <div key={c.id} className="bg-bg-light rounded-2xl border border-surface-light/20 p-6 hover:border-primary/30 transition-all duration-300">
                <div className="flex items-start justify-between mb-4">
                  <div className="w-12 h-12 rounded-2xl bg-gradient-to-br from-amber-500 to-amber-700 flex items-center justify-center text-xl">📚</div>
                </div>
                <h3 className="text-lg font-semibold">{c.name}</h3>
                <div className="mt-4 space-y-2">
                  <div className="flex justify-between text-sm">
                    <span className="text-text-muted">Total Fee</span>
                    <span className="font-medium text-amber-400">₹{c.totalFee?.toLocaleString()}</span>
                  </div>
                  <div className="flex justify-between text-sm">
                    <span className="text-text-muted">Students</span>
                    <span className="font-medium">{c.totalStudents || 0}</span>
                  </div>
                </div>
              </div>
            ))
          )}
        </div>
      )}
    </div>
  );
}
