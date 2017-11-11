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
    price integer,
    storeusername character varying(20)
);


ALTER TABLE filmticket OWNER TO postgres;

--
-- Name: filmticket_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE filmticket_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE filmticket_id_seq OWNER TO postgres;

--
-- Name: filmticket_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE filmticket_id_seq OWNED BY filmticket.id;


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
    studioid character varying(20),
    name character varying(5)
);


ALTER TABLE seat OWNER TO postgres;

--
-- Name: seat_number_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seat_number_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seat_number_seq OWNER TO postgres;

--
-- Name: seat_number_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE seat_number_seq OWNED BY seat.number;


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
-- Name: filmticket id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmticket ALTER COLUMN id SET DEFAULT nextval('filmticket_id_seq'::regclass);


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
-- Name: seat number; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY seat ALTER COLUMN number SET DEFAULT nextval('seat_number_seq'::regclass);


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

INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeusername) VALUES ('1', '6', '1', 'A5', '2', 123, 'blibli');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeusername) VALUES ('2', '6', '1', 'E3', '2', 0, 'blibli');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeusername) VALUES ('3', '6', '1', 'E7', '2', 0, 'blibli');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeusername) VALUES ('6', '6', '1', 'D5', '2', 0, 'blibli');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeusername) VALUES ('7', '6', '1', 'F8', '2', 0, 'blibli');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeusername) VALUES ('8', '6', '1', 'A10', '2', 0, 'blibli');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeusername) VALUES ('9', '6', '1', 'G4', '2', 0, 'blibli');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeusername) VALUES ('10', '6', '1', 'I7', '2', 0, 'blibli');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeusername) VALUES ('11', '6', '1', 'D8', '2', 0, 'blibli');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeusername) VALUES ('12', '6', '1', 'J6', '2', 0, 'blibli');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeusername) VALUES ('17', '6', '1', 'D1', '2', 0, 'blibli');


--
-- Name: filmticket_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('filmticket_id_seq', 17, true);


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

INSERT INTO seat (number, studioid, name) VALUES ('1', '1', 'a1');
INSERT INTO seat (number, studioid, name) VALUES ('2', '1', 'a2');
INSERT INTO seat (number, studioid, name) VALUES ('3', '1', 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('4', '1', 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('5', '1', 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('6', '1', 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('7', '1', 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('8', '1', 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('9', '1', 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('10', '1', 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('11', '1', 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('12', '1', 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('13', '1', 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('14', '1', 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('15', '1', 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('16', '1', 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('17', '1', 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('18', '1', 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('19', '1', 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('20', '1', 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('21', '1', 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('22', '1', 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('23', '1', 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('24', '1', 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('25', '1', 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('26', '1', 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('27', '1', 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('28', '1', 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('29', '1', 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('30', '1', 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('31', '1', 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('32', '1', 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('33', '1', 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('34', '1', 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('35', '1', 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('36', '1', 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('37', '1', 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('38', '1', 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('39', '1', 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('40', '1', 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('41', '1', 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('42', '1', 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('43', '1', 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('44', '1', 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('45', '1', 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('46', '1', 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('47', '1', 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('48', '1', 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('49', '1', 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('50', '1', 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('51', '1', 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('52', '1', 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('53', '1', 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('54', '1', 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('55', '1', 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('56', '1', 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('57', '1', 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('58', '1', 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('59', '1', 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('60', '1', 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('61', '1', 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('62', '1', 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('63', '1', 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('64', '1', 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('65', '1', 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('66', '1', 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('67', '1', 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('68', '1', 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('69', '1', 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('70', '1', 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('71', '1', 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('72', '1', 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('73', '1', 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('74', '1', 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('75', '1', 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('76', '1', 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('77', '1', 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('78', '1', 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('79', '1', 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('80', '1', 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('81', '1', 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('82', '1', 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('83', '1', 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('84', '1', 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('85', '1', 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('86', '1', 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('87', '1', 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('88', '1', 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('89', '1', 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('90', '1', 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('91', '1', 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('92', '1', 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('93', '1', 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('94', '1', 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('95', '1', 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('96', '1', 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('97', '1', 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('98', '1', 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('99', '1', 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('100', '1', 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('101', '2', 'A1');
INSERT INTO seat (number, studioid, name) VALUES ('102', '2', 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('103', '2', 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('104', '2', 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('105', '2', 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('106', '2', 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('107', '2', 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('108', '2', 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('109', '2', 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('110', '2', 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('111', '2', 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('112', '2', 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('113', '2', 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('114', '2', 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('115', '2', 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('116', '2', 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('117', '2', 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('118', '2', 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('119', '2', 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('120', '2', 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('121', '2', 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('122', '2', 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('123', '2', 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('124', '2', 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('125', '2', 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('126', '2', 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('127', '2', 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('128', '2', 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('129', '2', 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('130', '2', 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('131', '2', 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('132', '2', 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('133', '2', 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('134', '2', 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('135', '2', 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('136', '2', 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('137', '2', 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('138', '2', 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('139', '2', 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('140', '2', 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('141', '2', 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('142', '2', 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('143', '2', 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('144', '2', 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('145', '2', 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('146', '2', 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('147', '2', 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('148', '2', 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('149', '2', 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('150', '2', 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('151', '2', 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('152', '2', 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('153', '2', 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('154', '2', 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('155', '2', 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('156', '2', 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('157', '2', 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('158', '2', 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('159', '2', 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('160', '2', 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('161', '2', 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('162', '2', 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('163', '2', 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('164', '2', 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('165', '2', 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('166', '2', 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('167', '2', 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('168', '2', 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('169', '2', 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('170', '2', 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('171', '2', 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('172', '2', 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('173', '2', 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('174', '2', 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('175', '2', 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('176', '2', 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('177', '2', 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('178', '2', 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('179', '2', 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('180', '2', 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('181', '2', 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('182', '2', 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('183', '2', 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('184', '2', 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('185', '2', 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('186', '2', 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('187', '2', 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('188', '2', 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('189', '2', 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('190', '2', 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('191', '2', 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('192', '2', 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('193', '2', 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('194', '2', 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('195', '2', 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('196', '2', 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('197', '2', 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('198', '2', 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('199', '2', 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('200', '2', 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('201', '3', 'A1');
INSERT INTO seat (number, studioid, name) VALUES ('202', '3', 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('203', '3', 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('204', '3', 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('205', '3', 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('206', '3', 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('207', '3', 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('208', '3', 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('209', '3', 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('210', '3', 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('211', '3', 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('212', '3', 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('213', '3', 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('214', '3', 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('215', '3', 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('216', '3', 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('217', '3', 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('218', '3', 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('219', '3', 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('220', '3', 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('221', '3', 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('222', '3', 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('223', '3', 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('224', '3', 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('225', '3', 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('226', '3', 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('227', '3', 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('228', '3', 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('229', '3', 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('230', '3', 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('231', '3', 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('232', '3', 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('233', '3', 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('234', '3', 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('235', '3', 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('236', '3', 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('237', '3', 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('238', '3', 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('239', '3', 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('240', '3', 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('241', '3', 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('242', '3', 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('243', '3', 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('244', '3', 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('245', '3', 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('246', '3', 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('247', '3', 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('248', '3', 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('249', '3', 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('250', '3', 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('251', '3', 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('252', '3', 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('253', '3', 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('254', '3', 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('255', '3', 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('256', '3', 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('257', '3', 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('258', '3', 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('259', '3', 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('260', '3', 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('261', '3', 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('262', '3', 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('263', '3', 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('264', '3', 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('265', '3', 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('266', '3', 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('267', '3', 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('268', '3', 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('269', '3', 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('270', '3', 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('271', '3', 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('272', '3', 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('273', '3', 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('274', '3', 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('275', '3', 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('276', '3', 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('277', '3', 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('278', '3', 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('279', '3', 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('280', '3', 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('281', '3', 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('282', '3', 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('283', '3', 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('284', '3', 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('285', '3', 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('286', '3', 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('287', '3', 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('288', '3', 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('289', '3', 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('290', '3', 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('291', '3', 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('292', '3', 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('293', '3', 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('294', '3', 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('295', '3', 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('296', '3', 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('297', '3', 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('298', '3', 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('299', '3', 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('300', '3', 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('301', '4', 'A1');
INSERT INTO seat (number, studioid, name) VALUES ('302', '4', 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('303', '4', 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('304', '4', 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('305', '4', 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('306', '4', 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('307', '4', 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('308', '4', 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('309', '4', 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('310', '4', 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('311', '4', 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('312', '4', 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('313', '4', 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('314', '4', 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('315', '4', 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('316', '4', 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('317', '4', 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('318', '4', 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('319', '4', 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('320', '4', 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('321', '4', 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('322', '4', 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('323', '4', 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('324', '4', 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('325', '4', 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('326', '4', 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('327', '4', 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('328', '4', 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('329', '4', 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('330', '4', 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('331', '4', 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('332', '4', 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('333', '4', 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('334', '4', 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('335', '4', 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('336', '4', 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('337', '4', 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('338', '4', 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('339', '4', 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('340', '4', 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('341', '4', 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('342', '4', 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('343', '4', 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('344', '4', 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('345', '4', 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('346', '4', 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('347', '4', 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('348', '4', 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('349', '4', 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('350', '4', 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('351', '4', 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('352', '4', 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('353', '4', 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('354', '4', 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('355', '4', 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('356', '4', 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('357', '4', 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('358', '4', 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('359', '4', 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('360', '4', 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('361', '4', 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('362', '4', 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('363', '4', 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('364', '4', 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('365', '4', 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('366', '4', 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('367', '4', 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('368', '4', 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('369', '4', 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('370', '4', 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('371', '4', 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('372', '4', 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('373', '4', 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('374', '4', 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('375', '4', 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('376', '4', 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('377', '4', 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('378', '4', 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('379', '4', 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('380', '4', 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('381', '4', 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('382', '4', 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('383', '4', 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('384', '4', 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('385', '4', 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('386', '4', 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('387', '4', 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('388', '4', 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('389', '4', 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('390', '4', 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('391', '4', 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('392', '4', 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('393', '4', 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('394', '4', 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('395', '4', 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('396', '4', 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('397', '4', 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('398', '4', 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('399', '4', 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('400', '4', 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('401', '5', 'A1');
INSERT INTO seat (number, studioid, name) VALUES ('402', '5', 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('403', '5', 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('404', '5', 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('405', '5', 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('406', '5', 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('407', '5', 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('408', '5', 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('409', '5', 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('410', '5', 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('411', '5', 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('412', '5', 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('413', '5', 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('414', '5', 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('415', '5', 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('416', '5', 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('417', '5', 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('418', '5', 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('419', '5', 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('420', '5', 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('421', '5', 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('422', '5', 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('423', '5', 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('424', '5', 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('425', '5', 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('426', '5', 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('427', '5', 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('428', '5', 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('429', '5', 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('430', '5', 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('431', '5', 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('432', '5', 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('433', '5', 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('434', '5', 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('435', '5', 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('436', '5', 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('437', '5', 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('438', '5', 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('439', '5', 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('440', '5', 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('441', '5', 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('442', '5', 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('443', '5', 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('444', '5', 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('445', '5', 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('446', '5', 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('447', '5', 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('448', '5', 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('449', '5', 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('450', '5', 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('451', '5', 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('452', '5', 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('453', '5', 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('454', '5', 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('455', '5', 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('456', '5', 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('457', '5', 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('458', '5', 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('459', '5', 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('460', '5', 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('461', '5', 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('462', '5', 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('463', '5', 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('464', '5', 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('465', '5', 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('466', '5', 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('467', '5', 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('468', '5', 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('469', '5', 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('470', '5', 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('471', '5', 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('472', '5', 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('473', '5', 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('474', '5', 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('475', '5', 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('476', '5', 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('477', '5', 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('478', '5', 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('479', '5', 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('480', '5', 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('481', '5', 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('482', '5', 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('483', '5', 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('484', '5', 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('485', '5', 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('486', '5', 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('487', '5', 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('488', '5', 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('489', '5', 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('490', '5', 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('491', '5', 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('492', '5', 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('493', '5', 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('494', '5', 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('495', '5', 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('496', '5', 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('497', '5', 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('498', '5', 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('499', '5', 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('500', '5', 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('501', '6', 'A1');
INSERT INTO seat (number, studioid, name) VALUES ('502', '6', 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('503', '6', 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('504', '6', 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('505', '6', 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('506', '6', 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('507', '6', 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('508', '6', 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('509', '6', 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('510', '6', 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('511', '6', 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('512', '6', 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('513', '6', 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('514', '6', 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('515', '6', 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('516', '6', 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('517', '6', 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('518', '6', 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('519', '6', 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('520', '6', 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('521', '6', 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('522', '6', 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('523', '6', 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('524', '6', 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('525', '6', 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('526', '6', 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('527', '6', 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('528', '6', 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('529', '6', 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('530', '6', 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('531', '6', 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('532', '6', 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('533', '6', 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('534', '6', 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('535', '6', 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('536', '6', 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('537', '6', 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('538', '6', 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('539', '6', 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('540', '6', 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('541', '6', 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('542', '6', 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('543', '6', 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('544', '6', 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('545', '6', 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('546', '6', 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('547', '6', 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('548', '6', 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('549', '6', 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('550', '6', 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('551', '6', 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('552', '6', 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('553', '6', 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('554', '6', 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('555', '6', 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('556', '6', 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('557', '6', 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('558', '6', 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('559', '6', 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('560', '6', 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('561', '6', 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('562', '6', 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('563', '6', 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('564', '6', 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('565', '6', 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('566', '6', 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('567', '6', 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('568', '6', 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('569', '6', 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('570', '6', 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('571', '6', 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('572', '6', 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('573', '6', 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('574', '6', 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('575', '6', 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('576', '6', 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('577', '6', 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('578', '6', 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('579', '6', 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('580', '6', 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('581', '6', 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('582', '6', 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('583', '6', 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('584', '6', 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('585', '6', 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('586', '6', 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('587', '6', 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('588', '6', 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('589', '6', 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('590', '6', 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('591', '6', 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('592', '6', 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('593', '6', 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('594', '6', 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('595', '6', 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('596', '6', 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('597', '6', 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('598', '6', 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('599', '6', 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('600', '6', 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('601', '7', 'A1');
INSERT INTO seat (number, studioid, name) VALUES ('602', '7', 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('603', '7', 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('604', '7', 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('605', '7', 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('606', '7', 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('607', '7', 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('608', '7', 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('609', '7', 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('610', '7', 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('611', '7', 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('612', '7', 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('613', '7', 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('614', '7', 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('615', '7', 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('616', '7', 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('617', '7', 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('618', '7', 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('619', '7', 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('620', '7', 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('621', '7', 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('622', '7', 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('623', '7', 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('624', '7', 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('625', '7', 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('626', '7', 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('627', '7', 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('628', '7', 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('629', '7', 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('630', '7', 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('631', '7', 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('632', '7', 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('633', '7', 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('634', '7', 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('635', '7', 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('636', '7', 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('637', '7', 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('638', '7', 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('639', '7', 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('640', '7', 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('641', '7', 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('642', '7', 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('643', '7', 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('644', '7', 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('645', '7', 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('646', '7', 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('647', '7', 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('648', '7', 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('649', '7', 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('650', '7', 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('651', '7', 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('652', '7', 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('653', '7', 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('654', '7', 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('655', '7', 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('656', '7', 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('657', '7', 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('658', '7', 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('659', '7', 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('660', '7', 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('661', '7', 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('662', '7', 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('663', '7', 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('664', '7', 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('665', '7', 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('666', '7', 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('667', '7', 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('668', '7', 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('669', '7', 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('670', '7', 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('671', '7', 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('672', '7', 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('673', '7', 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('674', '7', 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('675', '7', 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('676', '7', 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('677', '7', 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('678', '7', 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('679', '7', 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('680', '7', 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('681', '7', 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('682', '7', 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('683', '7', 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('684', '7', 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('685', '7', 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('686', '7', 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('687', '7', 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('688', '7', 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('689', '7', 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('690', '7', 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('691', '7', 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('692', '7', 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('693', '7', 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('694', '7', 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('695', '7', 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('696', '7', 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('697', '7', 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('698', '7', 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('699', '7', 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('700', '7', 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('701', '8', 'A1');
INSERT INTO seat (number, studioid, name) VALUES ('702', '8', 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('703', '8', 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('704', '8', 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('705', '8', 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('706', '8', 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('707', '8', 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('708', '8', 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('709', '8', 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('710', '8', 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('711', '8', 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('712', '8', 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('713', '8', 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('714', '8', 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('715', '8', 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('716', '8', 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('717', '8', 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('718', '8', 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('719', '8', 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('720', '8', 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('721', '8', 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('722', '8', 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('723', '8', 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('724', '8', 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('725', '8', 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('726', '8', 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('727', '8', 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('728', '8', 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('729', '8', 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('730', '8', 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('731', '8', 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('732', '8', 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('733', '8', 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('734', '8', 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('735', '8', 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('736', '8', 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('737', '8', 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('738', '8', 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('739', '8', 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('740', '8', 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('741', '8', 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('742', '8', 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('743', '8', 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('744', '8', 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('745', '8', 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('746', '8', 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('747', '8', 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('748', '8', 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('749', '8', 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('750', '8', 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('751', '8', 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('752', '8', 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('753', '8', 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('754', '8', 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('755', '8', 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('756', '8', 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('757', '8', 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('758', '8', 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('759', '8', 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('760', '8', 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('761', '8', 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('762', '8', 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('763', '8', 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('764', '8', 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('765', '8', 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('766', '8', 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('767', '8', 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('768', '8', 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('769', '8', 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('770', '8', 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('771', '8', 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('772', '8', 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('773', '8', 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('774', '8', 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('775', '8', 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('776', '8', 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('777', '8', 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('778', '8', 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('779', '8', 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('780', '8', 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('781', '8', 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('782', '8', 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('783', '8', 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('784', '8', 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('785', '8', 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('786', '8', 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('787', '8', 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('788', '8', 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('789', '8', 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('790', '8', 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('791', '8', 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('792', '8', 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('793', '8', 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('794', '8', 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('795', '8', 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('796', '8', 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('797', '8', 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('798', '8', 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('799', '8', 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('800', '8', 'J10');


--
-- Name: seat_number_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seat_number_seq', 801, true);


--
-- Data for Name: storeaccount; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO storeaccount (username, password, storename) VALUES ('blibli', 'mantab', 'global digital niaga');


--
-- Data for Name: studio; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('6', 'blibli', 'D', 'Regular', 40000, true);
INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('8', 'blibli', 'H', 'Suite', 800000, true);
INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('4', 'blibli', 'C', 'Regular', 30000, true);
INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('1', 'blibli', 'A', 'Regular', 10000, true);
INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('2', 'blibli', 'B', 'Regular', 20000, true);
INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('7', 'blibli', 'E', 'Regular', 500000, true);
INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('5', 'blibli', 'G', 'VIP', 700000, true);
INSERT INTO studio (id, storeusername, name, type, price, status) VALUES ('3', 'blibli', 'F', 'Regular', 60000, true);


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
-- Name: filmticket filmticket_storeaccount_username_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmticket
    ADD CONSTRAINT filmticket_storeaccount_username_fk FOREIGN KEY (storeusername) REFERENCES storeaccount(username);


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

