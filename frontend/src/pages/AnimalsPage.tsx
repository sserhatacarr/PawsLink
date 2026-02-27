import React from 'react';
import { useQuery } from '@tanstack/react-query';
import axios from 'axios';

interface Animal {
  id: number;
  name: string;
  species: string;
  breed?: string;
  status: string;
  latitude?: number;
  longitude?: number;
  locationDescription?: string;
  photoUrl?: string;
  reportedAt: string;
}

const fetchAnimals = async (): Promise<Animal[]> => {
  const { data } = await axios.get('/api/v1/animals');
  return data;
};

const statusColors: Record<string, string> = {
  REPORTED: 'bg-red-100 text-red-800',
  RESCUE_IN_PROGRESS: 'bg-yellow-100 text-yellow-800',
  IN_SHELTER: 'bg-blue-100 text-blue-800',
  ADOPTED: 'bg-green-100 text-green-800',
  DECEASED: 'bg-gray-100 text-gray-800',
};

const AnimalsPage: React.FC = () => {
  const { data: animals, isLoading, isError } = useQuery({
    queryKey: ['animals'],
    queryFn: fetchAnimals,
  });

  if (isLoading) {
    return (
      <div className="flex justify-center items-center h-64">
        <div className="text-gray-500 text-lg">Loading animals...</div>
      </div>
    );
  }

  if (isError) {
    return (
      <div className="flex justify-center items-center h-64">
        <div className="text-red-500 text-lg">Failed to load animals. Is the backend running?</div>
      </div>
    );
  }

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-2xl font-bold text-gray-900">Animals in Need</h2>
        <span className="text-gray-500">{animals?.length ?? 0} records</span>
      </div>

      {animals && animals.length === 0 && (
        <div className="text-center py-16 text-gray-400 text-lg">
          No animals reported yet. Be the first to report!
        </div>
      )}

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        {animals?.map((animal) => (
          <div key={animal.id} className="bg-white rounded-xl shadow-sm border border-gray-100 overflow-hidden">
            {animal.photoUrl && (
              <img
                src={animal.photoUrl}
                alt={animal.name}
                className="w-full h-48 object-cover"
              />
            )}
            <div className="p-4">
              <div className="flex justify-between items-start mb-2">
                <h3 className="font-semibold text-gray-900 text-lg">{animal.name}</h3>
                <span className={`text-xs font-medium px-2 py-1 rounded-full ${statusColors[animal.status] ?? 'bg-gray-100 text-gray-800'}`}>
                  {animal.status.replace(/_/g, ' ')}
                </span>
              </div>
              <p className="text-gray-500 text-sm">
                {animal.species}{animal.breed ? ` · ${animal.breed}` : ''}
              </p>
              {animal.locationDescription && (
                <p className="text-gray-400 text-xs mt-2">📍 {animal.locationDescription}</p>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default AnimalsPage;
