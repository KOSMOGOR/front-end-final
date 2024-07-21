'use client';

import React, { useState } from "react";
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
    <div className="form-container">
      <h1>Add CV</h1>
      <form onSubmit={(e) => { e.preventDefault(); sendPost(); }}>
        <div className="form-group">
          <label>Title:</label>
          <input value={title} onChange={(e) => setTitle(e.target.value)} required />
        </div>
        <div className="form-group">
          <label>Experience:</label>
          <input value={experience} onChange={(e) => setExperience(e.target.value)} required />
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
