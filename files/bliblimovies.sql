--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.4
-- Dumped by pg_dump version 9.6.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE account (
    username character varying(20) NOT NULL,
    storeusername character varying(20),
    password character varying(20),
    type character varying(10),
    status boolean DEFAULT true
);


ALTER TABLE account OWNER TO postgres;

--
-- Name: film; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE film (
    id character varying(20) NOT NULL,
    storeusername character varying(20),
    cover character varying(150),
    title character varying(50),
    genre character varying(15),
    duration integer,
    director character varying(50),
    rating double precision,
    reviewtotal integer,
    starttime timestamp without time zone,
    endtime timestamp without time zone,
    language character varying(20),
    subtitle character varying(20),
    actor character varying(50),
    synopsis text,
    status boolean DEFAULT true
);


ALTER TABLE film OWNER TO postgres;

--
-- Name: film_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE film_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE film_id_seq OWNER TO postgres;

--
-- Name: film_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE film_id_seq OWNED BY film.id;


--
-- Name: filmticket; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE filmticket (
    id character varying(20) NOT NULL,
    filmid character varying(20),
    studioid character varying(20),
    seatnumber character varying(5),
    screeningid character varying(20),
    price integer
);


ALTER TABLE filmticket OWNER TO postgres;

--
-- Name: foodandbeverages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE foodandbeverages (
    id character varying(20) NOT NULL,
    storeusername character varying(20),
    cover character varying(150),
    name character varying(50),
    type character varying(20),
    size character varying(10),
    price integer
);


ALTER TABLE foodandbeverages OWNER TO postgres;

--
-- Name: foodandbeverages_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE foodandbeverages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE foodandbeverages_id_seq OWNER TO postgres;

--
-- Name: foodandbeverages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE foodandbeverages_id_seq OWNED BY foodandbeverages.id;


--
-- Name: invoice; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE invoice (
    id character varying(20) NOT NULL,
    memberid character varying(20),
    accountusername character varying(20),
    storeusername character varying(20),
    promoid character varying(10),
    orderdate timestamp without time zone,
    totalprice integer
);


ALTER TABLE invoice OWNER TO postgres;

--
-- Name: invoice_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE invoice_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE invoice_id_seq OWNER TO postgres;

--
-- Name: invoice_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE invoice_id_seq OWNED BY invoice.id;


--
-- Name: ledger; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ledger (
    id character varying(20) NOT NULL,
    invoiceid character varying(20),
    orderdetailid character varying(20),
    memberid character varying(20),
    storeusername character varying(20),
    date timestamp without time zone,
    itemname character varying(30),
    quantity integer,
    price integer
);


ALTER TABLE ledger OWNER TO postgres;

--
-- Name: membercard; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE membercard (
    id character varying(20) NOT NULL,
    storeusername character varying(20),
    fullname character varying(50),
    gender character varying(8),
    birthdate timestamp without time zone,
    phonenumber character varying(15),
    email character varying(30),
    status boolean DEFAULT true
);


ALTER TABLE membercard OWNER TO postgres;

--
-- Name: membercard_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE membercard_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE membercard_id_seq OWNER TO postgres;

--
-- Name: membercard_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE membercard_id_seq OWNED BY membercard.id;


--
-- Name: orderdetail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE orderdetail (
    id character varying(20) NOT NULL,
    invoiceid character varying(20),
    storeusername character varying(20),
    itemname character varying(30),
    quantity integer,
    price integer,
    discountstatus character varying(10)
);


ALTER TABLE orderdetail OWNER TO postgres;

--
-- Name: orderdetail_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE orderdetail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE orderdetail_id_seq OWNER TO postgres;

--
-- Name: orderdetail_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE orderdetail_id_seq OWNED BY orderdetail.id;


--
-- Name: promo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE promo (
    id character varying(20) NOT NULL,
    storeusername character varying(20),
    name character varying(50),
    description text,
    status boolean DEFAULT true,
    discountamount integer
);


ALTER TABLE promo OWNER TO postgres;

--
-- Name: screeningtime; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE screeningtime (
    id character varying(20) NOT NULL,
    filmid character varying(20),
    studioid character varying(20),
    storeusername character varying(20),
    "time" time without time zone,
    duration integer,
    status boolean DEFAULT true
);


ALTER TABLE screeningtime OWNER TO postgres;

--
-- Name: screeningtime_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE screeningtime_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE screeningtime_id_seq OWNER TO postgres;

--
-- Name: screeningtime_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE screeningtime_id_seq OWNED BY screeningtime.id;


--
-- Name: seat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE seat (
    number character varying(5) NOT NULL,
    studioid character varying(20)
);


ALTER TABLE seat OWNER TO postgres;

--
-- Name: storeaccount; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE storeaccount (
    username character varying(20) NOT NULL,
    password character varying(20),
    storename character varying(50)
);


ALTER TABLE storeaccount OWNER TO postgres;

--
-- Name: studio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE studio (
    id character varying(20) NOT NULL,
    storeusername character varying(20),
    name character varying(50),
    type character varying(20),
    price integer,
    status boolean DEFAULT true
);


ALTER TABLE studio OWNER TO postgres;

--
-- Name: studio_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE studio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE studio_id_seq OWNER TO postgres;

--
-- Name: studio_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE studio_id_seq OWNED BY studio.id;


--
-- Name: film id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY film ALTER COLUMN id SET DEFAULT nextval('film_id_seq'::regclass);


--
-- Name: foodandbeverages id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY foodandbeverages ALTER COLUMN id SET DEFAULT nextval('foodandbeverages_id_seq'::regclass);


--
-- Name: invoice id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY invoice ALTER COLUMN id SET DEFAULT nextval('invoice_id_seq'::regclass);


--
-- Name: membercard id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY membercard ALTER COLUMN id SET DEFAULT nextval('membercard_id_seq'::regclass);


--
-- Name: orderdetail id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orderdetail ALTER COLUMN id SET DEFAULT nextval('orderdetail_id_seq'::regclass);


--
-- Name: screeningtime id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY screeningtime ALTER COLUMN id SET DEFAULT nextval('screeningtime_id_seq'::regclass);


--
-- Name: studio id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY studio ALTER COLUMN id SET DEFAULT nextval('studio_id_seq'::regclass);


--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO account (username, storeusername, password, type, status) VALUES ('ndi', 'blibli', 'bear', 'admin', true);
INSERT INTO account (username, storeusername, password, type, status) VALUES ('admin', 'blibli', 'asdasd', 'admin', true);


--
-- Data for Name: film; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('6', 'blibli', 'asd', 'ad', 'Action', 123, 'asda', 13, 123, '2017-12-31 00:00:00', '2017-01-01 00:00:00', 'asdasd', 'asd', 'asd', 'asd', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('9', 'blibli', 'asda', 'asdasd', 'Action', 13123, 'asdasd', 13123, 313, '2017-12-31 00:00:00', '2017-12-31 00:00:00', 'asdasd', 'asdasd', 'asdasd', 'asd', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('10', 'blibli', 'asda', 'asdasd', 'Action', 13123, 'asdasd', 13123, 313, '2017-12-31 00:00:00', '2017-12-31 00:00:00', 'asdasd', 'asdasd', 'asdasd', 'asd', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('11', 'blibli', 'asda', 'asdasd', 'Action', 13123, 'asdasd', 13123, 313, '2017-12-31 00:00:00', '2017-12-31 00:00:00', 'asdasd', 'asdasd', 'asdasd', 'asd', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('12', 'blibli', 'asda', 'asdasd', 'Action', 13123, 'asdasd', 13123, 313, '2017-12-31 00:00:00', '2017-12-31 00:00:00', 'asdasd', 'asdasd', 'asdasd', 'asd', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('13', 'blibli', 'asda', 'asdasd', 'Action', 13123, 'asdasd', 13123, 313, '2017-12-31 00:00:00', '2017-12-31 00:00:00', 'asdasd', 'asdasd', 'asdasd', 'asd', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('14', NULL, 'asda', 'asdasd', 'Action', 13123, 'asdasd', 13123, 313, '2017-12-31 00:00:00', '2017-12-31 00:00:00', 'asdasd', 'asdasd', 'asdasd', 'asd', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('15', 'blibli', 'asd', 'asdasd', 'Action', 1231, 'asdasd', 123, 1123, '2017-12-31 00:00:00', '0012-12-12 00:00:00', 'aadds', 'asasd', 'asdasd', 'asdasd', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('16', 'blibli', 'asd', 'asdasd', 'Action', 1231, 'asdasd', 123, 1123, '2017-12-31 00:00:00', '0012-12-12 00:00:00', 'aadds', 'asasd', 'asdasd', 'asdasd', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('17', 'blibli', 'asd', 'asd', 'Action', 1, 'asdas', 12312, 1231, '2017-12-31 00:00:00', '2016-12-31 00:00:00', '123', '123', '1123', '123
', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('18', 'blibli', 'asd', 'asd', 'Action', 1, 'asdas', 12312, 1231, '2017-12-31 00:00:00', '2016-12-31 00:00:00', '123', '123', '1123', '123
', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('19', 'blibli', 'asd', 'asd', 'Action', 1, 'asdas', 12312, 1231, '2017-12-31 00:00:00', '2016-12-31 00:00:00', '123', '123', '1123', '123
', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('20', 'blibli', 'asd', 'asd', 'Action', 1, 'asdas', 12312, 1231, '2017-12-31 00:00:00', '2016-12-31 00:00:00', '123', '123', '1123', '123
', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('21', 'blibli', 'asd', 'asd', 'Action', 1, 'asdas', 12312, 1231, '2017-12-31 00:00:00', '2016-12-31 00:00:00', '123', '123', '1123', '123
', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('22', 'blibli', 'asdasd', 'asdasd', 'Action', 2, 'asda', 313, -1123123, '0123-03-12 00:00:00', '0123-03-12 00:00:00', '123123', '23123', '123123', '23
13', true);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('7', 'blibli', 'asd', 'asdasd', 'Action', 123, '123', 123, 123, '2017-12-31 00:00:00', '2017-12-31 00:00:00', '123', '2123', '123', '123', false);
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES ('8', 'blibli', 'tampan', 'asdasd', 'Action', 123, '123', 123, 123, '2017-12-31 00:00:00', '2017-12-31 00:00:00', '123', '2123', '123', '123', true);


--
-- Name: film_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('film_id_seq', 22, true);


--
-- Data for Name: filmticket; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: foodandbeverages; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO foodandbeverages (id, storeusername, cover, name, type, size, price) VALUES ('3', 'blibli', 's', 'asdasd', 'Beverages', 'Regular', 123131);
INSERT INTO foodandbeverages (id, storeusername, cover, name, type, size, price) VALUES ('1', 'blibli', 'Tampan', 'Mempeseona', 'Combo', 'Jumbo', 132);


--
-- Name: foodandbeverages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('foodandbeverages_id_seq', 4, true);


--
-- Data for Name: invoice; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO invoice (id, memberid, accountusername, storeusername, promoid, orderdate, totalprice) VALUES ('1', NULL, 'ndi', 'blibli', NULL, '2017-10-22 12:16:54.509', 10000);
INSERT INTO invoice (id, memberid, accountusername, storeusername, promoid, orderdate, totalprice) VALUES ('2', '1', 'ndi', 'blibli', NULL, '2017-10-22 12:16:54.509', 10000);


--
-- Name: invoice_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('invoice_id_seq', 1, false);


--
-- Data for Name: ledger; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: membercard; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO membercard (id, storeusername, fullname, gender, birthdate, phonenumber, email, status) VALUES ('2', 'blibli', 'asd', 'Pria', '2017-12-31 00:00:00', '12312', 'wendydamar.wb@gmail.com', true);
INSERT INTO membercard (id, storeusername, fullname, gender, birthdate, phonenumber, email, status) VALUES ('1', 'blibli', 'Tampan', 'Pria', '2017-12-31 00:00:00', '123', 'wendydamar.wb@gmail.com', true);


--
-- Name: membercard_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('membercard_id_seq', 2, true);


--
-- Data for Name: orderdetail; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO orderdetail (id, invoiceid, storeusername, itemname, quantity, price, discountstatus) VALUES ('1', '1', 'blibli', 'sabun', 5, 3000, NULL);
INSERT INTO orderdetail (id, invoiceid, storeusername, itemname, quantity, price, discountstatus) VALUES ('2', '1', 'blibli', 'sampo', 3, 3000, NULL);
INSERT INTO orderdetail (id, invoiceid, storeusername, itemname, quantity, price, discountstatus) VALUES ('3', '1', 'blibli', 'indomi', 2, 4000, NULL);
INSERT INTO orderdetail (id, invoiceid, storeusername, itemname, quantity, price, discountstatus) VALUES ('4', '2', 'blibli', 'selada', 1, 11000, NULL);


--
-- Name: orderdetail_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('orderdetail_id_seq', 1, false);


--
-- Data for Name: promo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO promo (id, storeusername, name, description, status, discountamount) VALUES ('1', 'blibli', 'ululu', 'diskon 10%', true, 10);


--
-- Data for Name: screeningtime; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO screeningtime (id, filmid, studioid, storeusername, "time", duration, status) VALUES ('2', '6', '1', 'blibli', '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeusername, "time", duration, status) VALUES ('4', '6', '1', 'blibli', '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeusername, "time", duration, status) VALUES ('7', '6', '1', 'blibli', '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeusername, "time", duration, status) VALUES ('10', '6', '1', 'blibli', '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeusername, "time", duration, status) VALUES ('11', '6', '1', 'blibli', '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeusername, "time", duration, status) VALUES ('12', '6', '1', 'blibli', '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeusername, "time", duration, status) VALUES ('6', '6', '2', 'blibli', '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeusername, "time", duration, status) VALUES ('5', '6', '2', 'blibli', '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeusername, "time", duration, status) VALUES ('8', '6', '3', 'blibli', '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeusername, "time", duration, status) VALUES ('3', '6', '2', 'blibli', '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeusername, "time", duration, status) VALUES ('9', '6', '3', 'blibli', '23:56:00', 123, true);


--
-- Name: screeningtime_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('screeningtime_id_seq', 12, true);


--
-- Data for Name: seat; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: storeaccount; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO storeaccount (username, password, storename) VALUES ('blibli', 'mantab', 'global digital niaga');


--
-- Data for Name: studio; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('1', 'blibli', 'A', 'Regular', NULL, true);
INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('2', 'blibli', 'B', 'Regular', NULL, true);
INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('4', 'blibli', 'C', 'Regular', NULL, true);
INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('6', 'blibli', 'D', 'Regular', NULL, true);
INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('7', 'blibli', 'E', 'Regular', NULL, true);
INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('3', 'blibli', 'F', 'Regular', NULL, true);
INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('5', 'blibli', 'G', 'VIP', NULL, true);
INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('8', 'blibli', 'H', 'Suite', NULL, true);


--
-- Name: studio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('studio_id_seq', 8, true);


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account
    ADD CONSTRAINT account_pkey PRIMARY KEY (username);


--
-- Name: film film_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY film
    ADD CONSTRAINT film_pkey PRIMARY KEY (id);


--
-- Name: filmticket filmticket_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmticket
    ADD CONSTRAINT filmticket_pkey PRIMARY KEY (id);


--
-- Name: foodandbeverages foodandbeverages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY foodandbeverages
    ADD CONSTRAINT foodandbeverages_pkey PRIMARY KEY (id);


--
-- Name: invoice invoice_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_pkey PRIMARY KEY (id);


--
-- Name: ledger ledger_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ledger
    ADD CONSTRAINT ledger_pkey PRIMARY KEY (id);


--
-- Name: membercard membercard_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY membercard
    ADD CONSTRAINT membercard_pkey PRIMARY KEY (id);


--
-- Name: orderdetail orderdetail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orderdetail
    ADD CONSTRAINT orderdetail_pkey PRIMARY KEY (id);


--
-- Name: promo promo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY promo
    ADD CONSTRAINT promo_pkey PRIMARY KEY (id);


--
-- Name: screeningtime screeningtime_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY screeningtime
    ADD CONSTRAINT screeningtime_pkey PRIMARY KEY (id);


--
-- Name: seat seat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY seat
    ADD CONSTRAINT seat_pkey PRIMARY KEY (number);


--
-- Name: storeaccount storeaccount_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY storeaccount
    ADD CONSTRAINT storeaccount_pkey PRIMARY KEY (username);


--
-- Name: studio studio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY studio
    ADD CONSTRAINT studio_pkey PRIMARY KEY (id);


--
-- Name: account account_storeaccount_username_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account
    ADD CONSTRAINT account_storeaccount_username_fk FOREIGN KEY (storeusername) REFERENCES storeaccount(username);


--
-- Name: film film_storeusername_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY film
    ADD CONSTRAINT film_storeusername_fkey FOREIGN KEY (storeusername) REFERENCES storeaccount(username);


--
-- Name: filmticket filmticket_filmid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmticket
    ADD CONSTRAINT filmticket_filmid_fkey FOREIGN KEY (filmid) REFERENCES film(id);


--
-- Name: filmticket filmticket_screeningid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmticket
    ADD CONSTRAINT filmticket_screeningid_fkey FOREIGN KEY (screeningid) REFERENCES screeningtime(id);


--
-- Name: filmticket filmticket_seatnumber_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmticket
    ADD CONSTRAINT filmticket_seatnumber_fkey FOREIGN KEY (seatnumber) REFERENCES seat(number);


--
-- Name: filmticket filmticket_studioid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmticket
    ADD CONSTRAINT filmticket_studioid_fkey FOREIGN KEY (studioid) REFERENCES studio(id);


--
-- Name: foodandbeverages foodandbeverages_storeusername_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY foodandbeverages
    ADD CONSTRAINT foodandbeverages_storeusername_fkey FOREIGN KEY (storeusername) REFERENCES storeaccount(username);


--
-- Name: invoice invoice_accountusername_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_accountusername_fkey FOREIGN KEY (accountusername) REFERENCES account(username);


--
-- Name: invoice invoice_memberid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_memberid_fkey FOREIGN KEY (memberid) REFERENCES membercard(id);


--
-- Name: invoice invoice_promoid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_promoid_fkey FOREIGN KEY (promoid) REFERENCES promo(id);


--
-- Name: invoice invoice_storeusername_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_storeusername_fkey FOREIGN KEY (storeusername) REFERENCES storeaccount(username);


--
-- Name: ledger ledger_invoiceid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ledger
    ADD CONSTRAINT ledger_invoiceid_fkey FOREIGN KEY (invoiceid) REFERENCES invoice(id);


--
-- Name: ledger ledger_memberid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ledger
    ADD CONSTRAINT ledger_memberid_fkey FOREIGN KEY (memberid) REFERENCES membercard(id);


--
-- Name: ledger ledger_orderdetailid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ledger
    ADD CONSTRAINT ledger_orderdetailid_fkey FOREIGN KEY (orderdetailid) REFERENCES orderdetail(id);


--
-- Name: ledger ledger_storeusername_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ledger
    ADD CONSTRAINT ledger_storeusername_fkey FOREIGN KEY (storeusername) REFERENCES storeaccount(username);


--
-- Name: membercard membercard_storeusername_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY membercard
    ADD CONSTRAINT membercard_storeusername_fkey FOREIGN KEY (storeusername) REFERENCES storeaccount(username);


--
-- Name: orderdetail orderdetail_invoiceid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orderdetail
    ADD CONSTRAINT orderdetail_invoiceid_fkey FOREIGN KEY (invoiceid) REFERENCES invoice(id);


--
-- Name: orderdetail orderdetail_storeusername_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orderdetail
    ADD CONSTRAINT orderdetail_storeusername_fkey FOREIGN KEY (storeusername) REFERENCES storeaccount(username);


--
-- Name: promo promo_storeusername_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY promo
    ADD CONSTRAINT promo_storeusername_fkey FOREIGN KEY (storeusername) REFERENCES storeaccount(username);


--
-- Name: screeningtime screeningtime_filmid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY screeningtime
    ADD CONSTRAINT screeningtime_filmid_fkey FOREIGN KEY (filmid) REFERENCES film(id);


--
-- Name: screeningtime screeningtime_storeusername_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY screeningtime
    ADD CONSTRAINT screeningtime_storeusername_fkey FOREIGN KEY (storeusername) REFERENCES storeaccount(username);


--
-- Name: screeningtime screeningtime_studioid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY screeningtime
    ADD CONSTRAINT screeningtime_studioid_fkey FOREIGN KEY (studioid) REFERENCES studio(id);


--
-- Name: seat seat_studioid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY seat
    ADD CONSTRAINT seat_studioid_fkey FOREIGN KEY (studioid) REFERENCES studio(id);


--
-- Name: studio studio_storeusername_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY studio
    ADD CONSTRAINT studio_storeusername_fkey FOREIGN KEY (storeusername) REFERENCES storeaccount(username);


--
-- PostgreSQL database dump complete
--

