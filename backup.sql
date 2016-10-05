--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: admin; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE admin (
    adminid integer NOT NULL,
    first_name character varying(255) NOT NULL,
    hire_date date NOT NULL,
    last_name character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    user_id integer
);


ALTER TABLE admin OWNER TO rush;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: rush
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO rush;

--
-- Name: student; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE student (
    studentid integer NOT NULL,
    first_name character varying(255) NOT NULL,
    grade_level integer NOT NULL,
    last_name character varying(255) NOT NULL,
    student_number character varying(255) NOT NULL,
    user_id integer
);


ALTER TABLE student OWNER TO rush;

--
-- Name: teacher; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE teacher (
    teacherid integer NOT NULL,
    department character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    hire_date date NOT NULL,
    last_name character varying(255) NOT NULL,
    user_id integer
);


ALTER TABLE teacher OWNER TO rush;

--
-- Name: users; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE users (
    id integer NOT NULL,
    disabled boolean NOT NULL,
    email character varying(255) NOT NULL,
    last_login date,
    password character varying(255) NOT NULL,
    role integer
);


ALTER TABLE users OWNER TO rush;

--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY admin (adminid, first_name, hire_date, last_name, title, user_id) FROM stdin;
172	Admin	2016-01-01	Alpha	Principal	171
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: rush
--

SELECT pg_catalog.setval('hibernate_sequence', 182, true);


--
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY student (studentid, first_name, grade_level, last_name, student_number, user_id) FROM stdin;
176	Student	12	Alpha	AB123456	175
178	George	12	Walters	GW123456	177
\.


--
-- Data for Name: teacher; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY teacher (teacherid, department, first_name, hire_date, last_name, user_id) FROM stdin;
174	Science	Teacher	2016-01-01	Alpha	173
180	Science	Andrew	2012-07-15	Nagel	179
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY users (id, disabled, email, last_login, password, role) FROM stdin;
173	f	teacher@fakeschools.org	\N	sha1:64000:18:O6JiL4DnlACaKBJJ5XRhnvhsf7/AUZYJ:IF+dtxn68gEPeJWlfnURwp7V	2
175	f	student@fakeschools.org	\N	sha1:64000:18:N5AmgXrVDIEEBbwgmHKDt3PB2yFkpsux:rvVhnpZIKW/19ZPT1mr9tffG	3
171	f	admin@fakeschools.org	2016-10-05	sha1:64000:18:fp2QIpv4a97d1OdENf3LZtW+Rfmzbfjo:KwFGyleqxHzu8VeYxckr7D+I	1
177	f	gw123456@fakeschools.org	\N	sha1:64000:18:XXUDkhIL3tqJIzW5/L38mzsPmPtZYLHO:fJP+7Q/wkE1G3nXGRaWi4Cmn	3
179	f	anagel@fakeschools.org	\N	sha1:64000:18:eyxRgCZYAu+Svb8MN8K1+GlKX7n/Xynr:6wQKb82APEO9loyrNtseeKGV	2
\.


--
-- Name: admin_pkey; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (adminid);


--
-- Name: student_pkey; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY student
    ADD CONSTRAINT student_pkey PRIMARY KEY (studentid);


--
-- Name: teacher_pkey; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY teacher
    ADD CONSTRAINT teacher_pkey PRIMARY KEY (teacherid);


--
-- Name: uk_6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: fkcp1vpkh4bh0qux9vtvs0fkwrn; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY teacher
    ADD CONSTRAINT fkcp1vpkh4bh0qux9vtvs0fkwrn FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: fkk0thg920a3xk3v59yjbsatw1l; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY student
    ADD CONSTRAINT fkk0thg920a3xk3v59yjbsatw1l FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: fkq7pdkck9je126wpd9ijw3uwml; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY admin
    ADD CONSTRAINT fkq7pdkck9je126wpd9ijw3uwml FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

