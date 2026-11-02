USE ssafydb;

-- --------------------------------------------------------
-- 숫자 관련 함수 
-- --------------------------------------------------------

# ROUND(number, [i] ) 

select 1234567.891011, ROUND(1234567.891011), ROUND(1234567.891011, 0), ROUND(1234567.891011, 1), ROUND(1234567.891011, 2);

# TRUNCATE(number, i) 

-- 버림처리 결과를 숫자형 반환, 연산 가능
select 1234567.891011, TRUNCATE(1234567.891011, 0), TRUNCATE(1234567.891011, 0), TRUNCATE(1234567.891011, 1), TRUNCATE(1234567.891011,2);

-- 문자열 반환, 천단위마다 컴마표기 문자열, 따라서 연산 불가
select 1234567.891011, format(1234567.891011, 0), format(1234567.891011, 0), format(1234567.891011, 1), format(1234567.891011,2);

select 1234567.891011, TRUNCATE(1234567.891011, 0) + 1000, format(1234567.891011, 0) + 1000;

# ceil(n) , floor(n)

select 1234567.891011, ceil(1234567.891011), ceil(-1234567.891011), floor(1234567.891011), floor(-1234567.891011);

# mod(n1, n2)

select mod(6, 5), mod(7, -2), mod(7, 0), mod(7,7)
from dual
;

-- --------------------------------------------------------
-- 문자 관련 함수 
-- --------------------------------------------------------

# lower(), upper()

select lower('My name is Chang Tae Jong'), upper('My name is Chang Tae Jong');

# concat() 

select concat('My name is', 'Chang Tae Jong');

# substr()

select substr('My name is Chang Tae Jong', 12);
select substr('My name is Chang Tae Jong', 12, 5);

# left(), right(), mid() 

select left('My name is Chang Tae Jong', 2), right('My name is Chang Tae Jong',15), mid('My name is Chang Tae Jong', 3, 5);

# instr() 

select instr('My name is Chang Tae Jong', 'a');
-- 가장 빨리 나오는 문자열 인덱스를 반환

# 메일주소에서 id부분만 추출하기
# ssafy13@example.com, javalove@ssafy.com

select left('ssafy13@example.com', instr('ssafy13@example.com', '@')-1);

# 메일주소에서 @이후부분만 추출하기
# ssafy13@example.com, javalove@ssafy.com

select mid('ssafy13@example.com', instr('ssafy13@example.com', '@')+1);

# length(), char_length()

select length('My name is Chang Tae Jong');
select char_length('My name is Chang Tae Jong');
select length('123'),length('abc'),length('ㄱㄴㄷ');
select char_length('123'),char_length('abc'),char_length('ㄱㄴㄷ');


# lpad(), rpad()

select lpad('123456789', 14, 0), rpad('123456789', 14, 0);

# ltrim(), rtrim(), trim() 

select ltrim('    My name is Chang Tae Jong        '), rtrim('       My name is Chang Tae Jong        '), trim('    My name is Chang Tae Jong        ');

-- --------------------------------------------------------
-- 날짜 관련 함수  
-- --------------------------------------------------------

# sysdate() , now()

select now(), sysdate(), now()=sysdate();

# CURDATE() , CURTIME()

select curdate(), curtime();

# DATE_ADD(date, INTERVAL value xx),  DATE_SUB(date, INTERVAL value xx) 

select date_add(now(), interval 1 day) ;

# DATEDIFF(), TIMESTAMPDIFF()

select datediff(now(), date_add(now(), interval 18 month));
select TIMESTAMPDIFF(year,now(), date_add(now(), interval 18 month));
# date(),year(),month(),day()

select date(now()), year(now()), month(now()), day(now());

-- --------------------------------------------------------
-- 변환 함수
-- --------------------------------------------------------

# STR_TO_DATE()

select str_to_date('26/11/02', '%Y/%m/%d');

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
