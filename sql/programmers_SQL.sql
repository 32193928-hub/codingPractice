-- 이름이 없는 동물의 아이디
SELECT ANIMAL_ID from ANIMAL_INS where name IS null;
-- 특정 형질을 가지는 대장균 찾기
select count(*) from ECOLI_DATA where ((GENOTYPE MOD 8 DIV 4>=1 or GENOTYPE MOD 8 MOD 4 MOD 2>=1) and (GENOTYPE MOD 8 MOD 4 DIV 2 = 0));
-- 부모의 형질을 모두 가지는 대장균 찾기
select e1.id, e1.genotype, e2.genotype as parent_genotype from ecoli_data e1 join ecoli_data e2 on e1.parent_id=e2.id where (e1.genotype&e2.genotype)=e2.genotype order by id;
-- 조건에 맞는 개발자 찾기
select distinct d.id, d.email,  d.first_name,d.last_name from developers d where (d.skill_code & (select code from skillcodes where name='C#') OR (d.skill_code&(select code from skillcodes where name='python'))) order by d.id;
-- 연도별 대장균 크기의 편차 구하기
select distinct year(e1.differentiation_date) YEAR,(e2.size_of_colony-e1.size_of_colony) YEAR_DEV, e1.id from ecoli_data e1 join(select year(differentiation_date) as YEAR, MAX(size_of_colony) as size_of_colony from ecoli_data group by 1) e2 on year(e1.differentiation_date) = e2.YEAR order by 1, 2
-- 업그레이드 된 아이템 구하기
select ii.item_id, ii.item_name, ii.rarity from item_info ii join item_tree it on ii.item_id=it.item_id where it.parent_item_id in (select item_id from item_info where rarity='RARE') order by 1 desc
