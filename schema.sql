
    create table Condicion (
        tipoCondicion integer not null,
        condicionId bigint generated by default as identity (start with 1),
        ultimosAnios integer,
        peso numeric(19,2),
        nroAComparar numeric(19,2),
        criterio integer,
        metodologia_id bigint,
        indicador_id bigint,
        primary key (condicionId)
    )
    
    create table User (
        userId bigint generated by default as identity (start with 1),
        email varchar(255),
        password varchar(255),
        primary key (userId)
    )

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
        userId bigint,
        primary key (empresaId)
    )

    create table Metodologia (
        metodologiaId bigint generated by default as identity (start with 1),
        nombre varchar(255),
        userId bigint,
        primary key (metodologiaId)
    )

    create table Periodo (
        periodoId bigint generated by default as identity (start with 1),
        anio varbinary(255),
        semestre integer,
        empresa_id bigint,
        primary key (periodoId)
    )

    create table RegistroIndicador (
        registroIndicadorId bigint generated by default as identity (start with 1),
        formula varchar(255),
        nombre varchar(255),
        userId bigint,
        primary key (registroIndicadorId)
    )

    alter table Condicion 
        add constraint FK_ey2qw1npivcanxvn183abaj04 
        foreign key (metodologia_id) 
        references Metodologia

    alter table Condicion 
        add constraint FK_eysyy3klb44uxywscs62nsg3q 
        foreign key (indicador_id) 
        references RegistroIndicador

    alter table Cuenta 
        add constraint FK_pc2vpwjlg7gwqmbwkj7su8oeb 
        foreign key (periodo_id) 
        references Periodo

    alter table Periodo 
        add constraint FK_tgtkh0o82xsi066x8cerxncab 
        foreign key (empresa_id) 
        references Empresa
        
    alter table Empresa 
        add constraint FK_userID
        foreign key (userId) 
        references User

    alter table RegistroIndicador 
        add constraint FK_userID
        foreign key (userId) 
        references User
    
    alter table Metodologia 
        add constraint FK_userID
        foreign key (userId) 
        references User