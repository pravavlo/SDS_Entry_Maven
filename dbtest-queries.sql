select d.insured_name  as insuredName, d.insured_address  as insuredAddress , d.claimed_charge  as claimedCharge 
from document d 
left join batch b 
on b.id = d.batch_id where 
d.status= 'TO_REPRICE' and (b.customer_id= 2 or b.customer_id= 1) order by b.customer_id desc;

select sum(d.claimed_charge) as total
from document d;

SELECT SUM(document.claimed_charge) as total_claimed_charge
FROM document
JOIN batch ON document.batch_id = batch.id;