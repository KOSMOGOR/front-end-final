'use client';

import { useState } from "react";
import { addCv } from "../storageHelper";

export default function Page() {
  const [title, setTitle] = useState("");
  const [experience, setExperience] = useState("");
  const [message, setMessage] = useState("");

  const sendPost = () => {
    addCv({ title, experience, message });
    window.location.href = '/cvs';
  };

  return (
    <div className="form">
      <h1>Добавить CV</h1>
      <form onSubmit={(e) => { e.preventDefault(); sendPost(); }}>
        <div>
          <label>Название:</label>
          <input value={title} onChange={(e) => setTitle(e.target.value)} required />
        </div>
        <div>
          <label>Опыт:</label>
          <input value={experience} onChange={(e) => setExperience(e.target.value)} required />
        </div>
        <div>
          <label>Сообщение:</label>
          <input value={message} onChange={(e) => setMessage(e.target.value)} required />
        </div>
        <input type="submit" value="Добавить" />
      </form>
    </div>
  );
}
