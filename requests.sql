--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

-- Started on 2021-03-25 19:11:15

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
-- TOC entry 202 (class 1259 OID 16682)
-- Name: requests; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.requests (
    id character varying(255) NOT NULL,
    approved character varying(255),
    remainingrequests integer,
    requesttime timestamp without time zone,
    address_id character varying(255),
    document_id character varying(255)
);


ALTER TABLE public.requests OWNER TO postgres;

--
-- TOC entry 2994 (class 0 OID 16682)
-- Dependencies: 202
-- Data for Name: requests; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.requests (id, approved, remainingrequests, requesttime, address_id, document_id) FROM stdin;
efbab214-1a82-496f-86dc-23f23f798f4b	Pending	2	2021-03-25 18:08:33.249	27ddea2a-7dad-4cb5-a682-fd3bdbb4c114	3f2558bd-c578-4853-82f1-3796f00ad584
5935f495-b988-45a4-b39d-bd9bfa1f3e21	Pending	2	2021-03-25 18:08:36.642	27ddea2a-7dad-4cb5-a682-fd3bdbb4c114	3da9bf3a-f98b-4987-8f58-fd99c143c596
3a7e39cd-229b-40dc-a28f-2bf3f2ec83b3	Pending	2	2021-03-25 18:08:41.267	b33f53d2-8ab6-4ad2-8af2-3e2895916ffc	6ee47b71-6cca-41cc-8f5f-945475e78604
4201448d-41aa-4b59-8486-150f0feed7af	Pending	2	2021-03-25 18:28:07.815	83552b76-98e4-4a3d-b398-2c93cda20e52	3da9bf3a-f98b-4987-8f58-fd99c143c596
7a34111d-50bf-4aed-a4ee-316c7314519a	Approved	2	2021-03-25 18:10:52.581	83552b76-98e4-4a3d-b398-2c93cda20e52	6ee47b71-6cca-41cc-8f5f-945475e78604
\.


--
-- TOC entry 2861 (class 2606 OID 16689)
-- Name: requests requests_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.requests
    ADD CONSTRAINT requests_pkey PRIMARY KEY (id);


--
-- TOC entry 2863 (class 2606 OID 16708)
-- Name: requests fknh268dkqurpshvh1o3bkhdw6k; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.requests
    ADD CONSTRAINT fknh268dkqurpshvh1o3bkhdw6k FOREIGN KEY (document_id) REFERENCES public.documents(id);


--
-- TOC entry 2862 (class 2606 OID 16703)
-- Name: requests fkrafrdii3gvnwhxlyi7om7suej; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.requests
    ADD CONSTRAINT fkrafrdii3gvnwhxlyi7om7suej FOREIGN KEY (address_id) REFERENCES public.addresses(id);


-- Completed on 2021-03-25 19:11:15

--
-- PostgreSQL database dump complete
--

