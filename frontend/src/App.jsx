import React, { useState, useEffect } from 'react';
import { 
  Database, 
  FolderGit2, 
  Users, 
  BookOpen, 
  Plus, 
  Search, 
  TrendingUp, 
  Download, 
  ShieldCheck, 
  Award, 
  Activity, 
  X,
  Building,
  DollarSign
} from 'lucide-react';

const API_BASE = 'http://localhost:8080/api';

export default function App() {
  const [activeTab, setActiveTab] = useState('projects');
  const [searchQuery, setSearchQuery] = useState('');
  const [showProjectModal, setShowProjectModal] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  // Application Data States
  const [metrics, setMetrics] = useState({
    totalProjects: 4,
    activeProjects: 3,
    totalFunding: 4450000.00,
    totalDatasets: 4,
    totalStorageMb: 9861.5,
    totalResearchers: 4,
    totalPublications: 3,
    totalCitations: 247
  });

  const [projects, setProjects] = useState([
    { id: 1, title: 'Genomic Sequence Variant Mapping', description: 'Large-scale DNA variant indexing and structural variant modeling.', domain: 'Bioinformatics', principalInvestigator: 'Dr. Aris Thorne', grantAmount: 1250000, status: 'ONGOING', startDate: '2024-01-15', endDate: '2026-12-31' },
    { id: 2, title: 'Quantum Cryptographic Protocol Testing', description: 'Post-quantum key distribution and grid defense framework.', domain: 'Cybersecurity', principalInvestigator: 'Dr. Elena Rostova', grantAmount: 850000, status: 'ONGOING', startDate: '2023-06-01', endDate: '2025-08-30' },
    { id: 3, title: 'Deep Sea Thermal Anomaly Modeling', description: 'Autonomous subsea telemetry and ocean temperature analytics.', domain: 'Climate Science', principalInvestigator: 'Prof. Marcus Vance', grantAmount: 450000, status: 'COMPLETED', startDate: '2022-03-10', endDate: '2024-02-28' },
    { id: 4, title: 'Neuromorphic Edge AI Accelerator', description: 'Ultra-low latency spiking neural network design for embedded devices.', domain: 'AI/ML', principalInvestigator: 'Dr. Sarah Lin', grantAmount: 1900000, status: 'ONGOING', startDate: '2024-04-01', endDate: '2027-04-01' }
  ]);

  const [datasets, setDatasets] = useState([
    { id: 1, name: 'Human Genome Exome Variant Index (v4.2)', description: 'High-coverage WES variant call format file dataset.', fileFormat: 'VCF', sizeMb: 4250.5, accessLevel: 'RESTRICTED', downloadsCount: 142, uploadedBy: 'Dr. Aris Thorne' },
    { id: 2, name: 'Quantum Key Exchange Telemetry Logs', description: 'Raw bit error rate logs from 50km fiber testbed.', fileFormat: 'JSON', sizeMb: 620.0, accessLevel: 'CONFIDENTIAL', downloadsCount: 89, uploadedBy: 'Dr. Elena Rostova' },
    { id: 3, name: 'Pacific Basin Thermal Sensors 2023-2024', description: 'Time-series sea surface and abyssal temperatures.', fileFormat: 'CSV', sizeMb: 1890.2, accessLevel: 'PUBLIC', downloadsCount: 512, uploadedBy: 'Prof. Marcus Vance' },
    { id: 4, name: 'Spiking Neural Net Spike Train Samples', description: 'Synthetic and recorded neuromorphic sensor stream arrays.', fileFormat: 'Parquet', sizeMb: 3100.8, accessLevel: 'PUBLIC', downloadsCount: 304, uploadedBy: 'Dr. Sarah Lin' }
  ]);

  const [researchers, setResearchers] = useState([
    { id: 1, name: 'Dr. Aris Thorne', email: 'aris.thorne@drims.org', department: 'Genomics & Bio-Data', role: 'Principal Investigator', orcidId: '0000-0002-1825-0097', publicationsCount: 14 },
    { id: 2, name: 'Dr. Elena Rostova', email: 'elena.rostova@drims.org', department: 'Cybersecurity', role: 'Lead Cryptographer', orcidId: '0000-0001-9034-4412', publicationsCount: 22 },
    { id: 3, name: 'Prof. Marcus Vance', email: 'marcus.vance@drims.org', department: 'Oceanography', role: 'Senior Scientist', orcidId: '0000-0003-7721-1189', publicationsCount: 35 },
    { id: 4, name: 'Dr. Sarah Lin', email: 'sarah.lin@drims.org', department: 'Computer Science', role: 'AI Research Chair', orcidId: '0000-0002-4510-8890', publicationsCount: 19 }
  ]);

  // Form State
  const [newProject, setNewProject] = useState({
    title: '',
    description: '',
    domain: 'Bioinformatics',
    principalInvestigator: '',
    grantAmount: '',
    status: 'ONGOING',
    startDate: '',
    endDate: ''
  });

  // Fetch data from Spring Boot REST API
  useEffect(() => {
    fetchMetrics();
    fetchProjects();
    fetchDatasets();
    fetchResearchers();
  }, []);

  const fetchMetrics = async () => {
    try {
      const res = await fetch(`${API_BASE}/analytics/dashboard`);
      if (res.ok) {
        const data = await res.json();
        setMetrics(data);
      }
    } catch (e) {
      console.log('Using local fallback state (backend API disconnected or starting up)');
    }
  };

  const fetchProjects = async () => {
    try {
      const res = await fetch(`${API_BASE}/projects`);
      if (res.ok) {
        const data = await res.json();
        setProjects(data);
      }
    } catch (e) {
      console.log('Projects API unavailable, using local mock data');
    }
  };

  const fetchDatasets = async () => {
    try {
      const res = await fetch(`${API_BASE}/datasets`);
      if (res.ok) {
        const data = await res.json();
        setDatasets(data);
      }
    } catch (e) {
      console.log('Datasets API unavailable');
    }
  };

  const fetchResearchers = async () => {
    try {
      const res = await fetch(`${API_BASE}/researchers`);
      if (res.ok) {
        const data = await res.json();
        setResearchers(data);
      }
    } catch (e) {
      console.log('Researchers API unavailable');
    }
  };

  const handleCreateProject = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    const payload = {
      ...newProject,
      grantAmount: parseFloat(newProject.grantAmount) || 0
    };

    try {
      const res = await fetch(`${API_BASE}/projects`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });
      if (res.ok) {
        const saved = await res.json();
        setProjects([saved, ...projects]);
      } else {
        // Fallback local save
        setProjects([{ id: Date.now(), ...payload }, ...projects]);
      }
    } catch (err) {
      setProjects([{ id: Date.now(), ...payload }, ...projects]);
    }

    setIsLoading(false);
    setShowProjectModal(false);
    setNewProject({ title: '', description: '', domain: 'Bioinformatics', principalInvestigator: '', grantAmount: '', status: 'ONGOING', startDate: '', endDate: '' });
  };

  const handleDownload = async (datasetId) => {
    setDatasets(datasets.map(d => d.id === datasetId ? { ...d, downloadsCount: d.downloadsCount + 1 } : d));
    try {
      await fetch(`${API_BASE}/datasets/${datasetId}/download`, { method: 'POST' });
    } catch (e) {
      // Ignore fallback
    }
  };

  // Filtered Lists
  const filteredProjects = projects.filter(p => 
    p.title.toLowerCase().includes(searchQuery.toLowerCase()) || 
    p.domain.toLowerCase().includes(searchQuery.toLowerCase()) ||
    p.principalInvestigator.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const filteredDatasets = datasets.filter(d =>
    d.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
    d.fileFormat.toLowerCase().includes(searchQuery.toLowerCase()) ||
    d.accessLevel.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <div className="app-container">
      {/* Top Navbar */}
      <header className="header">
        <div className="brand">
          <div className="brand-icon">
            <Database size={24} />
          </div>
          <div>
            <div className="brand-title">DRIMS</div>
            <div className="brand-subtitle">Data Research Information Management System</div>
          </div>
        </div>

        <nav className="nav-tabs">
          <button className={`nav-btn ${activeTab === 'projects' ? 'active' : ''}`} onClick={() => setActiveTab('projects')}>
            <FolderGit2 size={16} /> Research Projects
          </button>
          <button className={`nav-btn ${activeTab === 'datasets' ? 'active' : ''}`} onClick={() => setActiveTab('datasets')}>
            <Database size={16} /> Datasets Catalog
          </button>
          <button className={`nav-btn ${activeTab === 'researchers' ? 'active' : ''}`} onClick={() => setActiveTab('researchers')}>
            <Users size={16} /> Researchers Directory
          </button>
        </nav>
      </header>

      {/* Main Content Dashboard */}
      <main className="main-content">
        {/* Metric Summary Bar */}
        <div className="metrics-grid">
          <div className="metric-card">
            <div className="metric-icon-box" style={{ background: 'rgba(59, 130, 246, 0.15)', color: '#60a5fa' }}>
              <FolderGit2 size={26} />
            </div>
            <div>
              <div className="metric-value">{metrics.totalProjects || projects.length}</div>
              <div className="metric-label">Total Research Projects</div>
            </div>
          </div>

          <div className="metric-card">
            <div className="metric-icon-box" style={{ background: 'rgba(16, 185, 129, 0.15)', color: '#34d399' }}>
              <DollarSign size={26} />
            </div>
            <div>
              <div className="metric-value">${(metrics.totalFunding / 1000000).toFixed(2)}M</div>
              <div className="metric-label">Active Grant Funding</div>
            </div>
          </div>

          <div className="metric-card">
            <div className="metric-icon-box" style={{ background: 'rgba(139, 92, 246, 0.15)', color: '#c084fc' }}>
              <Database size={26} />
            </div>
            <div>
              <div className="metric-value">{(metrics.totalStorageMb / 1024).toFixed(1)} GB</div>
              <div className="metric-label">Indexed Research Datasets</div>
            </div>
          </div>

          <div className="metric-card">
            <div className="metric-icon-box" style={{ background: 'rgba(245, 158, 11, 0.15)', color: '#fbbf24' }}>
              <BookOpen size={26} />
            </div>
            <div>
              <div className="metric-value">{metrics.totalCitations || 247}</div>
              <div className="metric-label">Total Peer Citations</div>
            </div>
          </div>
        </div>

        {/* Filter Controls Bar */}
        <div className="controls-bar">
          <div className="search-input-box">
            <Search className="search-icon" size={18} />
            <input 
              type="text" 
              placeholder={`Search ${activeTab}...`} 
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
            />
          </div>

          {activeTab === 'projects' && (
            <button className="action-btn" onClick={() => setShowProjectModal(true)}>
              <Plus size={18} /> New Research Project
            </button>
          )}
        </div>

        {/* TAB 1: PROJECTS */}
        {activeTab === 'projects' && (
          <div className="cards-grid">
            {filteredProjects.map((p) => (
              <div key={p.id} className="data-card">
                <div>
                  <div className="card-header">
                    <div className="card-title">{p.title}</div>
                    <span className={`card-badge ${p.status === 'ONGOING' ? 'badge-green' : 'badge-amber'}`}>
                      {p.status}
                    </span>
                  </div>
                  <p className="card-desc">{p.description}</p>
                </div>

                <div>
                  <div style={{ fontSize: '0.82rem', color: '#94a3b8', marginBottom: '0.5rem' }}>
                    <strong>Domain:</strong> {p.domain}
                  </div>
                  <div style={{ fontSize: '0.82rem', color: '#94a3b8', marginBottom: '0.75rem' }}>
                    <strong>Lead Investigator:</strong> {p.principalInvestigator}
                  </div>
                  <div className="card-footer">
                    <span>Grant: <strong>${p.grantAmount?.toLocaleString()}</strong></span>
                    <span>{p.startDate} - {p.endDate || 'Present'}</span>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}

        {/* TAB 2: DATASETS */}
        {activeTab === 'datasets' && (
          <div className="cards-grid">
            {filteredDatasets.map((d) => (
              <div key={d.id} className="data-card">
                <div>
                  <div className="card-header">
                    <div className="card-title">{d.name}</div>
                    <span className={`card-badge ${
                      d.accessLevel === 'PUBLIC' ? 'badge-blue' : 
                      d.accessLevel === 'RESTRICTED' ? 'badge-amber' : 'badge-purple'
                    }`}>
                      {d.accessLevel}
                    </span>
                  </div>
                  <p className="card-desc">{d.description}</p>
                </div>

                <div>
                  <div style={{ display: 'flex', gap: '0.5rem', marginBottom: '0.75rem' }}>
                    <span className="card-badge badge-blue">{d.fileFormat}</span>
                    <span className="card-badge badge-green">{d.sizeMb} MB</span>
                  </div>
                  <div className="card-footer">
                    <span>Uploaded by: {d.uploadedBy}</span>
                    <button className="action-btn" style={{ padding: '0.4rem 0.8rem', fontSize: '0.75rem' }} onClick={() => handleDownload(d.id)}>
                      <Download size={14} /> Download ({d.downloadsCount})
                    </button>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}

        {/* TAB 3: RESEARCHERS DIRECTORY */}
        {activeTab === 'researchers' && (
          <div className="cards-grid">
            {researchers.map((r) => (
              <div key={r.id} className="data-card">
                <div>
                  <div className="card-header">
                    <div className="card-title">{r.name}</div>
                    <span className="card-badge badge-purple">{r.role}</span>
                  </div>
                  <div style={{ fontSize: '0.85rem', color: '#94a3b8', marginTop: '0.5rem' }}>
                    <Building size={14} style={{ verticalAlign: 'middle', marginRight: '4px' }} />
                    {r.department}
                  </div>
                  <div style={{ fontSize: '0.82rem', color: '#64748b', marginTop: '0.25rem' }}>
                    {r.email}
                  </div>
                </div>

                <div style={{ marginTop: '1.25rem' }}>
                  <div className="card-footer">
                    <span>ORCID: <strong style={{ color: '#60a5fa' }}>{r.orcidId}</strong></span>
                    <span>{r.publicationsCount} Publications</span>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </main>

      {/* CREATE PROJECT MODAL */}
      {showProjectModal && (
        <div className="modal-overlay">
          <div className="modal-content">
            <div className="modal-header">
              <h3 style={{ fontSize: '1.2rem', fontWeight: 700 }}>New Research Project</h3>
              <button onClick={() => setShowProjectModal(false)} style={{ background: 'none', border: 'none', color: '#94a3b8', cursor: 'pointer' }}>
                <X size={20} />
              </button>
            </div>

            <form onSubmit={handleCreateProject}>
              <div className="form-group">
                <label>Project Title</label>
                <input required type="text" value={newProject.title} onChange={e => setNewProject({...newProject, title: e.target.value})} placeholder="e.g. Distributed Neural Interface Protocol" />
              </div>

              <div className="form-group">
                <label>Description</label>
                <textarea rows="3" value={newProject.description} onChange={e => setNewProject({...newProject, description: e.target.value})} placeholder="Brief overview of research objectives..." />
              </div>

              <div className="form-row">
                <div className="form-group">
                  <label>Domain</label>
                  <select value={newProject.domain} onChange={e => setNewProject({...newProject, domain: e.target.value})}>
                    <option value="Bioinformatics">Bioinformatics</option>
                    <option value="Cybersecurity">Cybersecurity</option>
                    <option value="Climate Science">Climate Science</option>
                    <option value="AI/ML">AI/ML</option>
                    <option value="Quantum Physics">Quantum Physics</option>
                  </select>
                </div>

                <div className="form-group">
                  <label>Grant Amount ($)</label>
                  <input required type="number" value={newProject.grantAmount} onChange={e => setNewProject({...newProject, grantAmount: e.target.value})} placeholder="500000" />
                </div>
              </div>

              <div className="form-group">
                <label>Principal Investigator</label>
                <input required type="text" value={newProject.principalInvestigator} onChange={e => setNewProject({...newProject, principalInvestigator: e.target.value})} placeholder="Dr. Jane Doe" />
              </div>

              <div className="form-row">
                <div className="form-group">
                  <label>Start Date</label>
                  <input type="date" value={newProject.startDate} onChange={e => setNewProject({...newProject, startDate: e.target.value})} />
                </div>
                <div className="form-group">
                  <label>End Date</label>
                  <input type="date" value={newProject.endDate} onChange={e => setNewProject({...newProject, endDate: e.target.value})} />
                </div>
              </div>

              <div className="modal-actions">
                <button type="button" className="btn-secondary" onClick={() => setShowProjectModal(false)}>Cancel</button>
                <button type="submit" className="action-btn" disabled={isLoading}>
                  {isLoading ? 'Saving...' : 'Save Project'}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* Footer */}
      <footer className="footer">
        DRIMS — Data Research Information Management System &copy; {new Date().getFullYear()}
      </footer>
    </div>
  );
}
