import Image from "next/image";
import styles from "./page.module.css";
import AppButton1 from './AppButton1';

export default function Home() {
  return (
    <body>
      <p className="p">team 6</p>
      <p className="p">recruitment helper</p>
      <h1>выберите нужное:</h1>
      <div className="main">
          <AppButton1 img='/public/img_1.png' text='вакансии' path='/home/vacancies' />
          <AppButton1 img='/public/img_2.png' text='специалисты' path='/home/cvs' />
      </div>
    </body>
  );
}
