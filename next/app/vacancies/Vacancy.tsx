'use client';

import React from "react";
import { deleteVacancy } from "./storageHelper";

interface VacancyInt {
  title: string;
}

export default function Vacancy(props: VacancyInt) {
  return (
    <div className="vacancy">
      <p>{props.title}</p>
      <button onClick={() => {
        deleteVacancy(props.title);
        window.location.reload();
      }}>
        Удалить
      </button>
    </div>
  );
}
