-- TRIGGERS
CREATE OR REPLACE FUNCTION trigger_contract_open() RETURNS TRIGGER AS $$
BEGIN
	IF NEW.end_km ISNULL AND NEW.delivery_date ISNULL THEN
		UPDATE car SET
			situation_id = (SELECT id FROM situation WHERE name = 'alquilado')
		WHERE plate = NEW.car_plate;
	END IF;
	RETURN NEW;
END; $$
LANGUAGE plpgsql;
CREATE TRIGGER trigger_contract_open AFTER INSERT ON contract
FOR EACH ROW EXECUTE PROCEDURE trigger_contract_open();


CREATE OR REPLACE FUNCTION trigger_contract_close() RETURNS TRIGGER AS $$
BEGIN
	IF NEW.end_km IS NOT NULL AND NEW.delivery_date IS NOT NULL THEN
		UPDATE car SET
			cant_km = NEW.end_km,
			situation_id = (SELECT id FROM situation WHERE name = 'disponible')
		WHERE plate = NEW.car_plate;
	END IF;
	RETURN NEW;
END; $$
LANGUAGE plpgsql;
CREATE TRIGGER trigger_contract_close AFTER UPDATE ON contract
FOR EACH ROW EXECUTE PROCEDURE trigger_contract_close();