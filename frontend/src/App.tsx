import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import HomePage from './pages/HomePage';
import AnimalsPage from './pages/AnimalsPage';

const App: React.FC = () => {
  return (
    <Router>
      <div className="min-h-screen bg-gray-50">
        <nav className="bg-white shadow-sm border-b border-gray-200">
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div className="flex justify-between h-16 items-center">
              <div className="flex items-center gap-2">
                <span className="text-2xl">🐾</span>
                <span className="text-xl font-bold text-primary-700">PawsLink</span>
              </div>
              <div className="flex gap-6">
                <Link to="/" className="text-gray-600 hover:text-primary-600 font-medium">
                  Home
                </Link>
                <Link to="/animals" className="text-gray-600 hover:text-primary-600 font-medium">
                  Animals
                </Link>
              </div>
            </div>
          </div>
        </nav>
        <main>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/animals" element={<AnimalsPage />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
};

export default App;
