export interface Vacancy {
    title: string;
    message: string;
}
  
export const addVacancy = (vacancy: Vacancy) => {
    const existingVacancies = JSON.parse(localStorage.getItem('vacancies') || '[]') as Vacancy[];
    existingVacancies.push(vacancy);
    localStorage.setItem('vacancies', JSON.stringify(existingVacancies));
};
  
export const getVacancies = () => {
    return JSON.parse(localStorage.getItem('vacancies') || '[]') as Vacancy[];
};
  
export const deleteVacancy = (title: string) => {
    const existingVacancies = JSON.parse(localStorage.getItem('vacancies') || '[]') as Vacancy[];
    const updatedVacancies = existingVacancies.filter(vacancy => vacancy.title !== title);
    localStorage.setItem('vacancies', JSON.stringify(updatedVacancies));
};
  