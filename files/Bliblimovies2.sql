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

COPY account (username, storeid, password, roleid, status, id) FROM stdin;
ndi	1	1325902686	1	t	1
lala	1	3583	1	t	3
tampan	1	1743073945	2	t	4
admin	1	-1408658752	1	t	2
tes	1	3314090	2	t	6
admin	16	92668751	20	t	24
admin	17	92668751	22	t	25
admin	18	92668751	24	t	26
admin	19	92668751	26	t	27
admin	19	92668751	28	t	28
admin	19	92668751	30	t	29
admin	19	92668751	32	t	30
admin	23	92668751	34	t	31
admin	24	92668751	36	t	32
admin	25	92668751	38	t	33
admin	26	92668751	40	t	34
admin	27	92668751	42	t	35
admin	28	92668751	44	t	36
admin	1	92668751	1	t	37
lala	1	3583	1	t	38
damar	1	113141045	1	t	39
\.


--
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('account_id_seq', 39, true);


--
-- Data for Name: accountrole; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY accountrole (id, role, storeid, status) FROM stdin;
2	cashier	1	t
1	admin	1	t
20	admin	16	t
21	cashier	16	t
22	admin	17	t
23	cashier	17	t
24	admin	18	t
25	cashier	18	t
26	admin	19	t
27	cashier	19	t
28	admin	19	t
29	cashier	19	t
30	admin	19	t
31	cashier	19	t
32	admin	19	t
33	cashier	19	t
34	admin	23	t
35	cashier	23	t
36	admin	24	t
37	cashier	24	t
38	admin	25	t
39	cashier	25	t
40	admin	26	t
41	cashier	26	t
42	admin	27	t
43	cashier	27	t
44	admin	28	t
45	cashier	28	t
\.


--
-- Name: accountrole_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('accountrole_id_seq', 45, true);


--
-- Data for Name: film; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY film (id, storeid, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis, status) FROM stdin;
20	1	asd	asd	1	1	asdas	12312	1231	2017-12-31	2016-12-31	123	123	1123	123\n	f
29	1	/blibli/film/ss (2017).jpg	ss	1	1	1	1	1	2017-12-03	2017-12-03	1	1	1	1	f
26	1	/blibli/film/Wendy's (2021).jpg	Wendy's	1	123	123	123	213	2021-12-07	2024-12-07	qwe	qwe	wqe	qwe	f
27	1	/blibli/film/ssss (2017).jpg?20171203221750	ssss	1	123	asd	12	12	2017-12-02	2017-12-03	we	we	we	we	f
24	1	\\blibli\\film\\Wendy's (2017).jpg	Wendy's	1	12	12	12	12	2017-12-05	2017-12-07	Jawa	Jawa	Wendy	Wendy Damar	f
25	1	/blibli/film/WEndy Damar (2015).jpg	WEndy Damar	1	123	123	12	12	2015-12-03	2021-12-03	Wendy	WEnd	WEndy	Wendy	f
22	1	asdasd	asdasd	1	2	asda	313	-1123123	0123-03-12	0123-03-12	123123	23123	123123	23\n13	f
21	1	asd	asd	1	1	asdas	12312	1231	2017-12-31	2016-12-31	123	123	1123	123\n	f
19	1	asd	asd	1	1	asdas	12312	1231	2017-12-31	2016-12-31	123	123	1123	123\n	f
17	1	asd	asd	1	1	asdas	12312	1231	2017-12-31	2016-12-31	123	123	1123	123\n	f
18	1	asd	asd	1	1	asdas	12312	1231	2017-12-31	2016-12-31	123	123	1123	123\n	f
15	1	asd	asdasd	1	1231	asdasd	123	1123	2017-12-31	0012-12-12	aadds	asasd	asdasd	asdasd	f
16	1	asd	asdasd	1	1231	asdasd	123	1123	2017-12-31	0012-12-12	aadds	asasd	asdasd	asdasd	f
13	1	asda	asdasd	1	13123	asdasd	13123	313	2017-12-31	2017-12-31	asdasd	asdasd	asdasd	asd	f
14	1	asda	asdasd	1	13123	asdasd	13123	313	2017-12-31	2017-12-31	asdasd	asdasd	asdasd	asd	f
12	1	asda	asdasd	1	13123	asdasd	13123	313	2017-12-31	2017-12-31	asdasd	asdasd	asdasd	asd	f
23	1	\\blibli\\film\\WendyDamar (2017).jpg	WendyDamar	2	123	asd	12	123	2017-12-03	2017-01-03	indon	indon	wendy	asd	f
8	1	tampan	asdasd	1	123	123	123	123	2017-12-31	2017-12-31	123	2123	123	123	f
6	1	asd	ad	1	123	asda	13	123	2017-12-31	2017-01-01	asdasd	asd	asd	asd	f
7	1	asd	asdasd	1	123	123	123	123	2017-12-31	2017-12-31	123	2123	123	123	f
9	1	asda	asdasd	1	13123	asdasd	13123	313	2017-12-31	2017-12-31	asdasd	asdasd	asdasd	asd	f
30	1	/blibli/film/[20171203224541] asdasd (2017).jpg	asdasd	1	1	1	1	1	2017-12-03	2017-12-03	1	1	1	1	f
34	1	/1/film/asda (2017) [20171229101251].jpg	asda	1	123	123	123	123	2017-12-30	2017-12-31	12	123	123	akuganteng	t
31	1	/blibli/film/[20171203223531] 1 (2017).jpg	1	2	1	1	1	1	2017-12-04	2017-12-05	1	1	1	1	f
11	1	asda	asdasd	1	13123	asdasd	13123	313	2017-12-31	2017-12-31	asdasd	asdasd	asdasd	asd	f
35	1	/1/film/testis (2017) [20171229101303].jpg	testis	1	5	tampan	1	1	2017-12-31	2017-12-31	a	a	a	a\n	t
37	1	/1/film/Jujujuju (2018) [20180101192704].jpg	Jujujuju	1	123	12	1	12	2018-12-31	2020-11-30	wendy	enn	asd	asd	t
32	1	/1/film/Avatar (2017) [20171228164831].jpg	Avatar	1	1	1	1	1	2017-12-04	2018-12-05	1	1	1	1	t
33	1	/1/film/asd (2017) [20171228152018].jpg	asd	2	123	asd	12	12	2017-12-31	2018-12-31	asd	asd	asd	To check for any empty file input in the form while uploding any file to the server best way follow my instructions 1. use @MultipartConfig() at the top of your servlet class 2. add the following method to your class private InputStream getImageStream(HttpServletRequest request, String image){ try { Part part 	t
28	1	/blibli/film/qsd (2017).jpg	qsd	1	1	1	1	1	2017-12-03	2018-12-03	1	1	1	1	t
10	1	asda	asdasd	1	13123	asdasd	13123	313	2017-12-31	2017-12-31	asdasd	asdasd	asdasd	asd	t
\.


--
-- Name: film_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('film_id_seq', 37, true);


--
-- Data for Name: filmgenre; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY filmgenre (id, genre, storeid, status) FROM stdin;
8	mantapgg	1	f
3	Romance	1	t
7	Fantasy	1	t
6	Comedy	1	t
5	Adventure	1	t
4	Melodrama	1	t
9	aleleleandro	1	f
2	HorrorBgt	1	t
10	Baru	1	t
11	Banget	1	f
1	Action	1	t
\.


--
-- Name: filmgenre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('filmgenre_id_seq', 11, true);


--
-- Data for Name: filmticket; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY filmticket (id, filmid, studioid, seatnumber, screeningid, price, storeid, date) FROM stdin;
18	6	1	A2	2	0	1	2018-01-01
19	6	1	A3	2	0	1	2018-01-01
35	32	4	A5	13	30000	1	2018-01-01
36	32	4	D1	13	30000	1	2018-01-01
33	32	10	F2	15	123123	1	2018-01-01
34	32	10	B6	15	123123	1	2018-01-01
2	6	1	E3	2	0	1	2018-01-01
3	6	1	E7	2	0	1	2018-01-01
1	6	1	A5	2	123	1	2018-01-01
6	6	1	D5	2	0	1	2018-01-01
7	6	1	F8	2	0	1	2018-01-01
8	6	1	A10	2	0	1	2018-01-01
9	6	1	G4	2	0	1	2018-01-01
10	6	1	I7	2	0	1	2018-01-01
31	32	4	D4	13	30000	1	2018-01-01
32	32	4	D5	13	30000	1	2018-01-01
30	32	4	C7	13	30000	1	2018-01-01
24	32	4	H6	13	30000	1	2018-01-01
22	32	4	F5	13	30000	1	2018-01-01
23	32	4	H5	13	30000	1	2018-01-01
28	32	4	A7	13	30000	1	2018-01-01
29	32	4	A3	13	30000	1	2018-01-01
26	32	4	A9	13	30000	1	2018-01-01
27	32	4	A8	13	30000	1	2018-01-01
20	6	1	J1	2	10000	1	2018-01-01
21	6	1	G1	2	10000	1	2018-01-01
11	6	1	D8	2	0	1	2018-01-01
12	6	1	J6	2	0	1	2018-01-01
17	6	1	D1	2	0	1	2018-01-01
37	32	4	A1	13	30000	1	2018-01-02
38	32	4	F6	13	30000	1	2018-01-01
39	32	4	F4	13	30000	1	2018-01-01
40	32	4	J1	13	30000	1	2018-01-01
25	32	4	A10	13	30000	1	2018-01-03
41	32	4	A1	13	30000	1	2018-01-01
42	32	4	A2	13	30000	1	2018-01-01
43	32	4	B1	13	30000	1	2018-01-01
44	32	4	C1	13	30000	1	2018-01-01
45	32	4	A4	13	30000	1	2018-01-01
46	32	4	A10	13	30000	1	2018-01-01
47	32	10	A1	14	123123	1	2018-01-02
48	32	10	A2	14	123123	1	2018-01-02
49	32	10	A3	14	123123	1	2018-01-02
50	32	10	A4	14	123123	1	2018-01-02
51	32	10	A4	14	123123	1	2018-01-02
52	32	10	A5	14	123123	1	2018-01-02
53	32	10	A6	14	123123	1	2018-01-02
54	33	7	G5	16	500000	1	2018-01-02
55	33	7	G6	16	500000	1	2018-01-02
56	33	7	I3	16	500000	1	2018-01-02
57	33	7	I4	16	500000	1	2018-01-02
\.


--
-- Name: filmticket_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('filmticket_id_seq', 57, true);


--
-- Data for Name: fnbsize; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY fnbsize (id, size, storeid, status) FROM stdin;
1	Regular	1	t
3	Jumbo	1	t
2	LargeBgt	1	t
4	Besarrr Banget	1	f
\.


--
-- Name: fnbsize_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fnbsize_id_seq', 14, true);


--
-- Data for Name: fnbtype; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY fnbtype (id, type, storeid, status) FROM stdin;
4	barubgt	1	f
2	Beverages	1	t
1	Food	1	t
3	ComboGila	1	t
\.


--
-- Name: fnbtype_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fnbtype_id_seq', 11, true);


--
-- Data for Name: foodandbeverages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY foodandbeverages (id, storeid, cover, name, type, size, price, status) FROM stdin;
7	1	/1/fnb/wendyyyy (1) [20171229091252].jpg	wendyyyy	3	1	20000	t
8	1	/1/fnb/Walala (1) [20171229091302].jpg	Walala	3	1	10000	t
9	1	/1/fnb/tes (1) [20171229091152].jpg	tes	1	1	100	t
10	1	/1/fnb/tes (1) [20171229091152].jpg	tes	1	1	100	t
11	1	/1/fnb/tes (1) [20171229091152].jpg	tes	1	1	100	t
12	1	/1/fnb/tes (1) [20171229091152].jpg	tes	1	1	100	t
13	1	/1/fnb/tes (1) [20171229091152].jpg	tes	1	1	100	t
14	1	/1/fnb/tes (1) [20171229091152].jpg	tes	1	1	100	t
15	1	/1/fnb/tes (1) [20171229091152].jpg	tes	1	1	100	t
16	1	/1/fnb/tes (1) [20171229091152].jpg	tes	1	1	100	t
17	1	/1/fnb/tes (1) [20171229091152].jpg	tes	1	1	100	t
18	1	/1/fnb/tes (1) [20171229091152].jpg	tes	1	1	100	t
19	1	/1/fnb/tes (1) [20171229091152].jpg	tes	1	1	100	t
\.


--
-- Name: foodandbeverages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('foodandbeverages_id_seq', 19, true);


--
-- Data for Name: invoice; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY invoice (id, memberid, cashierid, storeid, promoid, orderdate, totalprice) FROM stdin;
45	\N	1	1	\N	2017-11-27 12:58:53	6169750
12	\N	1	1	\N	2017-11-19 16:28:18	13200
17	\N	1	1	\N	2017-11-27 10:46:13	2482420
18	\N	1	1	\N	2017-11-27 10:46:54	12381740
50	\N	1	1	\N	2017-11-27 13:02:28	1320
30	\N	1	1	\N	2017-11-27 11:07:18	10970539
32	\N	1	1	\N	2017-11-27 12:37:53	1237910
31	\N	1	1	\N	2017-11-27 11:11:35	1244510
10	\N	1	1	\N	2017-11-19 16:20:11	13200
27	\N	1	1	\N	2017-11-27 11:02:51	12201849
26	\N	1	1	\N	2017-11-27 11:01:04	1120059
29	\N	1	1	\N	2017-11-27 11:05:43	1120059
28	\N	1	1	\N	2017-11-27 11:04:27	1120059
23	\N	1	1	\N	2017-11-27 10:58:14	1355893
22	\N	1	1	\N	2017-11-27 10:58:07	1355893
25	\N	1	1	\N	2017-11-27 10:58:46	1355893
24	\N	1	1	\N	2017-11-27 10:58:21	1355893
2	1	1	1	\N	2017-10-22 12:16:54.509	10000
61	\N	1	1	\N	2017-11-28 13:09:21	1243950
63	\N	1	1	\N	2017-11-28 13:23:11	2473940
62	\N	1	1	\N	2017-11-28 13:19:17	2473940
75	\N	1	1	\N	2017-12-21 13:54:46	23232323
64	\N	1	1	\N	2017-11-28 13:23:38	2475820
42	\N	1	1	\N	2017-11-27 12:58:53	6169750
65	1	1	1	1	2017-11-28 13:25:28	10188
60	\N	1	1	\N	2017-11-28 13:08:06	2473940
59	\N	1	1	\N	2017-11-28 13:07:27	11320
56	\N	1	1	\N	2017-11-28 13:04:24	23200
55	1	1	1	1	2017-11-28 12:45:33	14940
58	\N	1	1	\N	2017-11-28 13:06:36	23200
57	\N	1	1	\N	2017-11-28 13:04:26	23200
74	\N	1	1	\N	2017-12-21 13:51:18	1161616150
9	\N	1	1	\N	2017-11-19 09:19:33.575	100
52	\N	1	1	\N	2017-11-27 13:02:49	2640
51	\N	1	1	\N	2017-11-27 13:02:36	1233950
54	1	1	1	1	2017-11-27 13:03:39	2228238
53	1	1	1	1	2017-11-27 13:02:58	11880
21	\N	1	1	\N	2017-11-27 10:57:58	1355893
20	\N	1	1	\N	2017-11-27 10:57:34	1355893
49	\N	1	1	\N	2017-11-27 13:02:17	13200
48	\N	1	1	\N	2017-11-27 13:02:17	13200
16	\N	1	1	\N	2017-11-19 16:37:23	13200
15	\N	1	1	\N	2017-11-19 16:34:25	13200
44	\N	1	1	\N	2017-11-27 12:58:53	6169750
47	\N	1	1	\N	2017-11-27 12:58:54	6169750
46	\N	1	1	\N	2017-11-27 12:58:53	6169750
11	\N	1	1	\N	2017-11-19 16:27:39	13200
14	\N	1	1	\N	2017-11-19 16:32:14	13200
13	\N	1	1	\N	2017-11-19 16:29:54	12313100
68	1	1	1	1	2017-12-04 00:12:21	81000
69	1	1	1	1	2017-12-04 00:22:44	81000
67	\N	1	1	\N	2017-11-30 10:58:22	1320
70	\N	1	1	\N	2017-12-04 13:34:37	60132
81	1	1	1	1	2017-12-24 13:49:49	72000
80	1	1	1	1	2017-12-24 13:43:49	72000
83	1	1	1	1	2017-12-28 10:30:42	200810.70000000001
82	\N	1	1	\N	2017-12-24 14:00:06	60000
78	1	1	1	1	2017-12-21 13:58:44	45000
77	\N	1	1	\N	2017-12-21 13:58:24	10000
79	1	1	1	1	2017-12-21 20:33:25	90000
1	\N	1	1	\N	2017-10-22 12:16:54.509	10000
4	\N	1	1	\N	2016-11-19 16:14:15	13200
5	\N	1	1	\N	2017-11-19 16:16:52	1320
73	1	1	1	1	2017-12-04 13:40:05	27118.799999999999
76	1	1	1	1	2017-12-21 13:56:40	209090907
41	\N	1	1	\N	2017-11-27 12:58:52	6169750
40	\N	1	1	\N	2017-11-27 12:58:52	6169750
43	\N	1	1	\N	2017-11-27 12:58:53	6169750
72	1	1	1	1	2017-12-04 13:36:33	54000
71	\N	1	1	\N	2017-12-04 13:34:48	23292323
38	\N	1	1	\N	2017-11-27 12:58:52	6169750
37	\N	1	1	\N	2017-11-27 12:58:51	6169750
66	1	1	1	1	2017-11-28 13:25:41	1188
39	\N	1	1	\N	2017-11-27 12:58:52	6169750
34	\N	1	1	\N	2017-11-27 12:57:05	1233950
33	\N	1	1	\N	2017-11-27 12:54:25	618295
36	1	1	1	1	2017-11-27 12:58:36	5552775
35	1	1	1	1	2017-11-27 12:58:31	5552775
19	\N	1	1	\N	2017-11-27 10:50:49	2464204
84	1	4	1	1	2017-12-28 11:08:42	9000
85	\N	1	1	\N	2017-12-29 08:57:14	23262323
86	\N	1	1	\N	2017-12-29 09:16:48	30000
87	\N	1	1	\N	2017-12-29 09:17:15	10000
88	1	1	1	1	2017-12-29 09:17:35	90
89	1	1	1	1	2017-12-29 09:42:36	1080
90	\N	1	1	\N	2017-12-29 09:45:02	100
91	1	1	1	1	2017-12-29 10:18:55	27990
92	1	1	1	1	2017-12-31 00:34:34	90
93	1	1	1	1	2018-01-02 09:41:54	117450
94	\N	4	1	\N	2018-01-02 11:36:25	1000000
95	\N	3	1	\N	2018-01-02 12:21:40	1000200
\.


--
-- Name: invoice_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('invoice_id_seq', 95, true);


--
-- Data for Name: membercard; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY membercard (id, storeid, fullname, gender, birthdate, phonenumber, email, status) FROM stdin;
1	1	Tampan	2	2017-12-31 00:00:00	123	wendydamar.wb@gmail.com	t
2	1	asd	2	2017-12-31 00:00:00	12312	wendydamarA.wb@gmail.com	t
3	1	as	2	2017-12-31 00:00:00	123	we@aA	f
\.


--
-- Name: membercard_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('membercard_id_seq', 12, true);


--
-- Data for Name: membergender; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY membergender (id, gender, storeid, status) FROM stdin;
2	Wanita	1	t
1	Pria	1	t
16	Pria	16	t
17	Wanita	16	t
18	Pria	17	t
19	Wanita	17	t
20	Pria	18	t
21	Wanita	18	t
22	Pria	19	t
23	Wanita	19	t
24	Pria	19	t
25	Wanita	19	t
26	Pria	19	t
27	Wanita	19	t
28	Pria	19	t
29	Wanita	19	t
30	Pria	23	t
31	Wanita	23	t
32	Pria	24	t
33	Wanita	24	t
34	Pria	25	t
35	Wanita	25	t
36	Pria	26	t
37	Wanita	26	t
38	Pria	27	t
39	Wanita	27	t
40	Pria	28	t
41	Wanita	28	t
\.


--
-- Name: membergender_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('membergender_id_seq', 41, true);


--
-- Data for Name: orderdetail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY orderdetail (id, invoiceid, storeid, itemname, quantity, price, discountstatus) FROM stdin;
48	51	1	Mempeseona	20	132	false
39	42	1	Mempeseona	100	132	true
38	42	1	asdasd	50	123131	true
37	42	1	Mempeseona	100	132	true
50	52	1	Mempeseona	20	132	false
36	42	1	asdasd	50	123131	true
84	79	1	Walala	10	10000	true
54	55	1	Mempeseona	50	132	true
30	38	1	asdasd	50	123131	true
52	54	1	Mempeseona	100	132	true
43	47	1	Mempeseona	100	132	true
42	42	1	asdasd	50	123131	true
41	42	1	Mempeseona	100	132	true
40	42	1	asdasd	50	123131	true
55	60	1	ad	1	10000	true
56	60	1	asdasd	20	123131	false
57	61	1	ad	1	10000	true
59	62	1	ad	1	10000	true
53	54	1	asdasd	20	123131	true
61	63	1	ad	1	10000	true
60	62	1	asdasd	20	123131	false
65	64	1	Mempeseona	100	132	false
62	63	1	asdasd	20	123131	false
63	63	1	Mempeseona	10	132	false
10	30	1	asdasd	89	123131	false
49	51	1	asdasd	10	123131	false
64	64	1	asdasd	20	123131	false
14	32	1	asdasd	10	123131	false
13	32	1	Mempeseona	50	132	false
12	31	1	asdasd	10	123131	false
11	31	1	Mempeseona	100	132	false
18	34	1	asdasd	10	123131	false
17	34	1	Mempeseona	20	132	false
16	33	1	asdasd	5	123131	false
15	33	1	Mempeseona	20	132	false
19	35	1	Mempeseona	100	132	true
77	73	1	11111	1	30000	true
21	36	1	Mempeseona	100	132	true
20	35	1	asdasd	50	123131	true
69	67	1	Mempeseona	10	132	false
68	66	1	Mempeseona	10	132	true
67	65	1	Mempeseona	10	132	true
66	65	1	ad	1	10000	true
71	69	1	11111	3	30000	true
8	27	1	Mempeseona	90	132	false
3	1	1	indomi	2	4000	\N
2	1	1	sampo	3	3000	\N
1	1	1	sabun	5	3000	\N
7	26	1	Mempeseona	90	132	false
6	25	1	Mempeseona	11	132	false
5	24	1	Mempeseona	11	132	false
4	2	1	selada	1	11000	\N
89	81	1	Walala	2	10000	true
9	30	1	Mempeseona	90	132	false
51	53	1	Mempeseona	100	132	true
31	38	1	Mempeseona	100	132	true
72	70	1	11111	2	30000	true
58	61	1	asdasd	10	123131	false
70	68	1	11111	3	30000	true
76	72	1	11111	2	30000	true
75	71	1	wendyyyy	1	23232323	false
74	71	1	11111	2	30000	true
73	70	1	Mempeseona	1	132	false
88	81	1	11111	2	30000	true
79	74	1	wendyyyy	50	23232323	false
78	73	1	Mempeseona	1	132	true
25	38	1	Mempeseona	100	132	true
24	37	1	asdasd	50	123131	true
23	37	1	Mempeseona	100	132	true
22	36	1	asdasd	50	123131	true
29	38	1	Mempeseona	100	132	true
28	38	1	asdasd	50	123131	true
27	38	1	Mempeseona	100	132	true
26	38	1	asdasd	50	123131	true
90	82	1	11111	2	30000	true
83	78	1	Walala	5	10000	true
82	77	1	Walala	1	10000	false
81	76	1	wendyyyy	10	23232323	true
80	75	1	wendyyyy	1	23232323	false
87	80	1	wendyyyy	0	23232323	true
86	80	1	Walala	2	10000	true
85	80	1	11111	2	30000	true
32	38	1	asdasd	50	123131	true
47	50	1	Mempeseona	10	132	false
46	48	1	Mempeseona	100	132	false
45	48	1	Mempeseona	100	132	false
44	47	1	asdasd	50	123131	true
35	42	1	Mempeseona	100	132	true
34	42	1	asdasd	50	123131	true
33	42	1	Mempeseona	100	132	true
91	83	1	11111	1	123123	true
92	83	1	Walala	10	10000	true
93	84	1	Walala	1	10000	true
94	85	1	Avatar	1	30000	true
95	85	1	wendyyyy	1	23232323	false
96	86	1	Avatar	1	30000	true
97	87	1	Walala	1	10000	false
98	88	1	tes	1	100	true
99	89	1	tes	12	100	true
100	90	1	tes	1	100	false
101	91	1	Avatar	1	30000	true
102	91	1	tes	11	100	true
103	92	1	tes	1	100	true
104	93	1	tes	1	100	true
105	93	1	tes	4	100	true
106	93	1	Walala	3	10000	true
107	93	1	wendyyyy	5	20000	true
108	94	1	asd	2	500000	true
109	95	1	asd	2	500000	true
110	95	1	tes	2	100	false
\.


--
-- Name: orderdetail_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('orderdetail_id_seq', 110, true);


--
-- Data for Name: promo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY promo (id, storeid, name, description, status, discountamount) FROM stdin;
1	1	ululu	diskon 10%	t	10
4	16	tes	tes	t	10
5	17	tes	tes	t	10
6	18	tes	tes	t	10
7	23	tes	tes	t	10
8	24	tes	tes	t	10
9	25	tes	tes	t	10
10	26	tes	tes	t	10
11	27	tes	tes	t	10
12	28	tes	tes	t	10
\.


--
-- Name: promo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('promo_id_seq', 12, true);


--
-- Data for Name: screeningtime; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY screeningtime (id, filmid, studioid, storeid, "time", duration, status) FROM stdin;
9	6	3	1	23:56:00	123	t
8	6	3	1	23:56:00	123	t
7	6	1	1	23:56:00	123	t
6	6	2	1	23:56:00	123	t
5	6	2	1	23:56:00	123	t
4	6	1	1	23:56:00	123	t
3	6	2	1	23:56:00	123	t
2	6	1	1	23:56:00	123	t
15	32	10	1	23:58:00	1	t
10	6	1	1	23:56:00	123	t
11	6	1	1	23:56:00	123	t
12	6	1	1	23:56:00	123	t
13	32	4	1	17:03:00	1	t
14	32	10	1	12:12:00	1	t
16	33	7	1	10:10:00	123	t
\.


--
-- Name: screeningtime_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('screeningtime_id_seq', 24, true);


--
-- Data for Name: seat; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY seat (number, studioid, name) FROM stdin;
3	1	A3
4	1	A4
5	1	A5
6	1	A6
7	1	A7
8	1	A8
9	1	A9
10	1	A10
11	1	B1
12	1	B2
13	1	B3
14	1	B4
15	1	B5
16	1	B6
17	1	B7
18	1	B8
19	1	B9
20	1	B10
21	1	C1
22	1	C2
23	1	C3
24	1	C4
25	1	C5
26	1	C6
27	1	C7
28	1	C8
29	1	C9
30	1	C10
31	1	D1
32	1	D2
33	1	D3
34	1	D4
35	1	D5
36	1	D6
37	1	D7
38	1	D8
39	1	D9
40	1	D10
41	1	E1
42	1	E2
43	1	E3
44	1	E4
45	1	E5
46	1	E6
47	1	E7
48	1	E8
49	1	E9
50	1	E10
51	1	F1
52	1	F2
53	1	F3
54	1	F4
55	1	F5
56	1	F6
57	1	F7
58	1	F8
59	1	F9
60	1	F10
61	1	G1
62	1	G2
63	1	G3
64	1	G4
65	1	G5
66	1	G6
67	1	G7
68	1	G8
69	1	G9
70	1	G10
71	1	H1
72	1	H2
73	1	H3
74	1	H4
75	1	H5
76	1	H6
77	1	H7
78	1	H8
79	1	H9
80	1	H10
81	1	I1
82	1	I2
83	1	I3
84	1	I4
85	1	I5
86	1	I6
87	1	I7
88	1	I8
89	1	I9
90	1	I10
91	1	J1
92	1	J2
93	1	J3
94	1	J4
95	1	J5
96	1	J6
97	1	J7
98	1	J8
99	1	J9
100	1	J10
101	2	A1
102	2	A2
103	2	A3
104	2	A4
105	2	A5
106	2	A6
107	2	A7
108	2	A8
109	2	A9
110	2	A10
111	2	B1
112	2	B2
113	2	B3
114	2	B4
115	2	B5
116	2	B6
117	2	B7
118	2	B8
119	2	B9
120	2	B10
121	2	C1
122	2	C2
123	2	C3
124	2	C4
125	2	C5
126	2	C6
127	2	C7
128	2	C8
129	2	C9
130	2	C10
131	2	D1
132	2	D2
133	2	D3
134	2	D4
135	2	D5
136	2	D6
137	2	D7
138	2	D8
139	2	D9
140	2	D10
141	2	E1
142	2	E2
143	2	E3
144	2	E4
145	2	E5
146	2	E6
147	2	E7
148	2	E8
149	2	E9
150	2	E10
151	2	F1
152	2	F2
153	2	F3
154	2	F4
155	2	F5
156	2	F6
157	2	F7
158	2	F8
159	2	F9
160	2	F10
161	2	G1
162	2	G2
163	2	G3
164	2	G4
165	2	G5
166	2	G6
167	2	G7
168	2	G8
169	2	G9
170	2	G10
171	2	H1
172	2	H2
173	2	H3
174	2	H4
175	2	H5
176	2	H6
177	2	H7
178	2	H8
179	2	H9
180	2	H10
181	2	I1
182	2	I2
183	2	I3
184	2	I4
185	2	I5
186	2	I6
187	2	I7
188	2	I8
189	2	I9
190	2	I10
191	2	J1
192	2	J2
193	2	J3
194	2	J4
195	2	J5
196	2	J6
197	2	J7
198	2	J8
199	2	J9
200	2	J10
201	3	A1
202	3	A2
203	3	A3
204	3	A4
205	3	A5
206	3	A6
207	3	A7
208	3	A8
209	3	A9
210	3	A10
211	3	B1
212	3	B2
213	3	B3
214	3	B4
215	3	B5
216	3	B6
217	3	B7
218	3	B8
219	3	B9
220	3	B10
221	3	C1
222	3	C2
223	3	C3
224	3	C4
225	3	C5
226	3	C6
227	3	C7
228	3	C8
229	3	C9
230	3	C10
231	3	D1
232	3	D2
233	3	D3
234	3	D4
235	3	D5
236	3	D6
237	3	D7
238	3	D8
239	3	D9
240	3	D10
241	3	E1
242	3	E2
243	3	E3
244	3	E4
245	3	E5
246	3	E6
247	3	E7
248	3	E8
249	3	E9
250	3	E10
251	3	F1
252	3	F2
253	3	F3
254	3	F4
255	3	F5
256	3	F6
257	3	F7
258	3	F8
259	3	F9
260	3	F10
261	3	G1
262	3	G2
263	3	G3
264	3	G4
265	3	G5
266	3	G6
267	3	G7
268	3	G8
269	3	G9
270	3	G10
271	3	H1
272	3	H2
273	3	H3
274	3	H4
275	3	H5
276	3	H6
277	3	H7
278	3	H8
279	3	H9
280	3	H10
281	3	I1
282	3	I2
283	3	I3
284	3	I4
285	3	I5
286	3	I6
287	3	I7
288	3	I8
289	3	I9
290	3	I10
291	3	J1
292	3	J2
293	3	J3
294	3	J4
295	3	J5
296	3	J6
297	3	J7
298	3	J8
299	3	J9
300	3	J10
301	4	A1
302	4	A2
303	4	A3
304	4	A4
305	4	A5
306	4	A6
307	4	A7
308	4	A8
309	4	A9
310	4	A10
311	4	B1
312	4	B2
313	4	B3
314	4	B4
315	4	B5
316	4	B6
317	4	B7
318	4	B8
319	4	B9
320	4	B10
321	4	C1
322	4	C2
323	4	C3
324	4	C4
325	4	C5
326	4	C6
327	4	C7
328	4	C8
329	4	C9
330	4	C10
331	4	D1
332	4	D2
333	4	D3
334	4	D4
335	4	D5
336	4	D6
337	4	D7
338	4	D8
339	4	D9
340	4	D10
341	4	E1
342	4	E2
343	4	E3
344	4	E4
345	4	E5
346	4	E6
347	4	E7
348	4	E8
349	4	E9
350	4	E10
351	4	F1
352	4	F2
353	4	F3
354	4	F4
355	4	F5
356	4	F6
357	4	F7
358	4	F8
359	4	F9
360	4	F10
361	4	G1
362	4	G2
363	4	G3
364	4	G4
365	4	G5
366	4	G6
367	4	G7
368	4	G8
369	4	G9
370	4	G10
371	4	H1
372	4	H2
373	4	H3
374	4	H4
375	4	H5
376	4	H6
377	4	H7
378	4	H8
379	4	H9
380	4	H10
381	4	I1
382	4	I2
383	4	I3
384	4	I4
385	4	I5
386	4	I6
387	4	I7
388	4	I8
389	4	I9
390	4	I10
391	4	J1
392	4	J2
393	4	J3
394	4	J4
395	4	J5
396	4	J6
397	4	J7
398	4	J8
399	4	J9
400	4	J10
401	5	A1
402	5	A2
403	5	A3
404	5	A4
405	5	A5
406	5	A6
407	5	A7
408	5	A8
409	5	A9
410	5	A10
411	5	B1
412	5	B2
413	5	B3
414	5	B4
415	5	B5
416	5	B6
417	5	B7
418	5	B8
419	5	B9
420	5	B10
421	5	C1
422	5	C2
423	5	C3
424	5	C4
425	5	C5
426	5	C6
427	5	C7
428	5	C8
429	5	C9
430	5	C10
431	5	D1
432	5	D2
433	5	D3
434	5	D4
435	5	D5
436	5	D6
437	5	D7
438	5	D8
439	5	D9
440	5	D10
441	5	E1
442	5	E2
443	5	E3
444	5	E4
445	5	E5
446	5	E6
447	5	E7
448	5	E8
449	5	E9
450	5	E10
451	5	F1
452	5	F2
453	5	F3
454	5	F4
455	5	F5
456	5	F6
457	5	F7
458	5	F8
459	5	F9
460	5	F10
461	5	G1
462	5	G2
463	5	G3
464	5	G4
465	5	G5
466	5	G6
467	5	G7
468	5	G8
469	5	G9
470	5	G10
471	5	H1
472	5	H2
473	5	H3
474	5	H4
475	5	H5
476	5	H6
477	5	H7
478	5	H8
479	5	H9
480	5	H10
481	5	I1
482	5	I2
483	5	I3
484	5	I4
485	5	I5
486	5	I6
487	5	I7
488	5	I8
489	5	I9
490	5	I10
491	5	J1
492	5	J2
493	5	J3
494	5	J4
495	5	J5
496	5	J6
497	5	J7
498	5	J8
499	5	J9
500	5	J10
501	6	A1
502	6	A2
503	6	A3
504	6	A4
505	6	A5
506	6	A6
507	6	A7
508	6	A8
509	6	A9
510	6	A10
511	6	B1
512	6	B2
513	6	B3
514	6	B4
515	6	B5
516	6	B6
517	6	B7
518	6	B8
519	6	B9
520	6	B10
521	6	C1
522	6	C2
523	6	C3
524	6	C4
525	6	C5
526	6	C6
527	6	C7
528	6	C8
529	6	C9
530	6	C10
531	6	D1
532	6	D2
533	6	D3
534	6	D4
535	6	D5
536	6	D6
537	6	D7
538	6	D8
539	6	D9
540	6	D10
541	6	E1
542	6	E2
543	6	E3
544	6	E4
545	6	E5
546	6	E6
547	6	E7
548	6	E8
549	6	E9
550	6	E10
551	6	F1
552	6	F2
553	6	F3
554	6	F4
555	6	F5
556	6	F6
557	6	F7
558	6	F8
559	6	F9
560	6	F10
561	6	G1
562	6	G2
563	6	G3
564	6	G4
565	6	G5
566	6	G6
567	6	G7
568	6	G8
569	6	G9
570	6	G10
571	6	H1
572	6	H2
573	6	H3
574	6	H4
575	6	H5
576	6	H6
577	6	H7
578	6	H8
579	6	H9
580	6	H10
581	6	I1
582	6	I2
583	6	I3
584	6	I4
585	6	I5
586	6	I6
587	6	I7
588	6	I8
589	6	I9
590	6	I10
591	6	J1
592	6	J2
593	6	J3
594	6	J4
595	6	J5
596	6	J6
597	6	J7
598	6	J8
599	6	J9
600	6	J10
601	7	A1
602	7	A2
603	7	A3
604	7	A4
605	7	A5
606	7	A6
607	7	A7
608	7	A8
609	7	A9
610	7	A10
611	7	B1
612	7	B2
613	7	B3
614	7	B4
615	7	B5
616	7	B6
617	7	B7
618	7	B8
619	7	B9
620	7	B10
621	7	C1
622	7	C2
623	7	C3
624	7	C4
625	7	C5
626	7	C6
627	7	C7
628	7	C8
629	7	C9
630	7	C10
631	7	D1
632	7	D2
633	7	D3
634	7	D4
635	7	D5
636	7	D6
637	7	D7
638	7	D8
639	7	D9
640	7	D10
641	7	E1
642	7	E2
643	7	E3
644	7	E4
645	7	E5
646	7	E6
647	7	E7
648	7	E8
649	7	E9
650	7	E10
651	7	F1
652	7	F2
653	7	F3
654	7	F4
655	7	F5
656	7	F6
657	7	F7
658	7	F8
659	7	F9
660	7	F10
661	7	G1
662	7	G2
663	7	G3
664	7	G4
665	7	G5
666	7	G6
667	7	G7
668	7	G8
669	7	G9
670	7	G10
671	7	H1
672	7	H2
673	7	H3
674	7	H4
675	7	H5
676	7	H6
677	7	H7
678	7	H8
679	7	H9
680	7	H10
681	7	I1
682	7	I2
683	7	I3
684	7	I4
685	7	I5
686	7	I6
687	7	I7
688	7	I8
689	7	I9
690	7	I10
691	7	J1
692	7	J2
693	7	J3
694	7	J4
695	7	J5
696	7	J6
697	7	J7
698	7	J8
699	7	J9
700	7	J10
701	8	A1
702	8	A2
703	8	A3
704	8	A4
705	8	A5
706	8	A6
707	8	A7
708	8	A8
709	8	A9
710	8	A10
711	8	B1
712	8	B2
713	8	B3
714	8	B4
715	8	B5
716	8	B6
717	8	B7
718	8	B8
719	8	B9
720	8	B10
721	8	C1
722	8	C2
723	8	C3
724	8	C4
725	8	C5
726	8	C6
727	8	C7
728	8	C8
729	8	C9
730	8	C10
731	8	D1
732	8	D2
733	8	D3
734	8	D4
735	8	D5
736	8	D6
737	8	D7
738	8	D8
739	8	D9
740	8	D10
741	8	E1
742	8	E2
743	8	E3
744	8	E4
745	8	E5
746	8	E6
747	8	E7
748	8	E8
749	8	E9
750	8	E10
751	8	F1
752	8	F2
753	8	F3
754	8	F4
755	8	F5
756	8	F6
757	8	F7
758	8	F8
759	8	F9
760	8	F10
761	8	G1
762	8	G2
763	8	G3
764	8	G4
765	8	G5
766	8	G6
767	8	G7
768	8	G8
769	8	G9
770	8	G10
771	8	H1
772	8	H2
773	8	H3
774	8	H4
775	8	H5
776	8	H6
777	8	H7
778	8	H8
779	8	H9
780	8	H10
781	8	I1
782	8	I2
783	8	I3
784	8	I4
785	8	I5
786	8	I6
787	8	I7
788	8	I8
789	8	I9
790	8	I10
791	8	J1
792	8	J2
793	8	J3
794	8	J4
795	8	J5
796	8	J6
797	8	J7
798	8	J8
799	8	J9
800	8	J10
2	1	A2
1	1	A1
\.


--
-- Name: seat_number_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seat_number_seq', 801, true);


--
-- Data for Name: storeaccount; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY storeaccount (username, password, storename, id, status) FROM stdin;
blibli	-1081416709	global digital niaga	1	t
bukalapak	66882492	bukalapakajadulu	16	t
blibli4	-27007852	blibli4	23	t
asdasd	-1422600940	asdasd	24	t
blibli3	203415793	bliblibaru	19	t
lala	3314090	lala	26	t
s	48661	ss	28	t
sdsdsd	109297745	sdsdsd	25	t
blibli2	-1386344544	blibli	18	t
po	3446846	po	27	t
tokopedia	344197682	tokopedia	17	t
\.


--
-- Name: storeaccount_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('storeaccount_id_seq', 28, true);


--
-- Data for Name: studio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY studio (id, storeid, name, type, price, status) FROM stdin;
9	1	Mantap	1	123	f
8	1	H	3	800000	t
7	1	E	1	500000	t
2	1	B	1	20000	t
3	1	F	1	60000	t
10	1	Alalal	1	123123	t
5	1	G	2	700000	t
6	1	D	1	40000	t
4	1	C	2	30000	t
11	1	asds	1	123	t
1	1	AA	1	10000	t
\.


--
-- Name: studio_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('studio_id_seq', 11, true);


--
-- Data for Name: studiotype; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY studiotype (id, type, storeid, status) FROM stdin;
1	Regular	1	t
4	WawwBgt	1	f
3	VIP	1	t
2	SuiteMantap	1	t
\.


--
-- Name: studiotype_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('studiotype_id_seq', 17, true);


--
-- Data for Name: superadmin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY superadmin (id, username, password, status) FROM stdin;
1	wendy	-1110123361	f
6	lalaa	3583	t
7	a	3583	t
8	b	3583	t
9	v	3583	t
10	d	3583	t
11	q	3583	t
12	w	3583	t
14	r	3583	t
15	z	3583	t
16	s	3583	t
13	e	1508416	t
17	ndi	1325902686	t
2	lala	0	t
5	ss	0	t
18	lalas	3583	t
\.


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

