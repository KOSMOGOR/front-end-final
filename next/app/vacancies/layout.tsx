import React from "react";

import type { Metadata } from "next";

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
        {children}
      </body>
    </html>
  );
}
