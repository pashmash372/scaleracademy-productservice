select u1_0.id, u1_0.clazz_, u1_0.email, u1_0.name, u1_0.average_rating, u1_0.attendance, u1_0.psp
from (select id, email, name, null as average_rating, null as attendance, null as psp, 0 as clazz_
      from tpc_user
      union all
      select id, email, name, average_rating, null as attendance, null as psp, 1 as clazz_
      from tpc_mentor
      union all
      select id, email, name, null as average_rating, attendance, psp, 2 as clazz_
      from tpc_student
      union all
      select id, email, name, average_rating, null as attendance, null as psp, 3 as clazz_
      from tpc_ta) u1_0 Hibernate:

select u1_0.id,
       case
           when u1_1.user_id is not null then 1
           when u1_2.user_id is not null then 2
           when u1_3.user_id is not null then 3
           when u1_0.id is not null then 0 end,
       u1_0.email,
       u1_0.name,
       u1_1.average_rating,
       u1_2.attendance,
       u1_2.psp,
       u1_3.average_rating
from jt_user u1_0
         left join jt_mentor u1_1 on u1_0.id = u1_1.user_id
         left join jt_student u1_2 on u1_0.id = u1_2.user_id
         left join jt_ta u1_3 on u1_0.id = u1_3.user_id