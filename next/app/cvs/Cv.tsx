'use client'

interface CvInt {
    title: string
}

export default function Cv(props: CvInt) {
    return (
        <div className="vacancy">
            {props.title}
            <button onClick={() => {
                fetch('http://localhost:8080/getCvs', {mode: "no-cors"})
            }}>Удалить</button>
        </div>
    )
}
