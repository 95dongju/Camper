--------------------------------------------------------------------------------
---------------------------------- GUEST ---------------------------------------
--------------------------------------------------------------------------------

-- 1. 게스트 로그인
SELECT * FROM MEMBER_GUEST WHERE S_GID='testguest' AND S_GPW='1' AND G_DEL_YN = 'N';

-- 2. 아이디로 DTO 가져오기
SELECT * FROM MEMBER_GUEST WHERE S_GID='testguest';

-- 3. 게스트 아이디 중복체크
SELECT * FROM MEMBER_GUEST WHERE S_GID='testguest';

-- 4. 게스트 이메일 중복체크
SELECT * FROM MEMBER_GUEST WHERE S_GEMAIL='test@mail.com';

-- 5. 게스트 회원가입
INSERT INTO MEMBER_GUEST 
    VALUES ('testguest', 'test@mail.com', '111', '게스트', '게스트', '010-0000-0000','noprofile.jpg', 'G', 'N', SYSDATE); 
    
-- 6. 게스트 정보 수정
UPDATE MEMBER_GUEST SET S_GEMAIL = ?, 
                        S_GPW = ?, 
                        S_GNICK = ?, 
                        S_GTEL = ?, 
                        S_GPHOTO = ?
                    WHERE S_GID = ?;

-- 7. 게스트 탈퇴
UPDATE MEMBER_GUEST SET G_DEL_YN = 'Y' WHERE S_GID = 'test1' AND S_GPW = '111';

--------------------------------------------------------------------------------
--------------------------------- SEARCH ---------------------------------------
--------------------------------------------------------------------------------

-- 1. 캠핑장 검색 (위치)
SELECT S_CAMP_NAME, S_CAMP_ADDR, S_CAMP_MAINPIC 
FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM HOST_CAMPGROUND) A, MEMBER_HOST MH 
WHERE A.S_HID = MH.S_HID  AND S_CAMP_ADDR LIKE '%'||'%'||'%' AND MH.H_DEL_YN = 'N' AND A.CG_DEL_YN = 'N') WHERE RN BETWEEN 1 AND 10;

-- 2. 캠핑장 검색 (이름)
SELECT S_CAMP_NAME, S_CAMP_ADDR, S_CAMP_MAINPIC 
FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM HOST_CAMPGROUND) A, MEMBER_HOST MH 
WHERE A.S_HID = MH.S_HID AND S_CAMP_NAME LIKE '%'||'캠핑장'||'%' AND MH.H_DEL_YN = 'N' AND A.CG_DEL_YN = 'N') WHERE RN BETWEEN 1 AND 10;

-- 2. 전체 검색 수
SELECT COUNT(*) CNT FROM HOST_CAMPGROUND HC, MEMBER_HOST MH WHERE HC.S_HID = MH.S_HID AND MH.H_DEL_YN= 'N' AND HC.CG_DEL_YN = 'N' AND S_CAMP_NAME LIKE '%'||'캠핑장'||'%';


-- 3. 전체 캠핑장 수
SELECT S_CAMP_NAME, S_CAMP_ADDR, S_CAMP_MAINPIC 
FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM HOST_CAMPGROUND) A, MEMBER_HOST MH 
WHERE A.S_HID = MH.S_HID AND MH.H_DEL_YN = 'N' AND A.CG_DEL_YN = 'N') WHERE RN BETWEEN 1 AND 10;

SELECT COUNT(*) CNT FROM HOST_CAMPGROUND HC, MEMBER_HOST MH WHERE HC.S_HID = MH.S_HID AND MH.H_DEL_YN= 'N' AND HC.CG_DEL_YN = 'N' AND S_CAMP_NAME LIKE '%'||'캠핑장'||'%';

--------------------------------------------------------------------------------
------------------------------- RESERVATION ------------------------------------
--------------------------------------------------------------------------------

-- 1. 예약
INSERT INTO GUEST_RESERVATION 
    VALUES (TO_CHAR(SYSDATE, 'YYMMDD')||'-'||(TO_CHAR(GUEST_RESERVATION_NO_SEQ.NEXTVAL)), 'CS111111', TO_DATE('2023-03-30', 'YYYY-MM-DD'), 'testguest', SYSDATE, 'N');

-- 2. 예약 취소
DELETE FROM GUEST_RESERVATION WHERE S_REZ_NO = '230321-111111';


--------------------------------------------------------------------------------
----------------------------- HOST RESERVATION ---------------------------------
--------------------------------------------------------------------------------

-- 캠핑장 예약 수
SELECT * FROM GUEST_RESERVATION WHERE S_SITE_NO = 'CS111112';

-- 예약 수락 여부
SELECT * FROM GUEST_RESERVATION WHERE S_SITE_NO = '111111' AND GR_STATUS = 'N';

-- 1. 예약 확인
SELECT GR.*, S_GTEL FROM GUEST_RESERVATION GR, MEMBER_GUEST MG WHERE GR.S_GID = MG.S_GID AND S_SITE_NO = '111111' ORDER BY D_REZ_DATE;

-- 2. 예약 수락
UPDATE GUEST_RESERVATION SET GR_STATUS = 'Y'
                        WHERE S_REZ_NO = '230321-111112';
-- 3. 예약 수정                
UPDATE GUEST_RESERVATION SET S_SITE_NO = 'CS111112',
                            D_SELECT = TO_DATE('2023-03-30', 'YYYY-MM-DD')
                        WHERE S_REZ_NO = '230330-111117';
-- 4. 예약 삭제
DELETE FROM GUEST_RESERVATION WHERE S_REZ_NO = '230321-111112';

--------------------------------------------------------------------------------
---------------------------------- HOST ----------------------------------------
--------------------------------------------------------------------------------
commit;
-- 1. 호스트 로그인
SELECT * FROM MEMBER_HOST WHERE S_HID='host' AND S_HPW='111' AND H_DEL_YN = 'N';

-- 2. 아이디로 DTO 가져오기
SELECT * FROM MEMBER_HOST WHERE S_HID='host11';

-- 3. 호스트 아이디 중복체크
SELECT * FROM MEMBER_HOST WHERE S_HID='host';

-- 4. 호스트 이메일 중복체크
SELECT * FROM MEMBER_HOST WHERE S_HEMAIL='host@mail.com';

-- 5. 호스트 사업자 중복체크
SELECT * FROM MEMBER_HOST WHERE S_HBIS_NUM = '111-1111-1111';

-- 6. 호스트 가입
INSERT INTO MEMBER_HOST VALUES
    ('host', 'host@mail.com', '111', '최명희', '010-1111-1111', '지리산유기농식품영농조합법인', '613-81-58971', 'bispic.jpg', '경남 산청군 시천면 지리산대로1478번길 31-10', '농협', '1111-1111-111111', 'account.jpg', 'noprofile.jpg', 'N', 'N', SYSDATE);
commit;
-- 7. 호스트 정보 수정
UPDATE MEMBER_HOST SET S_HPW = '121',
                        S_HBIS_NAME = '홍석민',
                        S_HBIS_NUM = '182-37-19283',
                        S_HBIS_PIC = 'miehj2.jpg',
                        S_HADDR = '강원도 홍천 한우마을',
                        S_HACC_BANKNAME = "",
                        S_HACCOUNT = '3333-222233-12212',
                        S_HACC_PIC = 'test.jpg',
                        S_HPIC = 'noprofile.jpg'
                    WHERE S_HID = 'host1';
SELECT * FROM MEMBER_HOST;

-- 8. 호스트 탈퇴
UPDATE MEMBER_HOST SET H_DEL_YN = 'N' WHERE S_HID = 'host1' AND S_HPW = '121';
SELECT * FROM HOST_CAMPGROUND;

-- 9. 호스트 탈퇴 여부 확인
SELECT * FROM MEMBER_GUEST WHERE S_HID = ? AND H_DEL_YN = 'Y';

--------------------------------------------------------------------------------
------------------------------- CAMPGROUND -------------------------------------
--------------------------------------------------------------------------------

-- 1. 캠핑장 등록
INSERT INTO HOST_CAMPGROUND 
    VALUES ('CG'||TO_CHAR(HOST_CAMPGROUND_NO_SEQ.NEXTVAL), '산마루캠핑장', '산마루캠핑장입니다', '경남 산청군 시천면 지리산대로1478번길 31-10', 'host1', 'main.jpg', 'map.jpg', 'photo1.jpg', 'photo2.jpg', NULL, NULL, NULL, 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', SYSDATE);
commit;
-- 2. 캠핑장 삭제
UPDATE HOST_CAMPSITE SET CS_DEL_YN = 'Y' WHERE S_SITE_NO = 'CS111113';
UPDATE HOST_CAMPGROUND SET CG_DEL_YN = 'Y' WHERE S_CAMP_NO = 'CG111111';

-- 3. 캠핑장 정보 수정
UPDATE HOST_CAMPGROUND SET S_CAMP_DESC = '공기 좋고 물 좋아요',
                            S_CAMP_MAINPIC = 'gamja.jpg',
                            S_CAMP_MAPPIC = 'gam.jpg',
                            S_CAMP_PIC1 = '1.jpg',
                            S_CAMP_PIC2 = '2.jpg',
                            S_CAMP_PIC3 = '3.jpg',
                            S_CAMP_PIC4 = '4.jpg',
                            S_CAMP_PIC5 = '5.jpg',
                            S_BATHROOM = 'Y',
                            S_SHOWERBOOTH = 'Y',
                            S_STORE = 'Y',
                            S_SINK = 'Y',
                            S_WIFI = 'N',
                            S_PLAYGROUND = 'N',
                            S_WITH_PET = 'N',
                            S_SWIM_POOL = 'Y'
                        WHERE S_CAMP_NO = '111111';

-- 4. 내 캠핑장 수
SELECT COUNT(*) CNT FROM HOST_CAMPGROUND HC, MEMBER_HOST MH WHERE HC.S_HID = MH.S_HID AND HC.S_HID = 'host' AND MH.H_DEL_YN= 'N' AND HC.CG_DEL_YN = 'N';

-- 5. 등록된 내 캠핑장 목록
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM HOST_CAMPGROUND) A, MEMBER_HOST MH WHERE A.S_HID = MH.S_HID AND A.S_HID = 'host' AND MH.H_DEL_YN = 'N' AND A.CG_DEL_YN = 'N') WHERE RN BETWEEN 1 AND 10;
UPDATE HOST_CAMPGROUND SET CG_DEL_YN = 'Y' WHERE S_HID = 'host';
-- 6. 캠핑장 상세 보기
SELECT S_CAMP_NAME, S_CAMP_DESC, S_CAMP_ADDR, S_CAMP_MAINPIC, S_CAMP_MAPPIC, S_CAMP_PIC1, S_CAMP_PIC2, S_CAMP_PIC3, S_CAMP_PIC4, S_CAMP_PIC5, S_BATHROOM, S_SHOWERBOOTH, S_STORE, S_SINK, S_WIFI, S_PLAYGROUND, S_WITH_PET, S_SWIM_POOL FROM HOST_CAMPGROUND HC, MEMBER_HOST MH WHERE HC.S_HID = MH.S_HID AND MH.H_DEL_YN= 'N' AND HC.CG_DEL_YN = 'N' AND HC.S_CAMP_NO = 'CG111111';
--------------------------------------------------------------------------------
-------------------------------- CAMPSITE --------------------------------------
--------------------------------------------------------------------------------

-- 1. 캠핑 사이트 등록
INSERT INTO HOST_CAMPSITE
    VALUES ('CS'||TO_CHAR(HOST_CAMPSITE_NO_SEQ.NEXTVAL), '111111', '데크2', '50000', 'N', SYSDATE);

-- 2. 캠핑 사이트 삭제
UPDATE HOST_CAMPSITE SET CS_DEL_YN = 'Y' WHERE S_SITE_NO = '111111';

-- 3. 캠핑 사이트 수정
UPDATE HOST_CAMPSITE SET S_SITENAME = '데크2',
                        S_SITEPRICE = '40000'
                    WHERE S_SITE_NO = '111111';

-- 4. 캠핑 사이트 목록
SELECT S_SITENAME, S_SITEPRICE FROM HOST_CAMPSITE HCS, HOST_CAMPGROUND HC, MEMBER_HOST MH WHERE HCS.S_CAMP_NO = HC.S_CAMP_NO AND MH.H_DEL_YN = 'N' AND HC.S_HID = MH.S_HID AND HC.S_CAMP_NO = 'CS111121' ORDER BY CS_RDATE;

SELECT * FROM MEMBER_GUEST WHERE S_GID = 'testguest' AND G_DEL_YN = 'Y';
SELECT * FROM HOST_CAMPSITE;

--------------------------------------------------------------------------------
------------------------------ BOARD REVIEW ------------------------------------
--------------------------------------------------------------------------------

-- GUEST

-- 1. 전체 리뷰 목록

SELECT S_GNAME, S_REV_TITLE, S_REV_CONTENT, D_RDATE, S_REV_MAINPIC FROM MEMBER_GUEST G, GUEST_REVIEW R WHERE G.S_GID = R.S_GID AND G.G_DEL_YN = 'N' ORDER BY D_RDATE DESC;

-- 2. 특정 캠핑장 리뷰 목록
SELECT S_GNAME, S_REV_TITLE, D_RDATE, S_REV_MAINPIC FROM MEMBER_GUEST G, GUEST_REVIEW R WHERE G.S_GID = R.S_GID AND G.G_DEL_YN = 'N' AND R.S_CAMP_NO = '111111' ORDER BY D_RDATE DESC;

-- 3. 리뷰 상세 보기
SELECT S_GNAME, S_REV_TITLE, S_REV_CONTENT, D_RDATE, S_REV_MAINPIC, S_REV_PIC1, S_REV_PIC2, S_REV_PIC3 FROM MEMBER_GUEST G, GUEST_REVIEW R WHERE G.S_GID = R.S_GID AND G.G_DEL_YN = 'N' AND R.I_REV_NO = '111111';

-- 4. 리뷰 작성
INSERT INTO GUEST_REVIEW 
    VALUES (GUEST_REVIEW_NO_SEQ.NEXTVAL, '111111', 'testguest', '제목', '내용', SYSDATE, '192.1.1.2', 'NOIMG.jpg', NULL, NULL, NULL);
SELECT * FROM GUEST_REVIEW;

-- 5. 리뷰 수정
UPDATE GUEST_REVIEW SET S_REV_TITLE = '산마루 좋아요',
                        S_REV_CONTENT = '최고',
                        S_REV_MAINPIC = 'null.jpg',
                        S_REV_PIC1 = 'null.jpg',
                        S_REV_PIC2 = 'null.jpg',
                        S_REV_PIC3 = 'null.jpg'
                    WHERE I_REV_NO = '111111';
                    
-- 6. 리뷰 삭제
DELETE FROM GUEST_REVIEW WHERE I_REV_NO = '111111';

-- 7. 전체 리뷰 수
SELECT COUNT(*) CNT FROM GUEST_REVIEW;

-- 8. 특정 캠핑장 리뷰 수
SELECT COUNT(*) CNT FROM GUEST_REVIEW WHERE S_CAMP_NO = '111111';

--------------------------------------------------------------------------------
------------------------------- BOARD REPLY ------------------------------------
--------------------------------------------------------------------------------

-- 1. 리뷰 댓글 보기
SELECT S_REP_CONTENT, S_CAMP_NAME FROM HOST_REPLY HR, HOST_CAMPGROUND CG WHERE HR.S_HID = CG.S_HID AND I_REV_NO = '111112';

-- 2. 리뷰 댓글 작성
INSERT INTO HOST_REPLY
    VALUES (HOST_REPLY_NO_SEQ.NEXTVAL, '감사합니다', '111112', '111112', 'host', '192.0.0.1', SYSDATE);

-- 3. 리뷰 댓글 삭제
DELETE FROM HOST_REPLY WHERE I_REP_NO = '111113';

-- 4. 리뷰 댓글 수정
UPDATE HOST_REPLY SET S_REP_CONTENT = '수정' WHERE I_REP_NO = '111114';

-- 5. 글에 달린 전체 댓글 수
SELECT COUNT(*) CNT FROM HOST_REPLY WHERE I_REV_NO = '111112';
--------------------------------------------------------------------------------
---------------------------------- ADMIN ---------------------------------------
--------------------------------------------------------------------------------

-- 회원 리스트
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM MEMBER_GUEST ORDER BY G_RDATE DESC) A) WHERE RN BETWEEN 1 AND 10;

-- 회원 수
SELECT COUNT(*) CNT FROM MEMBER_GUEST;

-- 회원 정보 수정

-- 회원 

-- 예약 수정
