import type { Metadata } from "next";

export const metadata: Metadata = {
  title: "Vacancies",
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
