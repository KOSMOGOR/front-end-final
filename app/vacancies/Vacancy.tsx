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
    router.push(`/vacancies/${encodeURIComponent(title)}`);
  };

  return (
    <div className="vacancy" onClick={handleClick}>
      <p>{title}</p>
    </div>
  );
}
