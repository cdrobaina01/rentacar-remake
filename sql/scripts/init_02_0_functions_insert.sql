-- ADMIN INSERT FUNCTIONS
CREATE OR REPLACE FUNCTION insert_fee_admin(fee_name text, fee_day_cost float) RETURNS void AS $$
BEGIN
	INSERT INTO fee (name, day_cost) VALUES (fee_name, fee_day_cost);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_user_admin(user_username text, user_password text, user_rol integer) RETURNS void AS $$
BEGIN
	INSERT INTO user_local (username, password, rol_id) VALUES (user_username, user_password, user_rol);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_rol_admin(rol_name text, rol_description text) RETURNS void AS $$
BEGIN
	INSERT INTO rol (name, description) VALUES (rol_name, rol_description);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_contract_admin(contract_plate varchar(7), contract_start_date date, contract_passport varchar(9), contract_end_date date, contract_start_km integer, contract_delivery_date date, contract_end_km integer, contract_pay_method integer, contract_dni varchar(11)) RETURNS void AS $$
BEGIN
	INSERT INTO contract (car_plate, start_date, tourist_passport, end_date, start_km, delivery_date, end_km, pay_method_id, driver_dni, value) VALUES (
		contract_plate,
		contract_start_date,
		contract_passport,
		contract_end_date,
		contract_start_km,
		contract_delivery_date,
		contract_end_km,
		contract_pay_method,
		contract_dni,
		public.business_contract_value(contract_start_date, contract_end_date, contract_delivery_date)
		);
END; $$
LANGUAGE plpgsql;


-- REGULAR INSERT FUNCTIONS
CREATE OR REPLACE FUNCTION insert_user(user_username text, user_password text, user_rol integer) RETURNS void AS $$
BEGIN
	INSERT INTO user_local (username, password, rol_id) VALUES (user_username, user_password, user_rol);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_situation(text) RETURNS void AS $$
BEGIN
	INSERT INTO situation (name) VALUES ($1);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_paymethod(text) RETURNS void AS $$
BEGIN
	INSERT INTO pay_method (name) VALUES ($1);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_brand(text) RETURNS void AS $$
BEGIN
	INSERT INTO brand (name) VALUES ($1);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_country(text) RETURNS void AS $$
BEGIN
	INSERT INTO country (name) VALUES ($1);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_category(text) RETURNS void AS $$
BEGIN
	INSERT INTO category (name) VALUES ($1);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_model(model_name text, model_brand integer) RETURNS void AS $$
BEGIN
	INSERT INTO model (brand_id, name) VALUES (
			model_brand,
			model_name
			);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_driver(driver_dni varchar(11), driver_name text, driver_category integer, driver_address text) RETURNS void AS $$
BEGIN
	INSERT INTO driver (dni, name, category_id, address) VALUES (
		driver_dni,
		driver_name,
		driver_category,
		driver_address
		);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_tourist(tourist_passport varchar(9), tourist_name text, tourist_age integer, tourist_sex char, tourist_contact text, tourist_country integer) RETURNS void AS $$
BEGIN
	INSERT INTO tourist (passport, name, age, sex, contact, country_id) VALUES (
		tourist_passport,
		tourist_name,
		tourist_age,
		tourist_sex,
		tourist_contact,
		tourist_country
		);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insert_car(car_plate varchar(7), car_brand integer, car_model integer, car_cant_km integer, car_color text, car_situation integer) RETURNS void AS $$
BEGIN
	INSERT INTO car (plate, brand_id, model_id, cant_km, color, situation_id) VALUES (
		car_plate,
		car_brand,
		car_model,
		car_cant_km,
		car_color,
		car_situation
		);
END; $$
LANGUAGE plpgsql;