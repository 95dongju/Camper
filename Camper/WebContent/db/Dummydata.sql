-- GUEST

INSERT INTO MEMBER_GUEST 
    VALUES ('testguest', 'test@mail.com', '111', '게스트', '게스트', '010-0000-0000','NOIMG.jpg', 'G', 'N', SYSDATE); 

INSERT INTO MEMBER_GUEST 
    VALUES ('admin123', 'admin@mail.com', 'admin1', '관리자', '관리자', '010-0000-0000','NOIMG.jpg', 'A', 'N', SYSDATE); 
    
INSERT INTO MEMBER_GUEST 
    VALUES ('test123', '2@mail.com', '111', '게스트2', '게스트2', '010-0000-0000','NOIMG.jpg', 'G', 'Y', SYSDATE); 

SELECT * FROM MEMBER_GUEST;

-- HOST

SELECT * FROM MEMBER_HOST;

INSERT INTO MEMBER_HOST VALUES
    ('host', 'host@mail.com', '111', '최명희', '010-1111-1111', '지리산명인조합', '424-12-12332', 'bispic.jpg', '경남 산청군 시천면 지리산대로1478번길 31-10', '농협', '1111-1111-111111', 'account.jpg', 'null.jpg', 'N', 'N', SYSDATE);

INSERT INTO MEMBER_HOST VALUES
    ('host1', 'host1@mail.com', '111', '이순찬', '010-2222-3333', '감자농장', '313-81-58971', 'bispic.jpg', '경남 산청군 시천면 지리산대로1478번길 31-10', '우리은행', '1111-1111-111111', 'account.jpg', 'null.jpg', 'N', 'N', SYSDATE);

SELECT * FROM HOST_CAMPGROUND;

INSERT INTO HOST_CAMPGROUND 
    VALUES (HOST_CAMPGROUND_NO_SEQ.NEXTVAL, '자연캠핑장', '자연을 벗삼아', '강원 홍천군 서면 개야마을길 68-27', '055-2091-2091', 'host', 'main.jpg', 'map.jpg', 'photo1.jpg', 'photo2.jpg', NULL, NULL, NULL, 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', SYSDATE);


INSERT INTO HOST_CAMPGROUND 
    VALUES (HOST_CAMPGROUND_NO_SEQ.NEXTVAL, '난지캠핑장', '난지캠핑장', '서울특별시 마포구 상암동 495-81', '02-0929-1928', 'host1', 'main.jpg', 'map.jpg', 'photo1.jpg', 'photo2.jpg', NULL, NULL, NULL, 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', SYSDATE);


SELECT * FROM HOST_CAMPSITE;

INSERT INTO HOST_CAMPSITE 
    VALUES (HOST_CAMPSITE_NO_SEQ.NEXTVAL, HOST_CAMPGROUND_NO_SEQ.CURRVAL, '데크1', '50000', 'N', SYSDATE);

INSERT INTO GUEST_REVIEW 
    VALUES (GUEST_REVIEW_NO_SEQ.NEXTVAL, 'CG111111', 'testguest', '제목', '내용', SYSDATE, '192.1.1.2', 'NOIMG.jpg', NULL, NULL, NULL);

SELECT * FROM GUEST_REVIEW;
