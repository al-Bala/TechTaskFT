insert into keyword(id_keyword, word) values (100, 'fashion');
insert into keyword(id_keyword, word) values (101, 'clothes');
insert into keyword(id_keyword, word) values (102, 'bag');
insert into keyword(id_keyword, word) values (103, 'shoes');
insert into keyword(id_keyword, word) values (104, 'motorization');
insert into keyword(id_keyword, word) values (105, 'car');
insert into keyword(id_keyword, word) values (106, 'motorbike');

insert into CAMPAIGN values (1, 'campaign', 100, 1000, true, 'Krakow', 30);
insert into CAMPAIGN_KEYWORD values (1, 100);
insert into CAMPAIGN_KEYWORD values (1, 102);