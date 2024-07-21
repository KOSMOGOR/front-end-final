'use client';

import React, { useEffect, useState } from "react";
import Button from "./Button";
import Vacancy from "./Vacancy";
import { getVacancies } from "./storageHelper";
import { Vacancy as VacancyType } from "./types";

export default function Page() {
  const [vacancies, setVacancies] = useState<VacancyType[]>([]);

  useEffect(() => {
    const storedVacancies = getVacancies();
    console.log("Loaded vacancies:", storedVacancies);
    setVacancies(storedVacancies);
  }, []);

  return (
    <div className="layout">
      <h1>Vacancies:</h1>
      <div className="vacancies">
        {vacancies.map((vacancy) => (
          <Vacancy key={vacancy.title} title={vacancy.title} />
        ))}
      </div>
      <Button />
    </div>
  );
}
