'use client';

import React, { useEffect, useState } from "react";
import { useRouter } from 'next/navigation';
import { getVacancies, Vacancy as VacancyType, deleteVacancy } from "../storageHelper";

export default function VacancyDetail({ params }: { params: { id: string } }) {
  const [vacancy, setVacancy] = useState<VacancyType | null>(null);
  const router = useRouter();
  const { id } = params;
  const decodedId = decodeURIComponent(id);

  useEffect(() => {
    const storedVacancies = getVacancies();
    const foundVacancy = storedVacancies.find(vacancy => vacancy.title === decodedId);
    setVacancy(foundVacancy || null);
  }, [decodedId]);

  if (!vacancy) {
    return <p>Vacancy not found</p>;
  }

  const handleDelete = () => {
    deleteVacancy(vacancy.title);
    router.push('/vacancies');
  };

  return (
    <div className="layout">
      <h1>Vacancy {vacancy.title}</h1>
      <p>{vacancy.message}</p>
      <button className="delete-button" onClick={handleDelete}>Delete</button>
    </div>
  );
}
