export default function StatCard({ title, value, icon, color }) {
  const colors = {
    indigo: 'from-indigo-500 to-indigo-700',
    emerald: 'from-emerald-500 to-emerald-700',
    amber: 'from-amber-500 to-amber-700',
    rose: 'from-rose-500 to-rose-700',
  };

  return (
    <div className="bg-bg-light rounded-2xl border border-surface-light/20 p-6 hover:border-primary/30 transition-all duration-300 hover:shadow-lg hover:shadow-primary/5">
      <div className="flex items-center justify-between">
        <div>
          <p className="text-text-muted text-sm font-medium">{title}</p>
          <p className="text-3xl font-bold mt-2 bg-gradient-to-r from-white to-text bg-clip-text text-transparent">
            {value}
          </p>
        </div>
        <div className={`w-14 h-14 rounded-2xl bg-gradient-to-br ${colors[color] || colors.indigo} flex items-center justify-center text-2xl shadow-lg`}>
          {icon}
        </div>
      </div>
    </div>
  );
}
