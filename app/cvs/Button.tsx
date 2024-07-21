'use client'

import React from "react";

export default function Button() {
    return (
        <button className="add-button" onClick={() => window.location.href='/cvs/add'}>Add new</button>
    )
}
