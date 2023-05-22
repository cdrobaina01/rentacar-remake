-- INSERT INITIAL DATA
SELECT public.insert_rol_admin('Visitante', 'Acceso a los Reportes presentados por la aplicación, así como a consultar la información de las distintas tablas. Solo Lectura.');
SELECT public.insert_rol_admin('Trabajador', 'Acceso a abrir y cerrar Contratos, registrar y modificar Turistas, Países, Autos, Marcas, Modelos, Situaciónes de los Autos y Categorías de Licencia.');
SELECT public.insert_rol_admin('Jefe de Área', 'Acceso a registrar y modificar Métodos de Pago, modificar las tarifas, contratar y despedir Conductores, modificar y eliminar Contratos.');
SELECT public.insert_rol_admin('Administrador', 'Acceso total a la aplicación, lo que incluye creación, modificación y eliminación de usuarios y roles.');

SELECT public.insert_user_admin('admin', '$2a$10$FSGXIjxBqC1KsyoKCW5bweent/GCxMBmPjd8zaHWk4Mdrznk0bD2q', (SELECT id FROM rol WHERE name = 'Administrador'));

SELECT public.insert_fee_admin('regular', 10);
SELECT public.insert_fee_admin('prórroga', 14);

SELECT public.insert_situation('disponible');
SELECT public.insert_situation('alquilado');
SELECT public.insert_situation('en taller');

SELECT public.insert_paymethod('efectivo');
SELECT public.insert_paymethod('tarjeta de crédito');
SELECT public.insert_paymethod('cheque');

SELECT public.insert_brand('Audi');
SELECT public.insert_brand('Kia');
SELECT public.insert_brand('Ford');
SELECT public.insert_brand('Chevrolet');

SELECT public.insert_category('B');
SELECT public.insert_category('C');
SELECT public.insert_category('D');
SELECT public.insert_category('E');

SELECT public.insert_country('Canadá');
SELECT public.insert_country('Estados Unidos');
SELECT public.insert_country('Rusia');
SELECT public.insert_country('Francia');
SELECT public.insert_country('España');

SELECT public.insert_model('A4',       (SELECT id FROM brand WHERE name = 'Audi'));
SELECT public.insert_model('Picanto',  (SELECT id FROM brand WHERE name = 'Kia'));
SELECT public.insert_model('Rio',      (SELECT id FROM brand WHERE name = 'Kia'));
SELECT public.insert_model('Sportage', (SELECT id FROM brand WHERE name = 'Kia'));
SELECT public.insert_model('Fiesta',   (SELECT id FROM brand WHERE name = 'Ford'));
SELECT public.insert_model('Focus',    (SELECT id FROM brand WHERE name = 'Ford'));
SELECT public.insert_model('Camaro',   (SELECT id FROM brand WHERE name = 'Chevrolet'));

-- Drivers
SELECT public.insert_driver(
	'99030467856',
	'Marcos Pérez',
	(SELECT id FROM category WHERE name = 'B'),
	'23 e/ A y B, Plaza de la Revolución'
);
SELECT public.insert_driver(
	'80112745301',
	'Alberto Prado',
	(SELECT id FROM category WHERE name = 'E'),
	'Espada e/ Escudo y Armadura, Centro Habana'
);
SELECT public.insert_driver(
	'79092345678',
	'Luis Serio',
	(SELECT id FROM category WHERE name = 'C'),
	'10 de Octubre e/ Santa Catalina y Juan Delgado, 10 de Octubre'
);
SELECT public.insert_driver(
	'95071834205',
	'Ulises Martínez',
	(SELECT id FROM category WHERE name = 'B'),
	'41 e/ 42 y 44, Playa'
);


-- Tourists
SELECT public.insert_tourist(
	'ABC090807',
	'Peter Smith',
	34,
	'M',
	'+1345234098',
	(SELECT id FROM country WHERE name = 'Estados Unidos')
);
SELECT public.insert_tourist(
	'ART090807',
	'Daniel Smith',
	34,
	'M',
	'+1345234098',
	(SELECT id FROM country WHERE name = 'Estados Unidos')
);
SELECT public.insert_tourist(
	'ABC070807',
	'Lidia Oropesa',
	25,
	'F',
	'+34345234098',
	(SELECT id FROM country WHERE name = 'España')
);
SELECT public.insert_tourist(
	'ABC230789',
	'Peter Mollineux',
	22,
	'M',
	'+09345234098',
	(SELECT id FROM country WHERE name = 'Canadá')
);
SELECT public.insert_tourist(
	'AEC070789',
	'Ivanka Putina',
	43,
	'F',
	'+56345234098',
	(SELECT id FROM country WHERE name = 'Rusia')
);
SELECT public.insert_tourist(
	'AEC071239',
	'Inna Vladimirova',
	43,
	'F',
	'+56345234098',
	(SELECT id FROM country WHERE name = 'Rusia')
);
SELECT public.insert_tourist(
	'FEC340789',
	'Karina Kalashkinova',
	19,
	'F',
	'+5678900898',
	(SELECT id FROM country WHERE name = 'Rusia')
);

-- Cars
SELECT public.insert_car(
	'T890123',
	(SELECT id FROM brand WHERE name = 'Kia'),
	(SELECT id FROM model WHERE name = 'Picanto'),
	2575,
	'Blanco',
	(SELECT id FROM situation WHERE name = 'disponible')
);
SELECT public.insert_car(
	'T895690',
	(SELECT id FROM brand WHERE name = 'Chevrolet'),
	(SELECT id FROM model WHERE name = 'Camaro'),
	3012,
	'Negro',
	(SELECT id FROM situation WHERE name = 'disponible')
);
SELECT public.insert_car(
	'T123456',
	(SELECT id FROM brand WHERE name = 'Ford'),
	(SELECT id FROM model WHERE name = 'Fiesta'),
	1207,
	'Amarillo',
	(SELECT id FROM situation WHERE name = 'disponible')
);
SELECT public.insert_car(
	'T123478',
	(SELECT id FROM brand WHERE name = 'Kia'),
	(SELECT id FROM model WHERE name = 'Rio'),
	1632,
	'Negro',
	(SELECT id FROM situation WHERE name = 'disponible')
);
SELECT public.insert_car(
	'T452309',
	(SELECT id FROM brand WHERE name = 'Kia'),
	(SELECT id FROM model WHERE name = 'Sportage'),
	2150,
	'Blanco',
	(SELECT id FROM situation WHERE name = 'disponible')
);
SELECT public.insert_car(
	'T098734',
	(SELECT id FROM brand WHERE name = 'Audi'),
	(SELECT id FROM model WHERE name = 'A4'),
	2970,
	'Negro',
	(SELECT id FROM situation WHERE name = 'disponible')
);
SELECT public.insert_car(
	'T654985',
	(SELECT id FROM brand WHERE name = 'Ford'),
	(SELECT id FROM model WHERE name = 'Focus'),
	789,
	'Rojo',
	(SELECT id FROM situation WHERE name = 'disponible')
);
SELECT public.insert_car(
	'T963852',
	(SELECT id FROM brand WHERE name = 'Kia'),
	(SELECT id FROM model WHERE name = 'Rio'),
	1346,
	'Blanco',
	(SELECT id FROM situation WHERE name = 'disponible')
);
SELECT public.insert_car(
	'T741852',
	(SELECT id FROM brand WHERE name = 'Audi'),
	(SELECT id FROM model WHERE name = 'A4'),
	1590,
	'Blanco',
	(SELECT id FROM situation WHERE name = 'disponible')
);

-- Contracts
SELECT public.business_contract_open(
	'T895690',
	'2021/12/02',
	'AEC071239',
	'2021/12/27',
	(SELECT id FROM pay_method WHERE name = 'efectivo'),
	null
);
SELECT public.business_contract_close(
	'T895690',
	'2021/12/02',
	'2021/12/28',
	7090
);
SELECT public.business_contract_open(
	'T895690',
	'2023/01/27',
	'ABC070807',
	'2023/04/15',
	(SELECT id FROM pay_method WHERE name = 'cheque'),
	null
);
SELECT public.business_contract_open(
	'T123456',
	'2023/02/13',
	'AEC071239',
	'2023/03/13',
	(SELECT id FROM pay_method WHERE name = 'efectivo'),
	'79092345678'
);
SELECT public.business_contract_close(
	'T123456',
	'2023/02/13',
	'2023/03/13',
	1667
);
SELECT public.business_contract_open(
	'T098734',
	'2023/03/07',
	'ABC090807',
	'2023/05/16',
	(SELECT id FROM pay_method WHERE name = 'tarjeta de crédito'),
	'80112745301'
);
SELECT public.business_contract_open(
	'T123478',
	'2022/08/30',
	'ABC230789',
	'2022/09/30',
	(SELECT id FROM pay_method WHERE name = 'efectivo'),
	'95071834205'
);
SELECT public.business_contract_close(
	'T123478',
	'2022/08/30',
	'2022/10/30',
	3409
);
SELECT public.business_contract_open(
	'T452309',
	'2021/12/04',
	'AEC070789',
	'2022/04/30',
	(SELECT id FROM pay_method WHERE name = 'cheque'),
	'99030467856'
);
SELECT public.business_contract_close(
	'T452309',
	'2021/12/04',
	'2022/04/30',
	4278
);
SELECT public.business_contract_open(
	'T654985',
	'2023/05/03',
	'ART090807',
	'2023/05/17',
	(SELECT id FROM pay_method WHERE name = 'efectivo'),
	null
);
SELECT public.business_contract_open(
	'T741852',
	'2022/01/20',
	'DFG098765',
	'2022/02/20',
	(SELECT id FROM pay_method WHERE name = 'tarjeta de crédito'),
	'99030467856'
);
SELECT public.business_contract_close(
	'T741852',
	'2022/01/20',
	'2022/03/17',
	9067
);
SELECT public.business_contract_open(
	'T890123',
	'2023/04/01',
	'FEC340789',
	'2023/05/22',
	(SELECT id FROM pay_method WHERE name = 'tarjeta de crédito'),
	null
);
SELECT public.business_contract_open(
	'T963852',
	'2022/11/02',
	'ABC230789',
	'2022/12/30',
	(SELECT id FROM pay_method WHERE name = 'efectivo'),
	'79092345678'
);
SELECT public.business_contract_close(
	'T963852',
	'2022/11/02',
	'2022/12/30',
	6873
);
SELECT public.business_contract_open(
	'T123478',
	'2022/12/02',
	'ABC070807',
	'2022/12/27',
	(SELECT id FROM pay_method WHERE name = 'efectivo'),
	'95071834205'
);
SELECT public.business_contract_close(
	'T123478',
	'2022/12/02',
	'2022/12/30',
	5400
);