'use client'

import React from "react";

interface ButtonInterface {
    img: string,
    text: string,
    path: string
}

export default function AppButton1(props: ButtonInterface) {
    return (
        <div className="main_link" onClick={() => window.location.href=props.path}>
              <p>{props.text}</p>
              <img src={props.img}/>
        </div>
    )
}