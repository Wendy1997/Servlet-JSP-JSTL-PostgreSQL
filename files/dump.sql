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
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE account (
    username character varying(20) NOT NULL,
    storeusername character varying(20),
    password character varying(20),
    type character varying(10)
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
    synopsis text
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
    email character varying(30)
);


ALTER TABLE membercard OWNER TO postgres;

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
-- Name: promo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE promo (
    id character varying(20) NOT NULL,
    storeusername character varying(20),
    name character varying(50),
    description text,
    status character varying(10),
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
    "time" timestamp without time zone,
    duration integer
);


ALTER TABLE screeningtime OWNER TO postgres;

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
    type character varying(20)
);


ALTER TABLE studio OWNER TO postgres;

--
-- Name: film id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY film ALTER COLUMN id SET DEFAULT nextval('film_id_seq'::regclass);


--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO account (username, storeusername, password, type) VALUES ('ndi', 'blibli', 'bear', 'admin');


--
-- Data for Name: film; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis) VALUES ('2', 'blibli', 'coveasdasdeer', 'nama', 'genre', 1, 'director', 1, 1, '2017-08-08 00:00:00', '2017-08-08 00:00:00', 'language', 'subtitle', 'actor', 'sinopsis');
INSERT INTO film (id, storeusername, cover, title, genre, duration, director, rating, reviewtotal, starttime, endtime, language, subtitle, actor, synopsis) VALUES ('3', 'blibli', 'coveasdasdeer', 'nama', 'genre', 1, 'director', 1, 1, '2017-08-08 00:00:00', '2017-08-08 00:00:00', 'language', 'subtitle', 'actor', 'sinopsis');


--
-- Name: film_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('film_id_seq', 1, true);


--
-- Data for Name: filmticket; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: foodandbeverages; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: invoice; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: ledger; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: membercard; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: orderdetail; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: promo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: screeningtime; Type: TABLE DATA; Schema: public; Owner: postgres
--



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
-- Name: account account_storeusername_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account
    ADD CONSTRAINT account_storeusername_fkey FOREIGN KEY (storeusername) REFERENCES storeaccount(username);


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

