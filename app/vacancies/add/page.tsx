'use client'

import React, { useState } from "react";
import { addVacancy } from "../storageHelper";
import { Vacancy as VacancyType } from "../types";

export default function AddVacancyPage() {
  const [title, setTitle] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const newVacancy: VacancyType = { title, message };
    addVacancy(newVacancy);
    // Redirect to vacancies page
    window.location.href = '/vacancies';
  };

  return (
    <div className="form-container">
      <h1>Add Vacancy</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Title:</label>
          <input value={title} onChange={(e) => setTitle(e.target.value)} required />
        </div>
        <div className="form-group">
          <label>Message:</label>
          <textarea value={message} onChange={(e) => setMessage(e.target.value)} required />
        </div>
        <button type="submit">Add</button>
      </form>
    </div>
  );
}
