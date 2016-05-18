--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

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
-- Name: bands; Type: TABLE; Schema: public; Owner: Liam; Tablespace: 
--

CREATE TABLE bands (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE bands OWNER TO "Liam";

--
-- Name: bands_id_seq; Type: SEQUENCE; Schema: public; Owner: Liam
--

CREATE SEQUENCE bands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bands_id_seq OWNER TO "Liam";

--
-- Name: bands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Liam
--

ALTER SEQUENCE bands_id_seq OWNED BY bands.id;


--
-- Name: concerts; Type: TABLE; Schema: public; Owner: Liam; Tablespace: 
--

CREATE TABLE concerts (
    id integer NOT NULL,
    band_id integer,
    venue_id integer
);


ALTER TABLE concerts OWNER TO "Liam";

--
-- Name: concerts_id_seq; Type: SEQUENCE; Schema: public; Owner: Liam
--

CREATE SEQUENCE concerts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE concerts_id_seq OWNER TO "Liam";

--
-- Name: concerts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Liam
--

ALTER SEQUENCE concerts_id_seq OWNED BY concerts.id;


--
-- Name: venues; Type: TABLE; Schema: public; Owner: Liam; Tablespace: 
--

CREATE TABLE venues (
    id integer NOT NULL,
    name character varying,
    city character varying
);


ALTER TABLE venues OWNER TO "Liam";

--
-- Name: venues_id_seq; Type: SEQUENCE; Schema: public; Owner: Liam
--

CREATE SEQUENCE venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE venues_id_seq OWNER TO "Liam";

--
-- Name: venues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Liam
--

ALTER SEQUENCE venues_id_seq OWNED BY venues.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Liam
--

ALTER TABLE ONLY bands ALTER COLUMN id SET DEFAULT nextval('bands_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Liam
--

ALTER TABLE ONLY concerts ALTER COLUMN id SET DEFAULT nextval('concerts_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Liam
--

ALTER TABLE ONLY venues ALTER COLUMN id SET DEFAULT nextval('venues_id_seq'::regclass);


--
-- Data for Name: bands; Type: TABLE DATA; Schema: public; Owner: Liam
--

COPY bands (id, name) FROM stdin;
\.


--
-- Name: bands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Liam
--

SELECT pg_catalog.setval('bands_id_seq', 64, true);


--
-- Data for Name: concerts; Type: TABLE DATA; Schema: public; Owner: Liam
--

COPY concerts (id, band_id, venue_id) FROM stdin;
\.


--
-- Name: concerts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Liam
--

SELECT pg_catalog.setval('concerts_id_seq', 62, true);


--
-- Data for Name: venues; Type: TABLE DATA; Schema: public; Owner: Liam
--

COPY venues (id, name, city) FROM stdin;
\.


--
-- Name: venues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Liam
--

SELECT pg_catalog.setval('venues_id_seq', 67, true);


--
-- Name: bands_pkey; Type: CONSTRAINT; Schema: public; Owner: Liam; Tablespace: 
--

ALTER TABLE ONLY bands
    ADD CONSTRAINT bands_pkey PRIMARY KEY (id);


--
-- Name: concerts_pkey; Type: CONSTRAINT; Schema: public; Owner: Liam; Tablespace: 
--

ALTER TABLE ONLY concerts
    ADD CONSTRAINT concerts_pkey PRIMARY KEY (id);


--
-- Name: venues_pkey; Type: CONSTRAINT; Schema: public; Owner: Liam; Tablespace: 
--

ALTER TABLE ONLY venues
    ADD CONSTRAINT venues_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: Liam
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM "Liam";
GRANT ALL ON SCHEMA public TO "Liam";
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

