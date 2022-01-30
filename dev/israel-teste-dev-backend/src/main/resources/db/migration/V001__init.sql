DO $$
BEGIN


CREATE sequence h_sequence start 1 increment 1;
CREATE TABLE health_issues_card
(
    id                    int8 NOT NULL DEFAULT nextval('h_sequence'),
    name                  text NOT NULL,
    level_disease         int8 NOT NULL,
    fk_client_user_id     int8,
    PRIMARY KEY (id)
);

CREATE sequence uc_sequence start 1 increment 1;
CREATE TABLE user_client_user
(
    id                    int8 NOT NULL DEFAULT nextval('uc_sequence'),
    first_name            text NOT NULL,
    surname               text NOT NULL,
    birth_date            timestamptz NOT NULL,
    gender                text,
    registration_date     timestamptz NOT NULL,
    last_update           timestamptz NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE health_issues_card
    ADD CONSTRAINT FK_health_issues_card_user_client_user FOREIGN KEY (fk_client_user_id) REFERENCES user_client_user (id) ON DELETE CASCADE;

   EXCEPTION WHEN OTHERS THEN
      RAISE NOTICE '%: % %', 'AN ERROR OCCURRED ON RUN SCRIPT', SQLERRM, SQLSTATE;
      ROLLBACK;
END $$;
