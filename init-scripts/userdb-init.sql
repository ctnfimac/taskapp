CREATE TABLE users(
	id BIGSERIAL PRIMARY KEY,
	username varchar(20),
	email varchar(30) unique,
	created_at timestamp,
	updated_at timestamp
);