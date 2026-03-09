import { NavLink } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const navItems = [
  { path: '/', label: 'Dashboard', icon: '📊' },
  { path: '/students', label: 'Students', icon: '🎓' },
  { path: '/teachers', label: 'Teachers', icon: '👨‍🏫' },
  { path: '/courses', label: 'Courses', icon: '📚' },
  { path: '/attendance', label: 'Attendance', icon: '📋' },
];

export default function Sidebar() {
  const { user, logout } = useAuth();

  return (
    <aside className="fixed left-0 top-0 h-screen w-64 bg-bg-light border-r border-surface-light/30 flex flex-col z-50">
      {/* Logo */}
      <div className="p-6 border-b border-surface-light/30">
        <h1 className="text-xl font-bold bg-gradient-to-r from-primary-light to-accent bg-clip-text text-transparent">
          SchoolMS
        </h1>
        <p className="text-xs text-text-muted mt-1">Management System</p>
      </div>

      {/* Nav */}
      <nav className="flex-1 p-4 space-y-1">
        {navItems.map((item) => (
          <NavLink
            key={item.path}
            to={item.path}
            end={item.path === '/'}
            className={({ isActive }) =>
              `flex items-center gap-3 px-4 py-3 rounded-xl text-sm font-medium transition-all duration-200 ${
                isActive
                  ? 'bg-primary/20 text-primary-light shadow-lg shadow-primary/10'
                  : 'text-text-muted hover:text-text hover:bg-surface-light/30'
              }`
            }
          >
            <span className="text-lg">{item.icon}</span>
            {item.label}
          </NavLink>
        ))}
      </nav>

      {/* User */}
      <div className="p-4 border-t border-surface-light/30">
        <div className="flex items-center gap-3 mb-3">
          <div className="w-9 h-9 rounded-full bg-gradient-to-br from-primary to-accent flex items-center justify-center text-sm font-bold text-white">
            {user?.name?.charAt(0) || 'U'}
          </div>
          <div className="flex-1 min-w-0">
            <p className="text-sm font-medium truncate">{user?.name || 'User'}</p>
            <p className="text-xs text-text-muted truncate">{user?.role || 'Role'}</p>
          </div>
        </div>
        <button
          onClick={logout}
          className="w-full px-4 py-2 text-sm text-danger hover:bg-danger/10 rounded-lg transition-colors cursor-pointer"
        >
          Sign Out
        </button>
      </div>
    </aside>
  );
}
