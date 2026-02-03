CREATE TABLE trainer (
                         trainer_id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         phone VARCHAR(30),
                         age INTEGER,
                         specialization VARCHAR(100),
                         salary DECIMAL(10,2) NOT NULL
);

CREATE TABLE member (
                        member_id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        phone VARCHAR(30),
                        age INTEGER,
                        membership_type VARCHAR(50) NOT NULL,
                        monthly_fee DECIMAL(10,2) NOT NULL
);

