CREATE TABLE users(
	id BIGSERIAL PRIMARY KEY,
	email varchar(30) unique not null,
	password varchar(50) not null,
	created_at timestamp,
	updated_at timestamp
);