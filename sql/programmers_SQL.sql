-- 프로그래머스 SQL / MySQL 풀이
-- 확인 기준: 2026-11-03 SQL 고득점 Kit, Level 2 총 36문제.
-- 기존 Level 2 풀이 4개 + 추가 풀이 32개. 기존 Level 1 풀이 2개도 유지한다.
-- 문제 설명은 핵심 요구사항을 요약했다. 전체 지문과 예시는 각 원문 링크를 참고한다.
-- 각 문제는 서로 독립적이므로 해당 문제의 쿼리만 선택하여 실행한다.
-- 목록: https://school.programmers.co.kr/learn/challenges?tab=sql_practice_kit

-- ========================================================
-- 기존 풀이 (Level 1: 2문제, Level 2: 4문제)
-- ========================================================

-- [Level 1 / 기존 풀이] 이름이 없는 동물의 아이디
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/59039
-- 문제: ANIMAL_INS에서 이름이 NULL인 동물의 ANIMAL_ID를 조회한다.
-- 정렬: ANIMAL_ID 오름차순.
SELECT ANIMAL_ID
FROM ANIMAL_INS
WHERE NAME IS NULL
ORDER BY ANIMAL_ID;

-- [Level 1 / 기존 풀이] 특정 형질을 가지는 대장균 찾기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/301646
-- 문제: ECOLI_DATA에서 2번 형질이 없고, 1번 또는 3번 형질이 있는 개체 수를 센다.
-- 출력: COUNT. GENOTYPE의 각 비트는 순서대로 1번, 2번, 3번 형질을 나타낸다.
SELECT COUNT(*) AS COUNT
FROM ECOLI_DATA
WHERE (GENOTYPE MOD 8 DIV 4 >= 1 OR GENOTYPE MOD 8 MOD 4 MOD 2 >= 1)
  AND GENOTYPE MOD 8 MOD 4 DIV 2 = 0;

-- [Level 2 / 01 / 기존 풀이] 부모의 형질을 모두 가지는 대장균 찾기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/301647
-- 문제: ECOLI_DATA에서 부모의 형질을 빠짐없이 가진 자식 개체를 찾는다.
-- 출력: 자식 ID, GENOTYPE, 부모 GENOTYPE을 PARENT_GENOTYPE으로 표시.
-- 정렬: 자식 ID 오름차순. 비트 AND 결과가 부모 형질과 같으면 조건을 만족한다.
SELECT E1.ID,
       E1.GENOTYPE,
       E2.GENOTYPE AS PARENT_GENOTYPE
FROM ECOLI_DATA AS E1
JOIN ECOLI_DATA AS E2 ON E1.PARENT_ID = E2.ID
WHERE (E1.GENOTYPE & E2.GENOTYPE) = E2.GENOTYPE
ORDER BY E1.ID;

-- [Level 2 / 02 / 기존 풀이] 조건에 맞는 개발자 찾기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/276034
-- 문제: DEVELOPERS에서 Python 또는 C# 스킬을 보유한 개발자를 조회한다.
-- 출력: ID, EMAIL, FIRST_NAME, LAST_NAME. 정렬: ID 오름차순.
-- SKILLCODES.CODE와 SKILL_CODE의 비트 AND로 해당 스킬 보유 여부를 확인한다.
SELECT DISTINCT D.ID, D.EMAIL, D.FIRST_NAME, D.LAST_NAME
FROM DEVELOPERS AS D
WHERE (D.SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'C#')) <> 0
   OR (D.SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'Python')) <> 0
ORDER BY D.ID;

-- [Level 2 / 03 / 기존 풀이] 연도별 대장균 크기의 편차 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/299310
-- 문제: ECOLI_DATA의 개체마다 분화 연도와 해당 연도 최대 크기에서 자신의 크기를 뺀 값을 구한다.
-- 출력: YEAR, YEAR_DEV, ID. 정렬: YEAR 오름차순, YEAR_DEV 오름차순.
SELECT DISTINCT YEAR(E1.DIFFERENTIATION_DATE) AS YEAR,
       E2.SIZE_OF_COLONY - E1.SIZE_OF_COLONY AS YEAR_DEV,
       E1.ID
FROM ECOLI_DATA AS E1
JOIN (
    SELECT YEAR(DIFFERENTIATION_DATE) AS YEAR,
           MAX(SIZE_OF_COLONY) AS SIZE_OF_COLONY
    FROM ECOLI_DATA
    GROUP BY YEAR(DIFFERENTIATION_DATE)
) AS E2 ON YEAR(E1.DIFFERENTIATION_DATE) = E2.YEAR
ORDER BY YEAR, YEAR_DEV;

-- [Level 2 / 04 / 기존 풀이] 업그레이드 된 아이템 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/273711
-- 문제: ITEM_INFO와 ITEM_TREE에서 희귀도가 RARE인 아이템의 바로 다음 업그레이드 아이템을 찾는다.
-- 출력: 업그레이드 결과의 ITEM_ID, ITEM_NAME, RARITY. 정렬: ITEM_ID 내림차순.
SELECT II.ITEM_ID, II.ITEM_NAME, II.RARITY
FROM ITEM_INFO AS II
JOIN ITEM_TREE AS IT ON II.ITEM_ID = IT.ITEM_ID
WHERE IT.PARENT_ITEM_ID IN (
    SELECT ITEM_ID
    FROM ITEM_INFO
    WHERE RARITY = 'RARE'
)
ORDER BY II.ITEM_ID DESC;

-- ========================================================
-- 추가 풀이: SELECT
-- ========================================================

-- [Level 2 / 05] 3월에 태어난 여성 회원 목록 출력하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/131120
-- 문제: MEMBER_PROFILE에서 생일이 3월인 여성(W) 중 전화번호가 NULL이 아닌 회원을 찾는다.
-- 출력: MEMBER_ID, MEMBER_NAME, GENDER, DATE_OF_BIRTH(YYYY-MM-DD).
-- 정렬: MEMBER_ID 오름차순.
SELECT MEMBER_ID,
       MEMBER_NAME,
       GENDER,
       DATE_FORMAT(DATE_OF_BIRTH, '%Y-%m-%d') AS DATE_OF_BIRTH
FROM MEMBER_PROFILE
WHERE MONTH(DATE_OF_BIRTH) = 3
  AND GENDER = 'W'
  AND TLNO IS NOT NULL
ORDER BY MEMBER_ID;

-- [Level 2 / 06] 재구매가 일어난 상품과 회원 리스트 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/131536
-- 문제: ONLINE_SALE에서 같은 회원이 같은 상품을 두 번 이상 구매한 조합을 찾는다.
-- 출력: USER_ID, PRODUCT_ID. 정렬: USER_ID 오름차순, PRODUCT_ID 내림차순.
SELECT USER_ID, PRODUCT_ID
FROM ONLINE_SALE
GROUP BY USER_ID, PRODUCT_ID
HAVING COUNT(*) >= 2
ORDER BY USER_ID, PRODUCT_ID DESC;

-- [Level 2 / 07] 특정 물고기를 잡은 총 수 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/298518
-- 문제: FISH_INFO와 FISH_NAME_INFO를 FISH_TYPE으로 연결해 BASS와 SNAPPER의 총 포획 수를 구한다.
-- 출력: FISH_COUNT. 길이가 NULL인 물고기도 개체 수에 포함한다.
SELECT COUNT(*) AS FISH_COUNT
FROM FISH_INFO AS F
JOIN FISH_NAME_INFO AS N ON F.FISH_TYPE = N.FISH_TYPE
WHERE N.FISH_NAME IN ('BASS', 'SNAPPER');

-- ========================================================
-- 추가 풀이: SUM, MAX, MIN
-- ========================================================

-- [Level 2 / 08] 가격이 제일 비싼 식품의 정보 출력하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/131115
-- 문제: FOOD_PRODUCT에서 최고 가격에 해당하는 식품 정보를 조회한다.
-- 출력: PRODUCT_ID, PRODUCT_NAME, PRODUCT_CD, CATEGORY, PRICE.
SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_CD, CATEGORY, PRICE
FROM FOOD_PRODUCT
WHERE PRICE = (SELECT MAX(PRICE) FROM FOOD_PRODUCT);

-- [Level 2 / 09] 최솟값 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/59038
-- 문제: ANIMAL_INS에서 가장 이른 보호 시작 일시(DATETIME)를 구한다.
-- 출력: 시간.
SELECT MIN(DATETIME) AS 시간
FROM ANIMAL_INS;

-- [Level 2 / 10] 동물 수 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/59406
-- 문제: ANIMAL_INS에 등록된 동물의 전체 수를 구한다. 이름이 NULL인 동물도 포함한다.
-- 출력: COUNT.
SELECT COUNT(*) AS COUNT
FROM ANIMAL_INS;

-- [Level 2 / 11] 중복 제거하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/59408
-- 문제: ANIMAL_INS에서 NULL을 제외하고 서로 다른 동물 이름의 수를 구한다.
-- 출력: COUNT. COUNT(DISTINCT NAME)은 NULL과 중복 이름을 제외한다.
SELECT COUNT(DISTINCT NAME) AS COUNT
FROM ANIMAL_INS;

-- [Level 2 / 12] 조건에 맞는 아이템들의 가격의 총합 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/273709
-- 문제: ITEM_INFO에서 RARITY가 LEGEND인 아이템들의 PRICE를 합산한다.
-- 출력: TOTAL_PRICE.
SELECT SUM(PRICE) AS TOTAL_PRICE
FROM ITEM_INFO
WHERE RARITY = 'LEGEND';

-- ========================================================
-- 추가 풀이: GROUP BY
-- ========================================================

-- [Level 2 / 13] 성분으로 구분한 아이스크림 총 주문량
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/133026
-- 문제: FIRST_HALF와 ICECREAM_INFO를 FLAVOR로 연결하여 성분별 상반기 주문량 합계를 구한다.
-- 출력: INGREDIENT_TYPE, TOTAL_ORDER. 정렬: TOTAL_ORDER 오름차순.
SELECT I.INGREDIENT_TYPE, SUM(F.TOTAL_ORDER) AS TOTAL_ORDER
FROM FIRST_HALF AS F
JOIN ICECREAM_INFO AS I ON F.FLAVOR = I.FLAVOR
GROUP BY I.INGREDIENT_TYPE
ORDER BY TOTAL_ORDER;

-- [Level 2 / 14] 진료과별 총 예약 횟수 출력하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/132202
-- 문제: APPOINTMENT에서 APNT_YMD가 2022년 5월인 예약 건수를 진료과별로 센다.
-- 출력: 진료과 코드, 5월예약건수. 취소 여부나 환자 중복에 대한 추가 필터는 없다.
-- 정렬: 예약 건수 오름차순, 진료과 코드 오름차순.
SELECT MCDP_CD AS `진료과 코드`, COUNT(*) AS `5월예약건수`
FROM APPOINTMENT
WHERE APNT_YMD >= '2022-05-01'
  AND APNT_YMD < '2022-06-01'
GROUP BY MCDP_CD
ORDER BY `5월예약건수`, MCDP_CD;

-- [Level 2 / 15] 자동차 종류 별 특정 옵션이 포함된 자동차 수 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/151137
-- 문제: CAR_RENTAL_COMPANY_CAR에서 통풍시트, 열선시트, 가죽시트 중 하나 이상을 가진 차를 센다.
-- 출력: CAR_TYPE, CARS. 정렬: CAR_TYPE 오름차순.
SELECT CAR_TYPE, COUNT(*) AS CARS
FROM CAR_RENTAL_COMPANY_CAR
WHERE OPTIONS LIKE '%통풍시트%'
   OR OPTIONS LIKE '%열선시트%'
   OR OPTIONS LIKE '%가죽시트%'
GROUP BY CAR_TYPE
ORDER BY CAR_TYPE;

-- [Level 2 / 16] 고양이와 개는 몇 마리 있을까
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/59040
-- 문제: ANIMAL_INS에서 고양이(Cat)와 개(Dog)를 각각 집계한다.
-- 출력: ANIMAL_TYPE, COUNT. 정렬: 고양이 먼저, 개 나중.
SELECT ANIMAL_TYPE, COUNT(*) AS COUNT
FROM ANIMAL_INS
WHERE ANIMAL_TYPE IN ('Cat', 'Dog')
GROUP BY ANIMAL_TYPE
ORDER BY ANIMAL_TYPE;

-- [Level 2 / 17] 동명 동물 수 찾기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/59041
-- 문제: ANIMAL_INS에서 이름이 NULL인 동물을 제외하고, 두 번 이상 쓰인 이름과 횟수를 구한다.
-- 출력: NAME, COUNT. 정렬: NAME 오름차순.
SELECT NAME, COUNT(*) AS COUNT
FROM ANIMAL_INS
WHERE NAME IS NOT NULL
GROUP BY NAME
HAVING COUNT(*) >= 2
ORDER BY NAME;

-- [Level 2 / 18] 입양 시각 구하기(1)
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/59412
-- 문제: ANIMAL_OUTS에서 09:00부터 19:59까지 발생한 입양을 시간대별로 센다.
-- 출력: HOUR, COUNT. 정렬: HOUR 오름차순.
SELECT HOUR(DATETIME) AS HOUR, COUNT(*) AS COUNT
FROM ANIMAL_OUTS
WHERE HOUR(DATETIME) BETWEEN 9 AND 19
GROUP BY HOUR(DATETIME)
ORDER BY HOUR;

-- [Level 2 / 19] 가격대 별 상품 개수 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/131530
-- 문제: PRODUCT의 상품을 1만원 단위 구간으로 묶어 집계한다. 가격대는 구간 시작 금액으로 표시한다.
-- 출력: PRICE_GROUP, PRODUCTS. 예: 10000 이상 20000 미만은 10000. 정렬: 가격대 오름차순.
SELECT FLOOR(PRICE / 10000) * 10000 AS PRICE_GROUP, COUNT(*) AS PRODUCTS
FROM PRODUCT
GROUP BY FLOOR(PRICE / 10000) * 10000
ORDER BY PRICE_GROUP;

-- [Level 2 / 20] 조건에 맞는 사원 정보 조회하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/284527
-- 문제: HR_GRADE에서 2022년 상반기와 하반기 SCORE를 사원별로 합산해 최고점 사원을 찾는다.
-- HR_EMPLOYEES와 EMP_NO로 연결한다. 최고점이 같은 사원이 있으면 모두 조회한다.
-- 출력: SCORE, EMP_NO, EMP_NAME, POSITION, EMAIL. 별도 정렬 조건은 없다.
WITH YEAR_SCORE AS (
    SELECT EMP_NO, SUM(SCORE) AS SCORE
    FROM HR_GRADE
    WHERE YEAR = 2022
    GROUP BY EMP_NO
)
SELECT G.SCORE, E.EMP_NO, E.EMP_NAME, E.POSITION, E.EMAIL
FROM YEAR_SCORE AS G
JOIN HR_EMPLOYEES AS E ON G.EMP_NO = E.EMP_NO
WHERE G.SCORE = (SELECT MAX(SCORE) FROM YEAR_SCORE);

-- [Level 2 / 21] 노선별 평균 역 사이 거리 조회하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/284531
-- 문제: SUBWAY_DISTANCE에서 ROUTE별 D_BETWEEN_DIST의 합계와 평균을 구한다.
-- 출력: ROUTE, TOTAL_DISTANCE(소수 1자리 반올림), AVERAGE_DISTANCE(소수 2자리 반올림).
-- 두 거리 값에 km를 붙인다. 정렬: 문자열이 아닌 실제 거리 합계 내림차순.
SELECT ROUTE,
       CONCAT(ROUND(SUM(D_BETWEEN_DIST), 1), 'km') AS TOTAL_DISTANCE,
       CONCAT(ROUND(AVG(D_BETWEEN_DIST), 2), 'km') AS AVERAGE_DISTANCE
FROM SUBWAY_DISTANCE
GROUP BY ROUTE
ORDER BY SUM(D_BETWEEN_DIST) DESC;

-- [Level 2 / 22] 물고기 종류 별 잡은 수 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/293257
-- 문제: FISH_INFO와 FISH_NAME_INFO를 FISH_TYPE으로 연결하여 잡힌 종류별 개체 수와 이름을 구한다.
-- 출력: FISH_COUNT, FISH_NAME. 길이가 NULL인 개체도 포함. 정렬: FISH_COUNT 내림차순.
SELECT COUNT(*) AS FISH_COUNT, N.FISH_NAME
FROM FISH_INFO AS F
JOIN FISH_NAME_INFO AS N ON F.FISH_TYPE = N.FISH_TYPE
GROUP BY F.FISH_TYPE, N.FISH_NAME
ORDER BY FISH_COUNT DESC;

-- [Level 2 / 23] 월별 잡은 물고기 수 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/293260
-- 문제: FISH_INFO의 TIME을 월별로 묶어 포획 수를 센다. 포획 기록이 없는 월은 제외한다.
-- 출력: FISH_COUNT, MONTH(1~12의 숫자). 정렬: MONTH 오름차순.
SELECT COUNT(*) AS FISH_COUNT, MONTH(TIME) AS MONTH
FROM FISH_INFO
GROUP BY MONTH(TIME)
ORDER BY MONTH;

-- ========================================================
-- 추가 풀이: IS NULL
-- ========================================================

-- [Level 2 / 24] NULL 처리하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/59410
-- 문제: ANIMAL_INS의 생물 종, 이름, 성별 및 중성화 정보를 조회하되 NULL 이름은 No name으로 바꾼다.
-- 출력: ANIMAL_TYPE, NAME, SEX_UPON_INTAKE. 정렬: ANIMAL_ID 오름차순.
SELECT ANIMAL_TYPE, IFNULL(NAME, 'No name') AS NAME, SEX_UPON_INTAKE
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;

-- [Level 2 / 25] ROOT 아이템 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/273710
-- 문제: ITEM_TREE에서 PARENT_ITEM_ID가 NULL인 ROOT 아이템의 정보를 ITEM_INFO에서 조회한다.
-- 출력: ITEM_ID, ITEM_NAME. 정렬: ITEM_ID 오름차순.
SELECT I.ITEM_ID, I.ITEM_NAME
FROM ITEM_INFO AS I
JOIN ITEM_TREE AS T ON I.ITEM_ID = T.ITEM_ID
WHERE T.PARENT_ITEM_ID IS NULL
ORDER BY I.ITEM_ID;

-- ========================================================
-- 추가 풀이: JOIN
-- ========================================================

-- [Level 2 / 26] 조건에 맞는 도서와 저자 리스트 출력하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/144854
-- 문제: BOOK과 AUTHOR를 AUTHOR_ID로 연결하여 경제 카테고리의 도서와 저자를 조회한다.
-- 출력: BOOK_ID, AUTHOR_NAME, PUBLISHED_DATE(YYYY-MM-DD). 정렬: 출판일 오름차순.
SELECT B.BOOK_ID,
       A.AUTHOR_NAME,
       DATE_FORMAT(B.PUBLISHED_DATE, '%Y-%m-%d') AS PUBLISHED_DATE
FROM BOOK AS B
JOIN AUTHOR AS A ON B.AUTHOR_ID = A.AUTHOR_ID
WHERE B.CATEGORY = '경제'
ORDER BY B.PUBLISHED_DATE;

-- [Level 2 / 27] 상품 별 오프라인 매출 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/131533
-- 문제: PRODUCT와 OFFLINE_SALE을 PRODUCT_ID로 연결해 상품코드별 판매가 * 판매량의 합계를 구한다.
-- 출력: PRODUCT_CODE, SALES. 정렬: SALES 내림차순, PRODUCT_CODE 오름차순.
SELECT P.PRODUCT_CODE, SUM(P.PRICE * S.SALES_AMOUNT) AS SALES
FROM PRODUCT AS P
JOIN OFFLINE_SALE AS S ON P.PRODUCT_ID = S.PRODUCT_ID
GROUP BY P.PRODUCT_CODE
ORDER BY SALES DESC, P.PRODUCT_CODE;

-- ========================================================
-- 추가 풀이: String, Date
-- ========================================================

-- [Level 2 / 28] 조건에 부합하는 중고거래 상태 조회하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/164672
-- 문제: USED_GOODS_BOARD에서 2022-10-05에 작성된 게시물을 조회한다.
-- 출력: BOARD_ID, WRITER_ID, TITLE, PRICE, STATUS.
-- 상태 변환: SALE -> 판매중, RESERVED -> 예약중, DONE -> 거래완료. 정렬: BOARD_ID 내림차순.
SELECT BOARD_ID, WRITER_ID, TITLE, PRICE,
       CASE STATUS
           WHEN 'SALE' THEN '판매중'
           WHEN 'RESERVED' THEN '예약중'
           WHEN 'DONE' THEN '거래완료'
       END AS STATUS
FROM USED_GOODS_BOARD
WHERE CREATED_DATE >= '2022-10-05'
  AND CREATED_DATE < '2022-10-06'
ORDER BY BOARD_ID DESC;

-- [Level 2 / 29] 자동차 평균 대여 기간 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/157342
-- 문제: CAR_RENTAL_COMPANY_RENTAL_HISTORY에서 자동차별 평균 대여 기간이 7일 이상인 차를 찾는다.
-- 대여 일수는 시작일과 종료일 모두 포함한다. 7일 이상인지는 반올림하기 전 평균으로 판정한다.
-- 출력: CAR_ID, AVERAGE_DURATION(소수 1자리). 정렬: AVERAGE_DURATION 내림차순, CAR_ID 내림차순.
SELECT CAR_ID,
       ROUND(AVG(DATEDIFF(END_DATE, START_DATE) + 1), 1) AS AVERAGE_DURATION
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID
HAVING AVG(DATEDIFF(END_DATE, START_DATE) + 1) >= 7
ORDER BY AVERAGE_DURATION DESC, CAR_ID DESC;

-- [Level 2 / 30] 루시와 엘라 찾기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/59046
-- 문제: ANIMAL_INS에서 이름이 Lucy, Ella, Pickle, Rogan, Sabrina, Mitty 중 하나인 동물을 찾는다.
-- 출력: ANIMAL_ID, NAME, SEX_UPON_INTAKE. 정렬: ANIMAL_ID 오름차순.
SELECT ANIMAL_ID, NAME, SEX_UPON_INTAKE
FROM ANIMAL_INS
WHERE NAME IN ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
ORDER BY ANIMAL_ID;

-- [Level 2 / 31] 이름에 el이 들어가는 동물 찾기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/59047
-- 문제: ANIMAL_INS에서 이름에 el이 포함된 개(Dog)를 대소문자 구분 없이 찾는다.
-- 출력: ANIMAL_ID, NAME. 정렬: NAME 오름차순, 이름이 같으면 ANIMAL_ID 오름차순.
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE ANIMAL_TYPE = 'Dog'
  AND LOWER(NAME) LIKE '%el%'
ORDER BY NAME, ANIMAL_ID;

-- [Level 2 / 32] 중성화 여부 파악하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/59409
-- 문제: ANIMAL_INS에서 SEX_UPON_INTAKE에 Neutered 또는 Spayed가 있으면 O, 그 외에는 X로 표시한다.
-- 출력: ANIMAL_ID, NAME, 중성화. 정렬: ANIMAL_ID 오름차순.
SELECT ANIMAL_ID, NAME,
       CASE
           WHEN SEX_UPON_INTAKE LIKE '%Neutered%'
             OR SEX_UPON_INTAKE LIKE '%Spayed%' THEN 'O'
           ELSE 'X'
       END AS 중성화
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;

-- [Level 2 / 33] 카테고리 별 상품 개수 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/131529
-- 문제: PRODUCT의 PRODUCT_CODE 앞 두 글자를 카테고리로 사용하여 상품 수를 센다.
-- 출력: CATEGORY, PRODUCTS. 정렬: CATEGORY 오름차순.
SELECT LEFT(PRODUCT_CODE, 2) AS CATEGORY, COUNT(*) AS PRODUCTS
FROM PRODUCT
GROUP BY LEFT(PRODUCT_CODE, 2)
ORDER BY CATEGORY;

-- [Level 2 / 34] DATETIME에서 DATE로 형 변환
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/59414
-- 문제: ANIMAL_INS에서 동물의 아이디, 이름, 보호 시작 날짜를 조회하며 시각은 제외한다.
-- 출력: ANIMAL_ID, NAME, 날짜(YYYY-MM-DD). 정렬: ANIMAL_ID 오름차순.
SELECT ANIMAL_ID, NAME, DATE_FORMAT(DATETIME, '%Y-%m-%d') AS 날짜
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;

-- [Level 2 / 35] 연도 별 평균 미세먼지 농도 조회하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/284530
-- 문제: AIR_POLLUTION에서 LOCATION2가 수원인 자료의 연도별 PM_VAL1, PM_VAL2 평균을 구한다.
-- 출력: YEAR, PM10, PM2.5. 두 평균은 소수 2자리로 반올림. 정렬: YEAR 오름차순.
SELECT YEAR(YM) AS YEAR,
       ROUND(AVG(PM_VAL1), 2) AS PM10,
       ROUND(AVG(PM_VAL2), 2) AS `PM2.5`
FROM AIR_POLLUTION
WHERE LOCATION2 = '수원'
GROUP BY YEAR(YM)
ORDER BY YEAR;

-- [Level 2 / 36] 분기별 분화된 대장균의 개체 수 구하기
-- 원문: https://school.programmers.co.kr/learn/courses/30/lessons/299308
-- 문제: ECOLI_DATA의 DIFFERENTIATION_DATE를 연도 구분 없이 분기로 묶어 개체 수를 센다.
-- 출력: QUARTER(1Q~4Q), ECOLI_COUNT. 정렬: 분기 오름차순.
SELECT CONCAT(QUARTER(DIFFERENTIATION_DATE), 'Q') AS QUARTER,
       COUNT(*) AS ECOLI_COUNT
FROM ECOLI_DATA
GROUP BY QUARTER(DIFFERENTIATION_DATE)
ORDER BY QUARTER;
