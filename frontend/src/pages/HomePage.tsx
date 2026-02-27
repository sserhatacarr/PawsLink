import React from 'react';
import { Link } from 'react-router-dom';

const HomePage: React.FC = () => {
  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-16">
      <div className="text-center">
        <h1 className="text-5xl font-extrabold text-gray-900 mb-4">
          🐾 Welcome to <span className="text-primary-600">PawsLink</span>
        </h1>
        <p className="text-xl text-gray-600 max-w-2xl mx-auto mb-8">
          Empowering NGOs and volunteers to protect and care for stray animals through
          open-source technology.
        </p>
        <div className="flex justify-center gap-4">
          <Link
            to="/animals"
            className="px-6 py-3 bg-primary-600 text-white rounded-lg font-semibold hover:bg-primary-700 transition"
          >
            View Animals
          </Link>
          <a
            href="https://github.com/sserhatacarr/PawsLink"
            target="_blank"
            rel="noopener noreferrer"
            className="px-6 py-3 border border-gray-300 text-gray-700 rounded-lg font-semibold hover:bg-gray-100 transition"
          >
            GitHub
          </a>
        </div>
      </div>

      <div className="mt-20 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        {[
          { icon: '🗺️', title: 'Location-Based Rescues', desc: 'Track stray animals needing urgent care using geocoding.' },
          { icon: '🏥', title: 'Shelter Coordination', desc: 'Manage shelter capacities and veterinary records.' },
          { icon: '🤝', title: 'Volunteer Matching', desc: 'Connect volunteers with urgent tasks near them.' },
          { icon: '🧠', title: 'AI-Powered Triage', desc: 'Upcoming: Prioritize emergency reports with AI.' },
        ].map((feature) => (
          <div key={feature.title} className="bg-white rounded-xl shadow-sm p-6 border border-gray-100">
            <div className="text-3xl mb-3">{feature.icon}</div>
            <h3 className="font-semibold text-gray-900 mb-1">{feature.title}</h3>
            <p className="text-gray-500 text-sm">{feature.desc}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default HomePage;
