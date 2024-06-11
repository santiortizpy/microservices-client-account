-- Script de Base de Datos: BaseDeDatos.sql
-- Eliminar la base de datos (opcional, dependiendo del entorno)
DROP DATABASE IF EXISTS microservice_db;
-- Crear la base de datos (opcional, dependiendo del entorno)
CREATE DATABASE IF NOT EXISTS microservice_db;
GRANT ALL PRIVILEGES ON microservice_db.* TO 'account'@'%';
USE microservice_db;

-- Crear la tabla Persona
CREATE TABLE IF NOT EXISTS persona (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         genero VARCHAR(10),
                         edad INT,
                         identificacion VARCHAR(50) UNIQUE NOT NULL,
                         direccion VARCHAR(255),
                         telefono VARCHAR(20),
                         persona_type VARCHAR(10) NOT NULL,
                         contrasenha VARCHAR(255) NOT NULL,
                         estado BOOLEAN NOT NULL
);



-- Crear la tabla Cuenta
CREATE TABLE IF NOT EXISTS cuenta (
                        cuenta_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        numero_cuenta VARCHAR(50) UNIQUE NOT NULL,
                        tipo_cuenta VARCHAR(50),
                        saldo_inicial DECIMAL(15, 2) NOT NULL,
                        estado BOOLEAN NOT NULL,
                        cliente_id BIGINT NOT NULL
                        );

-- Crear la tabla Movimiento
CREATE TABLE IF NOT EXISTS movimiento (
                            movimiento_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            tipo_movimiento VARCHAR(50) NOT NULL,
                            valor DECIMAL(15, 2) NOT NULL,
                            saldo DECIMAL(15, 2) NOT NULL,
                            cuenta_id BIGINT NOT NULL,
                            FOREIGN KEY (cuenta_id) REFERENCES cuenta(cuenta_id)
);

-- Insertar datos de ejemplo en Persona
INSERT IGNORE INTO  persona (nombre, genero, edad, identificacion, direccion, telefono,persona_type,contrasenha, estado) VALUES
                                                                                    ('Jose Lema', 'Masculino', 30, '1234567890', 'Otavalo sn y principal', '098254785', 'cliente','1234', TRUE),
                                                                                    ('Marianela Montalvo', 'Femenino', 28, '2345678901', 'Amazonas y NNUU', '097548965', 'cliente','1234', TRUE),
                                                                                    ('Juan Osorio', 'Masculino', 35, '3456789012', '13 junio y Equinoccial', '098874587', 'cliente','1234', TRUE);


-- Insertar datos de ejemplo en Cuenta
INSERT IGNORE INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id) VALUES
                                                                                       ('478758', 'Ahorros', 2000.00, TRUE, (SELECT id FROM persona WHERE identificacion = '1234567890')),
                                                                                       ('225487', 'Corriente', 100.00, TRUE, (SELECT id FROM persona WHERE identificacion = '2345678901')),
                                                                                       ('495878', 'Ahorros', 0.00, TRUE, (SELECT id FROM persona WHERE identificacion = '3456789012')),
                                                                                       ('496825', 'Ahorros', 540.00, TRUE, (SELECT id FROM persona WHERE identificacion = '2345678901')),
                                                                                       ('585545', 'Corriente', 1000.00, TRUE, (SELECT id FROM persona WHERE identificacion = '1234567890'));

-- Insertar datos de ejemplo en Movimiento
INSERT IGNORE INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id) VALUES
                                                                             ('2022-02-10', 'Retiro', 575.00, 1425.00, (SELECT cuenta_id FROM cuenta WHERE numero_cuenta = '478758')),
                                                                             ('2022-02-10', 'Deposito', 600.00, 700.00, (SELECT cuenta_id FROM cuenta WHERE numero_cuenta = '225487')),
                                                                             ('2022-02-10', 'Deposito', 150.00, 150.00, (SELECT cuenta_id FROM cuenta WHERE numero_cuenta = '495878')),
                                                                             ('2022-02-10', 'Retiro', 540.00, 0.00, (SELECT cuenta_id FROM cuenta WHERE numero_cuenta = '496825'));
