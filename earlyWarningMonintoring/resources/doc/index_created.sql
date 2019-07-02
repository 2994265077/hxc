#BLK_POPULATION
create index blk_pop_intgr_1  on  BLK_POPULATION(RKXZ,JDDM,SQDM,XB);
create index blk_pop_intgr_2  on  BLK_POPULATION(CJRQ,JDDM,SQDM,RKXZ);
create index blk_pop_intgr_3  on  BLK_POPULATION(RKXZ,SFZH,JDDM,SQDM);
create index blk_pop_intgr_4  on  BLK_POPULATION(XB,CSRQ,JDDM,SQDM);


#BLK_LEGAL_PERSON
create index idx_BLK_LEGAL_PERSON on BLK_LEGAL_PERSON(HYFL,YYZT);
create index BLK_LEGAL_PERSON_idx2 on BLK_LEGAL_PERSON(YSBGXSJ);
create index BLK_LEGAL_PERSON_idx3 on BLK_LEGAL_PERSON(YYZT,HYFL, STREET_CODE,COMMUNITY_CODE)


#BLK_POPULATION
create index  BLK_POP_hid  on  BLK_POPULATION(hid);
create index  BLK_POP_obj_id  on  BLK_POPULATION(object_id);
create index idx_BLK_POPULATION_IJSR on BLK_POPULATION(SFZH,ID,JDDM , SQDM, RKXZ,CJRQ);

#BLK_HOUSE
create  index  idx_blk_house_jddm  on  blk_house(JDDM);
create  index  idx_blk_house_sqdm  on  blk_house(sqdm);
create  index  idx_blk_house_syqk  on  blk_house(SYQK);
create  index  blk_house_idx1	 on  blk_house(SYQK,STREET_CODE,SQDM,JDDM);

#WEATHER_ALARM
create  index  WARNINGKEY on  WEATHER_ALARM(RELEASETIME,WARNINGLEVEL,ALERTCATEGORY,RELEASEAREA)

#TBL_MXSYS_FUTIAN
create  index  INDEX_FUTIAN on TBL_MXSYS_FUTIAN(ID,UPDATETIME,MANAGE_STATUS)