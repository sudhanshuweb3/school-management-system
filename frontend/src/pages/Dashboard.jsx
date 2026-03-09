import { useState, useEffect } from 'react';
import { dashboardService } from '../services/dataService';
import StatCard from '../components/StatCard';

export default function Dashboard() {
  const [stats, setStats] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    dashboardService.getStats()
      .then(res => setStats(res.data))
      .catch(() => setStats({ totalStudents: 0, totalTeachers: 0, totalCourses: 0, totalAttendanceToday: 0 }))
      .finally(() => setLoading(false));
  }, []);

  if (loading) {
    return (
      <div className="flex items-center justify-center h-64">
        <div className="animate-spin rounded-full h-10 w-10 border-t-2 border-b-2 border-primary"></div>
      </div>
    );
  }

  return (
    <div>
      <div className="mb-8">
        <h1 className="text-3xl font-bold">Dashboard</h1>
        <p className="text-text-muted mt-1">Welcome to the School Management System</p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-4 gap-6">
        <StatCard title="Total Students" value={stats?.totalStudents || 0} icon="🎓" color="indigo" />
        <StatCard title="Total Teachers" value={stats?.totalTeachers || 0} icon="👨‍🏫" color="emerald" />
        <StatCard title="Total Courses" value={stats?.totalCourses || 0} icon="📚" color="amber" />
        <StatCard title="Attendance Today" value={stats?.totalAttendanceToday || 0} icon="📋" color="rose" />
      </div>

      {/* Quick Info */}
      <div className="mt-10 grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div className="bg-bg-light rounded-2xl border border-surface-light/20 p-6">
          <h3 className="text-lg font-semibold mb-4">Quick Actions</h3>
          <div className="space-y-3">
            <a href="/students" className="flex items-center gap-3 px-4 py-3 rounded-xl hover:bg-surface-light/20 transition-colors text-text-muted hover:text-text">
              <span className="text-lg">➕</span> Add New Student
            </a>
            <a href="/teachers" className="flex items-center gap-3 px-4 py-3 rounded-xl hover:bg-surface-light/20 transition-colors text-text-muted hover:text-text">
              <span className="text-lg">➕</span> Add New Teacher
            </a>
            <a href="/attendance" className="flex items-center gap-3 px-4 py-3 rounded-xl hover:bg-surface-light/20 transition-colors text-text-muted hover:text-text">
              <span className="text-lg">📝</span> Mark Attendance
            </a>
          </div>
        </div>

        <div className="bg-bg-light rounded-2xl border border-surface-light/20 p-6">
          <h3 className="text-lg font-semibold mb-4">System Info</h3>
          <div className="space-y-3 text-sm text-text-muted">
            <div className="flex justify-between py-2 border-b border-surface-light/10">
              <span>Platform</span>
              <span className="text-text">React + Spring Boot</span>
            </div>
            <div className="flex justify-between py-2 border-b border-surface-light/10">
              <span>Database</span>
              <span className="text-text">H2 (In-Memory)</span>
            </div>
            <div className="flex justify-between py-2 border-b border-surface-light/10">
              <span>Authentication</span>
              <span className="text-text">JWT</span>
            </div>
            <div className="flex justify-between py-2">
              <span>API Version</span>
              <span className="text-text">v1.0</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
