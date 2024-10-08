CREATE TABLE IF NOT EXISTS MESSAGES (
  ID                     VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
  CONTENT                VARCHAR      NOT NULL,
  CONTENT_TYPE           VARCHAR(128) NOT NULL,
  SENT                   TIMESTAMP    NOT NULL,
  USERNAME               VARCHAR(60)  NOT NULL,
  USER_AVATAR_IMAGE_LINK VARCHAR(256) NOT NULL
);