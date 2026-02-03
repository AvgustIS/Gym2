
CREATE TABLE member (
                        member_id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        phone VARCHAR(30) NOT NULL,
                        age INTEGER NOT NULL CHECK (age >= 0),
                        membership_type VARCHAR(50) NOT NULL,
                        monthly_fee DOUBLE PRECISION NOT NULL CHECK (monthly_fee >= 0)
);

CREATE TABLE trainer (
                         trainer_id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         phone VARCHAR(30) NOT NULL,
                         age INTEGER NOT NULL CHECK (age >= 0),
                         specialization VARCHAR(100) NOT NULL,
                         salary DOUBLE PRECISION NOT NULL CHECK (salary >= 0)
);

INSERT INTO member (member_id, name, phone, age, membership_type, monthly_fee)
VALUES
    (101, 'Isaali', 87546445536, 20, 'pushup', 50000),
    (102, 'Kairat', 87326442366, 32, 'pushdown', 40000);

-- Test Waiters
INSERT INTO trainer (trainer_id, name, phone, age, specialization, salary)
VALUES
    (129, 'Samat', 8454576578, 26, 'box', 40000),
    (421, 'Gani', 87472790423, 35, 'swim', 30000);