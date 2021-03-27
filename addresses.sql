--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

-- Started on 2021-03-25 19:10:41

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
-- TOC entry 200 (class 1259 OID 16666)
-- Name: addresses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.addresses (
    id character varying(255) NOT NULL,
    apartment integer,
    block character(1),
    city character varying(255),
    county character varying(255),
    floor integer,
    nr integer,
    street character varying(255),
    user_id character varying(255)
);


ALTER TABLE public.addresses OWNER TO postgres;

--
-- TOC entry 2993 (class 0 OID 16666)
-- Dependencies: 200
-- Data for Name: addresses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.addresses (id, apartment, block, city, county, floor, nr, street, user_id) FROM stdin;
27ddea2a-7dad-4cb5-a682-fd3bdbb4c114	2	2	cluj	cluj	2	2	observator	a15aeea0-77e9-4252-9a43-74e3e9bd8bbe
b33f53d2-8ab6-4ad2-8af2-3e2895916ffc	2	2	bacau	bacau	2	2	marasesti	a15aeea0-77e9-4252-9a43-74e3e9bd8bbe
83552b76-98e4-4a3d-b398-2c93cda20e52	2	2	barcelona	catalunia	2	10	camp nou	472a35be-2648-4b8f-8add-a85e46e4c6a4
\.


--
-- TOC entry 2861 (class 2606 OID 16673)
-- Name: addresses addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);


--
-- TOC entry 2862 (class 2606 OID 16698)
-- Name: addresses fk9ppppxk94ir6cx6uafnblm9sf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT fk9ppppxk94ir6cx6uafnblm9sf FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


-- Completed on 2021-03-25 19:10:42

--
-- PostgreSQL database dump complete
--

