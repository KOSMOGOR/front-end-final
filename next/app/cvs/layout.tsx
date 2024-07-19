import type { Metadata } from "next";

export const metadata: Metadata = {
  title: "CVs",
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
