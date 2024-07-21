'use client';

import React, { useEffect, useState } from "react";
import { useRouter } from 'next/navigation';
import { getVacancies, Vacancy as VacancyType, deleteVacancy } from "../storageHelper";

export default function VacancyDetail({ params }: { params: { id: string } }) {
  const [vacancy, setVacancy] = useState<VacancyType | null>(null);
  const router = useRouter();
  const { id } = params;

  useEffect(() => {
    // Retrieve the vacancy from localStorage
    const storedVacancies = getVacancies();
    const foundVacancy = storedVacancies.find(vacancy => vacancy.title === id);
    setVacancy(foundVacancy || null);
  }, [id]);

  if (!vacancy) {
    return <p>Vacancy not found</p>;
  }

  const handleDelete = () => {
    deleteVacancy(vacancy.title);
    router.push('/vacancies'); // Navigate back to the list
  };

  return (
    <div className="layout">
      <h1>Vacancy {vacancy.title}</h1>
      <p>{vacancy.message}</p>
      <button onClick={handleDelete}>Delete</button>
    </div>
  );
}
