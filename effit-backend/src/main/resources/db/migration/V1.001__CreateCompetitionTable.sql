CREATE TABLE COMPETITION(
    ID UUID NOT NULL PRIMARY KEY,
    COMPETITION_ID VARCHAR(50) NOT NULL UNIQUE,
    NAME VARCHAR(50) NOT NULL,
    START_DATE DATE,
    END_DATE   DATE
);
