/*SALES*/

-- OrderState
CREATE TABLE public.order_state (
  pk_order_state SMALLINT NOT NULL,
  state VARCHAR(30) NOT NULL,
  PRIMARY KEY(pk_order_state)
)
WITH (oids = false);

INSERT INTO public.order_state ("pk_order_state", "state")
VALUES (1, E'Created'), (2, E'Initialized'), (3, E'In Progress'), (4, E'Processed'), (5, E'Unknow');

-- Order
CREATE TABLE public."order" (
  pk_order SERIAL,
  fk_order_state SMALLINT NOT NULL,
  date DATE DEFAULT now() NOT NULL,
  reference_id VARCHAR(32),
  priority INTEGER NOT NULL,
  CONSTRAINT order_pkey PRIMARY KEY(pk_order),
  CONSTRAINT order_reference_id_key UNIQUE(reference_id),
  CONSTRAINT order_state_fk FOREIGN KEY (fk_order_state)
  REFERENCES public.order_state(pk_order_state)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
    NOT DEFERRABLE
)
WITH (oids = false);


-- CREATE TABLE public.permission (
--   pk_permission INTEGER NOT NULL,
--   code VARCHAR(100) NOT NULL UNIQUE,
--   PRIMARY KEY(pk_permission)
-- )
-- WITH (oids = false);
--
-- CREATE TABLE public.permission_localization (
--   pk_permission INTEGER NOT NULL,
--   pk_locale VARCHAR(2) NOT NULL,
--   name VARCHAR(100) NOT NULL,
--   description VARCHAR(500) NOT NULL,
--   PRIMARY KEY(pk_permission, pk_locale)
-- )
-- WITH (oids = false);
--
-- ALTER TABLE public.permission_localization
--   ADD CONSTRAINT fk_permission_locale FOREIGN KEY (pk_permission)
-- REFERENCES public.permission(pk_permission)
-- ON DELETE NO ACTION
-- ON UPDATE NO ACTION
--   NOT DEFERRABLE;


-- CREATE TABLE public.user_group (
--   id SERIAL,
--   name VARCHAR(255) NOT NULL,
--   CONSTRAINT uk_kas9w8ead0ska5n3csefp2bpp UNIQUE(name),
--   CONSTRAINT user_group_pkey PRIMARY KEY(id)
-- )
-- WITH (oids = false);
--
-- CREATE TABLE public.user_group_permissions (
--   user_group_id INTEGER NOT NULL,
--   permissions_id VARCHAR(255) NOT NULL,
--   CONSTRAINT user_group_permissions_pkey PRIMARY KEY(user_group_id, permissions_id),
--   CONSTRAINT fk1kp2p8of20958qgwsh077f38q FOREIGN KEY (permissions_id)
--     REFERENCES public.permission(id)
--     ON DELETE NO ACTION
--     ON UPDATE NO ACTION
--     NOT DEFERRABLE,
--   CONSTRAINT fkl75ic1i1e9uro5gl52j3j55p4 FOREIGN KEY (user_group_id)
--     REFERENCES public.user_group(id)
--     ON DELETE NO ACTION
--     ON UPDATE NO ACTION
--     NOT DEFERRABLE
-- )
-- WITH (oids = false);
--
-- CREATE TABLE public.user (
--   id SERIAL,
--   deleted BOOLEAN NOT NULL,
--   enabled BOOLEAN NOT NULL,
--   password VARCHAR(60) NOT NULL,
--   username VARCHAR(50) NOT NULL,
--   CONSTRAINT user_pkey PRIMARY KEY(id)
-- )
-- WITH (oids = false);
--
-- CREATE TABLE public.profile (
--   id INTEGER NOT NULL,
--   email VARCHAR(50) NOT NULL,
--   first_name VARCHAR(100) NOT NULL,
--   last_name VARCHAR(100) NOT NULL,
--   locale VARCHAR(2) NOT NULL,
--   CONSTRAINT user_profile_pkey PRIMARY KEY(id)
-- )
-- WITH (oids = false);