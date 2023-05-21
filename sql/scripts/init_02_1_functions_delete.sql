-- ADMIN DELETE FUNCTIONS
CREATE OR REPLACE FUNCTION delete_fee_admin(text) RETURNS void AS $$
BEGIN
	DELETE FROM fee WHERE name = $1;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_rol_admin(integer) RETURNS void AS $$
BEGIN
	DELETE FROM rol WHERE id = $1;
END; $$
LANGUAGE plpgsql;

-- SPECIAL
CREATE OR REPLACE FUNCTION delete_situation(text) RETURNS void AS $$
BEGIN
	IF $1 NOT IN ('disponible', 'alquilado', 'en taller') THEN
		DELETE FROM situation WHERE name = $1;
	ELSE
		RAISE EXCEPTION 'asd';
	END IF;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_paymethod(text) RETURNS void AS $$
BEGIN
	IF $1 NOT IN ('efectivo', 'cheque', 'tarjeta de crédito') THEN
		DELETE FROM pay_method WHERE name = $1;
	END IF;
END; $$
LANGUAGE plpgsql;


-- REGULAR DELETE FUNCTIONS
CREATE OR REPLACE FUNCTION delete_user(integer) RETURNS void AS $$
BEGIN
	DELETE FROM user_local WHERE id = $1;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_brand(text) RETURNS void AS $$
BEGIN
	DELETE FROM brand WHERE name = $1;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_country(text) RETURNS void AS $$
BEGIN
	DELETE FROM country WHERE name = $1;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_category(text) RETURNS void AS $$
BEGIN
	DELETE FROM category WHERE name = $1;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_model(text) RETURNS void AS $$
BEGIN
	DELETE FROM model WHERE name = $1;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_driver(varchar(11)) RETURNS void AS $$
BEGIN
	DELETE FROM driver WHERE dni = $1;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_tourist(varchar(9)) RETURNS void AS $$
BEGIN
	DELETE FROM tourist WHERE passport = $1;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_car(varchar(7)) RETURNS void AS $$
BEGIN
	DELETE FROM car WHERE plate = $1;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION delete_contract(varchar(7), date) RETURNS void AS $$
BEGIN
	DELETE FROM contract WHERE car_plate = $1 AND start_date = $2;
END; $$
LANGUAGE plpgsql;