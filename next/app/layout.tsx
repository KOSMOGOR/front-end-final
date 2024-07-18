import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";

export const metadata: Metadata = {
  title: "Recruitment helper",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html>
      {children}
    </html>
  );
}
