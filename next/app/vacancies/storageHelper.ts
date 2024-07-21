import { Vacancy } from "./types";

export const getVacancies = (): Vacancy[] => {
  const vacancies = localStorage.getItem('vacancies');
  return vacancies ? JSON.parse(vacancies) : [];
};

export const addVacancy = (vacancy: Vacancy) => {
  const vacancies = getVacancies();
  vacancies.push(vacancy);
  localStorage.setItem('vacancies', JSON.stringify(vacancies));
};

export const deleteVacancy = (title: string) => {
  const vacancies = getVacancies().filter((vacancy) => vacancy.title !== title);
  localStorage.setItem('vacancies', JSON.stringify(vacancies));
};
