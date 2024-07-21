'use client';

import React from 'react';
import Link from 'next/link';

export default function Header() {
  return (
    <header className="header">
      <nav>
        <ul className="nav-list">
          <li><Link href="/">Home</Link></li>
          <li><Link href="/vacancies">Vacancies</Link></li>
          <li><Link href="/cvs">CVs</Link></li>
          <li><Link href="/about">About</Link></li>
        </ul>
      </nav>
    </header>
  );
}
