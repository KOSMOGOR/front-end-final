'use client'

import { useEffect, useState } from "react";
import Button from "./Button";
import styles from "./page.module.css";

interface int1 {
  title: string
}

export default function Page() {
  const [body, setBody] = useState(<div></div>);
  useEffect(() => {
    fetch('http://localhost:8080/getCvs', {
      mode: "no-cors"
    }).then(async a => {
      console.log(a.body)
      const text = await a.text()
      setBody(<div>
        {JSON.parse(text).map((x: int1) => <div key={x.title}>{x.title}</div>)}
      </div>)
    })
  }, [])
  return (
    <body>
      <div>CVs:</div>
      <div>{body}</div>
      <button onClick={() => window.location.href='/cvs/add'}>Добавить</button>
    </body>
  );
}
