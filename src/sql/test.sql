select count(*) from ourforum.users;
select * from `posts` order by `idPosts` desc;

select * from `posts` where `SectionIds`=? order by `idPosts` desc;
update `comment` set `commenttext`='thanks' , `UserIds`='100003' ,`idPosts`='000000' where `commentId`='000004';
