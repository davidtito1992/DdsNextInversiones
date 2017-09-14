
    create table Cuenta (
        cuentaId bigint generated by default as identity (start with 1),
        nombre varchar(255),
        valor numeric(19,2),
        periodo_id bigint,
        primary key (cuentaId)
    )

    create table Empresa (
        empresaId bigint generated by default as identity (start with 1),
        nombre varchar(255),
        primary key (empresaId)
    )

    create table Periodo (
        periodoId bigint generated by default as identity (start with 1),
        anio varbinary(255),
        semestre integer,
        empresa_id bigint,
        primary key (periodoId)
    )

    alter table Cuenta 
        add constraint FK_pc2vpwjlg7gwqmbwkj7su8oeb 
        foreign key (periodo_id) 
        references Periodo

    alter table Periodo 
        add constraint FK_tgtkh0o82xsi066x8cerxncab 
        foreign key (empresa_id) 
        references Empresa
