import React from "react";

import type { Metadata } from "next";

export const metadata: Metadata = {
  title: "Add CV",
  description: "Here you can dad new CV"
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
