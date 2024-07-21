'use client';

import React from "react";
import { deleteCv } from "./storageHelper";

interface CvInt {
  title: string;
}

export default function Cv(props: CvInt) {
  return (
    <div className="vacancy">
      <p>{props.title}</p>
      <button onClick={() => {
        deleteCv(props.title);
        window.location.reload();
      }}>
        Удалить
      </button>
    </div>
  );
}
