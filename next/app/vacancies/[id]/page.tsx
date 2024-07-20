import styles from "./page.module.css";

export default function Page({ params }: { params: { id: string } }) {
  return (
    <div className="layout">
      <h1>Vacancy {params.id}</h1>
      {
      }
    </div>
  );
}
