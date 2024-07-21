'use client';

import React, { useEffect, useState } from "react";
import { useRouter } from 'next/navigation';
import { getCvs, Cv as CvInterface, deleteCv } from "./storageHelper";

export default function CvDetail({ params }: { params: { title: string } }) {
  const [cv, setCv] = useState<CvInterface | null>(null);
  const router = useRouter();
  const { title } = params;

  useEffect(() => {
    // Fetch the CV from localStorage
    const storedCvs = getCvs();
    const foundCv = storedCvs.find(cv => cv.title === title);
    setCv(foundCv || null);
  }, [title]);

  if (!cv) {
    return <p>CV not found</p>;
  }

  const handleDelete = () => {
    deleteCv(cv.title);
    router.push('/cvs'); // Navigate back to the CV list
  };

  return (
    <div className="cv-detail">
      <h1>{cv.title}</h1>
      <p>Experience: {cv.experience}</p>
      <p>Message: {cv.message}</p>
      <button onClick={handleDelete}>Delete</button>
    </div>
  );
}
