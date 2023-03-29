import React from "react";
import Head from "next/head";
import Layout from "../src/components/Layout";
import type { AppProps } from "next/app";
import { AuthContextProvider } from "../src/context/AuthContext";

export default function MyApp({ Component, pageProps }: AppProps) {
  return (
    <>
      <Head>
        <title>QuizGPT</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      </Head>
      <AuthContextProvider>
        <Layout>
          <Component {...pageProps} />
        </Layout>
      </AuthContextProvider>
    </>
  );
}
