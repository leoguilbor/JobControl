--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5 (Ubuntu 10.5-0ubuntu0.18.04)
-- Dumped by pg_dump version 10.5 (Ubuntu 10.5-0ubuntu0.18.04)

-- Started on 2018-10-27 17:21:23 -03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "jobControl";
--
-- TOC entry 3007 (class 1262 OID 24576)
-- Name: jobControl; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "jobControl" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'pt_BR.UTF-8' LC_CTYPE = 'pt_BR.UTF-8';


ALTER DATABASE "jobControl" OWNER TO postgres;

\connect "jobControl"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 8 (class 2615 OID 24581)
-- Name: auth; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA auth;


ALTER SCHEMA auth OWNER TO postgres;

--
-- TOC entry 10 (class 2615 OID 24682)
-- Name: client; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA client;


ALTER SCHEMA client OWNER TO postgres;

--
-- TOC entry 3 (class 2615 OID 24577)
-- Name: job; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA job;


ALTER SCHEMA job OWNER TO postgres;

--
-- TOC entry 1 (class 3079 OID 13043)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3010 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 202 (class 1259 OID 24606)
-- Name: actions; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE auth.actions (
    id integer NOT NULL,
    name character varying,
    description character varying,
    uri character varying
);


ALTER TABLE auth.actions OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 24604)
-- Name: actions_id_seq; Type: SEQUENCE; Schema: auth; Owner: postgres
--

CREATE SEQUENCE auth.actions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE auth.actions_id_seq OWNER TO postgres;

--
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 201
-- Name: actions_id_seq; Type: SEQUENCE OWNED BY; Schema: auth; Owner: postgres
--

ALTER SEQUENCE auth.actions_id_seq OWNED BY auth.actions.id;


--
-- TOC entry 204 (class 1259 OID 24621)
-- Name: right; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE auth."right" (
    id integer NOT NULL,
    user_id integer,
    action_id integer
);


ALTER TABLE auth."right" OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 24619)
-- Name: right_id_seq; Type: SEQUENCE; Schema: auth; Owner: postgres
--

CREATE SEQUENCE auth.right_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE auth.right_id_seq OWNER TO postgres;

--
-- TOC entry 3012 (class 0 OID 0)
-- Dependencies: 203
-- Name: right_id_seq; Type: SEQUENCE OWNED BY; Schema: auth; Owner: postgres
--

ALTER SEQUENCE auth.right_id_seq OWNED BY auth."right".id;


--
-- TOC entry 200 (class 1259 OID 24593)
-- Name: user; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE auth."user" (
    id integer NOT NULL,
    login character varying(50),
    password character varying,
    name character varying
);


ALTER TABLE auth."user" OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 24591)
-- Name: user_id_seq; Type: SEQUENCE; Schema: auth; Owner: postgres
--

CREATE SEQUENCE auth.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE auth.user_id_seq OWNER TO postgres;

--
-- TOC entry 3013 (class 0 OID 0)
-- Dependencies: 199
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: auth; Owner: postgres
--

ALTER SEQUENCE auth.user_id_seq OWNED BY auth."user".id;


--
-- TOC entry 212 (class 1259 OID 24685)
-- Name: client; Type: TABLE; Schema: client; Owner: postgres
--

CREATE TABLE client.client (
    id integer NOT NULL,
    name character varying,
    address character(1),
    city character varying,
    telephone character varying
);


ALTER TABLE client.client OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 24683)
-- Name: client_id_seq; Type: SEQUENCE; Schema: client; Owner: postgres
--

CREATE SEQUENCE client.client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE client.client_id_seq OWNER TO postgres;

--
-- TOC entry 3014 (class 0 OID 0)
-- Dependencies: 211
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: client; Owner: postgres
--

ALTER SEQUENCE client.client_id_seq OWNED BY client.client.id;


--
-- TOC entry 208 (class 1259 OID 24652)
-- Name: category; Type: TABLE; Schema: job; Owner: postgres
--

CREATE TABLE job.category (
    id integer NOT NULL,
    name character varying,
    description character varying
);


ALTER TABLE job.category OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 24650)
-- Name: category_id_seq; Type: SEQUENCE; Schema: job; Owner: postgres
--

CREATE SEQUENCE job.category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE job.category_id_seq OWNER TO postgres;

--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 207
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: job; Owner: postgres
--

ALTER SEQUENCE job.category_id_seq OWNED BY job.category.id;


--
-- TOC entry 206 (class 1259 OID 24641)
-- Name: job; Type: TABLE; Schema: job; Owner: postgres
--

CREATE TABLE job.job (
    id integer NOT NULL,
    name character varying,
    description character varying,
    service_id integer NOT NULL,
    client_id integer NOT NULL,
    data character varying
);


ALTER TABLE job.job OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 24639)
-- Name: job_id_seq; Type: SEQUENCE; Schema: job; Owner: postgres
--

CREATE SEQUENCE job.job_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE job.job_id_seq OWNER TO postgres;

--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 205
-- Name: job_id_seq; Type: SEQUENCE OWNED BY; Schema: job; Owner: postgres
--

ALTER SEQUENCE job.job_id_seq OWNED BY job.job.id;


--
-- TOC entry 210 (class 1259 OID 24663)
-- Name: service; Type: TABLE; Schema: job; Owner: postgres
--

CREATE TABLE job.service (
    id integer NOT NULL,
    name character varying,
    category_id integer NOT NULL,
    description character varying,
    term integer
);


ALTER TABLE job.service OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 24661)
-- Name: service_id_seq; Type: SEQUENCE; Schema: job; Owner: postgres
--

CREATE SEQUENCE job.service_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE job.service_id_seq OWNER TO postgres;

--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 209
-- Name: service_id_seq; Type: SEQUENCE OWNED BY; Schema: job; Owner: postgres
--

ALTER SEQUENCE job.service_id_seq OWNED BY job.service.id;


--
-- TOC entry 2834 (class 2604 OID 24609)
-- Name: actions id; Type: DEFAULT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.actions ALTER COLUMN id SET DEFAULT nextval('auth.actions_id_seq'::regclass);


--
-- TOC entry 2835 (class 2604 OID 24624)
-- Name: right id; Type: DEFAULT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth."right" ALTER COLUMN id SET DEFAULT nextval('auth.right_id_seq'::regclass);


--
-- TOC entry 2833 (class 2604 OID 24596)
-- Name: user id; Type: DEFAULT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth."user" ALTER COLUMN id SET DEFAULT nextval('auth.user_id_seq'::regclass);


--
-- TOC entry 2839 (class 2604 OID 24688)
-- Name: client id; Type: DEFAULT; Schema: client; Owner: postgres
--

ALTER TABLE ONLY client.client ALTER COLUMN id SET DEFAULT nextval('client.client_id_seq'::regclass);


--
-- TOC entry 2837 (class 2604 OID 24655)
-- Name: category id; Type: DEFAULT; Schema: job; Owner: postgres
--

ALTER TABLE ONLY job.category ALTER COLUMN id SET DEFAULT nextval('job.category_id_seq'::regclass);


--
-- TOC entry 2836 (class 2604 OID 24644)
-- Name: job id; Type: DEFAULT; Schema: job; Owner: postgres
--

ALTER TABLE ONLY job.job ALTER COLUMN id SET DEFAULT nextval('job.job_id_seq'::regclass);


--
-- TOC entry 2838 (class 2604 OID 24666)
-- Name: service id; Type: DEFAULT; Schema: job; Owner: postgres
--

ALTER TABLE ONLY job.service ALTER COLUMN id SET DEFAULT nextval('job.service_id_seq'::regclass);


--
-- TOC entry 2991 (class 0 OID 24606)
-- Dependencies: 202
-- Data for Name: actions; Type: TABLE DATA; Schema: auth; Owner: postgres
--

COPY auth.actions (id, name, description, uri) FROM stdin;
\.


--
-- TOC entry 2993 (class 0 OID 24621)
-- Dependencies: 204
-- Data for Name: right; Type: TABLE DATA; Schema: auth; Owner: postgres
--

COPY auth."right" (id, user_id, action_id) FROM stdin;
\.


--
-- TOC entry 2989 (class 0 OID 24593)
-- Dependencies: 200
-- Data for Name: user; Type: TABLE DATA; Schema: auth; Owner: postgres
--

COPY auth."user" (id, login, password, name) FROM stdin;
1	admin	21232f297a57a5a743894a0e4a801fc3	administrator
\.


--
-- TOC entry 3001 (class 0 OID 24685)
-- Dependencies: 212
-- Data for Name: client; Type: TABLE DATA; Schema: client; Owner: postgres
--

COPY client.client (id, name, address, city, telephone) FROM stdin;
\.


--
-- TOC entry 2997 (class 0 OID 24652)
-- Dependencies: 208
-- Data for Name: category; Type: TABLE DATA; Schema: job; Owner: postgres
--

COPY job.category (id, name, description) FROM stdin;
\.


--
-- TOC entry 2995 (class 0 OID 24641)
-- Dependencies: 206
-- Data for Name: job; Type: TABLE DATA; Schema: job; Owner: postgres
--

COPY job.job (id, name, description, service_id, client_id, data) FROM stdin;
\.


--
-- TOC entry 2999 (class 0 OID 24663)
-- Dependencies: 210
-- Data for Name: service; Type: TABLE DATA; Schema: job; Owner: postgres
--

COPY job.service (id, name, category_id, description, term) FROM stdin;
\.


--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 201
-- Name: actions_id_seq; Type: SEQUENCE SET; Schema: auth; Owner: postgres
--

SELECT pg_catalog.setval('auth.actions_id_seq', 1, false);


--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 203
-- Name: right_id_seq; Type: SEQUENCE SET; Schema: auth; Owner: postgres
--

SELECT pg_catalog.setval('auth.right_id_seq', 1, false);


--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 199
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: auth; Owner: postgres
--

SELECT pg_catalog.setval('auth.user_id_seq', 1, true);


--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 211
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: client; Owner: postgres
--

SELECT pg_catalog.setval('client.client_id_seq', 1, false);


--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 207
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: job; Owner: postgres
--

SELECT pg_catalog.setval('job.category_id_seq', 1, false);


--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 205
-- Name: job_id_seq; Type: SEQUENCE SET; Schema: job; Owner: postgres
--

SELECT pg_catalog.setval('job.job_id_seq', 1, false);


--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 209
-- Name: service_id_seq; Type: SEQUENCE SET; Schema: job; Owner: postgres
--

SELECT pg_catalog.setval('job.service_id_seq', 1, false);


--
-- TOC entry 2845 (class 2606 OID 24614)
-- Name: actions action_id_pk; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.actions
    ADD CONSTRAINT action_id_pk PRIMARY KEY (id);


--
-- TOC entry 2847 (class 2606 OID 24616)
-- Name: actions action_name_un; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.actions
    ADD CONSTRAINT action_name_un UNIQUE (name);


--
-- TOC entry 2849 (class 2606 OID 24618)
-- Name: actions action_uri_un; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.actions
    ADD CONSTRAINT action_uri_un UNIQUE (uri);


--
-- TOC entry 2851 (class 2606 OID 24626)
-- Name: right right_id_PK; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth."right"
    ADD CONSTRAINT "right_id_PK" PRIMARY KEY (id);


--
-- TOC entry 2853 (class 2606 OID 24628)
-- Name: right right_user-action_un; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth."right"
    ADD CONSTRAINT "right_user-action_un" UNIQUE (user_id, action_id);


--
-- TOC entry 2841 (class 2606 OID 24601)
-- Name: user user_id_PK; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth."user"
    ADD CONSTRAINT "user_id_PK" PRIMARY KEY (id);


--
-- TOC entry 2843 (class 2606 OID 24603)
-- Name: user user_login_un; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth."user"
    ADD CONSTRAINT user_login_un UNIQUE (login);


--
-- TOC entry 2861 (class 2606 OID 24693)
-- Name: client client_id_PK; Type: CONSTRAINT; Schema: client; Owner: postgres
--

ALTER TABLE ONLY client.client
    ADD CONSTRAINT "client_id_PK" PRIMARY KEY (id);


--
-- TOC entry 2857 (class 2606 OID 24660)
-- Name: category category_id_PK; Type: CONSTRAINT; Schema: job; Owner: postgres
--

ALTER TABLE ONLY job.category
    ADD CONSTRAINT "category_id_PK" PRIMARY KEY (id);


--
-- TOC entry 2855 (class 2606 OID 24649)
-- Name: job job_id_PK; Type: CONSTRAINT; Schema: job; Owner: postgres
--

ALTER TABLE ONLY job.job
    ADD CONSTRAINT "job_id_PK" PRIMARY KEY (id);


--
-- TOC entry 2859 (class 2606 OID 24671)
-- Name: service service_id_PK; Type: CONSTRAINT; Schema: job; Owner: postgres
--

ALTER TABLE ONLY job.service
    ADD CONSTRAINT "service_id_PK" PRIMARY KEY (id);


--
-- TOC entry 2862 (class 2606 OID 24629)
-- Name: right right_action_FK; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth."right"
    ADD CONSTRAINT "right_action_FK" FOREIGN KEY (action_id) REFERENCES auth.actions(id);


--
-- TOC entry 2863 (class 2606 OID 24634)
-- Name: right right_user_FK; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth."right"
    ADD CONSTRAINT "right_user_FK" FOREIGN KEY (user_id) REFERENCES auth."user"(id);


--
-- TOC entry 2865 (class 2606 OID 24694)
-- Name: job job_client_id_FK; Type: FK CONSTRAINT; Schema: job; Owner: postgres
--

ALTER TABLE ONLY job.job
    ADD CONSTRAINT "job_client_id_FK" FOREIGN KEY (client_id) REFERENCES client.client(id);


--
-- TOC entry 2864 (class 2606 OID 24677)
-- Name: job job_service_id_fkey; Type: FK CONSTRAINT; Schema: job; Owner: postgres
--

ALTER TABLE ONLY job.job
    ADD CONSTRAINT job_service_id_fkey FOREIGN KEY (service_id) REFERENCES job.service(id);


--
-- TOC entry 2866 (class 2606 OID 24672)
-- Name: service service_category_id_FK; Type: FK CONSTRAINT; Schema: job; Owner: postgres
--

ALTER TABLE ONLY job.service
    ADD CONSTRAINT "service_category_id_FK" FOREIGN KEY (category_id) REFERENCES job.category(id);


--
-- TOC entry 3009 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2018-10-27 17:21:23 -03

--
-- PostgreSQL database dump complete
--

