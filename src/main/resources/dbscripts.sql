CREATE TABLE albumstable (
  album VARCHAR NOT NULL PRIMARY KEY ,
  band VARCHAR ,
  years INT ,
  rating INT
);

INSERT INTO albumstable (album, band, years, rating) VALUES
('Century Child', 'Nightwish', 2002, 10),
('Visatori cu plumb in ochi', 'Alternosfera', 2007, 10);

ALTER TABLE `albumstable` ADD `id` INT NOT NULL AUTO_INCREMENT FIRST, ADD PRIMARY KEY (`id`);