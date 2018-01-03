--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

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
    storeid integer,
    password character varying(20),
    roleid integer,
    status boolean DEFAULT true,
    id integer NOT NULL
);


ALTER TABLE account OWNER TO postgres;

--
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE account_id_seq OWNER TO postgres;

--
-- Name: account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE account_id_seq OWNED BY account.id;


--
-- Name: accountrole; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE accountrole (
    id integer NOT NULL,
    role character varying(20) NOT NULL,
    storeid integer,
    status boolean DEFAULT true
);


ALTER TABLE accountrole OWNER TO postgres;

--
-- Name: accountrole_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE accountrole_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE accountrole_id_seq OWNER TO postgres;

--
-- Name: accountrole_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE accountrole_id_seq OWNED BY accountrole.id;


--
-- Name: film; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE film (
    id integer NOT NULL,
    storeid integer,
    cover character varying(150),
    title character varying(50),
    genre integer,
    duration integer,
    director character varying(50),
    rating double precision,
    reviewtotal integer,
    starttime date,
    endtime date,
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
-- Name: filmgenre; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE filmgenre (
    id integer NOT NULL,
    genre character varying(20) NOT NULL,
    storeid integer,
    status boolean DEFAULT true
);


ALTER TABLE filmgenre OWNER TO postgres;

--
-- Name: filmgenre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE filmgenre_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE filmgenre_id_seq OWNER TO postgres;

--
-- Name: filmgenre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE filmgenre_id_seq OWNED BY filmgenre.id;


--
-- Name: filmticket; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE filmticket (
    id integer NOT NULL,
    filmid integer,
    studioid integer,
    seatnumber character varying(5),
    screeningid integer,
    price integer,
    storeid integer,
    date date
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
-- Name: fnbsize; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE fnbsize (
    id integer NOT NULL,
    size character varying(20) NOT NULL,
    storeid integer,
    status boolean DEFAULT true
);


ALTER TABLE fnbsize OWNER TO postgres;

--
-- Name: fnbsize_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fnbsize_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fnbsize_id_seq OWNER TO postgres;

--
-- Name: fnbsize_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE fnbsize_id_seq OWNED BY fnbsize.id;


--
-- Name: fnbtype; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE fnbtype (
    id integer NOT NULL,
    type character varying(20) NOT NULL,
    storeid integer,
    status boolean DEFAULT true
);


ALTER TABLE fnbtype OWNER TO postgres;

--
-- Name: fnbtype_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fnbtype_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fnbtype_id_seq OWNER TO postgres;

--
-- Name: fnbtype_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE fnbtype_id_seq OWNED BY fnbtype.id;


--
-- Name: foodandbeverages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE foodandbeverages (
    id integer NOT NULL,
    storeid integer,
    cover character varying(150),
    name character varying(50),
    type integer,
    size integer,
    price integer,
    status boolean DEFAULT true
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
    id integer NOT NULL,
    memberid integer,
    cashierid integer,
    storeid integer,
    promoid integer,
    orderdate timestamp without time zone,
    totalprice double precision
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
-- Name: membercard; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE membercard (
    id integer NOT NULL,
    storeid integer,
    fullname character varying(50) NOT NULL,
    gender integer,
    birthdate timestamp without time zone,
    phonenumber character varying(15) NOT NULL,
    email character varying(30) NOT NULL,
    status boolean DEFAULT false
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
-- Name: membergender; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE membergender (
    id integer NOT NULL,
    gender character varying(20),
    storeid integer,
    status boolean DEFAULT true
);


ALTER TABLE membergender OWNER TO postgres;

--
-- Name: membergender_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE membergender_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE membergender_id_seq OWNER TO postgres;

--
-- Name: membergender_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE membergender_id_seq OWNED BY membergender.id;


--
-- Name: orderdetail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE orderdetail (
    id integer NOT NULL,
    invoiceid integer,
    storeid integer,
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
    id integer NOT NULL,
    storeid integer,
    name character varying(50),
    description text,
    status boolean DEFAULT true,
    discountamount integer
);


ALTER TABLE promo OWNER TO postgres;

--
-- Name: promo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE promo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE promo_id_seq OWNER TO postgres;

--
-- Name: promo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE promo_id_seq OWNED BY promo.id;


--
-- Name: screeningtime; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE screeningtime (
    id integer NOT NULL,
    filmid integer,
    studioid integer,
    storeid integer,
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
    studioid integer,
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
    storename character varying(50),
    id integer NOT NULL,
    status boolean DEFAULT true
);


ALTER TABLE storeaccount OWNER TO postgres;

--
-- Name: storeaccount_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE storeaccount_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE storeaccount_id_seq OWNER TO postgres;

--
-- Name: storeaccount_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE storeaccount_id_seq OWNED BY storeaccount.id;


--
-- Name: studio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE studio (
    id integer NOT NULL,
    storeid integer,
    name character varying(50),
    type integer,
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
-- Name: studiotype; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE studiotype (
    id integer NOT NULL,
    type character varying(20) NOT NULL,
    storeid integer,
    status boolean DEFAULT true
);


ALTER TABLE studiotype OWNER TO postgres;

--
-- Name: studiotype_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE studiotype_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE studiotype_id_seq OWNER TO postgres;

--
-- Name: studiotype_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE studiotype_id_seq OWNED BY studiotype.id;


--
-- Name: superadmin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE superadmin (
    id integer NOT NULL,
    username character varying(20) NOT NULL,
    password character varying(20) NOT NULL,
    status boolean DEFAULT true NOT NULL
);


ALTER TABLE superadmin OWNER TO postgres;

--
-- Name: superadmin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE superadmin_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE superadmin_id_seq OWNER TO postgres;

--
-- Name: superadmin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE superadmin_id_seq OWNED BY superadmin.id;


--
-- Name: account id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account ALTER COLUMN id SET DEFAULT nextval('account_id_seq'::regclass);


--
-- Name: accountrole id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY accountrole ALTER COLUMN id SET DEFAULT nextval('accountrole_id_seq'::regclass);


--
-- Name: film id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY film ALTER COLUMN id SET DEFAULT nextval('film_id_seq'::regclass);


--
-- Name: filmgenre id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmgenre ALTER COLUMN id SET DEFAULT nextval('filmgenre_id_seq'::regclass);


--
-- Name: filmticket id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmticket ALTER COLUMN id SET DEFAULT nextval('filmticket_id_seq'::regclass);


--
-- Name: fnbsize id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fnbsize ALTER COLUMN id SET DEFAULT nextval('fnbsize_id_seq'::regclass);


--
-- Name: fnbtype id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fnbtype ALTER COLUMN id SET DEFAULT nextval('fnbtype_id_seq'::regclass);


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
-- Name: membergender id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY membergender ALTER COLUMN id SET DEFAULT nextval('membergender_id_seq'::regclass);


--
-- Name: orderdetail id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orderdetail ALTER COLUMN id SET DEFAULT nextval('orderdetail_id_seq'::regclass);


--
-- Name: promo id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY promo ALTER COLUMN id SET DEFAULT nextval('promo_id_seq'::regclass);


--
-- Name: screeningtime id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY screeningtime ALTER COLUMN id SET DEFAULT nextval('screeningtime_id_seq'::regclass);


--
-- Name: seat number; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY seat ALTER COLUMN number SET DEFAULT nextval('seat_number_seq'::regclass);


--
-- Name: storeaccount id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY storeaccount ALTER COLUMN id SET DEFAULT nextval('storeaccount_id_seq'::regclass);


--
-- Name: studio id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY studio ALTER COLUMN id SET DEFAULT nextval('studio_id_seq'::regclass);


--
-- Name: studiotype id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY studiotype ALTER COLUMN id SET DEFAULT nextval('studiotype_id_seq'::regclass);


--
-- Name: superadmin id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY superadmin ALTER COLUMN id SET DEFAULT nextval('superadmin_id_seq'::regclass);


--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('lala', 1, '3583', 1, true, 3);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('tampan', 1, '1743073945', 2, true, 4);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 1, '-1408658752', 1, true, 2);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('tes', 1, '3314090', 2, true, 6);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 16, '92668751', 20, true, 24);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 17, '92668751', 22, true, 25);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 18, '92668751', 24, true, 26);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 19, '92668751', 26, true, 27);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 19, '92668751', 28, true, 28);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 19, '92668751', 30, true, 29);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 19, '92668751', 32, true, 30);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 23, '92668751', 34, true, 31);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 24, '92668751', 36, true, 32);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 25, '92668751', 38, true, 33);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 26, '92668751', 40, true, 34);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 27, '92668751', 42, true, 35);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 28, '92668751', 44, true, 36);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('admin', 1, '92668751', 1, true, 37);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('lala', 1, '3583', 1, true, 38);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('damar', 1, '113141045', 1, true, 39);
INSERT INTO account (username, storeid, password, roleid, status, id) VALUES ('ndi', 1, '1325902686', 1, false, 1);


--
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('account_id_seq', 39, true);


--
-- Data for Name: accountrole; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO accountrole (id, role, storeid, status) VALUES (2, 'cashier', 1, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (1, 'admin', 1, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (20, 'admin', 16, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (21, 'cashier', 16, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (22, 'admin', 17, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (23, 'cashier', 17, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (24, 'admin', 18, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (25, 'cashier', 18, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (26, 'admin', 19, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (27, 'cashier', 19, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (28, 'admin', 19, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (29, 'cashier', 19, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (30, 'admin', 19, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (31, 'cashier', 19, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (32, 'admin', 19, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (33, 'cashier', 19, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (34, 'admin', 23, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (35, 'cashier', 23, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (36, 'admin', 24, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (37, 'cashier', 24, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (38, 'admin', 25, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (39, 'cashier', 25, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (40, 'admin', 26, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (41, 'cashier', 26, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (42, 'admin', 27, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (43, 'cashier', 27, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (44, 'admin', 28, true);
INSERT INTO accountrole (id, role, storeid, status) VALUES (45, 'cashier', 28, true);


--
-- Name: accountrole_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('accountrole_id_seq', 45, true);


--
-- Data for Name: film; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (20, 1, 'asd', 'asd', 1, 1, 'asdas', 12312, 1231, '2017-12-31', '2016-12-31', '123', '123', '1123', '123
', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (29, 1, '/blibli/film/ss (2017).jpg', 'ss', 1, 1, '1', 1, 1, '2017-12-03', '2017-12-03', '1', '1', '1', '1', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (26, 1, '/blibli/film/Wendy''s (2021).jpg', 'Wendy''s', 1, 123, '123', 123, 213, '2021-12-07', '2024-12-07', 'qwe', 'qwe', 'wqe', 'qwe', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (27, 1, '/blibli/film/ssss (2017).jpg?20171203221750', 'ssss', 1, 123, 'asd', 12, 12, '2017-12-02', '2017-12-03', 'we', 'we', 'we', 'we', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (24, 1, '\blibli\film\Wendy''s (2017).jpg', 'Wendy''s', 1, 12, '12', 12, 12, '2017-12-05', '2017-12-07', 'Jawa', 'Jawa', 'Wendy', 'Wendy Damar', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (25, 1, '/blibli/film/WEndy Damar (2015).jpg', 'WEndy Damar', 1, 123, '123', 12, 12, '2015-12-03', '2021-12-03', 'Wendy', 'WEnd', 'WEndy', 'Wendy', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (22, 1, 'asdasd', 'asdasd', 1, 2, 'asda', 313, -1123123, '0123-03-12', '0123-03-12', '123123', '23123', '123123', '23
13', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (21, 1, 'asd', 'asd', 1, 1, 'asdas', 12312, 1231, '2017-12-31', '2016-12-31', '123', '123', '1123', '123
', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (19, 1, 'asd', 'asd', 1, 1, 'asdas', 12312, 1231, '2017-12-31', '2016-12-31', '123', '123', '1123', '123
', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (17, 1, 'asd', 'asd', 1, 1, 'asdas', 12312, 1231, '2017-12-31', '2016-12-31', '123', '123', '1123', '123
', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (18, 1, 'asd', 'asd', 1, 1, 'asdas', 12312, 1231, '2017-12-31', '2016-12-31', '123', '123', '1123', '123
', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (16, 1, 'asd', 'asdasd', 1, 1231, 'asdasd', 123, 1123, '2017-12-31', '0012-12-12', 'aadds', 'asasd', 'asdasd', 'asdasd', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (13, 1, 'asda', 'asdasd', 1, 13123, 'asdasd', 13123, 313, '2017-12-31', '2017-12-31', 'asdasd', 'asdasd', 'asdasd', 'asd', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (14, 1, 'asda', 'asdasd', 1, 13123, 'asdasd', 13123, 313, '2017-12-31', '2017-12-31', 'asdasd', 'asdasd', 'asdasd', 'asd', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (12, 1, 'asda', 'asdasd', 1, 13123, 'asdasd', 13123, 313, '2017-12-31', '2017-12-31', 'asdasd', 'asdasd', 'asdasd', 'asd', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (23, 1, '\blibli\film\WendyDamar (2017).jpg', 'WendyDamar', 2, 123, 'asd', 12, 123, '2017-12-03', '2017-01-03', 'indon', 'indon', 'wendy', 'asd', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (8, 1, 'tampan', 'asdasd', 1, 123, '123', 123, 123, '2017-12-31', '2017-12-31', '123', '2123', '123', '123', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (7, 1, 'asd', 'asdasd', 1, 123, '123', 123, 123, '2017-12-31', '2017-12-31', '123', '2123', '123', '123', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (9, 1, 'asda', 'asdasd', 1, 13123, 'asdasd', 13123, 313, '2017-12-31', '2017-12-31', 'asdasd', 'asdasd', 'asdasd', 'asd', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (30, 1, '/blibli/film/[20171203224541] asdasd (2017).jpg', 'asdasd', 1, 1, '1', 1, 1, '2017-12-03', '2017-12-03', '1', '1', '1', '1', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (34, 1, '/1/film/asda (2017) [20171229101251].jpg', 'asda', 1, 123, '123', 123, 123, '2017-12-30', '2017-12-31', '12', '123', '123', 'akuganteng', true);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (31, 1, '/blibli/film/[20171203223531] 1 (2017).jpg', '1', 2, 1, '1', 1, 1, '2017-12-04', '2017-12-05', '1', '1', '1', '1', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (11, 1, 'asda', 'asdasd', 1, 13123, 'asdasd', 13123, 313, '2017-12-31', '2017-12-31', 'asdasd', 'asdasd', 'asdasd', 'asd', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (35, 1, '/1/film/testis (2017) [20171229101303].jpg', 'testis', 1, 5, 'tampan', 1, 1, '2017-12-31', '2017-12-31', 'a', 'a', 'a', 'a
', true);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (37, 1, '/1/film/Jujujuju (2018) [20180101192704].jpg', 'Jujujuju', 1, 123, '12', 1, 12, '2018-12-31', '2020-11-30', 'wendy', 'enn', 'asd', 'asd', true);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (32, 1, '/1/film/Avatar (2017) [20171228164831].jpg', 'Avatar', 1, 1, '1', 1, 1, '2017-12-04', '2018-12-05', '1', '1', '1', '1', true);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (33, 1, '/1/film/asd (2017) [20171228152018].jpg', 'asd', 2, 123, 'asd', 12, 12, '2017-12-31', '2018-12-31', 'asd', 'asd', 'asd', 'To check for any empty file input in the form while uploding any file to the server best way follow my instructions 1. use @MultipartConfig() at the top of your servlet class 2. add the following method to your class private InputStream getImageStream(HttpServletRequest request, String image){ try { Part part ', true);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (28, 1, '/blibli/film/qsd (2017).jpg', 'qsd', 1, 1, '1', 1, 1, '2017-12-03', '2018-12-03', '1', '1', '1', '1', true);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (10, 1, 'asda', 'asdasd', 1, 13123, 'asdasd', 13123, 313, '2017-12-31', '2017-12-31', 'asdasd', 'asdasd', 'asdasd', 'asd', true);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (6, 1, 'asd', 'ad', 1, 123, 'asda', 13, 123, '2017-12-31', '2017-01-01', 'asdasd', 'asd', 'asd', 'asd', true);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (38, 1, '/1/film/N (2018) [20180102172550].jpg', 'Naaaa', 1, 1, 'a', 0, 1, '2018-01-11', '2018-01-12', 'sd', 'as', 'a', '-', false);
INSERT INTO film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) VALUES (15, 1, 'asd', 'asdasd', 1, 1231, 'asdasd', 123, 1123, '2017-12-31', '0012-12-12', 'aadds', 'asasd', 'asdasd', 'asdasd', true);


--
-- Name: film_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('film_id_seq', 38, true);


--
-- Data for Name: filmgenre; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO filmgenre (id, genre, storeid, status) VALUES (8, 'mantapgg', 1, false);
INSERT INTO filmgenre (id, genre, storeid, status) VALUES (3, 'Romance', 1, true);
INSERT INTO filmgenre (id, genre, storeid, status) VALUES (7, 'Fantasy', 1, true);
INSERT INTO filmgenre (id, genre, storeid, status) VALUES (6, 'Comedy', 1, true);
INSERT INTO filmgenre (id, genre, storeid, status) VALUES (5, 'Adventure', 1, true);
INSERT INTO filmgenre (id, genre, storeid, status) VALUES (4, 'Melodrama', 1, true);
INSERT INTO filmgenre (id, genre, storeid, status) VALUES (2, 'HorrorBgt', 1, true);
INSERT INTO filmgenre (id, genre, storeid, status) VALUES (10, 'Baru', 1, true);
INSERT INTO filmgenre (id, genre, storeid, status) VALUES (11, 'Banget', 1, false);
INSERT INTO filmgenre (id, genre, storeid, status) VALUES (1, 'Action', 1, true);
INSERT INTO filmgenre (id, genre, storeid, status) VALUES (9, 'aleleleandro', 1, false);


--
-- Name: filmgenre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('filmgenre_id_seq', 11, true);


--
-- Data for Name: filmticket; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (18, 6, 1, 'A2', 2, 0, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (19, 6, 1, 'A3', 2, 0, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (35, 32, 4, 'A5', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (36, 32, 4, 'D1', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (33, 32, 10, 'F2', 15, 123123, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (34, 32, 10, 'B6', 15, 123123, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (2, 6, 1, 'E3', 2, 0, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (3, 6, 1, 'E7', 2, 0, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (1, 6, 1, 'A5', 2, 123, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (6, 6, 1, 'D5', 2, 0, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (7, 6, 1, 'F8', 2, 0, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (8, 6, 1, 'A10', 2, 0, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (9, 6, 1, 'G4', 2, 0, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (10, 6, 1, 'I7', 2, 0, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (31, 32, 4, 'D4', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (32, 32, 4, 'D5', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (30, 32, 4, 'C7', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (24, 32, 4, 'H6', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (22, 32, 4, 'F5', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (23, 32, 4, 'H5', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (28, 32, 4, 'A7', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (29, 32, 4, 'A3', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (26, 32, 4, 'A9', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (27, 32, 4, 'A8', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (20, 6, 1, 'J1', 2, 10000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (21, 6, 1, 'G1', 2, 10000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (11, 6, 1, 'D8', 2, 0, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (12, 6, 1, 'J6', 2, 0, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (17, 6, 1, 'D1', 2, 0, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (37, 32, 4, 'A1', 13, 30000, 1, '2018-01-02');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (38, 32, 4, 'F6', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (39, 32, 4, 'F4', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (40, 32, 4, 'J1', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (25, 32, 4, 'A10', 13, 30000, 1, '2018-01-03');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (41, 32, 4, 'A1', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (42, 32, 4, 'A2', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (43, 32, 4, 'B1', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (44, 32, 4, 'C1', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (45, 32, 4, 'A4', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (46, 32, 4, 'A10', 13, 30000, 1, '2018-01-01');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (47, 32, 10, 'A1', 14, 123123, 1, '2018-01-02');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (48, 32, 10, 'A2', 14, 123123, 1, '2018-01-02');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (49, 32, 10, 'A3', 14, 123123, 1, '2018-01-02');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (50, 32, 10, 'A4', 14, 123123, 1, '2018-01-02');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (51, 32, 10, 'A4', 14, 123123, 1, '2018-01-02');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (52, 32, 10, 'A5', 14, 123123, 1, '2018-01-02');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (53, 32, 10, 'A6', 14, 123123, 1, '2018-01-02');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (54, 33, 7, 'G5', 16, 500000, 1, '2018-01-02');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (55, 33, 7, 'G6', 16, 500000, 1, '2018-01-02');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (56, 33, 7, 'I3', 16, 500000, 1, '2018-01-02');
INSERT INTO filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) VALUES (57, 33, 7, 'I4', 16, 500000, 1, '2018-01-02');


--
-- Name: filmticket_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('filmticket_id_seq', 57, true);


--
-- Data for Name: fnbsize; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO fnbsize (id, size, storeid, status) VALUES (1, 'Regular', 1, true);
INSERT INTO fnbsize (id, size, storeid, status) VALUES (3, 'Jumbo', 1, true);
INSERT INTO fnbsize (id, size, storeid, status) VALUES (2, 'LargeBgt', 1, true);
INSERT INTO fnbsize (id, size, storeid, status) VALUES (4, 'Besarrr Banget', 1, false);


--
-- Name: fnbsize_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fnbsize_id_seq', 14, true);


--
-- Data for Name: fnbtype; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO fnbtype (id, type, storeid, status) VALUES (4, 'barubgt', 1, false);
INSERT INTO fnbtype (id, type, storeid, status) VALUES (2, 'Beverages', 1, true);
INSERT INTO fnbtype (id, type, storeid, status) VALUES (1, 'Food', 1, true);
INSERT INTO fnbtype (id, type, storeid, status) VALUES (3, 'ComboGila', 1, true);


--
-- Name: fnbtype_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fnbtype_id_seq', 11, true);


--
-- Data for Name: foodandbeverages; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO foodandbeverages (id, storeid, cover, name, type, size, price, status) VALUES (8, 1, '/1/fnb/Walala (1) [20171229091302].jpg', 'Walala', 3, 1, 10000, true);
INSERT INTO foodandbeverages (id, storeid, cover, name, type, size, price, status) VALUES (9, 1, '/1/fnb/tes (1) [20171229091152].jpg', 'tes', 1, 1, 100, true);
INSERT INTO foodandbeverages (id, storeid, cover, name, type, size, price, status) VALUES (10, 1, '/1/fnb/tes (1) [20171229091152].jpg', 'tes', 1, 1, 100, true);
INSERT INTO foodandbeverages (id, storeid, cover, name, type, size, price, status) VALUES (11, 1, '/1/fnb/tes (1) [20171229091152].jpg', 'tes', 1, 1, 100, true);
INSERT INTO foodandbeverages (id, storeid, cover, name, type, size, price, status) VALUES (12, 1, '/1/fnb/tes (1) [20171229091152].jpg', 'tes', 1, 1, 100, true);
INSERT INTO foodandbeverages (id, storeid, cover, name, type, size, price, status) VALUES (13, 1, '/1/fnb/tes (1) [20171229091152].jpg', 'tes', 1, 1, 100, true);
INSERT INTO foodandbeverages (id, storeid, cover, name, type, size, price, status) VALUES (14, 1, '/1/fnb/tes (1) [20171229091152].jpg', 'tes', 1, 1, 100, true);
INSERT INTO foodandbeverages (id, storeid, cover, name, type, size, price, status) VALUES (15, 1, '/1/fnb/tes (1) [20171229091152].jpg', 'tes', 1, 1, 100, true);
INSERT INTO foodandbeverages (id, storeid, cover, name, type, size, price, status) VALUES (16, 1, '/1/fnb/tes (1) [20171229091152].jpg', 'tes', 1, 1, 100, true);
INSERT INTO foodandbeverages (id, storeid, cover, name, type, size, price, status) VALUES (17, 1, '/1/fnb/tes (1) [20171229091152].jpg', 'tes', 1, 1, 100, true);
INSERT INTO foodandbeverages (id, storeid, cover, name, type, size, price, status) VALUES (18, 1, '/1/fnb/tes (1) [20171229091152].jpg', 'tes', 1, 1, 100, true);
INSERT INTO foodandbeverages (id, storeid, cover, name, type, size, price, status) VALUES (19, 1, '/1/fnb/tes (1) [20171229091152].jpg', 'tes', 1, 1, 100, true);
INSERT INTO foodandbeverages (id, storeid, cover, name, type, size, price, status) VALUES (7, 1, '/1/fnb/wendyyyy (1) [20171229091252].jpg', 'wendyyyyLA', 3, 1, 20000, true);


--
-- Name: foodandbeverages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('foodandbeverages_id_seq', 19, true);


--
-- Data for Name: invoice; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (45, NULL, 1, 1, NULL, '2017-11-27 12:58:53', 6169750);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (12, NULL, 1, 1, NULL, '2017-11-19 16:28:18', 13200);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (17, NULL, 1, 1, NULL, '2017-11-27 10:46:13', 2482420);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (18, NULL, 1, 1, NULL, '2017-11-27 10:46:54', 12381740);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (50, NULL, 1, 1, NULL, '2017-11-27 13:02:28', 1320);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (30, NULL, 1, 1, NULL, '2017-11-27 11:07:18', 10970539);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (32, NULL, 1, 1, NULL, '2017-11-27 12:37:53', 1237910);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (31, NULL, 1, 1, NULL, '2017-11-27 11:11:35', 1244510);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (10, NULL, 1, 1, NULL, '2017-11-19 16:20:11', 13200);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (27, NULL, 1, 1, NULL, '2017-11-27 11:02:51', 12201849);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (26, NULL, 1, 1, NULL, '2017-11-27 11:01:04', 1120059);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (29, NULL, 1, 1, NULL, '2017-11-27 11:05:43', 1120059);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (28, NULL, 1, 1, NULL, '2017-11-27 11:04:27', 1120059);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (23, NULL, 1, 1, NULL, '2017-11-27 10:58:14', 1355893);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (22, NULL, 1, 1, NULL, '2017-11-27 10:58:07', 1355893);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (25, NULL, 1, 1, NULL, '2017-11-27 10:58:46', 1355893);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (24, NULL, 1, 1, NULL, '2017-11-27 10:58:21', 1355893);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (2, 1, 1, 1, NULL, '2017-10-22 12:16:54.509', 10000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (61, NULL, 1, 1, NULL, '2017-11-28 13:09:21', 1243950);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (63, NULL, 1, 1, NULL, '2017-11-28 13:23:11', 2473940);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (62, NULL, 1, 1, NULL, '2017-11-28 13:19:17', 2473940);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (75, NULL, 1, 1, NULL, '2017-12-21 13:54:46', 23232323);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (64, NULL, 1, 1, NULL, '2017-11-28 13:23:38', 2475820);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (42, NULL, 1, 1, NULL, '2017-11-27 12:58:53', 6169750);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (65, 1, 1, 1, 1, '2017-11-28 13:25:28', 10188);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (60, NULL, 1, 1, NULL, '2017-11-28 13:08:06', 2473940);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (59, NULL, 1, 1, NULL, '2017-11-28 13:07:27', 11320);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (56, NULL, 1, 1, NULL, '2017-11-28 13:04:24', 23200);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (55, 1, 1, 1, 1, '2017-11-28 12:45:33', 14940);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (58, NULL, 1, 1, NULL, '2017-11-28 13:06:36', 23200);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (57, NULL, 1, 1, NULL, '2017-11-28 13:04:26', 23200);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (74, NULL, 1, 1, NULL, '2017-12-21 13:51:18', 1161616150);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (9, NULL, 1, 1, NULL, '2017-11-19 09:19:33.575', 100);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (52, NULL, 1, 1, NULL, '2017-11-27 13:02:49', 2640);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (51, NULL, 1, 1, NULL, '2017-11-27 13:02:36', 1233950);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (54, 1, 1, 1, 1, '2017-11-27 13:03:39', 2228238);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (53, 1, 1, 1, 1, '2017-11-27 13:02:58', 11880);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (21, NULL, 1, 1, NULL, '2017-11-27 10:57:58', 1355893);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (20, NULL, 1, 1, NULL, '2017-11-27 10:57:34', 1355893);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (49, NULL, 1, 1, NULL, '2017-11-27 13:02:17', 13200);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (48, NULL, 1, 1, NULL, '2017-11-27 13:02:17', 13200);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (16, NULL, 1, 1, NULL, '2017-11-19 16:37:23', 13200);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (15, NULL, 1, 1, NULL, '2017-11-19 16:34:25', 13200);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (44, NULL, 1, 1, NULL, '2017-11-27 12:58:53', 6169750);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (47, NULL, 1, 1, NULL, '2017-11-27 12:58:54', 6169750);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (46, NULL, 1, 1, NULL, '2017-11-27 12:58:53', 6169750);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (11, NULL, 1, 1, NULL, '2017-11-19 16:27:39', 13200);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (14, NULL, 1, 1, NULL, '2017-11-19 16:32:14', 13200);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (13, NULL, 1, 1, NULL, '2017-11-19 16:29:54', 12313100);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (68, 1, 1, 1, 1, '2017-12-04 00:12:21', 81000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (69, 1, 1, 1, 1, '2017-12-04 00:22:44', 81000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (67, NULL, 1, 1, NULL, '2017-11-30 10:58:22', 1320);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (70, NULL, 1, 1, NULL, '2017-12-04 13:34:37', 60132);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (81, 1, 1, 1, 1, '2017-12-24 13:49:49', 72000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (80, 1, 1, 1, 1, '2017-12-24 13:43:49', 72000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (83, 1, 1, 1, 1, '2017-12-28 10:30:42', 200810.70000000001);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (82, NULL, 1, 1, NULL, '2017-12-24 14:00:06', 60000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (78, 1, 1, 1, 1, '2017-12-21 13:58:44', 45000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (77, NULL, 1, 1, NULL, '2017-12-21 13:58:24', 10000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (79, 1, 1, 1, 1, '2017-12-21 20:33:25', 90000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (1, NULL, 1, 1, NULL, '2017-10-22 12:16:54.509', 10000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (4, NULL, 1, 1, NULL, '2016-11-19 16:14:15', 13200);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (5, NULL, 1, 1, NULL, '2017-11-19 16:16:52', 1320);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (73, 1, 1, 1, 1, '2017-12-04 13:40:05', 27118.799999999999);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (76, 1, 1, 1, 1, '2017-12-21 13:56:40', 209090907);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (41, NULL, 1, 1, NULL, '2017-11-27 12:58:52', 6169750);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (40, NULL, 1, 1, NULL, '2017-11-27 12:58:52', 6169750);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (43, NULL, 1, 1, NULL, '2017-11-27 12:58:53', 6169750);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (72, 1, 1, 1, 1, '2017-12-04 13:36:33', 54000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (71, NULL, 1, 1, NULL, '2017-12-04 13:34:48', 23292323);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (38, NULL, 1, 1, NULL, '2017-11-27 12:58:52', 6169750);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (37, NULL, 1, 1, NULL, '2017-11-27 12:58:51', 6169750);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (66, 1, 1, 1, 1, '2017-11-28 13:25:41', 1188);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (39, NULL, 1, 1, NULL, '2017-11-27 12:58:52', 6169750);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (34, NULL, 1, 1, NULL, '2017-11-27 12:57:05', 1233950);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (33, NULL, 1, 1, NULL, '2017-11-27 12:54:25', 618295);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (36, 1, 1, 1, 1, '2017-11-27 12:58:36', 5552775);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (35, 1, 1, 1, 1, '2017-11-27 12:58:31', 5552775);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (19, NULL, 1, 1, NULL, '2017-11-27 10:50:49', 2464204);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (84, 1, 4, 1, 1, '2017-12-28 11:08:42', 9000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (85, NULL, 1, 1, NULL, '2017-12-29 08:57:14', 23262323);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (86, NULL, 1, 1, NULL, '2017-12-29 09:16:48', 30000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (87, NULL, 1, 1, NULL, '2017-12-29 09:17:15', 10000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (88, 1, 1, 1, 1, '2017-12-29 09:17:35', 90);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (89, 1, 1, 1, 1, '2017-12-29 09:42:36', 1080);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (90, NULL, 1, 1, NULL, '2017-12-29 09:45:02', 100);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (91, 1, 1, 1, 1, '2017-12-29 10:18:55', 27990);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (92, 1, 1, 1, 1, '2017-12-31 00:34:34', 90);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (93, 1, 1, 1, 1, '2018-01-02 09:41:54', 117450);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (94, NULL, 4, 1, NULL, '2018-01-02 11:36:25', 1000000);
INSERT INTO invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (95, NULL, 3, 1, NULL, '2018-01-02 12:21:40', 1000200);


--
-- Name: invoice_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('invoice_id_seq', 95, true);


--
-- Data for Name: membercard; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO membercard (id, storeid, fullname, gender, birthdate, phonenumber, email, status) VALUES (2, 1, 'asd', 2, '2017-12-31 00:00:00', '12312', 'wendydamarA.wb@gmail.com', true);
INSERT INTO membercard (id, storeid, fullname, gender, birthdate, phonenumber, email, status) VALUES (3, 1, 'as', 2, '2017-12-31 00:00:00', '123', 'we@aA', false);
INSERT INTO membercard (id, storeid, fullname, gender, birthdate, phonenumber, email, status) VALUES (1, 1, 'Tampanaaa', 2, '2017-12-31 00:00:00', '1230000000', 'wendydamar.wb@gmail.com', false);


--
-- Name: membercard_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('membercard_id_seq', 12, true);


--
-- Data for Name: membergender; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO membergender (id, gender, storeid, status) VALUES (2, 'Wanita', 1, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (1, 'Pria', 1, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (16, 'Pria', 16, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (17, 'Wanita', 16, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (18, 'Pria', 17, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (19, 'Wanita', 17, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (20, 'Pria', 18, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (21, 'Wanita', 18, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (22, 'Pria', 19, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (23, 'Wanita', 19, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (24, 'Pria', 19, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (25, 'Wanita', 19, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (26, 'Pria', 19, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (27, 'Wanita', 19, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (28, 'Pria', 19, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (29, 'Wanita', 19, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (30, 'Pria', 23, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (31, 'Wanita', 23, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (32, 'Pria', 24, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (33, 'Wanita', 24, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (34, 'Pria', 25, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (35, 'Wanita', 25, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (36, 'Pria', 26, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (37, 'Wanita', 26, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (38, 'Pria', 27, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (39, 'Wanita', 27, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (40, 'Pria', 28, true);
INSERT INTO membergender (id, gender, storeid, status) VALUES (41, 'Wanita', 28, true);


--
-- Name: membergender_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('membergender_id_seq', 41, true);


--
-- Data for Name: orderdetail; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (48, 51, 1, 'Mempeseona', 20, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (39, 42, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (38, 42, 1, 'asdasd', 50, 123131, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (37, 42, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (50, 52, 1, 'Mempeseona', 20, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (36, 42, 1, 'asdasd', 50, 123131, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (84, 79, 1, 'Walala', 10, 10000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (54, 55, 1, 'Mempeseona', 50, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (30, 38, 1, 'asdasd', 50, 123131, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (52, 54, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (43, 47, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (42, 42, 1, 'asdasd', 50, 123131, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (41, 42, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (40, 42, 1, 'asdasd', 50, 123131, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (55, 60, 1, 'ad', 1, 10000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (56, 60, 1, 'asdasd', 20, 123131, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (57, 61, 1, 'ad', 1, 10000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (59, 62, 1, 'ad', 1, 10000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (53, 54, 1, 'asdasd', 20, 123131, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (61, 63, 1, 'ad', 1, 10000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (60, 62, 1, 'asdasd', 20, 123131, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (65, 64, 1, 'Mempeseona', 100, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (62, 63, 1, 'asdasd', 20, 123131, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (63, 63, 1, 'Mempeseona', 10, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (10, 30, 1, 'asdasd', 89, 123131, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (49, 51, 1, 'asdasd', 10, 123131, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (64, 64, 1, 'asdasd', 20, 123131, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (14, 32, 1, 'asdasd', 10, 123131, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (13, 32, 1, 'Mempeseona', 50, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (12, 31, 1, 'asdasd', 10, 123131, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (11, 31, 1, 'Mempeseona', 100, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (18, 34, 1, 'asdasd', 10, 123131, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (17, 34, 1, 'Mempeseona', 20, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (16, 33, 1, 'asdasd', 5, 123131, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (15, 33, 1, 'Mempeseona', 20, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (19, 35, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (77, 73, 1, '11111', 1, 30000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (21, 36, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (20, 35, 1, 'asdasd', 50, 123131, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (69, 67, 1, 'Mempeseona', 10, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (68, 66, 1, 'Mempeseona', 10, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (67, 65, 1, 'Mempeseona', 10, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (66, 65, 1, 'ad', 1, 10000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (71, 69, 1, '11111', 3, 30000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (8, 27, 1, 'Mempeseona', 90, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (3, 1, 1, 'indomi', 2, 4000, NULL);
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (2, 1, 1, 'sampo', 3, 3000, NULL);
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (1, 1, 1, 'sabun', 5, 3000, NULL);
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (7, 26, 1, 'Mempeseona', 90, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (6, 25, 1, 'Mempeseona', 11, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (5, 24, 1, 'Mempeseona', 11, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (4, 2, 1, 'selada', 1, 11000, NULL);
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (89, 81, 1, 'Walala', 2, 10000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (9, 30, 1, 'Mempeseona', 90, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (51, 53, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (31, 38, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (72, 70, 1, '11111', 2, 30000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (58, 61, 1, 'asdasd', 10, 123131, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (70, 68, 1, '11111', 3, 30000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (76, 72, 1, '11111', 2, 30000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (75, 71, 1, 'wendyyyy', 1, 23232323, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (74, 71, 1, '11111', 2, 30000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (73, 70, 1, 'Mempeseona', 1, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (88, 81, 1, '11111', 2, 30000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (79, 74, 1, 'wendyyyy', 50, 23232323, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (78, 73, 1, 'Mempeseona', 1, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (25, 38, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (24, 37, 1, 'asdasd', 50, 123131, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (23, 37, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (22, 36, 1, 'asdasd', 50, 123131, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (29, 38, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (28, 38, 1, 'asdasd', 50, 123131, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (27, 38, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (26, 38, 1, 'asdasd', 50, 123131, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (90, 82, 1, '11111', 2, 30000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (83, 78, 1, 'Walala', 5, 10000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (82, 77, 1, 'Walala', 1, 10000, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (81, 76, 1, 'wendyyyy', 10, 23232323, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (80, 75, 1, 'wendyyyy', 1, 23232323, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (87, 80, 1, 'wendyyyy', 0, 23232323, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (86, 80, 1, 'Walala', 2, 10000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (85, 80, 1, '11111', 2, 30000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (32, 38, 1, 'asdasd', 50, 123131, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (47, 50, 1, 'Mempeseona', 10, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (46, 48, 1, 'Mempeseona', 100, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (45, 48, 1, 'Mempeseona', 100, 132, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (44, 47, 1, 'asdasd', 50, 123131, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (35, 42, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (34, 42, 1, 'asdasd', 50, 123131, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (33, 42, 1, 'Mempeseona', 100, 132, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (91, 83, 1, '11111', 1, 123123, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (92, 83, 1, 'Walala', 10, 10000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (93, 84, 1, 'Walala', 1, 10000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (94, 85, 1, 'Avatar', 1, 30000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (95, 85, 1, 'wendyyyy', 1, 23232323, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (96, 86, 1, 'Avatar', 1, 30000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (97, 87, 1, 'Walala', 1, 10000, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (98, 88, 1, 'tes', 1, 100, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (99, 89, 1, 'tes', 12, 100, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (100, 90, 1, 'tes', 1, 100, 'false');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (101, 91, 1, 'Avatar', 1, 30000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (102, 91, 1, 'tes', 11, 100, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (103, 92, 1, 'tes', 1, 100, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (104, 93, 1, 'tes', 1, 100, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (105, 93, 1, 'tes', 4, 100, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (106, 93, 1, 'Walala', 3, 10000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (107, 93, 1, 'wendyyyy', 5, 20000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (108, 94, 1, 'asd', 2, 500000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (109, 95, 1, 'asd', 2, 500000, 'true');
INSERT INTO orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (110, 95, 1, 'tes', 2, 100, 'false');


--
-- Name: orderdetail_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('orderdetail_id_seq', 110, true);


--
-- Data for Name: promo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO promo (id, storeid, name, description, status, discountamount) VALUES (1, 1, 'ululu', 'diskon 10%', true, 10);
INSERT INTO promo (id, storeid, name, description, status, discountamount) VALUES (4, 16, 'tes', 'tes', true, 10);
INSERT INTO promo (id, storeid, name, description, status, discountamount) VALUES (5, 17, 'tes', 'tes', true, 10);
INSERT INTO promo (id, storeid, name, description, status, discountamount) VALUES (6, 18, 'tes', 'tes', true, 10);
INSERT INTO promo (id, storeid, name, description, status, discountamount) VALUES (7, 23, 'tes', 'tes', true, 10);
INSERT INTO promo (id, storeid, name, description, status, discountamount) VALUES (8, 24, 'tes', 'tes', true, 10);
INSERT INTO promo (id, storeid, name, description, status, discountamount) VALUES (9, 25, 'tes', 'tes', true, 10);
INSERT INTO promo (id, storeid, name, description, status, discountamount) VALUES (10, 26, 'tes', 'tes', true, 10);
INSERT INTO promo (id, storeid, name, description, status, discountamount) VALUES (11, 27, 'tes', 'tes', true, 10);
INSERT INTO promo (id, storeid, name, description, status, discountamount) VALUES (12, 28, 'tes', 'tes', true, 10);


--
-- Name: promo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('promo_id_seq', 12, true);


--
-- Data for Name: screeningtime; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (9, 6, 3, 1, '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (8, 6, 3, 1, '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (7, 6, 1, 1, '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (6, 6, 2, 1, '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (5, 6, 2, 1, '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (4, 6, 1, 1, '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (3, 6, 2, 1, '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (2, 6, 1, 1, '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (15, 32, 10, 1, '23:58:00', 1, true);
INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (10, 6, 1, 1, '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (11, 6, 1, 1, '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (12, 6, 1, 1, '23:56:00', 123, true);
INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (13, 32, 4, 1, '17:03:00', 1, true);
INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (14, 32, 10, 1, '12:12:00', 1, true);
INSERT INTO screeningtime (id, filmid, studioid, storeid, "time", duration, status) VALUES (16, 33, 7, 1, '10:10:00', 123, true);


--
-- Name: screeningtime_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('screeningtime_id_seq', 24, true);


--
-- Data for Name: seat; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO seat (number, studioid, name) VALUES ('3', 1, 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('4', 1, 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('5', 1, 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('6', 1, 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('7', 1, 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('8', 1, 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('9', 1, 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('10', 1, 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('11', 1, 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('12', 1, 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('13', 1, 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('14', 1, 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('15', 1, 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('16', 1, 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('17', 1, 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('18', 1, 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('19', 1, 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('20', 1, 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('21', 1, 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('22', 1, 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('23', 1, 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('24', 1, 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('25', 1, 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('26', 1, 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('27', 1, 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('28', 1, 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('29', 1, 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('30', 1, 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('31', 1, 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('32', 1, 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('33', 1, 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('34', 1, 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('35', 1, 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('36', 1, 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('37', 1, 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('38', 1, 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('39', 1, 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('40', 1, 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('41', 1, 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('42', 1, 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('43', 1, 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('44', 1, 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('45', 1, 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('46', 1, 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('47', 1, 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('48', 1, 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('49', 1, 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('50', 1, 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('51', 1, 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('52', 1, 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('53', 1, 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('54', 1, 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('55', 1, 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('56', 1, 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('57', 1, 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('58', 1, 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('59', 1, 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('60', 1, 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('61', 1, 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('62', 1, 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('63', 1, 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('64', 1, 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('65', 1, 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('66', 1, 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('67', 1, 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('68', 1, 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('69', 1, 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('70', 1, 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('71', 1, 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('72', 1, 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('73', 1, 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('74', 1, 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('75', 1, 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('76', 1, 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('77', 1, 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('78', 1, 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('79', 1, 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('80', 1, 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('81', 1, 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('82', 1, 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('83', 1, 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('84', 1, 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('85', 1, 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('86', 1, 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('87', 1, 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('88', 1, 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('89', 1, 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('90', 1, 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('91', 1, 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('92', 1, 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('93', 1, 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('94', 1, 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('95', 1, 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('96', 1, 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('97', 1, 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('98', 1, 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('99', 1, 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('100', 1, 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('101', 2, 'A1');
INSERT INTO seat (number, studioid, name) VALUES ('102', 2, 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('103', 2, 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('104', 2, 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('105', 2, 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('106', 2, 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('107', 2, 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('108', 2, 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('109', 2, 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('110', 2, 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('111', 2, 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('112', 2, 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('113', 2, 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('114', 2, 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('115', 2, 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('116', 2, 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('117', 2, 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('118', 2, 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('119', 2, 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('120', 2, 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('121', 2, 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('122', 2, 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('123', 2, 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('124', 2, 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('125', 2, 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('126', 2, 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('127', 2, 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('128', 2, 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('129', 2, 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('130', 2, 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('131', 2, 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('132', 2, 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('133', 2, 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('134', 2, 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('135', 2, 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('136', 2, 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('137', 2, 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('138', 2, 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('139', 2, 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('140', 2, 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('141', 2, 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('142', 2, 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('143', 2, 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('144', 2, 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('145', 2, 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('146', 2, 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('147', 2, 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('148', 2, 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('149', 2, 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('150', 2, 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('151', 2, 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('152', 2, 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('153', 2, 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('154', 2, 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('155', 2, 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('156', 2, 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('157', 2, 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('158', 2, 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('159', 2, 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('160', 2, 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('161', 2, 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('162', 2, 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('163', 2, 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('164', 2, 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('165', 2, 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('166', 2, 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('167', 2, 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('168', 2, 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('169', 2, 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('170', 2, 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('171', 2, 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('172', 2, 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('173', 2, 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('174', 2, 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('175', 2, 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('176', 2, 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('177', 2, 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('178', 2, 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('179', 2, 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('180', 2, 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('181', 2, 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('182', 2, 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('183', 2, 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('184', 2, 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('185', 2, 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('186', 2, 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('187', 2, 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('188', 2, 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('189', 2, 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('190', 2, 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('191', 2, 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('192', 2, 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('193', 2, 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('194', 2, 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('195', 2, 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('196', 2, 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('197', 2, 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('198', 2, 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('199', 2, 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('200', 2, 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('201', 3, 'A1');
INSERT INTO seat (number, studioid, name) VALUES ('202', 3, 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('203', 3, 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('204', 3, 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('205', 3, 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('206', 3, 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('207', 3, 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('208', 3, 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('209', 3, 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('210', 3, 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('211', 3, 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('212', 3, 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('213', 3, 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('214', 3, 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('215', 3, 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('216', 3, 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('217', 3, 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('218', 3, 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('219', 3, 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('220', 3, 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('221', 3, 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('222', 3, 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('223', 3, 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('224', 3, 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('225', 3, 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('226', 3, 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('227', 3, 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('228', 3, 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('229', 3, 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('230', 3, 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('231', 3, 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('232', 3, 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('233', 3, 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('234', 3, 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('235', 3, 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('236', 3, 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('237', 3, 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('238', 3, 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('239', 3, 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('240', 3, 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('241', 3, 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('242', 3, 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('243', 3, 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('244', 3, 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('245', 3, 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('246', 3, 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('247', 3, 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('248', 3, 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('249', 3, 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('250', 3, 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('251', 3, 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('252', 3, 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('253', 3, 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('254', 3, 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('255', 3, 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('256', 3, 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('257', 3, 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('258', 3, 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('259', 3, 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('260', 3, 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('261', 3, 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('262', 3, 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('263', 3, 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('264', 3, 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('265', 3, 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('266', 3, 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('267', 3, 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('268', 3, 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('269', 3, 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('270', 3, 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('271', 3, 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('272', 3, 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('273', 3, 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('274', 3, 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('275', 3, 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('276', 3, 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('277', 3, 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('278', 3, 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('279', 3, 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('280', 3, 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('281', 3, 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('282', 3, 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('283', 3, 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('284', 3, 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('285', 3, 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('286', 3, 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('287', 3, 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('288', 3, 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('289', 3, 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('290', 3, 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('291', 3, 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('292', 3, 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('293', 3, 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('294', 3, 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('295', 3, 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('296', 3, 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('297', 3, 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('298', 3, 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('299', 3, 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('300', 3, 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('301', 4, 'A1');
INSERT INTO seat (number, studioid, name) VALUES ('302', 4, 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('303', 4, 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('304', 4, 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('305', 4, 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('306', 4, 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('307', 4, 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('308', 4, 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('309', 4, 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('310', 4, 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('311', 4, 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('312', 4, 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('313', 4, 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('314', 4, 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('315', 4, 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('316', 4, 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('317', 4, 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('318', 4, 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('319', 4, 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('320', 4, 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('321', 4, 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('322', 4, 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('323', 4, 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('324', 4, 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('325', 4, 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('326', 4, 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('327', 4, 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('328', 4, 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('329', 4, 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('330', 4, 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('331', 4, 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('332', 4, 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('333', 4, 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('334', 4, 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('335', 4, 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('336', 4, 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('337', 4, 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('338', 4, 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('339', 4, 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('340', 4, 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('341', 4, 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('342', 4, 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('343', 4, 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('344', 4, 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('345', 4, 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('346', 4, 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('347', 4, 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('348', 4, 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('349', 4, 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('350', 4, 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('351', 4, 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('352', 4, 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('353', 4, 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('354', 4, 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('355', 4, 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('356', 4, 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('357', 4, 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('358', 4, 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('359', 4, 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('360', 4, 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('361', 4, 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('362', 4, 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('363', 4, 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('364', 4, 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('365', 4, 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('366', 4, 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('367', 4, 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('368', 4, 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('369', 4, 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('370', 4, 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('371', 4, 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('372', 4, 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('373', 4, 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('374', 4, 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('375', 4, 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('376', 4, 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('377', 4, 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('378', 4, 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('379', 4, 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('380', 4, 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('381', 4, 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('382', 4, 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('383', 4, 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('384', 4, 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('385', 4, 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('386', 4, 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('387', 4, 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('388', 4, 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('389', 4, 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('390', 4, 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('391', 4, 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('392', 4, 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('393', 4, 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('394', 4, 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('395', 4, 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('396', 4, 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('397', 4, 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('398', 4, 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('399', 4, 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('400', 4, 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('401', 5, 'A1');
INSERT INTO seat (number, studioid, name) VALUES ('402', 5, 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('403', 5, 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('404', 5, 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('405', 5, 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('406', 5, 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('407', 5, 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('408', 5, 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('409', 5, 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('410', 5, 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('411', 5, 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('412', 5, 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('413', 5, 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('414', 5, 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('415', 5, 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('416', 5, 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('417', 5, 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('418', 5, 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('419', 5, 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('420', 5, 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('421', 5, 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('422', 5, 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('423', 5, 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('424', 5, 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('425', 5, 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('426', 5, 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('427', 5, 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('428', 5, 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('429', 5, 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('430', 5, 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('431', 5, 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('432', 5, 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('433', 5, 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('434', 5, 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('435', 5, 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('436', 5, 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('437', 5, 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('438', 5, 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('439', 5, 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('440', 5, 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('441', 5, 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('442', 5, 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('443', 5, 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('444', 5, 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('445', 5, 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('446', 5, 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('447', 5, 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('448', 5, 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('449', 5, 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('450', 5, 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('451', 5, 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('452', 5, 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('453', 5, 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('454', 5, 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('455', 5, 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('456', 5, 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('457', 5, 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('458', 5, 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('459', 5, 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('460', 5, 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('461', 5, 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('462', 5, 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('463', 5, 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('464', 5, 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('465', 5, 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('466', 5, 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('467', 5, 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('468', 5, 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('469', 5, 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('470', 5, 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('471', 5, 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('472', 5, 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('473', 5, 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('474', 5, 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('475', 5, 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('476', 5, 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('477', 5, 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('478', 5, 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('479', 5, 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('480', 5, 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('481', 5, 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('482', 5, 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('483', 5, 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('484', 5, 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('485', 5, 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('486', 5, 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('487', 5, 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('488', 5, 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('489', 5, 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('490', 5, 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('491', 5, 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('492', 5, 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('493', 5, 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('494', 5, 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('495', 5, 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('496', 5, 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('497', 5, 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('498', 5, 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('499', 5, 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('500', 5, 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('501', 6, 'A1');
INSERT INTO seat (number, studioid, name) VALUES ('502', 6, 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('503', 6, 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('504', 6, 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('505', 6, 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('506', 6, 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('507', 6, 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('508', 6, 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('509', 6, 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('510', 6, 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('511', 6, 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('512', 6, 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('513', 6, 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('514', 6, 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('515', 6, 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('516', 6, 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('517', 6, 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('518', 6, 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('519', 6, 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('520', 6, 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('521', 6, 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('522', 6, 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('523', 6, 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('524', 6, 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('525', 6, 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('526', 6, 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('527', 6, 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('528', 6, 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('529', 6, 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('530', 6, 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('531', 6, 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('532', 6, 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('533', 6, 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('534', 6, 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('535', 6, 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('536', 6, 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('537', 6, 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('538', 6, 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('539', 6, 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('540', 6, 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('541', 6, 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('542', 6, 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('543', 6, 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('544', 6, 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('545', 6, 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('546', 6, 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('547', 6, 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('548', 6, 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('549', 6, 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('550', 6, 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('551', 6, 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('552', 6, 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('553', 6, 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('554', 6, 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('555', 6, 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('556', 6, 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('557', 6, 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('558', 6, 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('559', 6, 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('560', 6, 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('561', 6, 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('562', 6, 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('563', 6, 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('564', 6, 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('565', 6, 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('566', 6, 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('567', 6, 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('568', 6, 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('569', 6, 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('570', 6, 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('571', 6, 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('572', 6, 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('573', 6, 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('574', 6, 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('575', 6, 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('576', 6, 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('577', 6, 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('578', 6, 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('579', 6, 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('580', 6, 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('581', 6, 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('582', 6, 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('583', 6, 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('584', 6, 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('585', 6, 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('586', 6, 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('587', 6, 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('588', 6, 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('589', 6, 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('590', 6, 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('591', 6, 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('592', 6, 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('593', 6, 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('594', 6, 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('595', 6, 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('596', 6, 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('597', 6, 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('598', 6, 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('599', 6, 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('600', 6, 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('601', 7, 'A1');
INSERT INTO seat (number, studioid, name) VALUES ('602', 7, 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('603', 7, 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('604', 7, 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('605', 7, 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('606', 7, 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('607', 7, 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('608', 7, 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('609', 7, 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('610', 7, 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('611', 7, 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('612', 7, 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('613', 7, 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('614', 7, 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('615', 7, 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('616', 7, 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('617', 7, 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('618', 7, 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('619', 7, 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('620', 7, 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('621', 7, 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('622', 7, 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('623', 7, 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('624', 7, 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('625', 7, 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('626', 7, 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('627', 7, 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('628', 7, 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('629', 7, 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('630', 7, 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('631', 7, 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('632', 7, 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('633', 7, 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('634', 7, 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('635', 7, 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('636', 7, 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('637', 7, 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('638', 7, 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('639', 7, 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('640', 7, 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('641', 7, 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('642', 7, 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('643', 7, 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('644', 7, 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('645', 7, 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('646', 7, 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('647', 7, 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('648', 7, 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('649', 7, 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('650', 7, 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('651', 7, 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('652', 7, 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('653', 7, 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('654', 7, 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('655', 7, 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('656', 7, 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('657', 7, 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('658', 7, 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('659', 7, 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('660', 7, 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('661', 7, 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('662', 7, 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('663', 7, 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('664', 7, 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('665', 7, 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('666', 7, 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('667', 7, 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('668', 7, 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('669', 7, 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('670', 7, 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('671', 7, 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('672', 7, 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('673', 7, 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('674', 7, 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('675', 7, 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('676', 7, 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('677', 7, 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('678', 7, 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('679', 7, 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('680', 7, 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('681', 7, 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('682', 7, 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('683', 7, 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('684', 7, 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('685', 7, 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('686', 7, 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('687', 7, 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('688', 7, 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('689', 7, 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('690', 7, 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('691', 7, 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('692', 7, 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('693', 7, 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('694', 7, 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('695', 7, 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('696', 7, 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('697', 7, 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('698', 7, 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('699', 7, 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('700', 7, 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('701', 8, 'A1');
INSERT INTO seat (number, studioid, name) VALUES ('702', 8, 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('703', 8, 'A3');
INSERT INTO seat (number, studioid, name) VALUES ('704', 8, 'A4');
INSERT INTO seat (number, studioid, name) VALUES ('705', 8, 'A5');
INSERT INTO seat (number, studioid, name) VALUES ('706', 8, 'A6');
INSERT INTO seat (number, studioid, name) VALUES ('707', 8, 'A7');
INSERT INTO seat (number, studioid, name) VALUES ('708', 8, 'A8');
INSERT INTO seat (number, studioid, name) VALUES ('709', 8, 'A9');
INSERT INTO seat (number, studioid, name) VALUES ('710', 8, 'A10');
INSERT INTO seat (number, studioid, name) VALUES ('711', 8, 'B1');
INSERT INTO seat (number, studioid, name) VALUES ('712', 8, 'B2');
INSERT INTO seat (number, studioid, name) VALUES ('713', 8, 'B3');
INSERT INTO seat (number, studioid, name) VALUES ('714', 8, 'B4');
INSERT INTO seat (number, studioid, name) VALUES ('715', 8, 'B5');
INSERT INTO seat (number, studioid, name) VALUES ('716', 8, 'B6');
INSERT INTO seat (number, studioid, name) VALUES ('717', 8, 'B7');
INSERT INTO seat (number, studioid, name) VALUES ('718', 8, 'B8');
INSERT INTO seat (number, studioid, name) VALUES ('719', 8, 'B9');
INSERT INTO seat (number, studioid, name) VALUES ('720', 8, 'B10');
INSERT INTO seat (number, studioid, name) VALUES ('721', 8, 'C1');
INSERT INTO seat (number, studioid, name) VALUES ('722', 8, 'C2');
INSERT INTO seat (number, studioid, name) VALUES ('723', 8, 'C3');
INSERT INTO seat (number, studioid, name) VALUES ('724', 8, 'C4');
INSERT INTO seat (number, studioid, name) VALUES ('725', 8, 'C5');
INSERT INTO seat (number, studioid, name) VALUES ('726', 8, 'C6');
INSERT INTO seat (number, studioid, name) VALUES ('727', 8, 'C7');
INSERT INTO seat (number, studioid, name) VALUES ('728', 8, 'C8');
INSERT INTO seat (number, studioid, name) VALUES ('729', 8, 'C9');
INSERT INTO seat (number, studioid, name) VALUES ('730', 8, 'C10');
INSERT INTO seat (number, studioid, name) VALUES ('731', 8, 'D1');
INSERT INTO seat (number, studioid, name) VALUES ('732', 8, 'D2');
INSERT INTO seat (number, studioid, name) VALUES ('733', 8, 'D3');
INSERT INTO seat (number, studioid, name) VALUES ('734', 8, 'D4');
INSERT INTO seat (number, studioid, name) VALUES ('735', 8, 'D5');
INSERT INTO seat (number, studioid, name) VALUES ('736', 8, 'D6');
INSERT INTO seat (number, studioid, name) VALUES ('737', 8, 'D7');
INSERT INTO seat (number, studioid, name) VALUES ('738', 8, 'D8');
INSERT INTO seat (number, studioid, name) VALUES ('739', 8, 'D9');
INSERT INTO seat (number, studioid, name) VALUES ('740', 8, 'D10');
INSERT INTO seat (number, studioid, name) VALUES ('741', 8, 'E1');
INSERT INTO seat (number, studioid, name) VALUES ('742', 8, 'E2');
INSERT INTO seat (number, studioid, name) VALUES ('743', 8, 'E3');
INSERT INTO seat (number, studioid, name) VALUES ('744', 8, 'E4');
INSERT INTO seat (number, studioid, name) VALUES ('745', 8, 'E5');
INSERT INTO seat (number, studioid, name) VALUES ('746', 8, 'E6');
INSERT INTO seat (number, studioid, name) VALUES ('747', 8, 'E7');
INSERT INTO seat (number, studioid, name) VALUES ('748', 8, 'E8');
INSERT INTO seat (number, studioid, name) VALUES ('749', 8, 'E9');
INSERT INTO seat (number, studioid, name) VALUES ('750', 8, 'E10');
INSERT INTO seat (number, studioid, name) VALUES ('751', 8, 'F1');
INSERT INTO seat (number, studioid, name) VALUES ('752', 8, 'F2');
INSERT INTO seat (number, studioid, name) VALUES ('753', 8, 'F3');
INSERT INTO seat (number, studioid, name) VALUES ('754', 8, 'F4');
INSERT INTO seat (number, studioid, name) VALUES ('755', 8, 'F5');
INSERT INTO seat (number, studioid, name) VALUES ('756', 8, 'F6');
INSERT INTO seat (number, studioid, name) VALUES ('757', 8, 'F7');
INSERT INTO seat (number, studioid, name) VALUES ('758', 8, 'F8');
INSERT INTO seat (number, studioid, name) VALUES ('759', 8, 'F9');
INSERT INTO seat (number, studioid, name) VALUES ('760', 8, 'F10');
INSERT INTO seat (number, studioid, name) VALUES ('761', 8, 'G1');
INSERT INTO seat (number, studioid, name) VALUES ('762', 8, 'G2');
INSERT INTO seat (number, studioid, name) VALUES ('763', 8, 'G3');
INSERT INTO seat (number, studioid, name) VALUES ('764', 8, 'G4');
INSERT INTO seat (number, studioid, name) VALUES ('765', 8, 'G5');
INSERT INTO seat (number, studioid, name) VALUES ('766', 8, 'G6');
INSERT INTO seat (number, studioid, name) VALUES ('767', 8, 'G7');
INSERT INTO seat (number, studioid, name) VALUES ('768', 8, 'G8');
INSERT INTO seat (number, studioid, name) VALUES ('769', 8, 'G9');
INSERT INTO seat (number, studioid, name) VALUES ('770', 8, 'G10');
INSERT INTO seat (number, studioid, name) VALUES ('771', 8, 'H1');
INSERT INTO seat (number, studioid, name) VALUES ('772', 8, 'H2');
INSERT INTO seat (number, studioid, name) VALUES ('773', 8, 'H3');
INSERT INTO seat (number, studioid, name) VALUES ('774', 8, 'H4');
INSERT INTO seat (number, studioid, name) VALUES ('775', 8, 'H5');
INSERT INTO seat (number, studioid, name) VALUES ('776', 8, 'H6');
INSERT INTO seat (number, studioid, name) VALUES ('777', 8, 'H7');
INSERT INTO seat (number, studioid, name) VALUES ('778', 8, 'H8');
INSERT INTO seat (number, studioid, name) VALUES ('779', 8, 'H9');
INSERT INTO seat (number, studioid, name) VALUES ('780', 8, 'H10');
INSERT INTO seat (number, studioid, name) VALUES ('781', 8, 'I1');
INSERT INTO seat (number, studioid, name) VALUES ('782', 8, 'I2');
INSERT INTO seat (number, studioid, name) VALUES ('783', 8, 'I3');
INSERT INTO seat (number, studioid, name) VALUES ('784', 8, 'I4');
INSERT INTO seat (number, studioid, name) VALUES ('785', 8, 'I5');
INSERT INTO seat (number, studioid, name) VALUES ('786', 8, 'I6');
INSERT INTO seat (number, studioid, name) VALUES ('787', 8, 'I7');
INSERT INTO seat (number, studioid, name) VALUES ('788', 8, 'I8');
INSERT INTO seat (number, studioid, name) VALUES ('789', 8, 'I9');
INSERT INTO seat (number, studioid, name) VALUES ('790', 8, 'I10');
INSERT INTO seat (number, studioid, name) VALUES ('791', 8, 'J1');
INSERT INTO seat (number, studioid, name) VALUES ('792', 8, 'J2');
INSERT INTO seat (number, studioid, name) VALUES ('793', 8, 'J3');
INSERT INTO seat (number, studioid, name) VALUES ('794', 8, 'J4');
INSERT INTO seat (number, studioid, name) VALUES ('795', 8, 'J5');
INSERT INTO seat (number, studioid, name) VALUES ('796', 8, 'J6');
INSERT INTO seat (number, studioid, name) VALUES ('797', 8, 'J7');
INSERT INTO seat (number, studioid, name) VALUES ('798', 8, 'J8');
INSERT INTO seat (number, studioid, name) VALUES ('799', 8, 'J9');
INSERT INTO seat (number, studioid, name) VALUES ('800', 8, 'J10');
INSERT INTO seat (number, studioid, name) VALUES ('2', 1, 'A2');
INSERT INTO seat (number, studioid, name) VALUES ('1', 1, 'A1');


--
-- Name: seat_number_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seat_number_seq', 801, true);


--
-- Data for Name: storeaccount; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO storeaccount (username, password, storename, id, status) VALUES ('blibli', '-1081416709', 'global digital niaga', 1, true);
INSERT INTO storeaccount (username, password, storename, id, status) VALUES ('bukalapak', '66882492', 'bukalapakajadulu', 16, true);
INSERT INTO storeaccount (username, password, storename, id, status) VALUES ('blibli4', '-27007852', 'blibli4', 23, true);
INSERT INTO storeaccount (username, password, storename, id, status) VALUES ('asdasd', '-1422600940', 'asdasd', 24, true);
INSERT INTO storeaccount (username, password, storename, id, status) VALUES ('blibli3', '203415793', 'bliblibaru', 19, true);
INSERT INTO storeaccount (username, password, storename, id, status) VALUES ('lala', '3314090', 'lala', 26, true);
INSERT INTO storeaccount (username, password, storename, id, status) VALUES ('s', '48661', 'ss', 28, true);
INSERT INTO storeaccount (username, password, storename, id, status) VALUES ('sdsdsd', '109297745', 'sdsdsd', 25, true);
INSERT INTO storeaccount (username, password, storename, id, status) VALUES ('blibli2', '-1386344544', 'blibli', 18, true);
INSERT INTO storeaccount (username, password, storename, id, status) VALUES ('po', '3446846', 'po', 27, true);
INSERT INTO storeaccount (username, password, storename, id, status) VALUES ('tokopedia', '344197682', 'tokopedia', 17, true);


--
-- Name: storeaccount_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('storeaccount_id_seq', 28, true);


--
-- Data for Name: studio; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO studio (id, storeid, name, type, price, status) VALUES (9, 1, 'Mantap', 1, 123, false);
INSERT INTO studio (id, storeid, name, type, price, status) VALUES (8, 1, 'H', 3, 800000, true);
INSERT INTO studio (id, storeid, name, type, price, status) VALUES (7, 1, 'E', 1, 500000, true);
INSERT INTO studio (id, storeid, name, type, price, status) VALUES (2, 1, 'B', 1, 20000, true);
INSERT INTO studio (id, storeid, name, type, price, status) VALUES (3, 1, 'F', 1, 60000, true);
INSERT INTO studio (id, storeid, name, type, price, status) VALUES (10, 1, 'Alalal', 1, 123123, true);
INSERT INTO studio (id, storeid, name, type, price, status) VALUES (5, 1, 'G', 2, 700000, true);
INSERT INTO studio (id, storeid, name, type, price, status) VALUES (6, 1, 'D', 1, 40000, true);
INSERT INTO studio (id, storeid, name, type, price, status) VALUES (4, 1, 'C', 2, 30000, true);
INSERT INTO studio (id, storeid, name, type, price, status) VALUES (11, 1, 'asds', 1, 123, true);
INSERT INTO studio (id, storeid, name, type, price, status) VALUES (1, 1, 'A', 1, 10000, true);


--
-- Name: studio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('studio_id_seq', 11, true);


--
-- Data for Name: studiotype; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO studiotype (id, type, storeid, status) VALUES (1, 'Regular', 1, true);
INSERT INTO studiotype (id, type, storeid, status) VALUES (4, 'WawwBgt', 1, false);
INSERT INTO studiotype (id, type, storeid, status) VALUES (3, 'VIP', 1, true);
INSERT INTO studiotype (id, type, storeid, status) VALUES (2, 'SuiteMantap', 1, true);


--
-- Name: studiotype_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('studiotype_id_seq', 17, true);


--
-- Data for Name: superadmin; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO superadmin (id, username, password, status) VALUES (1, 'wendy', '-1110123361', false);
INSERT INTO superadmin (id, username, password, status) VALUES (6, 'lalaa', '3583', true);
INSERT INTO superadmin (id, username, password, status) VALUES (7, 'a', '3583', true);
INSERT INTO superadmin (id, username, password, status) VALUES (8, 'b', '3583', true);
INSERT INTO superadmin (id, username, password, status) VALUES (9, 'v', '3583', true);
INSERT INTO superadmin (id, username, password, status) VALUES (10, 'd', '3583', true);
INSERT INTO superadmin (id, username, password, status) VALUES (11, 'q', '3583', true);
INSERT INTO superadmin (id, username, password, status) VALUES (12, 'w', '3583', true);
INSERT INTO superadmin (id, username, password, status) VALUES (14, 'r', '3583', true);
INSERT INTO superadmin (id, username, password, status) VALUES (15, 'z', '3583', true);
INSERT INTO superadmin (id, username, password, status) VALUES (16, 's', '3583', true);
INSERT INTO superadmin (id, username, password, status) VALUES (13, 'e', '1508416', true);
INSERT INTO superadmin (id, username, password, status) VALUES (17, 'ndi', '1325902686', true);
INSERT INTO superadmin (id, username, password, status) VALUES (2, 'lala', '0', true);
INSERT INTO superadmin (id, username, password, status) VALUES (5, 'ss', '0', true);
INSERT INTO superadmin (id, username, password, status) VALUES (18, 'lalas', '3583', true);


--
-- Name: superadmin_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('superadmin_id_seq', 18, true);


--
-- Name: account account_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account
    ADD CONSTRAINT account_id_pk PRIMARY KEY (id);


--
-- Name: accountrole accountrole_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY accountrole
    ADD CONSTRAINT accountrole_pkey PRIMARY KEY (id);


--
-- Name: film film_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY film
    ADD CONSTRAINT film_pkey PRIMARY KEY (id);


--
-- Name: filmgenre filmgenre_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmgenre
    ADD CONSTRAINT filmgenre_id_pk PRIMARY KEY (id);


--
-- Name: filmticket filmticket_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmticket
    ADD CONSTRAINT filmticket_pkey PRIMARY KEY (id);


--
-- Name: fnbsize fnbsize_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fnbsize
    ADD CONSTRAINT fnbsize_pkey PRIMARY KEY (id);


--
-- Name: fnbtype fnbtype_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fnbtype
    ADD CONSTRAINT fnbtype_pkey PRIMARY KEY (id);


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
-- Name: membercard membercard_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY membercard
    ADD CONSTRAINT membercard_pkey PRIMARY KEY (id);


--
-- Name: membergender membergender_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY membergender
    ADD CONSTRAINT membergender_id_pk PRIMARY KEY (id);


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
-- Name: storeaccount storeaccount_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY storeaccount
    ADD CONSTRAINT storeaccount_id_pk PRIMARY KEY (id);


--
-- Name: studio studio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY studio
    ADD CONSTRAINT studio_pkey PRIMARY KEY (id);


--
-- Name: studiotype studiotype_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY studiotype
    ADD CONSTRAINT studiotype_pkey PRIMARY KEY (id);


--
-- Name: superadmin superadmin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY superadmin
    ADD CONSTRAINT superadmin_pkey PRIMARY KEY (id);


--
-- Name: account_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX account_id_uindex ON account USING btree (id);


--
-- Name: accountrole_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX accountrole_id_uindex ON accountrole USING btree (id);


--
-- Name: filmgenre_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX filmgenre_id_uindex ON filmgenre USING btree (id);


--
-- Name: fnbsize_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX fnbsize_id_uindex ON fnbsize USING btree (id);


--
-- Name: fnbtype_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX fnbtype_id_uindex ON fnbtype USING btree (id);


--
-- Name: membergender_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX membergender_id_uindex ON membergender USING btree (id);


--
-- Name: storeaccount_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX storeaccount_id_uindex ON storeaccount USING btree (id);


--
-- Name: studiotype_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX studiotype_id_uindex ON studiotype USING btree (id);


--
-- Name: superadmin_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX superadmin_id_uindex ON superadmin USING btree (id);


--
-- Name: superadmin_username_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX superadmin_username_uindex ON superadmin USING btree (username);


--
-- Name: account account_accountrole_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account
    ADD CONSTRAINT account_accountrole_id_fk FOREIGN KEY (roleid) REFERENCES accountrole(id);


--
-- Name: account account_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account
    ADD CONSTRAINT account_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: accountrole accountrole_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY accountrole
    ADD CONSTRAINT accountrole_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: film film_filmgenre_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY film
    ADD CONSTRAINT film_filmgenre_id_fk FOREIGN KEY (genre) REFERENCES filmgenre(id);


--
-- Name: film film_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY film
    ADD CONSTRAINT film_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: filmgenre filmgenre_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmgenre
    ADD CONSTRAINT filmgenre_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: filmticket filmticket_film_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmticket
    ADD CONSTRAINT filmticket_film_id_fk FOREIGN KEY (filmid) REFERENCES film(id);


--
-- Name: filmticket filmticket_screeningtime_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmticket
    ADD CONSTRAINT filmticket_screeningtime_id_fk FOREIGN KEY (screeningid) REFERENCES screeningtime(id);


--
-- Name: filmticket filmticket_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmticket
    ADD CONSTRAINT filmticket_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: filmticket filmticket_studio_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filmticket
    ADD CONSTRAINT filmticket_studio_id_fk FOREIGN KEY (studioid) REFERENCES studio(id);


--
-- Name: fnbsize fnbsize_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fnbsize
    ADD CONSTRAINT fnbsize_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: fnbtype fnbtype_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fnbtype
    ADD CONSTRAINT fnbtype_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: foodandbeverages foodandbeverages_fnbsize_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY foodandbeverages
    ADD CONSTRAINT foodandbeverages_fnbsize_id_fk FOREIGN KEY (size) REFERENCES fnbsize(id);


--
-- Name: foodandbeverages foodandbeverages_fnbtype_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY foodandbeverages
    ADD CONSTRAINT foodandbeverages_fnbtype_id_fk FOREIGN KEY (type) REFERENCES fnbtype(id);


--
-- Name: foodandbeverages foodandbeverages_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY foodandbeverages
    ADD CONSTRAINT foodandbeverages_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: invoice invoice_account_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_account_id_fk FOREIGN KEY (cashierid) REFERENCES account(id);


--
-- Name: invoice invoice_membercard_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_membercard_id_fk FOREIGN KEY (memberid) REFERENCES membercard(id);


--
-- Name: invoice invoice_promo_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_promo_id_fk FOREIGN KEY (promoid) REFERENCES promo(id);


--
-- Name: invoice invoice_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: membercard membercard_membergender_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY membercard
    ADD CONSTRAINT membercard_membergender_id_fk FOREIGN KEY (gender) REFERENCES membergender(id);


--
-- Name: membercard membercard_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY membercard
    ADD CONSTRAINT membercard_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: membergender membergender_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY membergender
    ADD CONSTRAINT membergender_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: orderdetail orderdetail_invoice_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orderdetail
    ADD CONSTRAINT orderdetail_invoice_id_fk FOREIGN KEY (invoiceid) REFERENCES invoice(id);


--
-- Name: orderdetail orderdetail_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orderdetail
    ADD CONSTRAINT orderdetail_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: promo promo_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY promo
    ADD CONSTRAINT promo_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: screeningtime screeningtime_film_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY screeningtime
    ADD CONSTRAINT screeningtime_film_id_fk FOREIGN KEY (filmid) REFERENCES film(id);


--
-- Name: screeningtime screeningtime_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY screeningtime
    ADD CONSTRAINT screeningtime_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: screeningtime screeningtime_studio_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY screeningtime
    ADD CONSTRAINT screeningtime_studio_id_fk FOREIGN KEY (studioid) REFERENCES studio(id);


--
-- Name: seat seat_studio_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY seat
    ADD CONSTRAINT seat_studio_id_fk FOREIGN KEY (studioid) REFERENCES studio(id);


--
-- Name: studio studio_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY studio
    ADD CONSTRAINT studio_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- Name: studio studio_studiotype_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY studio
    ADD CONSTRAINT studio_studiotype_id_fk FOREIGN KEY (type) REFERENCES studiotype(id);


--
-- Name: studiotype studiotype_storeaccount_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY studiotype
    ADD CONSTRAINT studiotype_storeaccount_id_fk FOREIGN KEY (storeid) REFERENCES storeaccount(id);


--
-- PostgreSQL database dump complete
--

