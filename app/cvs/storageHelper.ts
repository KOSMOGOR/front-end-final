export interface Cv {
    title: string;
    experience: string;
    message: string;
}
  
export const addCv = (cv: Cv) => {
    const existingCvs = JSON.parse(localStorage.getItem('cvs') || '[]') as Cv[];
    existingCvs.push(cv);
    localStorage.setItem('cvs', JSON.stringify(existingCvs));
};
  
export const getCvs = () => {
    return JSON.parse(localStorage.getItem('cvs') || '[]') as Cv[];
};
  
export const deleteCv = (title: string) => {
    const existingCvs = JSON.parse(localStorage.getItem('cvs') || '[]') as Cv[];
    const updatedCvs = existingCvs.filter(cv => cv.title !== title);
    localStorage.setItem('cvs', JSON.stringify(updatedCvs));
};
  