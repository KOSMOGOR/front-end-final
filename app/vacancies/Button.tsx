'use client'

import React from "react";

export default function Button() {
    return (
        <button className="main_link" onClick={() => window.location.href='/vacancies/add'}>Добавить</button>
    )
}