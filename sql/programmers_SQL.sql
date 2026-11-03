-- 부모의 형질을 모두 가지는 대장균 찾기
select e1.id,
       e1.genotype,
       e2.genotype as parent_genotype
from ECOLI_DATA as e1
join ECOLI_DATA as e2 on e1.parent_id = e2.id
where (e1.genotype & e2.genotype) = e2.genotype
order by e1.id;

-- 조건에 맞는 개발자 찾기
select distinct d.id, d.email, d.first_name, d.last_name
from DEVELOPERS as d
where (d.skill_code & (select code from SKILLCODES where name = 'C#')) <> 0
   or (d.skill_code & (select code from SKILLCODES where name = 'Python')) <> 0
order by d.id;

-- 연도별 대장균 크기의 편차 구하기
select distinct year(e1.differentiation_date) as year,
       e2.size_of_colony - e1.size_of_colony as year_dev,
       e1.id
from ECOLI_DATA as e1
join (
    select year(differentiation_date) as year,
           max(size_of_colony) as size_of_colony
    from ECOLI_DATA
    group by year(differentiation_date)
) as e2 on year(e1.differentiation_date) = e2.year
order by year, year_dev;

-- 업그레이드 된 아이템 구하기
select ii.item_id, ii.item_name, ii.rarity
from ITEM_INFO as ii
join ITEM_TREE as it on ii.item_id = it.item_id
where it.parent_item_id in (
    select item_id
    from ITEM_INFO
    where rarity = 'RARE'
)
order by ii.item_id desc;

-- 3월에 태어난 여성 회원 목록 출력하기
select member_id,
       member_name,
       gender,
       date_format(date_of_birth, '%Y-%m-%d') as date_of_birth
from MEMBER_PROFILE
where month(date_of_birth) = 3
  and gender = 'W'
  and tlno is not null
order by member_id;

-- 재구매가 일어난 상품과 회원 리스트 구하기
select user_id, product_id
from ONLINE_SALE
group by user_id, product_id
having count(*) >= 2
order by user_id, product_id desc;

-- 특정 물고기를 잡은 총 수 구하기
select count(*) as fish_count
from FISH_INFO as f
join FISH_NAME_INFO as n on f.fish_type = n.fish_type
where n.fish_name in ('BASS', 'SNAPPER');

-- 가격이 제일 비싼 식품의 정보 출력하기
select product_id, product_name, product_cd, category, price
from FOOD_PRODUCT
where price = (select max(price) from FOOD_PRODUCT);

-- 최솟값 구하기
select min(datetime) as 시간
from ANIMAL_INS;

-- 동물 수 구하기
select count(*) as count
from ANIMAL_INS;

-- 중복 제거하기
select count(distinct name) as count
from ANIMAL_INS;

-- 조건에 맞는 아이템들의 가격의 총합 구하기
select sum(price) as total_price
from ITEM_INFO
where rarity = 'LEGEND';

-- 성분으로 구분한 아이스크림 총 주문량
select i.ingredient_type, sum(f.total_order) as total_order
from FIRST_HALF as f
join ICECREAM_INFO as i on f.flavor = i.flavor
group by i.ingredient_type
order by total_order;

-- 진료과별 총 예약 횟수 출력하기
select mcdp_cd as `진료과 코드`, count(*) as `5월예약건수`
from APPOINTMENT
where apnt_ymd >= '2022-05-01'
  and apnt_ymd < '2022-06-01'
group by mcdp_cd
order by `5월예약건수`, mcdp_cd;

-- 자동차 종류 별 특정 옵션이 포함된 자동차 수 구하기
select car_type, count(*) as cars
from CAR_RENTAL_COMPANY_CAR
where options like '%통풍시트%'
   or options like '%열선시트%'
   or options like '%가죽시트%'
group by car_type
order by car_type;

-- 고양이와 개는 몇 마리 있을까
select animal_type, count(*) as count
from ANIMAL_INS
where animal_type in ('Cat', 'Dog')
group by animal_type
order by animal_type;

-- 동명 동물 수 찾기
select name, count(*) as count
from ANIMAL_INS
where name is not null
group by name
having count(*) >= 2
order by name;

-- 입양 시각 구하기(1)
select hour(datetime) as hour, count(*) as count
from ANIMAL_OUTS
where hour(datetime) between 9 and 19
group by hour(datetime)
order by hour;

-- 가격대 별 상품 개수 구하기
select floor(price / 10000) * 10000 as price_group, count(*) as products
from PRODUCT
group by floor(price / 10000) * 10000
order by price_group;

-- 조건에 맞는 사원 정보 조회하기
with year_score as (
    select emp_no, sum(score) as score
    from HR_GRADE
    where year = 2022
    group by emp_no
)
select g.score, e.emp_no, e.emp_name, e.position, e.email
from year_score as g
join HR_EMPLOYEES as e on g.emp_no = e.emp_no
where g.score = (select max(score) from year_score);

-- 노선별 평균 역 사이 거리 조회하기
select route,
       concat(round(sum(d_between_dist), 1), 'km') as total_distance,
       concat(round(avg(d_between_dist), 2), 'km') as average_distance
from SUBWAY_DISTANCE
group by route
order by sum(d_between_dist) desc;

-- 물고기 종류 별 잡은 수 구하기
select count(*) as fish_count, n.fish_name
from FISH_INFO as f
join FISH_NAME_INFO as n on f.fish_type = n.fish_type
group by f.fish_type, n.fish_name
order by fish_count desc;

-- 월별 잡은 물고기 수 구하기
select count(*) as fish_count, month(time) as month
from FISH_INFO
group by month(time)
order by month;

-- NULL 처리하기
select animal_type, ifnull(name, 'No name') as name, sex_upon_intake
from ANIMAL_INS
order by animal_id;

-- ROOT 아이템 구하기
select i.item_id, i.item_name
from ITEM_INFO as i
join ITEM_TREE as t on i.item_id = t.item_id
where t.parent_item_id is null
order by i.item_id;

-- 조건에 맞는 도서와 저자 리스트 출력하기
select b.book_id,
       a.author_name,
       date_format(b.published_date, '%Y-%m-%d') as published_date
from BOOK as b
join AUTHOR as a on b.author_id = a.author_id
where b.category = '경제'
order by b.published_date;

-- 상품 별 오프라인 매출 구하기
select p.product_code, sum(p.price * s.sales_amount) as sales
from PRODUCT as p
join OFFLINE_SALE as s on p.product_id = s.product_id
group by p.product_code
order by sales desc, p.product_code;

-- 조건에 부합하는 중고거래 상태 조회하기
select board_id, writer_id, title, price,
       case status
           when 'SALE' then '판매중'
           when 'RESERVED' then '예약중'
           when 'DONE' then '거래완료'
       end as status
from USED_GOODS_BOARD
where created_date >= '2022-10-05'
  and created_date < '2022-10-06'
order by board_id desc;

-- 자동차 평균 대여 기간 구하기
select car_id,
       round(avg(datediff(end_date, start_date) + 1), 1) as average_duration
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by car_id
having avg(datediff(end_date, start_date) + 1) >= 7
order by average_duration desc, car_id desc;

-- 루시와 엘라 찾기
select animal_id, name, sex_upon_intake
from ANIMAL_INS
where name in ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
order by animal_id;

-- 이름에 el이 들어가는 동물 찾기
select animal_id, name
from ANIMAL_INS
where animal_type = 'Dog'
  and lower(name) like '%el%'
order by name, animal_id;

-- 중성화 여부 파악하기
select animal_id, name,
       case
           when sex_upon_intake like '%Neutered%'
             or sex_upon_intake like '%Spayed%' then 'O'
           else 'X'
       end as 중성화
from ANIMAL_INS
order by animal_id;

-- 카테고리 별 상품 개수 구하기
select left(product_code, 2) as category, count(*) as products
from PRODUCT
group by left(product_code, 2)
order by category;

-- DATETIME에서 DATE로 형 변환
select animal_id, name, date_format(datetime, '%Y-%m-%d') as 날짜
from ANIMAL_INS
order by animal_id;

-- 연도 별 평균 미세먼지 농도 조회하기
select year(ym) as year,
       round(avg(pm_val1), 2) as pm10,
       round(avg(pm_val2), 2) as `PM2.5`
from AIR_POLLUTION
where location2 = '수원'
group by year(ym)
order by year;

-- 분기별 분화된 대장균의 개체 수 구하기
select concat(quarter(differentiation_date), 'Q') as quarter,
       count(*) as ecoli_count
from ECOLI_DATA
group by quarter(differentiation_date)
order by quarter;
