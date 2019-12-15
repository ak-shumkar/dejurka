/*** Location ***/
CREATE TABLE IF NOT EXISTS ref_location (
  id         BIGSERIAL PRIMARY KEY,
  created_at TIMESTAMP WITHOUT TIME ZONE,
  updated_at TIMESTAMP WITHOUT TIME ZONE,
  parent_id  BIGINT NOT NULL REFERENCES ref_location (id) ON UPDATE CASCADE ON DELETE NO ACTION,
  location_type smallint,
  location_level smallint,
  title      VARCHAR(255)
);

/*** UIK ***/
CREATE TABLE IF NOT EXISTS ref_uik (
    id         BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    location_id  BIGINT NOT NULL REFERENCES ref_location (id) ON UPDATE CASCADE ON DELETE NO ACTION,
    title      VARCHAR(255),
    address    VARCHAR(255),
    landmark   VARCHAR(255),
    note       VARCHAR(255)
);

/*** Voter ***/
CREATE TABLE IF NOT EXISTS voter (
     id         BIGSERIAL PRIMARY KEY,
     created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
     updated_at TIMESTAMP WITHOUT TIME ZONE,
     uik_id  BIGINT NOT NULL REFERENCES ref_uik (id) ON UPDATE CASCADE ON DELETE NO ACTION,
     pin VARCHAR(15),
     first_name VARCHAR(255),
     last_name VARCHAR(255),
     middle_name VARCHAR(255),
     gender smallint,
     date_of_birth DATE,
     position VARCHAR(255),
     phone VARCHAR(255),
     enabled BOOLEAN DEFAULT TRUE
);

/*** Role ***/
CREATE TABLE IF NOT EXISTS roles (
  id         BIGSERIAL PRIMARY KEY,
  created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
  updated_at TIMESTAMP WITHOUT TIME ZONE,
  title VARCHAR(255) UNIQUE,
  code VARCHAR(255) UNIQUE
);

/*** User ***/
CREATE TABLE IF NOT EXISTS users (
  id         BIGSERIAL PRIMARY KEY,
  created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
  updated_at TIMESTAMP WITHOUT TIME ZONE,
  username VARCHAR(255) UNIQUE,
  password VARCHAR(255),
  email VARCHAR(255),
  phone VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  enabled BOOLEAN DEFAULT TRUE
);

/*** User - Role ***/
CREATE TABLE IF NOT EXISTS user_roles (
  user_id BIGINT REFERENCES users (id) ON UPDATE CASCADE ON DELETE NO ACTION,
  role_id BIGINT REFERENCES roles (id) ON UPDATE CASCADE ON DELETE NO ACTION
);

/*** User - Voter ***/
CREATE TABLE IF NOT EXISTS user_voter (
  user_id BIGINT REFERENCES users (id) ON UPDATE CASCADE ON DELETE NO ACTION,
  voter_id BIGINT REFERENCES voter (id) ON UPDATE CASCADE ON DELETE NO ACTION
);

/*** User - UIK ***/
CREATE TABLE IF NOT EXISTS user_voter (
  user_id BIGINT REFERENCES users (id) ON UPDATE CASCADE ON DELETE NO ACTION,
  uik_id BIGINT REFERENCES ref_uik (id) ON UPDATE CASCADE ON DELETE NO ACTION
);

/*** Permission ***/
CREATE TABLE IF NOT EXISTS permission(
  id BIGSERIAL PRIMARY KEY,
  created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
  updated_at TIMESTAMP WITHOUT TIME ZONE,
  title VARCHAR(255),
  code VARCHAR(255),
  UNIQUE (code)
);

/*** User - Region ***/
CREATE TABLE IF NOT EXISTS m2m_user_location_permission (
  user_id BIGINT REFERENCES users (id) ON UPDATE CASCADE ON DELETE NO ACTION,
  location_id BIGINT REFERENCES ref_location (id) ON UPDATE CASCADE ON DELETE NO ACTION
);

/*** Sidebar ***/
CREATE TABLE IF NOT EXISTS sidebar(
  id BIGSERIAL PRIMARY KEY,
  created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
  updated_at TIMESTAMP WITHOUT TIME ZONE,
  title VARCHAR(255) NOT NULL,
  icon VARCHAR(255) NOT NULL,
  href VARCHAR(255) NOT NULL,
  position INTEGER DEFAULT 0,
  uri_pattern VARCHAR(255) DEFAULT '/'::CHARACTER VARYING NOT NULL,
  parent_id BIGINT REFERENCES sidebar (id) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS m2m_role_permission(
  role_id BIGINT REFERENCES roles (id) ON UPDATE CASCADE ON DELETE NO ACTION,
  permission_id BIGINT REFERENCES permission (id) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS m2m_role_sidebar(
  role_id BIGINT REFERENCES roles (id) ON UPDATE CASCADE ON DELETE NO ACTION,
  sidebar_id BIGINT REFERENCES sidebar (id) ON UPDATE CASCADE ON DELETE NO ACTION
);