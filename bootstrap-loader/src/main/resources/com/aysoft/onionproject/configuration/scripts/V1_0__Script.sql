-- <editor-fold desc="Sales">
-- OrderState
CREATE TABLE public.order_state (
  pk_order_state SMALLINT NOT NULL,
  state VARCHAR(30) NOT NULL,
  PRIMARY KEY(pk_order_state)
)
WITH (oids = false);

INSERT INTO public.order_state ("pk_order_state", "state")
VALUES (1, 'Created'), (2, 'Initialized'), (3, 'In Progress'), (4, 'Processed'), (5, 'Unknow');

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
-- </editor-fold>

-- <editor-fold desc="Access">
-- Permission
CREATE TABLE public.permission (
  pk_permission INTEGER NOT NULL,
  code VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY(pk_permission)
)
WITH (oids = false);

CREATE TABLE public.permission_localization (
  pk_permission INTEGER NOT NULL,
  pk_locale VARCHAR(2) NOT NULL,
  name VARCHAR(100) NOT NULL,
  description TEXT NOT NULL,
  PRIMARY KEY(pk_permission, pk_locale)
)
WITH (oids = false);

ALTER TABLE public.permission_localization
  ADD CONSTRAINT fk_permission_locale FOREIGN KEY (pk_permission)
REFERENCES public.permission(pk_permission)
ON DELETE NO ACTION
ON UPDATE NO ACTION
  NOT DEFERRABLE;

INSERT INTO public.permission (pk_permission, code) VALUES (1, 'MUS');

INSERT INTO public.permission_localization (pk_permission, pk_locale, name, description)
VALUES (1, 'es', 'Administrar usuarios', 'Administración de usuarios del sistema');

INSERT INTO public.permission_localization (pk_permission, pk_locale, name, description)
VALUES (1, 'en', 'Manage users', 'System users management');
-- </editor-fold>