
-- --------------------------------------------------------
-- 숫자 관련 함수 
-- --------------------------------------------------------

# ROUND(number, [i] ) 
SELECT	123.123456789, ROUND(123.123456789),ROUND(123.123456789,0),
		ROUND(123.123456789,5),
        ROUND(123.123456789, -1),
        ROUND(126.123456789, -1) ; 


# TRUNCATE(number, i) 
SELECT	123.123456789, TRUNCATE(123.123456789,0),
		TRUNCATE(123.123456789,5),
        TRUNCATE(123.123456789, -1), 
        TRUNCATE(126.123456789, -2); 


# ceil(n) , floor(n)
SELECT	CEIL(1234.12), CEIL(0.12),CEIL(-1234.12),  --   1235  1 -1234
		floor(1234.12), floor(0.12),floor(-1234.12) ; -- 1234  0 -1235


# mod(n1, n2)
SELECT  MOD(5,3), MOD(60,0), 5%3 ;


-- --------------------------------------------------------
-- 문자 관련 함수 
-- --------------------------------------------------------

# lower(), upper()
SELECT 	ename, lower(ename), upper(ename) 
FROM	EMP;

# concat() 
SELECT	CONCAT('sql','plus');

# substr()
SELECT	SUBSTR('i love you',3,4) , SUBSTR('i love you',3), SUBSTR('i love you',1,3),
		SUBSTR('i love you',-3,3), SUBSTR('나 여기 있어',3,2); 

# left(), right(), mid() 
SELECT left('i love you',3) , right('i love you',3), mid('i love you',3,4); -- i l     you    love

# instr() 
SELECT	INSTR('i love you','you') , INSTR('i love you','me');               -- 3 0


# 메일주소에서 id부분만 추출하기
-- admin@naver.com, javalove@hanmail.net
SELECT	SUBSTR('admin@naver.com',1,INSTR('admin@naver.com','@')-1)  ,
		SUBSTR('javalove@hanmail.net',1 ,INSTR('javalove@hanmail.net','@')-1);

# 메일주소에서 @이후부분만 추출하기
SELECT	SUBSTR('admin@naver.com',INSTR('admin@naver.com','@')+1),
        SUBSTR('javalove@hanmail.net',INSTR('javalove@hanmail.net','@')+1);

# length() : byte 크기
# char_length() : 문자 개수 
SELECT	LENGTH('SSAFY'),LENGTH('김태희'), char_length('김태희');  -- 5  9  3


# lpad(내용, 자리수, 채울내용) 
# rpad(내용, 자리수, 채울내용)
SELECT LPAD('김태희',5,'*') ,LPAD(123456789, 5,'*'); --  전체자릿수 부족시 잘림

SELECT  ename, LPAD(ename, 10, '*'), RPAD(ename, 10,'*') 
FROM	EMP;


# ltrim(), rtrim(), trim() 
SELECT	LENGTH(' i love you '),  -- 12
		LENGTH(TRIM(' i love you ')), -- 10
		LENGTH(LTRIM(RTRIM(' i love you '))), -- 10
        LENGTH(LTRIM(' i love you ')), -- 11
        LENGTH(RTRIM(' i love you ')); -- 11

-- --------------------------------------------------------
-- 날짜 관련 함수  
-- --------------------------------------------------------

# sysdate() , now()
SELECT NOW(), SYSDATE(), SLEEP(1), NOW(), SYSDATE(), SLEEP(1), NOW(), SYSDATE();

# CURDATE() ,CURTIME()
SELECT CURDATE(), current_date,current_date(), NOW(),curtime(),current_time,current_time();

# DATE_ADD(date, INTERVAL value xx) 
# DATE_SUB(date, INTERVAL value xx) 
SELECT 	SYSDATE()
		, date_sub(SYSDATE(), interval 2 day) a -- 2일전
		, date_add(SYSDATE(), interval 14 day) b -- 14일후
        , date_add(SYSDATE(), interval 1 month) c -- 30일후
        , DATE_add('2022-02-02', INTERVAL 1 month) d
		, DATE_add('2022-02-02', INTERVAL -1 month) d2
        , DATE_add('2022-02-02', INTERVAL 30 DAY) e
        , date_add(SYSDATE(), interval 1 hour) f -- 1시간 후
        , date_add(SYSDATE(), interval 1 minute) g -- 1분 후
		, date_add(SYSDATE(), interval 1 second) h -- 1초 후 
;

# DATEDIFF()
# TIMESTAMPDIFF()
SELECT 	hiredate,curdate(),
		DATEDIFF(curdate(),hiredate), 
		timestampdiff(DAY,HIREDATE,curdate()), 
        timestampdiff(year,HIREDATE,curdate()),
        timestampdiff(hour,HIREDATE,curdate())
FROM	emp;

# date(),year(),month(),day()
SELECT date(hiredate), date(sysdate()), year(sysdate()), month(sysdate()), day(sysdate())
FROM   emp;


-- --------------------------------------------------------
-- 변환 함수
-- --------------------------------------------------------

# STR_TO_DATE()
SELECT STR_TO_DATE('70/01/01','%y/%m/%d'), STR_TO_DATE('00/01/01','%y/%m/%d')
		,STR_TO_DATE('1970/01/01','%Y/%m/%d'), STR_TO_DATE('2000/01/01','%Y/%m/%d');

# CAST()
SELECT	convert('2081/01/01', date), cast('2081/01/01' as date) ,cast('10' as decimal)
		,convert(12.345,	unsigned),convert(12.345 ,decimal(4,2));  


# FORMAT()
SELECT format(1230.456,1), format(123456789,0);


# DATE_FORMAT()
SELECT 	DATE_FORMAT(SYSDATE(), '%Y/%m/%d'),
		DATE_FORMAT(SYSDATE(), '%Y/%m/%d %H:%i:%s  %W'),
        DATE_FORMAT(SYSDATE(), '%Y년 %m월 %d일'),
        DATE_FORMAT(SYSDATE(), '%Y-%m-01');


-- --------------------------------------------------------
-- 기타 함수
-- --------------------------------------------------------

# if()
# 사원의 월급여가 3000이상이면 A등급,아니면 B
SELECT  empno,ename,sal, if ( sal>=3000 , 'A', 'B') 등급
FROM    emp;


# 사원의 월급여가 4000이상이면 A등급,3000이상이면 B등급, 아니면  C등급
SELECT  sal, IF(sal>4000, 'A', IF(sal>=3000, 'B', 'C')) 등급
FROM    emp;


# 000101-3010224 주민번호에서 성별 알아내기(IF 함수 활용)

SELECT '000101-3010224', IF(substr('000101-3010224',8,1) IN ('1','3'), '남','여') 성별; 



# coalesce()
# ifnull()
# 사원의 이름,월급여, 커미션, 1년급여+커미션 조회(단, comm이 정해지지 않았으면 0으로 처리)
SELECT  ename, sal,comm , sal*12+coalesce(comm,0), sal*12+ifnull(comm,0)
FROM    EMP;









