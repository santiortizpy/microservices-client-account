-- Script de Base de Datos: BaseDeDatos.sql

-- Crear la base de datos (opcional, dependiendo del entorno)
CREATE DATABASE microservice_db;
USE microservice_db;

-- Crear la tabla Persona
CREATE TABLE persona (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         genero VARCHAR(10),
                         edad INT,
                         identificacion VARCHAR(50) UNIQUE NOT NULL,
                         direccion VARCHAR(255),
                         telefono VARCHAR(20)
);

-- Crear la tabla Cliente heredando de Persona
CREATE TABLE cliente (
                         cliente_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         id BIGINT NOT NULL,
                         contraseña VARCHAR(255) NOT NULL,
                         estado BOOLEAN NOT NULL,
                         FOREIGN KEY (id) REFERENCES persona(id)
);

-- Crear la tabla Cuenta
CREATE TABLE cuenta (
                        cuenta_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        numero_cuenta VARCHAR(50) UNIQUE NOT NULL,
                        tipo_cuenta VARCHAR(50),
                        saldo_inicial DECIMAL(15, 2) NOT NULL,
                        estado BOOLEAN NOT NULL,
                        cliente_id BIGINT NOT NULL,
                        FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id)
);

-- Crear la tabla Movimiento
CREATE TABLE movimiento (
                            movimiento_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            tipo_movimiento VARCHAR(50) NOT NULL,
                            valor DECIMAL(15, 2) NOT NULL,
                            saldo DECIMAL(15, 2) NOT NULL,
                            cuenta_id BIGINT NOT NULL,
                            FOREIGN KEY (cuenta_id) REFERENCES cuenta(cuenta_id)
);

-- Insertar datos de ejemplo en Persona
INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono) VALUES
                                                                                    ('Jose Lema', 'Masculino', 30, '1234567890', 'Otavalo sn y principal', '098254785'),
                                                                                    ('Marianela Montalvo', 'Femenino', 28, '2345678901', 'Amazonas y NNUU', '097548965'),
                                                                                    ('Juan Osorio', 'Masculino', 35, '3456789012', '13 junio y Equinoccial', '098874587');

-- Insertar datos de ejemplo en Cliente
INSERT INTO cliente (id, contraseña, estado) VALUES
                                                 ((SELECT id FROM persona WHERE identificacion = '1234567890'), '1234', TRUE),
                                                 ((SELECT id FROM persona WHERE identificacion = '2345678901'), '5678', TRUE),
                                                 ((SELECT id FROM persona WHERE identificacion = '3456789012'), '1245', TRUE);

-- Insertar datos de ejemplo en Cuenta
INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id) VALUES
                                                                                       ('478758', 'Ahorros', 2000.00, TRUE, (SELECT cliente_id FROM cliente WHERE id = (SELECT id FROM persona WHERE identificacion = '1234567890'))),
                                                                                       ('225487', 'Corriente', 100.00, TRUE, (SELECT cliente_id FROM cliente WHERE id = (SELECT id FROM persona WHERE identificacion = '2345678901'))),
                                                                                       ('495878', 'Ahorros', 0.00, TRUE, (SELECT cliente_id FROM cliente WHERE id = (SELECT id FROM persona WHERE identificacion = '3456789012'))),
                                                                                       ('496825', 'Ahorros', 540.00, TRUE, (SELECT cliente_id FROM cliente WHERE id = (SELECT id FROM persona WHERE identificacion = '2345678901'))),
                                                                                       ('585545', 'Corriente', 1000.00, TRUE, (SELECT cliente_id FROM cliente WHERE id = (SELECT id FROM persona WHERE identificacion = '1234567890')));

-- Insertar datos de ejemplo en Movimiento
INSERT INTO movimiento (fecha, tipo_movimiento, valor, saldo, cuenta_id) VALUES
                                                                             ('2022-02-10', 'Retiro', 575.00, 1425.00, (SELECT cuenta_id FROM cuenta WHERE numero_cuenta = '478758')),
                                                                             ('2022-02-10', 'Deposito', 600.00, 700.00, (SELECT cuenta_id FROM cuenta WHERE numero_cuenta = '225487')),
                                                                             ('2022-02-10', 'Deposito', 150.00, 150.00, (SELECT cuenta_id FROM cuenta WHERE numero_cuenta = '495878')),
                                                                             ('2022-02-10', 'Retiro', 540.00, 0.00, (SELECT cuenta_id FROM cuenta WHERE numero_cuenta = '496825'));
