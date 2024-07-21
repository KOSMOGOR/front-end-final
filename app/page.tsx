import React from "react";

import AppButton1 from './AppButton1';

export default function Page() {
  return (
    <body>
      <p className="p">Ekaterina Akimenko & Egor Savchenko</p>
      <p className="p">recruitment helper</p>
      <h1>Choose what you need:</h1>
      <div className="main">
        <AppButton1 img='/img_1.png' text='Vacancies' path='/vacancies' />
        <AppButton1 img='/img_2.png' text='Specialists' path='/cvs' />
      </div>
    </body>
  );
}
