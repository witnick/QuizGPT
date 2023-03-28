import React from "react";
import Head from "next/head";
import Layout from "../src/components/Layout";

export default function MyApp({ Component, pageProps }) {
  return (
    <>
      <Head>
        <title>QuizGPT</title>
        <meta
          name="viewport"
          content="width=device-width, initial-scale=1.0"
        ></meta>
      </Head>
      <Layout>
        <Component {...pageProps} />
      </Layout>
    </>
  );
}
