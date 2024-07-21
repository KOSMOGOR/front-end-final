'use client'

import { useEffect, useState } from "react";
import Button from "./Button";
import Vacancy from "./Vacancy";
import styles from "./page.module.css";

interface int1 {
  title: string
}

export default function Page() {
  const [body, setBody] = useState(<div></div>);
  useEffect(() => {
    fetch('http://localhost:8080/getVacancies', {
      mode: "no-cors"
    }).then(async a => {
      console.log(a.body)
      const text = await a.text()
      setBody(<div>
        {JSON.parse(text).map((x: int1) => <Vacancy key={x.title} title={x.title}/>)}
      </div>)
    })
  }, [])
  return (
    <div className="layout">
      <h1>CVs:</h1>
      <div>{body}</div>
      <Button />
    </div>
  );
}
