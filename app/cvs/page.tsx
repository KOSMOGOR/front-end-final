'use client';

import React, { useEffect, useState } from "react";
import Button from "./Button";
import Cv from "./Cv";
import { getCvs, Cv as CvInterface } from "./storageHelper";

export default function Page() {
  const [cvs, setCvs] = useState<CvInterface[]>([]);

  useEffect(() => {
    const storedCvs = getCvs();
    setCvs(storedCvs);
  }, []);

  return (
    <div className="layout">
      <h1>CVs:</h1>
      <div className="vacancies">
        {cvs.map(cv => <Cv key={cv.title} title={cv.title} />)}
      </div>
      <Button />
    </div>
  );
}
