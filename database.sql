CREATE DATABASE IF NOT EXISTS world_of_books;

USE world_of_books;

DROP TABLE IF EXISTS listings;
DROP TABLE IF EXISTS locations;
DROP TABLE IF EXISTS listing_statuses;
DROP TABLE IF EXISTS marketplaces;

/* Create tables */

CREATE TABLE listings (
  id INT NOT NULL,
  inventory_item_location_id INT NOT NULL,
  listing_status INT NOT NULL,
  marketplace INT NOT NULL,
  title TEXT NOT NULL,
  description TEXT NOT NULL,
  listing_price DECIMAL(32, 2) NOT NULL CHECK(listing_price > 0),
  currency TEXT NOT NULL CHECK(LENGTH(currency) = 3),
  quantity INT NOT NULL CHECK(quantity > 0),
  upload_time DATE NOT NULL,
  owner_email_address TEXT NOT NULL CHECK(REGEXP_LIKE(owner_email_address, '^[^@]+@[^@]+\.[^@]{2,}$')),
  PRIMARY KEY (id)
);

CREATE TABLE locations (
  id INT NOT NULL,
  manager_name TEXT NOT NULL,
  phone TEXT NOT NULL,
  address_primary TEXT NOT NULL,
  address_secondary TEXT NOT NULL,
  country TEXT NOT NULL,
  town TEXT NOT NULL,
  postal_code TEXT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE listing_statuses (
  id INT NOT NULL,
  status_name TEXT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE marketplaces (
  id INT NOT NULL,
  marketplace_name TEXT NOT NULL,
  PRIMARY KEY (id)
);

/* References */

ALTER TABLE listings 
  ADD FOREIGN KEY (inventory_item_location_id)
    REFERENCES locations (id),
  ADD FOREIGN KEY (listing_status)
    REFERENCES listing_statuses (id),
  ADD FOREIGN KEY (marketplace)
    REFERENCES marketplaces (id);

/* dummy data */
INSERT INTO locations VALUES (
  4,
  'Manager name',
  '+36304356364',
  'primary address',
  'secondary address',
  'Hungary',
  'Budapest',
  '1007'
);

INSERT INTO listing_statuses VALUES (
  5,
  'A default listing status is unknown.'
);

INSERT INTO marketplaces VALUES (
  2,
  'The Famous Marketplace'
);

INSERT INTO listings VALUES (
  1,
  4,
  5,
  2,
  'Some title',
  'A long description',
  674.6478,
  'EUR',
  1,
  '2015/06/04',
  'someone@somemail.com'
);