-- Reports

-- Report #1
CREATE OR REPLACE FUNCTION report_tourist_list() RETURNS refcursor AS $$
DECLARE 
	tourist_list refcursor := 'tourist_list';
BEGIN
	OPEN tourist_list FOR
	SELECT plate, brand.name, model.name, color, cant_km FROM car
	JOIN model ON car.model_id = model.id
	JOIN brand ON model.brand_id = brand.id;
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
	SELECT tourist.name, car.plate, brand.name, model.name, pay_method.name, contract.start_date, contract.end_date,  
	(CASE contract.driver_dni WHEN null THEN 'NO'
				  ELSE 'SI' END) AS driver FROM contract
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
	SELECT driver.dni, driver.name, address, category.name, count(DISTINCT contract.car_plate) AS cars FROM driver
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
	SELECT car.plate, brand.name, situation.name, 
	(CASE situation.name WHEN 'rented' THEN contract.end_date
			     ELSE null END) AS end_date FROM car
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