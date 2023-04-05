create table public.img
(
    id         varchar(255) not null
        primary key,
    url        varchar(255) not null,
    value      text,
    created_at timestamp
);

alter table public.img
    owner to luo;
INSERT INTO public.img (id, url, value, created_at) VALUES ('123123', 'src/main/resources/assets/images/92e911621.png', null, null);
INSERT INTO public.img (id, url, value, created_at) VALUES ('444222', 'src/main/resources/assets/images/227ef0887.png', null, null);
INSERT INTO public.img (id, url, value, created_at) VALUES ('424242', 'src/main/resources/assets/images/20057f34d.png', null, null);
INSERT INTO public.img (id, url, value, created_at) VALUES ('444555', 'src/main/resources/assets/images/a4e1c55a9.png', null, null);
INSERT INTO public.img (id, url, value, created_at) VALUES ('tre122', 'src/main/resources/assets/images/c98f79f71.png', null, null);
INSERT INTO public.img (id, url, value, created_at) VALUES ('412rr13', 'src/main/resources/assets/images/d8edf2e40.png', null, null);
INSERT INTO public.img (id, url, value, created_at) VALUES ('d1d221e1', 'src/main/resources/assets/images/f27825b2c.png', null, null);