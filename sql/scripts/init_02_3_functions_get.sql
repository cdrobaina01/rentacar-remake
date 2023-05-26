-- GETTERS FUNCTIONS

-- GET ALL SUPPORT
CREATE OR REPLACE FUNCTION get_rol_all() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'rol';
BEGIN
	OPEN result FOR
	SELECT * FROM rol;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_user_all() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'users';
BEGIN
	OPEN result FOR
	SELECT * FROM user_local;
	RETURN result;
END; $$
LANGUAGE plpgsql;

-- GET SUPPORT BY KEY
CREATE OR REPLACE FUNCTION get_user_by_id(integer) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'user';
BEGIN
	OPEN result FOR
	SELECT * FROM user_local WHERE id = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_rol_by_id(integer) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'rol';
BEGIN
	OPEN result FOR
	SELECT * FROM rol WHERE id = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

-- GET SUPPORT
CREATE OR REPLACE FUNCTION get_user_by_username(text) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'user';
BEGIN
	OPEN result FOR
	SELECT * FROM user_local WHERE username = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

-- GET ALL AUXILIAR
CREATE OR REPLACE FUNCTION get_fee_all() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'fee';
BEGIN
	OPEN result FOR
	SELECT * FROM fee;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_situation_all() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'situations';
BEGIN
	OPEN result FOR
	SELECT * FROM situation;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_paymethod_all() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'paymethods';
BEGIN
	OPEN result FOR
	SELECT * FROM pay_method;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_brand_all() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'brands';
BEGIN
	OPEN result FOR
	SELECT * FROM brand;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_country_all() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'countries';
BEGIN
	OPEN result FOR
	SELECT * FROM country;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_category_all() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'categories';
BEGIN
	OPEN result FOR
	SELECT * FROM category;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_model_all() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'models';
BEGIN
	OPEN result FOR
	SELECT * FROM model;
	RETURN result;
END; $$
LANGUAGE plpgsql;

-- GET AUXILIAR BY ID
CREATE OR REPLACE FUNCTION get_fee_by_id(integer) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'fee';
BEGIN
	OPEN result FOR
	SELECT name, day_cost FROM fee WHERE id = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_situation_by_id(integer) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'situation';
BEGIN
	OPEN result FOR
	SELECT name FROM situation WHERE id = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_paymethod_by_id(integer) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'paymethod';
BEGIN
	OPEN result FOR
	SELECT name FROM pay_method WHERE id = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_brand_by_id(integer) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'brand';
BEGIN
	OPEN result FOR
	SELECT name FROM brand WHERE id = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_country_by_id(integer) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'country';
BEGIN
	OPEN result FOR
	SELECT name FROM country WHERE id = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_category_by_id(integer) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'category';
BEGIN
	OPEN result FOR
	SELECT name FROM category WHERE id = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_model_by_id(integer) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'model';
BEGIN
	OPEN result FOR
	SELECT name, brand_id FROM model WHERE id = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

-- GET AUXILIAR BY NAME
CREATE OR REPLACE FUNCTION get_situation_by_name(varchar) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'situation';
BEGIN
	OPEN result FOR
	SELECT id FROM situation WHERE name = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_paymethod_by_name(varchar) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'paymethod';
BEGIN
	OPEN result FOR
	SELECT id FROM pay_method WHERE name = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_brand_by_name(varchar) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'brand';
BEGIN
	OPEN result FOR
	SELECT id FROM brand WHERE name = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_country_by_name(varchar) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'country';
BEGIN
	OPEN result FOR
	SELECT id FROM country WHERE name = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_category_by_name(varchar) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'category';
BEGIN
	OPEN result FOR
	SELECT id FROM category WHERE name = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_model_by_name(varchar) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'model';
BEGIN
	OPEN result FOR
	SELECT id, brand_id FROM model WHERE name = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

-- GET ALL MAIN
CREATE OR REPLACE FUNCTION get_tourist_all() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'tourists';
BEGIN
	OPEN result FOR
	SELECT * FROM tourist;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_driver_all() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'drivers';
BEGIN
	OPEN result FOR
	SELECT * FROM driver;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_car_all() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'cars';
BEGIN
	OPEN result FOR
	SELECT * FROM car;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_contract_all() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'contract';
BEGIN
	OPEN result FOR
	SELECT * FROM contract;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_contract_by_open() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'contract';
BEGIN
	OPEN result FOR
	SELECT * FROM contract
	WHERE delivery_date ISNULL AND end_km ISNULL;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_contract_by_close() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'contract';
BEGIN
	OPEN result FOR
	SELECT * FROM contract
	WHERE delivery_date IS NOT NULL AND end_km IS NOT NULL;
	RETURN result;
END; $$
LANGUAGE plpgsql;

-- GET MAIN BY KEY
CREATE OR REPLACE FUNCTION get_car_by_plate(varchar) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'car';
BEGIN
	OPEN result FOR
	SELECT * FROM car WHERE plate = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_driver_by_dni(varchar) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'driver';
BEGIN
	OPEN result FOR
	SELECT * FROM driver WHERE dni = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_tourist_by_passport(varchar) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'tourist';
BEGIN
	OPEN result FOR
	SELECT * FROM tourist WHERE passport = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_contract_by_passport(varchar) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'contract';
BEGIN
	OPEN result FOR
	SELECT * FROM contract WHERE tourist_passport = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_contract_by_plate(varchar) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'contract';
BEGIN
	OPEN result FOR
	SELECT * FROM contract WHERE car_plate = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_contract_by_dni(varchar) RETURNS refcursor AS $$
DECLARE
	result refcursor := 'contract';
BEGIN
	OPEN result FOR
	SELECT * FROM contract WHERE driver_dni = $1;
	RETURN result;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_contract_by_year() RETURNS refcursor AS $$
DECLARE
	result refcursor := 'year';
BEGIN
	OPEN result FOR
	SELECT DISTINCT to_char(contract.start_date, '20YY') AS year FROM contract ORDER BY year;
	RETURN result;
END; $$
LANGUAGE plpgsql;
	