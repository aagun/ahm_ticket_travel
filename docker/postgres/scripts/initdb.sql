CREATE DATABASE ahm_ticket_travel;

CREATE TABLE ahmitjot_mstkotas (
    nid bigint NOT NULL,
    vasal character varying(64) NOT NULL,
    vcode character varying(64) NOT NULL,
    vstatus character varying(255) NOT NULL,
    vtujuan character varying(255) NOT NULL
);

ALTER TABLE ahmitjot_mstkotas OWNER TO developer;

CREATE SEQUENCE ahmitjot_mstkotas_seq START WITH 1 INCREMENT BY 1 MINVALUE 1 OWNED BY ahmitjot_mstkotas.nid;

ALTER TABLE ahmitjot_mstkotas_seq OWNER TO developer;

CREATE TABLE ahmitjot_msttickets (
    vkey character varying(32) NOT NULL,
    vnoticket character varying(64) NOT NULL,
    dbeli date,
    dberangkat date,
    nkursi bigint,
    vasal character varying(64) NOT NULL,
    vcode character varying(64) NOT NULL,
    vgender character varying(12) NOT NULL,
    vnama character varying(100) NOT NULL,
    vnik character varying(16) NOT NULL,
    vtujuan character varying(255) NOT NULL
);

ALTER TABLE ahmitjot_msttickets OWNER TO developer;

ALTER TABLE ONLY ahmitjot_mstkotas ADD CONSTRAINT ahmitjot_mstkotas_pkey PRIMARY KEY (nid);

ALTER TABLE ONLY public.ahmitjot_msttickets ADD CONSTRAINT ahmitjot_msttickets_pkey PRIMARY KEY (vkey, vnoticket);
