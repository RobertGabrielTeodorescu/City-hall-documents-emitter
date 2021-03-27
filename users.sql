--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

-- Started on 2021-03-25 19:11:28

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
-- TOC entry 203 (class 1259 OID 16690)
-- Name: user_entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_entity (
    id character varying(255) NOT NULL,
    email character varying(255),
    firstname character varying(255),
    lastname character varying(255),
    password character varying(255),
    status character varying(255)
);


ALTER TABLE public.user_entity OWNER TO postgres;

--
-- TOC entry 2992 (class 0 OID 16690)
-- Dependencies: 203
-- Data for Name: user_entity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_entity (id, email, firstname, lastname, password, status) FROM stdin;
a15aeea0-77e9-4252-9a43-74e3e9bd8bbe	robert@gmail.com	robert	gabriel	1qa2ws3ed	user
472a35be-2648-4b8f-8add-a85e46e4c6a4	messi@gmail.com	leo	messi	barcelona	user
d3d7db25-0be1-4f0e-833d-a87c32353cc4	ronaldo@gmail.com	cristiano	ronaldo	juventus	user
064a2485-381e-4c06-84cd-44b582e62fb1	admin@gmail.com	admin	admin	administrator	admin
\.


--
-- TOC entry 2861 (class 2606 OID 16697)
-- Name: user_entity user_entity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT user_entity_pkey PRIMARY KEY (id);


-- Completed on 2021-03-25 19:11:29

--
-- PostgreSQL database dump complete
--

