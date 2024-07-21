import React from "react";

import type { Metadata } from "next";
import Header from "./Header";
import "./globals.css";

export const metadata: Metadata = {
  title: "Recruitment helper",
  description: "Recruitment helper app"
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
