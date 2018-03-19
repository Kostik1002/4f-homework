-- CLIENTS
INSERT INTO PUBLIC.CLIENTS (NAME, PHONE, SURNAME, STATUS) VALUES ('James', '504-621-8927', 'Butt', 'CLEAR');
INSERT INTO PUBLIC.CLIENTS (NAME, PHONE, SURNAME, STATUS) VALUES ('Josephine', '810-292-9388', 'Darakjy', 'DIRTY');
INSERT INTO PUBLIC.CLIENTS (NAME, PHONE, SURNAME, STATUS) VALUES ('Art', '856-636-8749', 'Venere', 'DIRTY');
INSERT INTO PUBLIC.CLIENTS (NAME, PHONE, SURNAME, STATUS) VALUES ('Lenna', '907-385-4412', 'Paprocki', 'CLEAR');
INSERT INTO PUBLIC.CLIENTS (NAME, PHONE, SURNAME, STATUS) VALUES ('Donette', '513-570-1893', 'Foller', 'DIRTY');
INSERT INTO PUBLIC.CLIENTS (NAME, PHONE, SURNAME, STATUS) VALUES ('Simona', '419-503-2484', 'Morasca', 'CLEAR');
INSERT INTO PUBLIC.CLIENTS (NAME, PHONE, SURNAME, STATUS) VALUES ('Mitsue', '773-573-6914', 'Tollner', 'CLEAR');
INSERT INTO PUBLIC.CLIENTS (NAME, PHONE, SURNAME, STATUS) VALUES ('Leota', '408-752-3500', 'Dilliard', 'CLEAR');
INSERT INTO PUBLIC.CLIENTS (NAME, PHONE, SURNAME, STATUS) VALUES ('Sage', '605-414-2147', 'Wieser', 'DIRTY');
INSERT INTO PUBLIC.CLIENTS (NAME, PHONE, SURNAME, STATUS) VALUES ('Kris', '410-655-8723', 'Marrier', 'DIRTY');

-- LOANS
INSERT INTO PUBLIC.LOANS (AMOUNT, CLIENT_ID, DATE, STATUS) VALUES (1000.00, 5, DATEADD('DAY', -7, CURRENT_DATE), 'OPENED');
INSERT INTO PUBLIC.LOANS (AMOUNT, CLIENT_ID, DATE, STATUS) VALUES (500.00, 2, DATEADD('DAY', -5, CURRENT_DATE), 'OPENED');
INSERT INTO PUBLIC.LOANS (AMOUNT, CLIENT_ID, DATE, STATUS) VALUES (250.00, 1, DATEADD('DAY', -1, CURRENT_DATE), 'OPENED');
INSERT INTO PUBLIC.LOANS (AMOUNT, CLIENT_ID, DATE, STATUS) VALUES (400.00, 2, DATEADD('HOUR', -12, CURRENT_DATE), 'OPENED');
INSERT INTO PUBLIC.LOANS (AMOUNT, CLIENT_ID, DATE, STATUS) VALUES (600.00, 2, DATEADD('HOUR', -7, CURRENT_DATE), 'OPENED');
INSERT INTO PUBLIC.LOANS (AMOUNT, CLIENT_ID, DATE, STATUS) VALUES (600.00, 2, DATEADD('HOUR', -2, CURRENT_DATE), 'OPENED');
INSERT INTO PUBLIC.LOANS (AMOUNT, CLIENT_ID, DATE, STATUS) VALUES (800.00, 1, CURRENT_DATE, 'OPENED');

-- LOAN_DETAILS
INSERT INTO PUBLIC.LOAN_DETAILS (DATE, LOAN_ID, TERM, STATUS) VALUES (DATEADD('DAY', -7, CURRENT_DATE), 1, DATEADD('DAY', 7, CURRENT_DATE), 'INITIAL');
INSERT INTO PUBLIC.LOAN_DETAILS (DATE, LOAN_ID, TERM, STATUS) VALUES (DATEADD('DAY', -1, CURRENT_DATE), 2, DATEADD('DAY', 14, CURRENT_DATE), 'INITIAL');
INSERT INTO PUBLIC.LOAN_DETAILS (DATE, LOAN_ID, TERM, STATUS) VALUES (DATEADD('DAY', -2, CURRENT_DATE), 3, DATEADD('DAY', 15, CURRENT_DATE), 'INITIAL');
INSERT INTO PUBLIC.LOAN_DETAILS (DATE, LOAN_ID, TERM, STATUS) VALUES (DATEADD('HOUR', -12, CURRENT_DATE), 4, DATEADD('DAY', 4, CURRENT_DATE), 'INITIAL');
INSERT INTO PUBLIC.LOAN_DETAILS (DATE, LOAN_ID, TERM, STATUS) VALUES (DATEADD('HOUR', -7, CURRENT_DATE), 5, DATEADD('DAY', 30, CURRENT_DATE), 'INITIAL');
INSERT INTO PUBLIC.LOAN_DETAILS (DATE, LOAN_ID, TERM, STATUS) VALUES (DATEADD('HOUR', -2, CURRENT_DATE), 6, DATEADD('DAY', 24, CURRENT_DATE), 'INITIAL');
INSERT INTO PUBLIC.LOAN_DETAILS (DATE, LOAN_ID, TERM, STATUS) VALUES (CURRENT_DATE, 7, DATEADD('DAY', 3, CURRENT_DATE), 'INITIAL');

INSERT INTO PUBLIC.LOAN_DETAILS (DATE, LOAN_ID, TERM, STATUS) VALUES (DATEADD('DAY', -4, CURRENT_DATE), 1, DATEADD('DAY', 28, CURRENT_DATE), 'EXTENDED');
INSERT INTO PUBLIC.LOAN_DETAILS (DATE, LOAN_ID, TERM, STATUS) VALUES (DATEADD('DAY', -3, CURRENT_DATE), 1, DATEADD('DAY', 14, CURRENT_DATE), 'EXTENDED');
INSERT INTO PUBLIC.LOAN_DETAILS (DATE, LOAN_ID, TERM, STATUS) VALUES (DATEADD('DAY', -2, CURRENT_DATE), 3, DATEADD('DAY', 15, CURRENT_DATE), 'EXTENDED');
INSERT INTO PUBLIC.LOAN_DETAILS (DATE, LOAN_ID, TERM, STATUS) VALUES (DATEADD('DAY', 0, CURRENT_DATE), 2, DATEADD('DAY', 22, CURRENT_DATE), 'EXTENDED');

-- LOAN_REQUESTS
INSERT INTO PUBLIC.LOAN_REQUESTS (CLIENT_ID, DATE, IP) VALUES (5, DATEADD('DAY', -7, CURRENT_DATE), '192.168.1.104');
INSERT INTO PUBLIC.LOAN_REQUESTS (CLIENT_ID, DATE, IP) VALUES (2, DATEADD('DAY', -1, CURRENT_DATE), '192.168.1.111');
INSERT INTO PUBLIC.LOAN_REQUESTS (CLIENT_ID, DATE, IP) VALUES (1, DATEADD('DAY', -2, CURRENT_DATE), '192.168.1.999');
INSERT INTO PUBLIC.LOAN_REQUESTS (CLIENT_ID, DATE, IP) VALUES (2, DATEADD('HOUR', -12, CURRENT_DATE), '192.168.1.102');
INSERT INTO PUBLIC.LOAN_REQUESTS (CLIENT_ID, DATE, IP) VALUES (2, DATEADD('HOUR', -7, CURRENT_DATE), '192.168.1.102');
INSERT INTO PUBLIC.LOAN_REQUESTS (CLIENT_ID, DATE, IP) VALUES (2, DATEADD('HOUR', -2, CURRENT_DATE), '192.168.1.102');
INSERT INTO PUBLIC.LOAN_REQUESTS (CLIENT_ID, DATE, IP) VALUES (1, CURRENT_DATE, '192.168.1.99');
