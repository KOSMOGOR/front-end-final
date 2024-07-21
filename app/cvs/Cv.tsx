'use client';

import React from "react";
import { useRouter } from 'next/navigation';

interface CvInt {
  title: string;
}

export default function Cv({ title }: CvInt) {
  const router = useRouter();

  const handleClick = () => {
    router.push(`/cvs/${encodeURIComponent(title)}`);
  };

  return (
    <div className="vacancy" onClick={handleClick}>
      <p>{title}</p>
    </div>
  );
}
