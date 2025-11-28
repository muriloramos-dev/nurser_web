create table if not exists _user (
       user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
       first_name varchar(100) NOT NULL,
       last_name varchar(100) NOT NULL,
       email varchar(255) NOT NULL UNIQUE,
       password varchar(255) NOT NULL
)