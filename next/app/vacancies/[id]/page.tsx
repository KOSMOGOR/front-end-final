import styles from "./page.module.css";

export default function Page({ params }: { params: { id: string } }) {
  return (
    <body>
      <div>Vacancy {params.id}</div>
    </body>
  );
}
