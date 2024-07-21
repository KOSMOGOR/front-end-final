import React from "react";

import type { Metadata } from "next";
import Header from "../Header";


export const metadata: Metadata = {
  title: "Vacancies",
  description: "All vacancies"
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html>
      <body>
        <Header />
        {children}
      </body>
    </html>
  );
}
