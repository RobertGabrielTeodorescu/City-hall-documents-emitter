--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

-- Started on 2021-03-25 19:11:03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 16674)
-- Name: documents; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.documents (
    id character varying(255) NOT NULL,
    tip character varying(255)
);


ALTER TABLE public.documents OWNER TO postgres;

--
-- TOC entry 2992 (class 0 OID 16674)
-- Dependencies: 201
-- Data for Name: documents; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.documents (id, tip) FROM stdin;
3f2558bd-c578-4853-82f1-3796f00ad584	document1
3da9bf3a-f98b-4987-8f58-fd99c143c596	document2
6ee47b71-6cca-41cc-8f5f-945475e78604	document3
\.


--
-- TOC entry 2861 (class 2606 OID 16681)
-- Name: documents documents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.documents
    ADD CONSTRAINT documents_pkey PRIMARY KEY (id);


-- Completed on 2021-03-25 19:11:03

--
-- PostgreSQL database dump complete
--

