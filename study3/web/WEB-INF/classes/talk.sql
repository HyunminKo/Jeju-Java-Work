drop table talk_room_t;

create table talk_room_t (
  room_no number(5),
  apple char(4),
  banana char(4),
  orange char(4)
);

insert into talk_room_t values (0, '1234','2345','3456');
COMMIT;

create table talk_t(
  talk_no number(6),
  room_no number(5),
  content varchar2(128)
);

create sequence seq_talk;

insert into talk_t values(seq_talk.nextval,0,'blabla.....');