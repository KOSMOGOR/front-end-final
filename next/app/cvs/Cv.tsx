'use client';

import React from "react";
import { useRouter } from 'next/navigation';

interface CvInt {
  title: string;
}

export default function Cv(props: CvInt) {
  const router = useRouter();

  const handleClick = () => {
    router.push(`/cvs/${props.title}`); // Navigate to the CV details page
  };

  return (
    <div className="vacancy" onClick={handleClick}>
      <p>{props.title}</p>
      <button onClick={(e) => {
        e.stopPropagation(); // Prevent event bubbling
        handleClick();
      }}>
        View Details
      </button>
    </div>
  );
}
