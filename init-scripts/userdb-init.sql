CREATE TABLE users(
	id BIGSERIAL PRIMARY KEY,
	username varchar(20) not null,
	email varchar(30) unique not null,
	created_at timestamp,
	updated_at timestamp
);