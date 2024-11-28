package nefu.laboratory.Dox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Laboratory {
    @CreatedBy
    @Id
    private String id;
    private String name;
    private String config;
    private int capacity;
    private String manager;
}
/*
* create table if not exists `laboratory`(
    id         char(19) not null primary key,
    name       varchar(20) not null ,
    config     varchar(100) not null ,
    capacity   tinyint unsigned not null,
    manager    json not null comment '{["id":,"name":,"phone":]}'
);
* */