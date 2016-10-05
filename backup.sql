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
-- Name: academic_class; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE academic_class (
    classid integer NOT NULL,
    capacity integer NOT NULL,
    department character varying(255) NOT NULL,
    identifier character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    period integer NOT NULL,
    teacherid integer NOT NULL
);


ALTER TABLE academic_class OWNER TO rush;

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
-- Name: assignment; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE assignment (
    assignmentid integer NOT NULL,
    assignmentidnumber character varying(255),
    assignment_name character varying(255),
    date date,
    poss_points integer,
    academic_class_classid integer NOT NULL
);


ALTER TABLE assignment OWNER TO rush;

--
-- Name: grade; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE grade (
    gradeid integer NOT NULL,
    earned_points integer,
    assignment_assignmentid integer NOT NULL,
    student_studentid integer NOT NULL
);


ALTER TABLE grade OWNER TO rush;

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
-- Name: students_classes; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE students_classes (
    classid integer NOT NULL,
    studentid integer NOT NULL
);


ALTER TABLE students_classes OWNER TO rush;

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
-- Data for Name: academic_class; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY academic_class (classid, capacity, department, identifier, name, period, teacherid) FROM stdin;
191	28	Science	PHYS101-1A	Physics 101	1	180
192	28	Math	CALC101-3A	Calculus 101	3	174
\.


--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY admin (adminid, first_name, hire_date, last_name, title, user_id) FROM stdin;
172	Admin	2016-01-01	Alpha	Principal	171
\.


--
-- Data for Name: assignment; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY assignment (assignmentid, assignmentidnumber, assignment_name, date, poss_points, academic_class_classid) FROM stdin;
\.


--
-- Data for Name: grade; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY grade (gradeid, earned_points, assignment_assignmentid, student_studentid) FROM stdin;
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: rush
--

SELECT pg_catalog.setval('hibernate_sequence', 192, true);


--
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY student (studentid, first_name, grade_level, last_name, student_number, user_id) FROM stdin;
188	George	12	Walters	GW123456	187
190	Student	12	Alpha	SA123456	189
\.


--
-- Data for Name: students_classes; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY students_classes (classid, studentid) FROM stdin;
191	188
192	188
192	190
\.


--
-- Data for Name: teacher; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY teacher (teacherid, department, first_name, hire_date, last_name, user_id) FROM stdin;
180	Science	Andrew	2012-07-15	Nagel	179
174	Math	Teacher	2016-01-01	Alpha	173
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY users (id, disabled, email, last_login, password, role) FROM stdin;
171	f	admin@fakeschools.org	2016-10-05	sha1:64000:18:fp2QIpv4a97d1OdENf3LZtW+Rfmzbfjo:KwFGyleqxHzu8VeYxckr7D+I	1
179	f	anagel@fakeschools.org	\N	sha1:64000:18:eyxRgCZYAu+Svb8MN8K1+GlKX7n/Xynr:6wQKb82APEO9loyrNtseeKGV	2
187	f	gw123456@fakeschools.org	\N	sha1:64000:18:fGRTENnyCEoNPbegVCqyqhlDXlBuLgY4:e50JaWyfA/SSvjf2/hOGwE3c	3
189	f	student@fakeschools.org	\N	sha1:64000:18:5/ADOrXvsISztG0eC2LE7DKuz0iSI5VA:TB2Gq0mDvvf/TcPLzymWCU9h	3
173	f	teacher@fakeschools.org	\N	sha1:64000:18:xJNJXsHVXWIafsfmyPodezuUcfOYql34:oc+BIGqPl6xvmFYH/LdCvD0i	2
\.


--
-- Name: academic_class_pkey; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY academic_class
    ADD CONSTRAINT academic_class_pkey PRIMARY KEY (classid);


--
-- Name: admin_pkey; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (adminid);


--
-- Name: assignment_pkey; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY assignment
    ADD CONSTRAINT assignment_pkey PRIMARY KEY (assignmentid);


--
-- Name: grade_pkey; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY grade
    ADD CONSTRAINT grade_pkey PRIMARY KEY (gradeid);


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
-- Name: fk17o9g50f734hj8tfjwyw3q4hj; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY assignment
    ADD CONSTRAINT fk17o9g50f734hj8tfjwyw3q4hj FOREIGN KEY (academic_class_classid) REFERENCES academic_class(classid);


--
-- Name: fk4372wjw1w27eowm2k7w0bb0oy; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY grade
    ADD CONSTRAINT fk4372wjw1w27eowm2k7w0bb0oy FOREIGN KEY (student_studentid) REFERENCES student(studentid);


--
-- Name: fk57x1pfbay25xarw9urs6guixk; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY academic_class
    ADD CONSTRAINT fk57x1pfbay25xarw9urs6guixk FOREIGN KEY (teacherid) REFERENCES teacher(teacherid);


--
-- Name: fkbl8n7it5t570c5jogew3roj7p; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY grade
    ADD CONSTRAINT fkbl8n7it5t570c5jogew3roj7p FOREIGN KEY (assignment_assignmentid) REFERENCES assignment(assignmentid);


--
-- Name: fkcp1vpkh4bh0qux9vtvs0fkwrn; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY teacher
    ADD CONSTRAINT fkcp1vpkh4bh0qux9vtvs0fkwrn FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: fkctouokaom6a8whrnrn0n547q1; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY students_classes
    ADD CONSTRAINT fkctouokaom6a8whrnrn0n547q1 FOREIGN KEY (classid) REFERENCES academic_class(classid);


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
-- Name: fkrw9vm8pt4uhcbl461jjhxo67j; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY students_classes
    ADD CONSTRAINT fkrw9vm8pt4uhcbl461jjhxo67j FOREIGN KEY (studentid) REFERENCES student(studentid);


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

