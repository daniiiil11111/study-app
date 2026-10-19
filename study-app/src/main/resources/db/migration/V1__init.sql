-- initial empty migration
CREATE TABLE users (
                       id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       email         VARCHAR(255) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       created_at    TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE projects (
                          id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          owner_id   BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                          name       VARCHAR(255) NOT NULL,
                          type       VARCHAR(20) NOT NULL CHECK (type IN ('GERMAN', 'STUDY')),
                          created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
CREATE INDEX idx_projects_owner ON projects(owner_id);

CREATE TABLE card_sets (
                           id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           project_id BIGINT NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
                           name       VARCHAR(255) NOT NULL,
                           created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
CREATE INDEX idx_card_sets_project ON card_sets(project_id);

CREATE TABLE cards (
                       id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       card_set_id BIGINT NOT NULL REFERENCES card_sets(id) ON DELETE CASCADE,
                       type        VARCHAR(20) NOT NULL CHECK (type IN ('VOCAB', 'QA')),
                       front       TEXT NOT NULL,
                       back        TEXT NOT NULL,
                       extra       JSONB,
                       created_at  TIMESTAMPTZ NOT NULL DEFAULT now()
);
CREATE INDEX idx_cards_set ON cards(card_set_id);

CREATE TABLE review_states (
                               id       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                               card_id  BIGINT NOT NULL REFERENCES cards(id) ON DELETE CASCADE,
                               user_id  BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                               box      SMALLINT NOT NULL DEFAULT 1 CHECK (box BETWEEN 1 AND 5),
                               due_date DATE NOT NULL DEFAULT CURRENT_DATE,
                               UNIQUE (card_id, user_id)
);
CREATE INDEX idx_review_due ON review_states(user_id, due_date);

CREATE TABLE usage_counters (
                                id      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                                day     DATE NOT NULL,
                                count   INTEGER NOT NULL DEFAULT 0,
                                UNIQUE (user_id, day)
);