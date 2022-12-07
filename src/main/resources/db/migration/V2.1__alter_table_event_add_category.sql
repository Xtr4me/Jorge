alter table employer add column movel_id int not null;
alter table employer
    add constraint fk_employer_movel
    foreign key (movel_id) references movel (id);