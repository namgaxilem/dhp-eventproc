CREATE TABLE flow
  (
     flow_id         UUID NOT NULL,
     flow_name       VARCHAR(255),
     description        VARCHAR(255),
     count_version    VARCHAR(255),
     created_at TIMESTAMP,
     created_by  VARCHAR(255),
     updated_at TIMESTAMP,
     updated_by VARCHAR(255),
     PRIMARY KEY (flow_id)
  );

CREATE TABLE flow_version
  (
     flow_id    UUID NOT NULL,
     version    INTEGER,
     comment    VARCHAR(255),
     content    BLOB,
     updated_at TIMESTAMP,
     updated_by  VARCHAR(255),
     PRIMARY KEY (flow_id,version)
  );
  ALTER TABLE flow_version ADD CONSTRAINT FK FOREIGN KEY (flow_id) REFERENCES flow;