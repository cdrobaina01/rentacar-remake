-- Reports

-- Report #1
CREATE OR REPLACE FUNCTION report_tourist_list() RETURNS refcursor AS $$
DECLARE 
	tourist_list refcursor := 'tourist_list';
BEGIN
	OPEN tourist_list FOR
	SELECT country.name AS country, tourist.name AS name, tourist.passport AS passport, count(contract.tourist_passport) AS cars, sum(contract.value) AS value FROM tourist
	JOIN contract ON contract.tourist_passport = tourist.passport
	JOIN country ON country.id = tourist.country_id
	GROUP BY country.name, tourist.name, tourist.passport;
	RETURN car_list;
END; $$
LANGUAGE 'plpgsql';

-- Report #2
CREATE OR REPLACE FUNCTION report_car_list() RETURNS refcursor AS $$
DECLARE 
	car_list refcursor := 'car_list';
BEGIN
	OPEN car_list FOR
	SELECT plate, brand.name, model.name, color, cant_km FROM car
	JOIN model ON car.model_id = model.id
	JOIN brand ON model.brand_id = brand.id;
	RETURN car_list;
END; $$
LANGUAGE 'plpgsql';

-- Report #3
CREATE OR REPLACE FUNCTION report_contract_list() RETURNS refcursor AS $$
DECLARE 
	contract_list refcursor := 'contract_list';
BEGIN
	OPEN contract_list FOR
	SELECT tourist.name AS name, car.plate AS plate, brand.name AS brand, model.name AS model, pay_method.name AS pay_method,
	contract.start_date AS start_date, contract.end_date AS end_date,
	(CASE
		WHEN contract.delivery_date - contract.end_date = 0 THEN null
		ELSE contract.delivery_date - contract.end_date END) 
	AS extension,
	(CASE 
		WHEN contract.driver_dni IS NULL THEN 'No'
		ELSE 'Si' END) 
	AS driver, 
	contract.value AS value
	FROM contract
	JOIN tourist ON tourist.passport = contract.tourist_passport
	JOIN car ON car.plate = contract.car_plate
	JOIN model ON model.id = car.model_id
	JOIN brand ON brand.id = model.brand_id
	JOIN pay_method ON pay_method.id = contract.pay_method_id;
	RETURN contract_list;
END; $$
LANGUAGE 'plpgsql';

-- Report #4
CREATE OR REPLACE FUNCTION report_driver_list() RETURNS refcursor AS $$
DECLARE 
	driver_list refcursor := 'driver_list';
BEGIN
	OPEN driver_list FOR
	SELECT driver.dni AS dni, driver.name AS name, address AS address, category.name AS category, count(DISTINCT contract.car_plate) AS cars FROM driver
	JOIN category ON category.id = driver.category_id
	LEFT JOIN contract ON contract.driver_dni = driver.dni
	GROUP BY driver.dni, category.name;
	RETURN driver_list;
END; $$
LANGUAGE 'plpgsql';

-- Report #5
CREATE OR REPLACE FUNCTION report_situation_list() RETURNS refcursor AS $$
DECLARE 
	situation_list refcursor := 'situation_list';
BEGIN
	OPEN situation_list FOR
	SELECT car.plate AS plate, brand.name AS brand, situation.name AS situation, 
	(CASE situation.name 
		WHEN 'alquilado' THEN contract.end_date
		ELSE null END) 
	AS end_date 
	FROM car
	JOIN model ON model.id = car.model_id
	JOIN brand ON brand.id = model.brand_id
	JOIN situation ON car.situation_id = situation.id
	LEFT JOIN contract ON car.plate = contract.car_plate;
	RETURN situation_list;
END; $$
LANGUAGE 'plpgsql';

-- Report #6
CREATE OR REPLACE FUNCTION report_defaulters_list() RETURNS refcursor AS $$
DECLARE 
	defaulters_list refcursor := 'defaulters_list';
BEGIN
	OPEN defaulters_list FOR
	SELECT tourist.name, contract.end_date, contract.delivery_date FROM contract
	JOIN tourist ON tourist.passport = contract.tourist_passport
	WHERE end_date != delivery_date;
	RETURN defaulters_list;
END; $$
LANGUAGE 'plpgsql';

-- Report #7
CREATE OR REPLACE FUNCTION report_contract_brand() RETURNS refcursor AS $$
DECLARE 
	contracts_brands refcursor := 'contracts_brands';
BEGIN
	OPEN contracts_brands FOR
	SELECT
	brand.name AS brand,
	model.name AS model,
	count(car.plate) AS cars,
	sum(contract.delivery_date - contract.start_date) AS rented,
	(SELECT sum(contract.value) FROM contract WHERE contract.car_plate = car.plate AND contract.pay_method_id = (SELECT id FROM pay_method WHERE name = 'tarjeta de crédito')) AS credit_value,
	(SELECT sum(contract.value) FROM contract WHERE contract.car_plate = car.plate AND contract.pay_method_id = (SELECT id FROM pay_method WHERE name = 'cheque')) AS check_value,
	(SELECT sum(contract.value) FROM contract WHERE contract.car_plate = car.plate AND contract.pay_method_id = (SELECT id FROM pay_method WHERE name = 'efectivo')) AS cash_value,
	sum(contract.value) AS brand_value,
	(SELECT sum(contract.value) FROM contract)
	FROM brand
	JOIN model ON model.brand_id = brand.id
	JOIN car ON car.model_id = model.id
	LEFT JOIN contract ON contract.car_plate = car.plate
	GROUP BY brand, model, car.plate;
	RETURN contracts_brands;
END; $$
LANGUAGE 'plpgsql';

-- Report #8
CREATE OR REPLACE FUNCTION report_contract_country() RETURNS refcursor AS $$
DECLARE 
	contract_country refcursor := 'contract_country';
BEGIN
	OPEN contract_country FOR
	SELECT 
	country.name AS country, 
	brand.name AS brand, 
	model.name AS model, 
	sum(contract.delivery_date - contract.start_date) AS rented, 
	sum(contract.delivery_date - contract.end_date) AS extension,
	sum(CASE contract.pay_method_id 
		WHEN (SELECT id FROM pay_method WHERE name = 'efectivo') THEN contract.value
		ELSE 0
		END) AS cash_value,
	sum(contract.value) AS total_value
	FROM country
	JOIN tourist ON tourist.country_id = country.id
	JOIN contract ON contract.tourist_passport = tourist.passport
	JOIN car ON car.plate = contract.car_plate
	JOIN brand ON brand.id = car.brand_id
	JOIN model ON model.id = car.model_id
	GROUP BY country.name, brand.name, model.name;
	RETURN contract_country;
END; $$
LANGUAGE 'plpgsql';

-- Report #9
CREATE OR REPLACE FUNCTION report_income_year() RETURNS refcursor AS $$
DECLARE 
	income_year refcursor := 'income_year';
BEGIN
	OPEN income_year FOR
	SELECT
	extract(MONTH FROM contract.start_date) AS month_number,
	to_char(contract.start_date, 'TMMONTH') AS month,
	extract(YEAR FROM contract.start_date) AS year,
	sum(contract.value) AS month_income
	FROM contract
	GROUP BY month_number, month, year, year_income
	ORDER BY year DESC, month_number;
	RETURN income_year;
END; $$
LANGUAGE 'plpgsql';