create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, username varchar(255), auth_id varchar(255), created timestamp not null, updated timestamp not null, sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, username varchar(255), auth_id varchar(255), created timestamp not null, updated timestamp not null, sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), created timestamp not null, updated timestamp not null, username varchar(255), sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), created timestamp not null, updated timestamp not null, username varchar(255), sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), created timestamp not null, updated timestamp not null, username varchar(255), sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), created timestamp not null, updated timestamp not null, username varchar(255), sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), created timestamp not null, updated timestamp not null, username varchar(255), sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), created timestamp not null, updated timestamp not null, username varchar(255), sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), created timestamp not null, updated timestamp not null, username varchar(255), sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), created timestamp not null, updated timestamp not null, username varchar(255), sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), created timestamp not null, updated timestamp not null, username varchar(255), sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), created timestamp not null, updated timestamp not null, username varchar(255), sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), created timestamp not null, updated timestamp not null, username varchar(255), sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), created timestamp not null, updated timestamp not null, username varchar(255), sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, updated timestamp not null, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), created timestamp not null, updated timestamp not null, username varchar(255), sketch_id CHAR(16) FOR BIT DATA, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table user_profile add constraint FK1qaqsrhdf0jareui2t8widjny foreign key (sketch_id) references sketch
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, sketch_description varchar(255), updated timestamp not null, user_profile_id CHAR(16) FOR BIT DATA, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), bio varchar(255), created timestamp not null, follow_id varchar(255) for bit data, updated timestamp not null, username varchar(255), primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table sketch add constraint FK3r3s1pbhrq7bscihqy36mo6ph foreign key (user_profile_id) references user_profile
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, sketch_description varchar(255), updated timestamp not null, user_profile_id CHAR(16) FOR BIT DATA, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), bio varchar(255), created timestamp not null, follow_id varchar(255) for bit data, updated timestamp not null, username varchar(255), primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table sketch add constraint FK3r3s1pbhrq7bscihqy36mo6ph foreign key (user_profile_id) references user_profile
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, sketch_description varchar(255), updated timestamp not null, user_profile_id CHAR(16) FOR BIT DATA, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), bio varchar(255), created timestamp not null, follow_id varchar(255) for bit data, updated timestamp not null, username varchar(255), primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table sketch add constraint FK3r3s1pbhrq7bscihqy36mo6ph foreign key (user_profile_id) references user_profile
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, sketch_description varchar(255), updated timestamp not null, user_profile_id CHAR(16) FOR BIT DATA, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), bio varchar(255), created timestamp not null, follow_id varchar(255) for bit data, updated timestamp not null, username varchar(255), primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table sketch add constraint FK3r3s1pbhrq7bscihqy36mo6ph foreign key (user_profile_id) references user_profile
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, sketch_description varchar(255), updated timestamp not null, user_profile CHAR(16) FOR BIT DATA, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255), bio varchar(255), created timestamp not null, follow_id varchar(255) for bit data, updated timestamp not null, username varchar(255), primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table sketch add constraint FK6qj29jl2of0ovulsq4a5brckq foreign key (user_profile) references user_profile
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, sketch_description varchar(255), updated timestamp not null, user_profile CHAR(16) FOR BIT DATA, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255) not null, bio varchar(255), created timestamp not null, follow_id varchar(255) for bit data, updated timestamp not null, username varchar(255) not null, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table sketch add constraint FK6qj29jl2of0ovulsq4a5brckq foreign key (user_profile) references user_profile
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, sketch_description varchar(255), updated timestamp not null, user_profile CHAR(16) FOR BIT DATA, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255) not null, bio varchar(255), created timestamp not null, follow_id varchar(255) for bit data, updated timestamp not null, username varchar(255) not null, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table sketch add constraint FK6qj29jl2of0ovulsq4a5brckq foreign key (user_profile) references user_profile
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, sketch_description varchar(255), updated timestamp not null, user_profile CHAR(16) FOR BIT DATA, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255) not null, bio varchar(255), created timestamp not null, follow_id varchar(255) for bit data, updated timestamp not null, username varchar(255) not null, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table sketch add constraint FK6qj29jl2of0ovulsq4a5brckq foreign key (user_profile) references user_profile
create table like (like_id CHAR(16) FOR BIT DATA not null, created timestamp not null, sketch_id CHAR(16) FOR BIT DATA not null, user_profile_id CHAR(16) FOR BIT DATA not null, primary key (like_id))
create table sketch (sketch_id CHAR(16) FOR BIT DATA not null, created timestamp not null, name varchar(255) not null, sketch_description varchar(255), updated timestamp not null, user_profile CHAR(16) FOR BIT DATA, primary key (sketch_id))
create table user_profile (user_profile_id CHAR(16) FOR BIT DATA not null, auth_id varchar(255) not null, bio varchar(255), created timestamp not null, follow_id varchar(255) for bit data, updated timestamp not null, username varchar(255) not null, primary key (user_profile_id))
alter table sketch add constraint UK_ombsd58sggb1oscqmcyq927p9 unique (name)
alter table like add constraint FKc04dbur55q7b198g1vimvoil6 foreign key (sketch_id) references sketch
alter table like add constraint FKj6cijr407hmrl2fdcdsbfikdx foreign key (user_profile_id) references user_profile
alter table sketch add constraint FK6qj29jl2of0ovulsq4a5brckq foreign key (user_profile) references user_profile
