'use client'

interface VacancyInt {
    title: string
}

export default function Vacancy(props: VacancyInt) {
    return (
        <div className="vacancy">
            {props.title}
            <button onClick={() => {
                fetch('http://localhost:8080/getVacancies', {mode: "no-cors"})
            }}>Удалить</button>
        </div>
    )
}
