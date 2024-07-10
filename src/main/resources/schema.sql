CREATE TABLE IF NOT EXISTS Run (
   id INT NOT NULL,
   title VARCHAR(250) NOT NULL,
    started_on TIMESTAMP NOT NULL,
    ended_on TIMESTAMP NOT NULL,
    miles INT NOT NULL,
    location VARCHAR(100) NOT NULL, -- Increased the length of location
    PRIMARY KEY (id)
    );
