'use client'

import React from "react";

import { useState } from "react";
// import styles from "./page.module.css";

export default function Page() {
  const [title, setTitle] = useState("");
  const [experience, setExperience] = useState("");
  const [message, setMessage] = useState("");
  const [salary, setSalary] = useState("");

  const sendPost = () => {
    if (isNaN(Number(experience))) {
      alert('Experience shoud be a number')
      return;
    }
    if (isNaN(Number(salary))) {
      alert('Salary shoud be a number')
      return;
    }
    fetch('http://localhost:8080/addVacancy', {
      method: 'POST',
      body: JSON.stringify({
        title,
        experience,
        message,
        salary,
        chat_link: '...',
        message_link: '...',
        fromUser_link: '...',
        // chatId: '...'
      }),
      headers: {
        "content-type": "application/json",
        // "Bot-Key": "password"
      },
    }).catch(e => console.error(e))
    // window.location.href = '/vacancies'
  }

  return (
    <div className="layout">
      <form onSubmit={(e) => { e.preventDefault(); sendPost() }}>
        <div>
          <label>Название:</label>
          <input value={title} onChange={(e) => setTitle(e.target.value)} required/>
        </div>
        <div>
          <label>Опыт:</label>
          <input value={experience} onChange={(e) => setExperience(e.target.value)} required/>
        </div>
        <div>
          <label>Сообщение:</label>
          <input value={message} onChange={(e) => setMessage(e.target.value)} required/>
        </div>
        <div>
          <label>Зарплата:</label>
          <input value={salary} onChange={(e) => setSalary(e.target.value)} required/>
        </div>
        <input type="submit" value="Добавить" />
      </form>
    </div>
  );
}
