-- Support Tables
CREATE TABLE user_local (
	id SERIAL NOT NULL,
	username TEXT NOT NULL UNIQUE,
	password TEXT,
	rol_id INT NOT NULL,
	PRIMARY KEY ( id )
);

CREATE TABLE rol (
	id SERIAL NOT NULL,
	name TEXT NOT NULL,
	description TEXT,
	PRIMARY KEY ( id )
);

-- Main Tables
CREATE TABLE tourist (
	passport VARCHAR(9) NOT NULL,
	name TEXT NOT NULL,
	age SMALLINT NOT NULL,
	sex CHAR NOT NULL,
	contact TEXT NOT NULL,
	country_id INT NOT NULL,
	PRIMARY KEY ( passport )
);

CREATE TABLE car (
	plate VARCHAR(7) NOT NULL,
	brand_id INTEGER NOT NULL,
	model_id INTEGER NOT NULL,
	cant_km INTEGER NOT NULL,
	color TEXT NOT NULL,
	situation_id INTEGER NOT NULL,
	PRIMARY KEY ( plate )
);

CREATE TABLE driver (
	dni VARCHAR(11),
	name TEXT NOT NULL,
	category_id INTEGER NOT NULL,
	address TEXT NOT NULL,
	PRIMARY KEY ( dni )
);

CREATE TABLE contract (
	car_plate VARCHAR(7) NOT NULL,
	start_date DATE NOT NULL,
	tourist_passport VARCHAR(11) NOT NULL REFERENCES tourist(passport) ON DELETE CASCADE,
	end_date DATE NOT NULL,
	start_km INTEGER NOT NULL,
	delivery_date DATE,
	end_km INTEGER,
	pay_method_id INTEGER NOT NULL,
	driver_dni VARCHAR(11),
	value FLOAT NOT NULL,
	PRIMARY KEY ( car_plate, start_date )
);

-- Auxiliary Tables
CREATE TABLE country (
	id SERIAL NOT NULL,
	name TEXT NOT NULL UNIQUE,
	PRIMARY KEY ( id )
);

CREATE TABLE model (
	brand_id INTEGER NOT NULL,
	id SERIAL NOT NULL,
	name TEXT NOT NULL UNIQUE,
	PRIMARY KEY ( brand_id, id )
);

CREATE TABLE brand (
	id SERIAL NOT NULL,
	name TEXT NOT NULL UNIQUE,
	PRIMARY KEY ( id )
);

CREATE TABLE category (
	id SERIAL NOT NULL,
	name TEXT NOT NULL UNIQUE,
	PRIMARY KEY ( id )
);

CREATE TABLE situation (
	id SERIAL NOT NULL,
	name TEXT NOT NULL UNIQUE,
	PRIMARY KEY ( id )
);

CREATE TABLE pay_method (
	id SERIAL NOT NULL,
	name TEXT NOT NULL UNIQUE,
	PRIMARY KEY ( id )
);

CREATE TABLE fee (
	id SERIAL NOT NULL,
	name TEXT NOT NULL UNIQUE,
	day_cost FLOAT NOT NULL,
	PRIMARY KEY ( id )
);

-- Foreing Keys Definition
ALTER TABLE tourist ADD CONSTRAINT fk_tourist_country FOREIGN KEY ( country_id ) REFERENCES country ( id );
ALTER TABLE car ADD CONSTRAINT fk_car_model FOREIGN KEY ( brand_id, model_id ) REFERENCES model ( brand_id, id );
ALTER TABLE car ADD CONSTRAINT fk_car_situation FOREIGN KEY ( situation_id ) REFERENCES situation ( id );
ALTER TABLE driver ADD CONSTRAINT fk_driver_category FOREIGN KEY ( category_id ) REFERENCES category ( id );
ALTER TABLE model ADD CONSTRAINT fk_model_brand FOREIGN KEY ( brand_id ) REFERENCES brand ( id );
ALTER TABLE contract ADD CONSTRAINT fk_contract_car FOREIGN KEY ( car_plate ) REFERENCES car ( plate );
ALTER TABLE contract ADD CONSTRAINT fk_contract_tourist FOREIGN KEY ( tourist_passport ) REFERENCES tourist ( passport );
ALTER TABLE contract ADD CONSTRAINT fk_contract_driver FOREIGN KEY ( driver_dni ) REFERENCES driver ( dni );
ALTER TABLE contract ADD CONSTRAINT fk_contract_pay_method FOREIGN KEY ( pay_method_id ) REFERENCES pay_method ( id );