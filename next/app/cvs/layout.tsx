import React from "react";

import type { Metadata } from "next";

export const metadata: Metadata = {
  title: "CVs",
  description: "All CVs"

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
