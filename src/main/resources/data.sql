INSERT INTO article(title,content) VALUES('1번테스트','11111');
INSERT INTO article(title,content) VALUES('2번테스트','22222');
INSERT INTO article(title,content) VALUES('3번테스트','33333');
-- ('), (") 차이 조심--

-- article 테이블에 데이터 추가
INSERT INTO article(title,content) VALUES('인생 영화는?','댓글1');
INSERT INTO article(title,content) VALUES('소울 푸드는?','댓글2');
INSERT INTO article(title,content) VALUES('취미는?','댓글3');

-- 4번 게시글의 댓글 추가
INSERT INTO comment(article_id,N_name,body) VALUES(4,'Park','굿 윌 헌팅');
INSERT INTO comment(article_id,N_name,body) VALUES(4,'Kim','아이 엠 샘');
INSERT INTO comment(article_id,N_name,body) VALUES(4,'Choi','쇼생크 탈출');

-- 5번 게시글의 댓글 추가
INSERT INTO comment(article_id,N_name,body) VALUES(5,'Park','치킨');
INSERT INTO comment(article_id,N_name,body) VALUES(5,'Kim','샤브샤브');
INSERT INTO comment(article_id,N_name,body) VALUES(5,'Choi','초밥');

-- 6번 게시글의 댓글 추가
INSERT INTO comment(article_id,N_name,body) VALUES(6,'Park','조깅');
INSERT INTO comment(article_id,N_name,body) VALUES(6,'Kim','유튜브 시청');
INSERT INTO comment(article_id,N_name,body) VALUES(6,'Choi','독서');