'use client';

import React, { useEffect, useState } from "react";
import { useRouter } from 'next/navigation';
import { getCvs, Cv as CvInterface, deleteCv } from "../storageHelper";

export default function CvDetail({ params }: { params: { id: string } }) {
  const [cv, setCv] = useState<CvInterface | null>(null);
  const router = useRouter();
  const { id } = params;

  useEffect(() => {
    // Retrieve the CV from localStorage
    const storedCvs = getCvs();
    const foundCv = storedCvs.find(cv => cv.title === id);
    setCv(foundCv || null);
  }, [id]);

  if (!cv) {
    return <p>CV not found</p>;
  }

  const handleDelete = () => {
    deleteCv(cv.title);
    router.push('/cvs'); // Navigate back to the CV list
  };

  return (
    <div className="layout">
      <h1>CV {cv.title}</h1>
      <p>{cv.experience}</p>
      <p>{cv.message}</p>
      <button onClick={handleDelete}>Delete</button>
    </div>
  );
}
