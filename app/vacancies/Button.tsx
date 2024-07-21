'use client'

import React from "react";

export default function Button() {
    return (
        <button className="add-button" onClick={() => window.location.href='/vacancies/add'}>Add new</button>
    )
}
