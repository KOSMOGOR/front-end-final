import React from "react";

// import styles from "./page.module.css";

interface CvInt {
  title: string,
  message: string
}

export default async function Page({ params }: { params: { id: string } }) {
  const cvInfo: CvInt = await (await fetch(`http://localhost:8080/getCv?id=${params.id}`, {mode: "no-cors"})).json()
  return (
    <div className="layout">
      <h1>CV {params.id} - {cvInfo.title}</h1>
      <div>{cvInfo.message}</div>
    </div>
  );
}

