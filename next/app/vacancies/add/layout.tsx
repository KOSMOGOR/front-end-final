import React from "react";

import type { Metadata } from "next";
import "../../globals.css";

export const metadata: Metadata = {
  title: "Add Vacancy",
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
