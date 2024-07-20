import AppButton1 from './AppButton1';

export default function Page() {
  return (
    <body>
      <p className="p">team 6</p>
      <p className="p">recruitment helper</p>
      <h1>выберите нужное:</h1>
      <div className="main">
        <AppButton1 img='/img_1.png' text='вакансии' path='/vacancies' />
        <AppButton1 img='/img_2.png' text='специалисты' path='/cvs' />
      </div>
    </body>
  );
}
