'use client';

import React from "react";
import { useRouter } from 'next/navigation';
import { deleteVacancy } from "./storageHelper";

interface VacancyInt {
  title: string;
}

export default function Vacancy({ title }: VacancyInt) {
  const router = useRouter();

  const handleClick = () => {
    router.push(`/vacancies/${title}`);
  };

  const handleDelete = (e: React.MouseEvent) => {
    e.stopPropagation(); // Prevent event bubbling
    deleteVacancy(title);
    router.push('/vacancies'); // Navigate back to the list
  };

  return (
    <div className="vacancy" onClick={handleClick}>
      <p>{title}</p>
      <button onClick={handleClick}>
        View Details
      </button>
    </div>
  );
}
