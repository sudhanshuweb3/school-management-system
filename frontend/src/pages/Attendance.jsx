import { useState, useEffect } from 'react';
import { attendanceService, studentService } from '../services/dataService';

export default function Attendance() {
  const [records, setRecords] = useState([]);
  const [students, setStudents] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [form, setForm] = useState({ studentId: '', date: new Date().toISOString().split('T')[0], status: 'PRESENT' });
  const [filterDate, setFilterDate] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const fetchData = async () => {
    try {
      const [attendanceRes, studentsRes] = await Promise.all([
        attendanceService.getAll(),
        studentService.getAll(),
      ]);
      setRecords(attendanceRes.data);
      setStudents(studentsRes.data);
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
      await attendanceService.mark({
        studentId: Number(form.studentId),
        date: form.date,
        status: form.status,
      });
      setForm({ studentId: '', date: new Date().toISOString().split('T')[0], status: 'PRESENT' });
      setShowForm(false);
      fetchData();
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to mark attendance');
    }
  };

  const handleFilter = async () => {
    if (!filterDate) return;
    setLoading(true);
    try {
      const res = await attendanceService.getByDate(filterDate);
      setRecords(res.data);
    } catch {
      setError('Filter failed');
    } finally {
      setLoading(false);
    }
  };

  const clearFilter = () => {
    setFilterDate('');
    fetchData();
  };

  const statusColors = {
    PRESENT: 'bg-success/15 text-success',
    ABSENT: 'bg-danger/15 text-danger',
    LATE: 'bg-warning/15 text-warning',
  };

  return (
    <div>
      <div className="flex items-center justify-between mb-8">
        <div>
          <h1 className="text-3xl font-bold">Attendance</h1>
          <p className="text-text-muted mt-1">Track student attendance</p>
        </div>
        <button
          onClick={() => setShowForm(!showForm)}
          className="px-5 py-2.5 rounded-xl bg-gradient-to-r from-primary to-primary-dark text-white font-medium hover:shadow-lg hover:shadow-primary/25 transition-all cursor-pointer"
        >
          {showForm ? 'Cancel' : '+ Mark Attendance'}
        </button>
      </div>

      {error && (
        <div className="mb-4 px-4 py-3 rounded-xl bg-danger/10 border border-danger/30 text-danger text-sm">{error}</div>
      )}

      {showForm && (
        <div className="bg-bg-light rounded-2xl border border-surface-light/20 p-6 mb-6">
          <h3 className="text-lg font-semibold mb-4">Mark Attendance</h3>
          <form onSubmit={handleSubmit} className="grid grid-cols-1 md:grid-cols-3 gap-4">
            <select value={form.studentId} onChange={(e) => setForm({...form, studentId: e.target.value})} className="px-4 py-3 rounded-xl bg-surface-card border border-surface-light/30 text-text focus:outline-none focus:ring-2 focus:ring-primary/50" required>
              <option value="">Select Student</option>
              {students.map(s => <option key={s.id} value={s.id}>{s.name}</option>)}
            </select>
            <input type="date" value={form.date} onChange={(e) => setForm({...form, date: e.target.value})} className="px-4 py-3 rounded-xl bg-surface-card border border-surface-light/30 text-text focus:outline-none focus:ring-2 focus:ring-primary/50" required />
            <select value={form.status} onChange={(e) => setForm({...form, status: e.target.value})} className="px-4 py-3 rounded-xl bg-surface-card border border-surface-light/30 text-text focus:outline-none focus:ring-2 focus:ring-primary/50">
              <option value="PRESENT">Present</option>
              <option value="ABSENT">Absent</option>
              <option value="LATE">Late</option>
            </select>
            <div className="md:col-span-3">
              <button type="submit" className="px-6 py-3 rounded-xl bg-gradient-to-r from-success to-emerald-700 text-white font-medium hover:shadow-lg transition-all cursor-pointer">
                Save Attendance
              </button>
            </div>
          </form>
        </div>
      )}

      {/* Filter */}
      <div className="bg-bg-light rounded-2xl border border-surface-light/20 p-4 mb-6 flex items-center gap-4">
        <input type="date" value={filterDate} onChange={(e) => setFilterDate(e.target.value)} className="px-4 py-2 rounded-xl bg-surface-card border border-surface-light/30 text-text focus:outline-none focus:ring-2 focus:ring-primary/50" />
        <button onClick={handleFilter} className="px-4 py-2 rounded-xl bg-primary/20 text-primary-light hover:bg-primary/30 transition-colors cursor-pointer text-sm font-medium">Filter</button>
        <button onClick={clearFilter} className="px-4 py-2 rounded-xl bg-surface-light/20 text-text-muted hover:bg-surface-light/30 transition-colors cursor-pointer text-sm font-medium">Clear</button>
      </div>

      {loading ? (
        <div className="flex justify-center py-12">
          <div className="animate-spin rounded-full h-10 w-10 border-t-2 border-b-2 border-primary"></div>
        </div>
      ) : (
        <div className="bg-bg-light rounded-2xl border border-surface-light/20 overflow-hidden">
          <table className="w-full">
            <thead>
              <tr className="border-b border-surface-light/20">
                <th className="text-left px-6 py-4 text-sm font-medium text-text-muted">Student</th>
                <th className="text-left px-6 py-4 text-sm font-medium text-text-muted">Date</th>
                <th className="text-left px-6 py-4 text-sm font-medium text-text-muted">Status</th>
              </tr>
            </thead>
            <tbody>
              {records.length === 0 ? (
                <tr><td colSpan="3" className="text-center py-12 text-text-muted">No attendance records found.</td></tr>
              ) : (
                records.map((r) => (
                  <tr key={r.id} className="border-b border-surface-light/10 hover:bg-surface-light/10 transition-colors">
                    <td className="px-6 py-4 font-medium">{r.studentName}</td>
                    <td className="px-6 py-4 text-text-muted">{r.date}</td>
                    <td className="px-6 py-4">
                      <span className={`px-3 py-1 rounded-full text-sm font-medium ${statusColors[r.status] || ''}`}>{r.status}</span>
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
