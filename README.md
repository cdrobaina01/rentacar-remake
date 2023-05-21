# Rent a Car (Por favor piensen otro nombre)

## Contents
1. <a href="https://github.com/cdrobaina01/rentacar#project-structure">Project Structure</a>
2. <a href="https://github.com/cdrobaina01/rentacar#coding-style">Coding Style</a>
3. <a href="https://github.com/cdrobaina01/rentacar#source-control-standards">Source Control Standards</a> <br>
3.1 <a href="https://github.com/cdrobaina01/rentacar#conventional-commits">Conventional Commits</a> <br>
3.2 <a href="https://github.com/cdrobaina01/rentacar/edit/main/README.md#pull-request-template">Pull Requests</a>
4. <a href="https://github.com/cdrobaina01/rentacar/edit/main/README.md#contributors">Contributors</a>

## Project Structure
La estructura del proyecto de base de datos está compuesta por seis paquetes
principales.

```
src.main.java
  |--- cu.edu.cujae.structdb
        |--- crudDto
        |--- services
        |--- utils
        |--- gui
        |--- persistence
```

0. **cu.edu.cujae.structdb** : Paquete padre dentro del cual se encuentran el resto de paquetes.

1. **crudDto** : En el paquete crudDto aparecen los objetos o clases que permiten realizar la transferencia
de datos entre las capas de la aplicación. Estas clases son muy similares a las tablas
de la base de datos, deben contener los atributos, que se corresponden con las
columnas de la tabla, con sus respectivos métodos de acceso (get() y set()). Este
enfoque responde al patrón DTO (Data Transfer Object). Esto significa que al presionar
en el ActionPerformed de un botón se invoca a un servicio y este recibe como
parámetro un objeto DTO que se construye a partir de los campos de entrada de la
vista. Las clases DTO deben contener al final el sufijo “DTO”, por ejemplo: UserDTO.

2. **services** : En el paquete services aparecen las clases que contienen la lógica de negocio de la
aplicación. Es posible hacer una clase servicio por cada entidad o tabla de la base de
datos. Cuando se cree una clase servicio el nombre de la misma debe terminar en
Services, por ejemplo: xxxxServices.java. Dentro del paquete services debe aparecer
una clase llamada “ServicesLocator.java” en la que están todas las instancias de los
servicios de la aplicación, esto permite que desde las clases visuales solamente se
cree una sola instancia a todos los servicios, precisamente a través de esta clase. Por
tanto, en las clases visuales solamente se declara la instancia a la clase
“ServicesLocator.java” y a través de esta se accede a todos los servicios.

3. **utils** : En el paquete utils se encuentran diferentes clases que sirven de apoyo al desarrollo
del proyecto. Un ejemplo de esto pudiera ser una clase “Conection.java” donde se
define la conexión a la base de datos.

4. **gui** : El en paquete gui se ubican todas las clases correspondientes a la interfaz gráfica
de usuario. Los componentes visuales deben mapear con las clases DTO.

5. **persistence** : El paquete persistence va a almacenar todas las clases relacionadas con la persistencia de los datos. Esto incluye las clases correspondientes al patrón DAO, así como las clases que representan las entidades reflejadas en la DB.

## Coding Style
Please follow the guidelines provided by the Google Java Style Guide. However, this is just a guide so we are free to propose some variations.
In either case, the style MUST be consistent and readable.

Google Java Style Guide https://google.github.io/styleguide/javaguide.html

## Source Control Standards
The main project or original repo, will be located https://github.com/cdrobaina01/rentacar, in order to begin to collaborate you should fork this repo and create
your own working branch.

The project should have 4 continously working branches, the `main` and a `dev` branch for every one of us. For example this branches could be:<br>
`main`, `dev-cdrobaina01`, `dev-hyzokaaa`, `dev-sigel`.<br>
You are free to name your branch whatever you want, but you must always prefix it with `dev-`.<br>
Auxiliary branches can be created to fix bugs or add some dangerous changes.

#### Conventional Commits
This is not mandatory, but i strongly encourage you to start using **Conventional Commits**, you can read more about this in the link below. However, 
Conventional Commits stands as very usefull tool for making consistent the commits messages, searching for a better legibility in the source control system.<br>
Conventional Commits Homepage: https://www.conventionalcommits.org/en/v1.0.0/

Structure of a Commit: 
```
<type>(<scope>): <emoji> <Short Commit description>

<Detailed Commit Description>

<Footer>
```
Note: The Detailed Commit Description and the Footer can be often ommited.

Recommended Types: `init`, `feat`, `fix`, `style`, `docs`, `test`, `refactor`.

Recommended emojis: :one:`:one:`, :sparkles:`:sparkles:` , :bug:`:bug:`, :lipstick:`:lipstick:`, :memo:`:memo:`, :white_check_mark:`:white_check_mark:`, :recycle:`:recycle:`.

Recommended Scopes: `global`, `crudDto`, `services`, `utils`, `gui`.

#### Pull Request Template
You cand find a Pull Request example in the link below: <br>
<a href="https://github.com/cdrobaina01/rentacar/pull/2">Pull Request Example</a>

## Contributors
1. Carlos D. Robaina Rivero @cdrobaina01 :evergreen_tree:

Note: Feel free to add yourself in this list as a practice for your first pull request.
