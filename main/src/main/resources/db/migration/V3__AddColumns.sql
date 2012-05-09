ALTER TABLE loads ADD COLUMN destination varchar(40);
ALTER TABLE loads ADD COLUMN reserved boolean DEFAULT FALSE;