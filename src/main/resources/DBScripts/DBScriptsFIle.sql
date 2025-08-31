-- Creating table: public.created_in_db
DROP TABLE IF EXISTS public.created_in_db;

CREATE TABLE IF NOT EXISTS public.created_in_db
(
    primary_key1 integer NOT NULL,
    "Column1" character varying COLLATE pg_catalog."default",
    "Column2" character varying pg_catalog."default",
    CONSTRAINT created_in_db_pkey PRIMARY KEY (primary_key1)
)
-- All user-related objects, including tables, indexes, and materialized views, will be located in pg_default if no other tablespace is specified
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.created_in_db
    OWNER to postgres;
    
-- creating a sequence
CREATE SEQUENCE created_in_db_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
-- Select queries

SELECT * FROM public.created_in_db ORDER BY primary_key1 ASC 

-- Sequence has last_value, log_cnt, is_called
select * from created_in_db_seq;
