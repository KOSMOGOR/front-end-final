import React from "react";

import type { Metadata } from "next";
import "../../globals.css";

export const metadata: Metadata = {
  title: "Add Vacancy",
  description: "Here you can dad new vacancy"
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html>
      <body>
        {children}
      </body>
    </html>
  );
}
