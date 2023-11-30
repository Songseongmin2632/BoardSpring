create table comments (
    "id" number generated as identity primary key,
    "content" long not null,
    "created_at" timestamp default current_timestamp,
    "is_withdrew" number(1) default 0,
    "user_id" number not null,
    "board_id" number not null,
    "comment_id" number,
    constraint fk_users_to_comments foreign key ("user_id") references users("id"),
    constraint fk_boards_to_comments foreign key ("board_id") references boards("id"),
    constraint fk_comments_to_comments foreign key ("comment_id") references comments("id")
);

insert into comments ("content", "user_id", "board_id", "comment_id") values ('test', 1, 21, null);

select * from comments where "board_id" = 21 and "comment_id" is null order by "id"
desc offset 0 rows fetch first 5 rows only;

select * from comments where "board_id" = 21 and "comment_id" = 1 order by "id";