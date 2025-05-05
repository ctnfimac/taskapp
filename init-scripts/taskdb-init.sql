
CREATE TABLE task_block(
	id BIGSERIAL PRIMARY KEY,
	title varchar(100) not null,
	done boolean default false,
	created_at timestamp,
	updated_at timestamp,
	user_id int not null
);


CREATE TABLE tasks(
	id BIGSERIAL PRIMARY KEY,
	title varchar(100) not null,
	done boolean default false,
	created_at timestamp,
	updated_at timestamp,
	task_block_id int not null,
	CONSTRAINT fk_tasks_taskblock FOREIGN KEY(task_block_id) REFERENCES task_block(id)
);