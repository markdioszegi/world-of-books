USE world_of_books;

/* dummy data */
INSERT INTO locations VALUES (
  "abc3452-abef453",
  'Manager name',
  '+36304356364',
  'primary address',
  'secondary address',
  'Hungary',
  'Budapest',
  '1007'
);

INSERT INTO listing_statuses VALUES (
  3,
  'A default listing status is unknown.'
);

INSERT INTO marketplaces VALUES (
  2,
  'The Famous Marketplace'
);

INSERT INTO listings VALUES (
  "abc3452-acef453",
  "abc3452-abef453",
  3,
  2,
  'Some title',
  'A long description',
  674.6478,
  'EUR',
  1,
  '2015/06/04',
  'someone@somemail.com'
);