CREATE OR REPLACE FUNCTION business_contract_open(contract_plate varchar(7), contract_start_date date, contract_passport varchar(9), contract_end_date date, contract_pay_method integer, contract_dni varchar(11)) RETURNS void AS $$
BEGIN
	INSERT INTO contract (car_plate, start_date, tourist_passport, end_date, start_km, delivery_date, end_km, pay_method_id, driver_dni, value) VALUES (
		contract_plate,
		contract_start_date,
		contract_passport,
		contract_end_date,
		(SELECT cant_km FROM car WHERE plate = contract_plate),
		null,
		null,
		contract_pay_method,
		contract_dni,
		public.business_contract_value(contract_start_date, contract_end_date, null)
		);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION business_contract_close(contract_plate varchar(7), contract_start_date date, contract_delivery_date date, contract_end_km integer) RETURNS void AS $$
BEGIN
	UPDATE contract SET
		delivery_date = contract_delivery_date,
		end_km = contract_end_km,
		value = public.business_contract_value_update(contract_plate, contract_start_date)
	WHERE car_plate = contract_plate AND start_date = contract_start_date;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION business_contract_value_update(contract_plate varchar(7), contract_start_date date) RETURNS float AS $$
DECLARE
	total_cost float := 0;
	regular_days int := 0;
	extension_days int := 0;
BEGIN
	SELECT (end_date - start_date) INTO regular_days FROM contract WHERE car_plate = contract_plate AND start_date = contract_start_date;
	SELECT (delivery_date - end_date) INTO extension_days FROM contract WHERE car_plate = contract_plate AND start_date = contract_start_date;
	total_cost := total_cost + regular_days * (SELECT day_cost FROM fee WHERE name = 'regular');
	IF extension_days > 0 THEN
		total_cost := total_cost + extension_days * (SELECT day_cost FROM fee WHERE name = 'prórroga');
	END IF;
	RETURN total_cost;
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION business_contract_value(start_date date, end_date date, delivery_date date) RETURNS float AS $$
DECLARE
	total_cost float := 0;
	regular_days int := 0;
	extension_days int := 0;
BEGIN
	regular_days := end_date - start_date;
	extension_days := delivery_date - end_date;
	total_cost := total_cost + regular_days * (SELECT day_cost FROM fee WHERE name = 'regular');
	IF extension_days > 0 THEN
		total_cost := total_cost + extension_days * (SELECT day_cost FROM fee WHERE name = 'prórroga');
	END IF;
	RETURN total_cost;
END; $$
LANGUAGE plpgsql;