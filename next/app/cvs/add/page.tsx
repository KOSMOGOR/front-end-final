'use client'

import { useState } from "react";
import styles from "./page.module.css";

export default function Page() {
  const [title, setTitle] = useState("");
  const [experience, setExperience] = useState("");
  const [message, setMessage] = useState("");

  const sendPost = () => {
    fetch('http://localhost:8080/addCv', {
      method: 'POST',
      body: JSON.stringify({
        title,
        experience,
        message,
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
    // window.location.href = '/cvs'
  }

  return (
    <body>
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
        <input type="submit" value="Добавить" />
      </form>
    </body>
  );
}
