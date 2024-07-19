import type { Metadata } from "next";
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
