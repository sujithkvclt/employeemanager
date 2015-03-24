-- Table: employee

-- DROP TABLE employee;

CREATE TABLE employee
(
  emp_id serial NOT NULL,
  emp_name character varying(30) NOT NULL,
  emp_code character varying(10) NOT NULL,
  emp_designation character varying(30),
  emp_location character varying(30),
  emp_isdeleted boolean,
  emp_email character varying(50),
  emp_phone character varying(15),
  emp_image text,
  CONSTRAINT employee_pkey PRIMARY KEY (emp_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE employee
  OWNER TO postgres;

