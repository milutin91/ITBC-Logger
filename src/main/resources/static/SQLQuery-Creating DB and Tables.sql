CREATE DATABASE ITBCLogger;

CREATE TABLE ITBCLogger.dbo.users (
	userID UniqueIdentifier PRIMARY KEY,
	username varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	userRole varchar(255) NOT NULL
);


CREATE TABLE ITBCLogger.dbo.logs (
	logID bigint IDENTITY(1,1) PRIMARY KEY,
	message varchar(MAX) NOT NULL,
	logtype bigint NOT NULL,
	token varchar(255) NOT NULL,
	createdDate smalldatetime NOT NULL,
);