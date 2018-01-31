/*
  SALES
*/

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