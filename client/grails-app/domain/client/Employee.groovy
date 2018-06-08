package client

import static grails.gorm.rx.rest.mapping.MappingBuilder.*
import grails.gorm.rx.rest.*
import groovy.transform.ToString

@ToString
class Employee implements RxRestEntity<Employee> {

    String name

    static mapping = endpoint {
        uri "/employees{/id}"
    }
}
