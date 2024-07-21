import React from "react";

import type { Metadata } from "next";
import Header from "../../Header";

export const metadata: Metadata = {
  title: "Add CV",
  description: "Here you can add new CV"
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
