import React from "react";

// import styles from "./page.module.css";

interface VacancyInt {
  title: string,
  message: string
}

export default async function Page({ params }: { params: { id: string } }) {
  const vacancyInfo: VacancyInt = await (await fetch(`http://localhost:8080/getVacancy?id=${params.id}`, {mode: "no-cors"})).json()
  return (
    <div className="layout">
      <h1>Vacancy {params.id} - {vacancyInfo.title}</h1>
      <div>{vacancyInfo.message}</div>
    </div>
  );
}

