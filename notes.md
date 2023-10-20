lazy vs eager

when you see somehow lazy not working , leads to lot of slow down
go for eager

for non collection always eager


![img.png](img.png)

as you can see below 3 db calls happens  for each lazy call
![img_1.png](img_1.png)
with select  ![img_2.png](img_2.png)

now with subselect 2 db call happened  ==> SUBQUERY

![img_3.png](img_3.png)

THIS IS N+1 problem