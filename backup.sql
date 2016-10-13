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
    teacherid integer NOT NULL,
    class_sum_earned_points double precision,
    class_sum_poss_points double precision
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
    assignmentidnumber character varying(255) NOT NULL,
    assignment_name character varying(255) NOT NULL,
    date date NOT NULL,
    note character varying(255)
);


ALTER TABLE assignment OWNER TO rush;

--
-- Name: grade; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE grade (
    gradeid integer NOT NULL,
    date_created date,
    date_modified date,
    earned_points integer,
    poss_points integer NOT NULL,
    academic_class_classid integer NOT NULL,
    assignment_assignmentid integer,
    student_studentid integer,
    CONSTRAINT grade_poss_points_check CHECK ((poss_points >= 0))
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
-- Name: photo; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE photo (
    photoid integer NOT NULL,
    data bytea,
    file_name character varying(255)
);


ALTER TABLE photo OWNER TO rush;

--
-- Name: school; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE school (
    schoolid integer NOT NULL,
    address character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    district character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    phone character varying(255) NOT NULL,
    school_name character varying(255) NOT NULL,
    state character varying(255) NOT NULL,
    zip_code character varying(255) NOT NULL,
    photo_photoid integer,
    photo character varying(255)
);


ALTER TABLE school OWNER TO rush;

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
-- Data for Name: academic_class; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY academic_class (classid, capacity, department, identifier, name, period, teacherid, class_sum_earned_points, class_sum_poss_points) FROM stdin;
203	32	Science	PHYS101-4A	Physics 101	4	180	\N	\N
230	32	Math	ALGB101-6A	Algebra 1	6	174	\N	\N
191	32	Science	PHYS101-1A	Physics 101	1	180	\N	\N
229	32	Math	CALC101-1A	Calculus 101	1	174	\N	\N
193	32	Science	PHYS101-2A	Physics 101	2	180	\N	\N
192	32	Math	CALC101-3A	Calculus 101	3	174	\N	\N
194	24	Science	CHEM101-6A	Chemistry 101	6	180	\N	\N
\.


--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY admin (adminid, first_name, hire_date, last_name, title, user_id) FROM stdin;
378	Felicity	2015-01-01	Scott	Principal	377
172	Admin	2016-01-01	Alpha	Counselor	171
\.


--
-- Data for Name: assignment; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY assignment (assignmentid, assignmentidnumber, assignment_name, date, note) FROM stdin;
368	HW1	Intro to Physics	2016-09-06	\N
373	HW1	Intro to Calculus	2016-09-06	Lame.
\.


--
-- Data for Name: grade; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY grade (gradeid, date_created, date_modified, earned_points, poss_points, academic_class_classid, assignment_assignmentid, student_studentid) FROM stdin;
363	2016-10-12	\N	\N	0	191	\N	188
364	2016-10-12	\N	\N	0	191	\N	190
365	2016-10-12	\N	\N	0	194	\N	190
366	2016-10-12	\N	\N	0	192	\N	188
367	2016-10-12	\N	\N	0	192	\N	190
369	2016-10-12	\N	\N	10	191	368	188
371	2016-10-12	\N	\N	10	191	368	\N
370	2016-10-12	\N	10	10	191	368	190
376	2016-10-12	\N	\N	10	192	373	\N
375	2016-10-12	\N	10	10	192	373	190
374	2016-10-12	\N	\N	10	192	373	188
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: rush
--

SELECT pg_catalog.setval('hibernate_sequence', 383, true);


--
-- Data for Name: photo; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY photo (photoid, data, file_name) FROM stdin;
\.


--
-- Data for Name: school; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY school (schoolid, address, city, district, email, phone, school_name, state, zip_code, photo_photoid, photo) FROM stdin;
0	981 S. Emerson Steet	Cary	Fake County Public Schools	superioroffice@fakeschools.org	(919)867-5309	Superior High School	NC	27519	\N	\N
\.


--
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY student (studentid, first_name, grade_level, last_name, student_number, user_id) FROM stdin;
188	George	12	Walters	GW123456	187
190	Student	12	Alpha	SA123456	189
380	Billy	5	Madison	BM123456	379
\.


--
-- Data for Name: teacher; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY teacher (teacherid, department, first_name, hire_date, last_name, user_id) FROM stdin;
180	Science	Andrew	2012-07-15	Nagel	179
174	Math	Teacher	2016-01-01	Alpha	173
382	Technology	Jimmy	2014-06-27	Bush	381
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY users (id, disabled, email, last_login, password, role) FROM stdin;
189	f	student@fakeschools.org	\N	sha1:64000:18:5/ADOrXvsISztG0eC2LE7DKuz0iSI5VA:TB2Gq0mDvvf/TcPLzymWCU9h	3
179	f	anagel@fakeschools.org	2016-10-12	sha1:64000:18:uzpMcF/wt3Ps5ofgezhj1+2vVmrYRRZN:CeywgbepwQUxM3LypOqjfUvB	2
377	f	fscott@fakeschools.org	2016-10-12	sha1:64000:18:sUbFXMJ6bzmXCTXv0jVbV8m3HXIUvuDP:BBnwLSu8wqpk/4R+cyudS0Dk	1
379	f	bm123456@fakeschools.org	\N	sha1:64000:18:/NU/SMe5W2TbDXa1lH5EdUimLkcodzZd:BkcPpxi6Umr372nPudF5Us11	3
381	f	jbush@fakeschools.org	\N	sha1:64000:18:lWTJSr8hXrUQ8FaeCxNdHKLPySQ6wiRg:V14eCu5QKdgL6pPSxt60a+dC	2
171	f	admin@fakeschools.org	2016-10-13	sha1:64000:18:O2OvxioADWUbMi62wFc0+f4BIofjRxwb:x56RGybyV0K5TvXF/eTTVnN3	1
173	f	teacher@fakeschools.org	2016-10-13	sha1:64000:18:xJNJXsHVXWIafsfmyPodezuUcfOYql34:oc+BIGqPl6xvmFYH/LdCvD0i	2
187	f	gw123456@fakeschools.org	2016-10-13	sha1:64000:18:fJwJuwKKNKeoygZoN1HdCfJb8Ei/WZ8i:+cKqFWN3r7kWWo6qgbCWeWwU	3
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
-- Name: photo_pkey; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY photo
    ADD CONSTRAINT photo_pkey PRIMARY KEY (photoid);


--
-- Name: school_pkey; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY school
    ADD CONSTRAINT school_pkey PRIMARY KEY (schoolid);


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
-- Name: fkfx1s5ej7gvw9vagtf44joisqi; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY grade
    ADD CONSTRAINT fkfx1s5ej7gvw9vagtf44joisqi FOREIGN KEY (academic_class_classid) REFERENCES academic_class(classid);


--
-- Name: fkk0thg920a3xk3v59yjbsatw1l; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY student
    ADD CONSTRAINT fkk0thg920a3xk3v59yjbsatw1l FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: fknmb2otq7t3ok3pm89vnhtixg3; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY school
    ADD CONSTRAINT fknmb2otq7t3ok3pm89vnhtixg3 FOREIGN KEY (photo_photoid) REFERENCES photo(photoid);


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

