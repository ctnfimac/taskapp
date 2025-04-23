CREATE TABLE tasks(
	id BIGSERIAL PRIMARY KEY,
	title varchar(100),
	done boolean default false,
	created_at timestamp,
	updated_at timestamp,
	user_id int not null
);