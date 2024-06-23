create table campaign(
    id_campaign bigint AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL,
    bid_amount double NOT NULL,
    fund double NOT NULL,
    status boolean NOT NULL,
    town varchar(255),
    radius int NOT NULL
);

create table keyword(
    id_keyword bigint AUTO_INCREMENT PRIMARY KEY,
    word varchar(255)
);

create table campaign_keyword(
    id_campaign bigint NOT NULL,
    id_keyword bigint NOT NULL,
    PRIMARY KEY (id_campaign, id_keyword)
);